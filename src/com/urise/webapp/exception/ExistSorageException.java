package com.urise.webapp.exception;

public class ExistSorageException extends  StorageException{
    public ExistSorageException(String uuid) {
        super("Resume " + uuid + " already exist", uuid);
    }


}
