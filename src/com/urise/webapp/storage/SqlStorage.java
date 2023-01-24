package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.*;
import com.urise.webapp.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SqlStorage implements Storage {
    public SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.sqlHelper = new SqlHelper(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
            }
            insertExistingContacts(r, conn);
            insertExistingSections(r, conn);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        Resume r = sqlHelper.execute("SELECT * FROM resume r WHERE uuid = ?",
                (ps) -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    return new Resume(rs.getString("uuid").strip(), rs.getString("full_name"));
                });
        sqlHelper.execute("SELECT * FROM contact WHERE resume_uuid = ?",
                (ps) -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        setContacts(rs, r);
                    }
                    return null;
                });
        sqlHelper.execute("SELECT * FROM section WHERE resume_uuid = ?",
                (ps) -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        setSections(rs, r);
                    }
                    return null;
                });
        return r;
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", (ps) -> {
            ps.setString(1, uuid);
            int numberOfRows = ps.executeUpdate();
            if (numberOfRows == 0) {
                throw new NotExistStorageException("Nothing to delete");
            }
            return null;
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(2, r.getUuid());
                ps.setString(1, r.getFullName());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?; DELETE FROM section WHERE resume_uuid = ?;")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getUuid());
                ps.execute();
            }
            insertExistingContacts(r, conn);
            insertExistingSections(r, conn);
            return null;
        });
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume", (ps) -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        Map<String, Resume> resumes = new LinkedHashMap<>();
        sqlHelper.execute("SELECT * FROM resume ORDER BY full_name ASC, uuid ASC",
                (ps) -> {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Resume r = new Resume(rs.getString("uuid").strip(), rs.getString("full_name"));
                        resumes.put(r.getUuid(), r);
                    }
                    return null;
                });
        sqlHelper.execute("SELECT * FROM contact ORDER BY resume_uuid ASC",
                (ps) -> {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        setContacts(rs, resumes.get(rs.getString("resume_uuid").strip()));
                    }
                    return null;
                });
        sqlHelper.execute("SELECT * FROM section ORDER BY resume_uuid ASC",
                (ps) -> {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        setSections(rs, resumes.get(rs.getString("resume_uuid").strip()));
                    }
                    return null;
                });
        return new ArrayList<>(resumes.values());
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) rows_count FROM resume", (ps) -> {
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new StorageException("No rows in resume table", "");
            }
            return rs.getInt("rows_count");
        });
    }

    private void insertExistingContacts(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (value, type, resume_uuid) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, e.getValue());
                ps.setString(2, e.getKey().name());
                ps.setString(3, r.getUuid());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertExistingSections(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (value, type, resume_uuid) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, AbstractSection> e : r.getSections().entrySet()) {
                switch (e.getKey()) {
                    case PERSONAL, OBJECTIVE-> {
                        TextSection textSection = (TextSection) e.getValue();
                        ps.setString(1, textSection.getContent());
                        ps.setString(2, e.getKey().name());
                        ps.setString(3, r.getUuid());
                        ps.addBatch();
                    }
                    case ACHIEVEMENTS, QUALIFICATIONS -> {
                        ListSection listSection = (ListSection) e.getValue();
                        ps.setString(1, String.join("\n", listSection.getList()));
                        ps.setString(2, e.getKey().name());
                        ps.setString(3, r.getUuid());
                        ps.addBatch();
                    }
                    case EXPERIENCE, EDUCATION -> { // not yet
                    }
                }
            }
            ps.executeBatch();
        }
    }

    private void setContacts(ResultSet rs, Resume r) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            ContactType type = ContactType.valueOf(rs.getString("type"));
            r.setContact(type, value);
        }
    }

    private void setSections(ResultSet rs, Resume r) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            SectionType type = SectionType.valueOf(rs.getString("type"));
            switch (type) {
                case PERSONAL, OBJECTIVE  -> {
                    r.setSection(type, new TextSection(value));
                }
                case ACHIEVEMENTS, QUALIFICATIONS -> {
                    r.setSection(type, new ListSection(Arrays.asList(value.split("\\r?\\n"))));
                }
                case EXPERIENCE, EDUCATION -> { // not yet
                }
            }
        }
    }
}