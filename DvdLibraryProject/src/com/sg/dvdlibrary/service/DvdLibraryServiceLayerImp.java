package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;

import java.util.List;

public class DvdLibraryServiceLayerImp implements DvdLibraryServiceLayer {

    DvdLibraryDao dao;

    public DvdLibraryServiceLayerImp(DvdLibraryDao dao) {
        this.dao = dao;
    }

    @Override
    public void createDvd(String title, DVD dvd) throws
            DvdLibraryDuplicateTitleException,
            DvdLibraryDataValidationException,
            DvdLibraryPersistenceException {
        // First check to see if there is already a DVD
        // associated with the given title
        // If so, we're all done here -
        // throw a DvdLibraryDuplicateTitleException
        if (dao.getDvd(dvd.getTitle()) != null) {
            throw new DvdLibraryDuplicateTitleException(
                    "ERROR: Could not add DVD.  DVD title "
                            + dvd.getTitle()
                            + " already exists");
        }

        // Now validate all the fields on the given DVD object.
        // This method will throw an
        // exception if any of the validation rules are violated.
        validateDvd(dvd);

        // We passed all our business rules checks so go ahead
        // and persist the DVD object
        dao.addDvd(dvd.getTitle(), dvd);
    }

    @Override
    public List<DVD> getAllDvds() throws DvdLibraryPersistenceException {
        return null;
    }

    @Override
    public DVD getDvd(String title) throws DvdLibraryPersistenceException {
        return null;
    }

    @Override
    public DVD removeDVD(String title) throws DvdLibraryPersistenceException {
        return null;
    }

    private void validateDvd (DVD dvd) throws DvdLibraryDataValidationException {
        if (dvd.getTitle() == null
            || dvd.getTitle().trim().length() == 0
            || dvd.getReleaseDate() == null
            || dvd.getReleaseDate().trim().length() == 0
            || dvd.getRatingMPAA() == null
            || dvd.getRatingMPAA().trim().length() == 0
            || dvd.getDirectorName() == null
            || dvd.getDirectorName().trim().length() == 0
            || dvd.getStudio() == null
            || dvd.getStudio().trim().length() == 0
            || dvd.getUserRating() == null
            || dvd.getUserRating().trim().length() == 0) {

            throw new DvdLibraryDataValidationException(
                    "ERROR: All fields [Title, Release Date, MPAA Rating, Director's Name," +
                            "Studio, User Rating] are required.");
        }
    }
}
