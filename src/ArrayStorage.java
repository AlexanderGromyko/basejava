import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        this.storage = new Resume[10000];
    }

    void save(Resume r) {
        for (int i = 0; i < 10000; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < 10000; i++) {
            if (storage[i] == null) return null;
            else if (storage[i].uuid == uuid) return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        int size = this.size();
        if (size != 0) {
            for (int i = 0; i < 10000; i++) {
                if (storage != null && storage[i].uuid == uuid) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, this.size());
    }

    int size() {
        return (int) Arrays.stream(storage)
                .filter(r -> r != null)
                .count();
    }
}
