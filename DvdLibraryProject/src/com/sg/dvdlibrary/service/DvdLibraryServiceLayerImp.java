package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.*;
import com.sg.dvdlibrary.dto.DVD;

import java.util.List;

public class DvdLibraryServiceLayerImp implements DvdLibraryServiceLayer {

    DvdLibraryDao dao;

    private DvdLibraryAuditDao auditDao;

    public DvdLibraryServiceLayerImp(DvdLibraryDao dao, DvdLibraryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void createDvd(DVD dvd) throws
            DvdLibraryDuplicateTitleException,
            DvdLibraryDataValidationException,
            DvdLibraryPersistenceException {
        // First check to see if there is already a DVD
        // associated with the given title
        // If so, we're all done here -
        // throw a DvdLibraryDuplicateTitleException
        if (dao.getDvd(dvd.getTitle()) != null) {
            throw new DvdLibraryDuplicateTitleException(
                    "ERROR: Could not add DVD.  DVD title '"
                            + dvd.getTitle()
                            + "' already exists");
        }

        // Now validate all the fields on the given DVD object.
        // This method will throw an
        // exception if any of the validation rules are violated.
        validateDvd(dvd);

        // We passed all our business rules checks so go ahead
        // and persist the DVD object
        dao.addDvd(dvd.getTitle(), dvd);

        auditDao.writeAuditEntry(
                "DVD " + dvd.getTitle() + " CREATED.");
    }

    @Override
    public List<DVD> getAllDvds() throws DvdLibraryPersistenceException {
        return dao.getAllDvds();
    }

    @Override
    public DVD getDvd(String title) throws DvdLibraryPersistenceException {
        return dao.getDvd(title);
    }

    @Override
    public DVD removeDVD(String title) throws DvdLibraryPersistenceException {
        DVD removedDvd = dao.removeDVD(title);
        // Write to audit log
        auditDao.writeAuditEntry("DVD " + title + " REMOVED.");
        return removedDvd;
    }

    @Override
    public Boolean searchDvd(String title) throws
            DvdLibraryNoSuchTitleException,
            DvdLibraryPersistenceException {
        if (dao.searchDvd(title) == null) {
            editDvdCheck(title);
            return false;
        }
        return true;
    }

    @Override
    public void editDvd(String oldTitle, DVD dvd) throws
            DvdLibraryDataValidationException,
            DvdLibraryPersistenceException {

        validateDvd(dvd);
        dao.removeDVD(oldTitle);
        dao.editDvd(dvd.getTitle(), dvd);
        auditDao.writeAuditEntry(
                "DVD '" + dvd.getTitle() + "' EDITED.");
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

    private void editDvdCheck (String title) throws DvdLibraryNoSuchTitleException {
            throw new DvdLibraryNoSuchTitleException(
                    "ERROR: DVD " + title + " doesn't exist in our collection!");
    }
}
