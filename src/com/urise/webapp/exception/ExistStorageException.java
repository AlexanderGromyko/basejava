package com.urise.webapp.exception;

public class ExistStorageException extends StorageException{
    public ExistStorageException(Object searchKey) {
        super("Resume " + searchKey.toString() + " already exists", searchKey.toString());
    }


}
