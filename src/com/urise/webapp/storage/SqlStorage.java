package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private void setMainFields(Resume r, PreparedStatement ps) throws SQLException {
        ps.setString(1, r.getUuid());
        ps.setString(2, r.getFullName());
        ps.execute();
    }

    private void setContacts(Resume r, PreparedStatement ps) throws SQLException {
        for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
            ps.setString(1, e.getValue());
            ps.setString(2, e.getKey().name());
            ps.setString(3, r.getUuid());
            ps.addBatch();
        }
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                setMainFields(r, ps);
            }
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (value, type, resume_uuid) VALUES (?,?,?)")) {
                setContacts(r, ps);
                ps.executeBatch();
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("" +
                        " SELECT * FROM resume r " +
                        " JOIN contact c " +
                        " ON r.uuid = c.resume_uuid " +
                        " WHERE r.uuid = ?",
                (ps) -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));
                    do {
                        String value = rs.getString("value");
                        ContactType type = ContactType.valueOf(rs.getString("type"));
                        r.setContact(type, value);
                    } while (rs.next());
                    return r;
                });
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
                setMainFields(r, ps);
            }
            try (PreparedStatement ps = conn.prepareStatement("UPDATE contact SET value = ? WHERE type = ? AND resume_uuid = ?")) {
                setContacts(r, ps);
                int[] elementsUpdated = ps.executeBatch();
                for (int elementsCount : elementsUpdated) {
                    if (elementsCount == 0) {
                        throw new NotExistStorageException(r.getUuid());
                    }
                }
            }
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
        return sqlHelper.execute("SELECT * FROM resume ORDER BY full_name ASC", (ps) -> {
            ResultSet rs = ps.executeQuery();
            ArrayList<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return resumes;
        });
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
}