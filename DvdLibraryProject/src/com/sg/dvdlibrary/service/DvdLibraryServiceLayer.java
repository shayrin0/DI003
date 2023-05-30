package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;

import java.util.List;

public interface DvdLibraryServiceLayer {

    void createDvd (DVD dvd) throws
            DvdLibraryDuplicateTitleException,
            DvdLibraryDataValidationException,
            DvdLibraryPersistenceException;

    List<DVD> getAllDvds () throws DvdLibraryPersistenceException;

    DVD getDvd (String title) throws DvdLibraryPersistenceException;

    DVD removeDVD (String title) throws DvdLibraryPersistenceException;

    Boolean searchDvd(String title) throws
            DvdLibraryNoSuchTitleException,
            DvdLibraryPersistenceException;

    void editDvd(String oldTitle, DVD dvd) throws
            DvdLibraryDataValidationException,
            DvdLibraryPersistenceException;
}
