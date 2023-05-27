package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;

import java.util.List;

/**
 * This interface defines the methods that must be implemented by any class that wants to play
 * the role of DAO in the application.
 */
public interface DvdLibraryDao {

    DVD addDvd (String title, DVD dvd) throws DvdLibraryException;

    List<DVD> getAllDvds () throws DvdLibraryException;

    DVD getDvd (String title) throws DvdLibraryException;

    DVD removeDVD (String title) throws DvdLibraryException;

    Boolean searchDvd(String title) throws DvdLibraryException;

    void editDvd(String newTitle, DVD dvd) throws DvdLibraryException;
}
