package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;

import java.util.ArrayList;
import java.util.List;

public class DvdLibraryDaoStupImp implements DvdLibraryDao {

    //    public Student onlyStudent;
    public DVD onlyDvd;

    public DvdLibraryDaoStupImp() {
        onlyDvd = new DVD("0001");
        onlyDvd.setReleaseDate("2022");
        onlyDvd.setRatingMPAA("R");
        onlyDvd.setDirectorName("Steven");
        onlyDvd.setStudio("WBD");
        onlyDvd.setUserRating("Awesome!");
    }

    public DvdLibraryDaoStupImp(DVD testDvd) {
        this.onlyDvd = testDvd;
    }

    @Override
    public DVD addDvd(String title, DVD dvd) throws DvdLibraryPersistenceException {
        if (title.equals(onlyDvd.getTitle())) {
            return onlyDvd;
        }
        else {
            return null;
        }
    }

    @Override
    public List<DVD> getAllDvds() throws DvdLibraryPersistenceException {
        List<DVD> dvdList = new ArrayList<>();
        dvdList.add(onlyDvd);
        return dvdList;
    }

    @Override
    public DVD getDvd(String title) throws DvdLibraryPersistenceException {
        if(title.equals(onlyDvd.getTitle())) {
            return onlyDvd;
        }
        else {
            return null;
        }
    }

    @Override
    public DVD removeDVD(String title) throws DvdLibraryPersistenceException {
        if(title.equals(onlyDvd.getTitle())) {
            return onlyDvd;
        }
        else {
            return null;
        }
    }

    @Override
    public Boolean searchDvd(String title) throws DvdLibraryPersistenceException {
        return title.equals(onlyDvd.getTitle());
    }

    @Override
    public void editDvd(String newTitle, DVD dvd) throws DvdLibraryPersistenceException {

    }
}
