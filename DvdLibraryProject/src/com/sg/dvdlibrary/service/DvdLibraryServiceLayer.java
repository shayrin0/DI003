package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;

import java.util.List;

public interface DvdLibraryServiceLayer {

    void createDvd (String title, DVD dvd) throws
            DvdLibraryDuplicateTitleException,
            DvdLibraryDataValidationException,
            DvdLibraryPersistenceException;

    List<DVD> getAllDvds () throws DvdLibraryPersistenceException;

    DVD getDvd (String title) throws DvdLibraryPersistenceException;

    DVD removeDVD (String title) throws DvdLibraryPersistenceException;
}
