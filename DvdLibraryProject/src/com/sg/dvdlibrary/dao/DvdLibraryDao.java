package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;

import java.util.List;

/**
 * This interface defines the methods that must be implemented by any class that wants to play
 * the role of DAO in the application.
 */
public interface DvdLibraryDao {

    DVD addDvd (String title, DVD dvd) throws DvdLibraryPersistenceException;

    List<DVD> getAllDvds () throws DvdLibraryPersistenceException;

    DVD getDvd (String title) throws DvdLibraryPersistenceException;

    DVD removeDVD (String title) throws DvdLibraryPersistenceException;

    Boolean searchDvd(String title) throws DvdLibraryPersistenceException;

    void editDvd(String newTitle, DVD dvd) throws DvdLibraryPersistenceException;
}
