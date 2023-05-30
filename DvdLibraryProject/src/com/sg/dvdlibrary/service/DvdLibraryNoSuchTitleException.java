package com.sg.dvdlibrary.service;

public class DvdLibraryNoSuchTitleException extends Throwable {
    public DvdLibraryNoSuchTitleException(String message) {
        super(message);
    }

    public DvdLibraryNoSuchTitleException(String message, Throwable cause) {
        super(message, cause);
    }
}
