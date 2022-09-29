import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int storageSize;

    void clear() {
        for (int i = 0; i < size(); i++) {
                storage[i] = null;
            }
        storageSize = 0;
    }

    void save(Resume r) {
        if (size() < storage.length) {
            storage[size()] = r;
            storageSize++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid == uuid) return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid == uuid) {
                storage[i] = storage[size() - 1];
                storage[size() - 1] = null;
                storageSize--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        return storageSize;
    }
}
