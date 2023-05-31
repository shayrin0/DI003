package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DvdLibraryAuditDaoFileImpTest {

    DvdLibraryDao testDao;

    public DvdLibraryAuditDaoFileImpTest() {
    }

    @BeforeEach
    public void setUp() throws Exception{
        String testFile = "testdvd.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new DvdLibraryDaoFileImp(testFile);
    }

    @Test
    public void testAddGetDVD() throws Exception {
        // Create our method test inputs
        String title = "0001";
        DVD dvd = new DVD(title);
        dvd.setReleaseDate("2022");
        dvd.setRatingMPAA("R");
        dvd.setDirectorName("Steven");
        dvd.setStudio("WBD");
        dvd.setUserRating("Awesome!");

        //  Add the dvd to the DAO
        testDao.addDvd(title, dvd);
        // Get the dvd from the DAO
        DVD retrievedDvd = testDao.getDvd(title);

        // Check the data is equal
        assertEquals(dvd.getTitle(),
                retrievedDvd.getTitle(),
                "Checking DVD title.");
        assertEquals(dvd.getReleaseDate(),
                retrievedDvd.getReleaseDate(),
                "Checking DVD release date.");
        assertEquals(dvd.getRatingMPAA(),
                retrievedDvd.getRatingMPAA(),
                "Checking DVD MPAA Rating.");
        assertEquals(dvd.getDirectorName(),
                retrievedDvd.getDirectorName(),
                "Checking DVD's Director's Name.");
        assertEquals(dvd.getStudio(),
                retrievedDvd.getStudio(),
                "Checking DVD Studio.");
        assertEquals(dvd.getUserRating(),
                retrievedDvd.getUserRating(),
                "Checking User's Rating.");
    }

    @Test
    public void testAddGetAllDVDs() throws Exception {
        // Create our method test inputs
        String title1 = "0001";
        DVD dvd1 = new DVD(title1);
        dvd1.setReleaseDate("2022");
        dvd1.setRatingMPAA("R");
        dvd1.setDirectorName("Steven");
        dvd1.setStudio("WBD");
        dvd1.setUserRating("Awesome!");

        String title2 = "0002";
        DVD dvd2 = new DVD(title2);
        dvd2.setReleaseDate("2023");
        dvd2.setRatingMPAA("PG-13");
        dvd2.setDirectorName("Stela");
        dvd2.setStudio("Picture");
        dvd2.setUserRating("Bad movie!");

        //  Add the dvd to the DAO
        testDao.addDvd(title1, dvd1);
        testDao.addDvd(title2, dvd2);

        // Get the dvd from the DAO
        List<DVD> allDvds = testDao.getAllDvds();

        // First check the general contents of the list
        assertNotNull(allDvds, "The list of DVDs must not null");
        assertEquals(2, allDvds.size(),"List of DVDs should have 2 DVDs.");

        // Then the specifics
        assertTrue(testDao.getAllDvds().contains(dvd1),
                "The list of DVDs should include 0001.");
        assertTrue(testDao.getAllDvds().contains(dvd2),
                "The list of DVDs should include 0002.");
    }

    @Test
    public void testRemoveDVD() throws Exception {
        // Create our method test inputs
        String title1 = "0001";
        DVD dvd1 = new DVD(title1);
        dvd1.setReleaseDate("2022");
        dvd1.setRatingMPAA("R");
        dvd1.setDirectorName("Steven");
        dvd1.setStudio("WBD");
        dvd1.setUserRating("Awesome!");

        String title2 = "0002";
        DVD dvd2 = new DVD(title2);
        dvd2.setReleaseDate("2023");
        dvd2.setRatingMPAA("PG-13");
        dvd2.setDirectorName("Stela");
        dvd2.setStudio("Picture");
        dvd2.setUserRating("Bad movie!");

        //  Add the dvd to the DAO
        testDao.addDvd(title1, dvd1);
        testDao.addDvd(title2, dvd2);

        // remove the first DVD - 0001
        DVD removedDvd = testDao.removeDVD(dvd1.getTitle());

        // Check that the correct object was removed.
        assertEquals(removedDvd, dvd1,
                "The removed DVD should be 0001.");

        // Get the dvd from the DAO
        List<DVD> allDvds = testDao.getAllDvds();

        // First check the general contents of the list
        assertNotNull(allDvds, "The list of DVDs must not null");
        assertEquals(1, allDvds.size(),"List of DVDs should have 2 DVDs.");

        // Then the specifics
        assertFalse( allDvds.contains(dvd1),
                "All DVDs should NOT include 0001.");
        assertTrue( allDvds.contains(dvd2),
                "All DVDs should NOT include 0002.");

        // remove the second DVD - 0002
        removedDvd = testDao.removeDVD(dvd2.getTitle());

        // Check that the correct object was removed.
        assertEquals(removedDvd, dvd2,
                "The removed DVD should be 0002.");

        // Get the dvd from the DAO
        allDvds = testDao.getAllDvds();

        assertTrue( allDvds.isEmpty(),
                "The retrieved list of DVDs should be empty.");

        // Try to 'get' both students by their old id - they should be null!
        DVD retrievedDvd = testDao.getDvd(dvd1.getTitle());
        assertNull(retrievedDvd, "0001 was removed, should be null.");

        retrievedDvd = testDao.getDvd(dvd2.getTitle());
        assertNull(retrievedDvd, "0002 was removed, should be null.");
    }

    @Test
    public void testAddRemoveSearch() throws Exception {
        // Create our method test inputs
        String title1 = "0001";
        DVD dvd1 = new DVD(title1);
        dvd1.setReleaseDate("2022");
        dvd1.setRatingMPAA("R");
        dvd1.setDirectorName("Steven");
        dvd1.setStudio("WBD");
        dvd1.setUserRating("Awesome!");

        //  Add the dvd to the DAO
        testDao.addDvd(title1, dvd1);

        Boolean searchedDvd = testDao.searchDvd(title1);

        assertTrue( searchedDvd,
                "The DVD exists in the collection");

        // remove the first DVD - 0001
        DVD removedDvd = testDao.removeDVD(dvd1.getTitle());

        searchedDvd = testDao.searchDvd(title1);

        assertFalse(searchedDvd,
                "The DVD doesn't exist in the collection");
    }

    @Test
    public void testAddEdit() throws Exception {
        // Create our method test inputs
        String title = "0001";
        DVD dvd = new DVD(title);
        dvd.setReleaseDate("2022");
        dvd.setRatingMPAA("R");
        dvd.setDirectorName("Steven");
        dvd.setStudio("WBD");
        dvd.setUserRating("Awesome!");

        //  Add the dvd to the DAO
        testDao.addDvd(title, dvd);

        title = "0002";
        dvd = new DVD(title);
        dvd.setReleaseDate("2020");
        dvd.setRatingMPAA("PG-13");
        dvd.setDirectorName("Alex");
        dvd.setStudio("WBD");
        dvd.setUserRating("Bad movie!");

        testDao.editDvd(title, dvd);

        DVD retrievedDvd = testDao.getDvd(title);

        // Check the data is equal
        assertEquals(dvd.getTitle(),
                retrievedDvd.getTitle(),
                "Checking DVD title.");
        assertEquals(dvd.getReleaseDate(),
                retrievedDvd.getReleaseDate(),
                "Checking DVD release date.");
        assertEquals(dvd.getRatingMPAA(),
                retrievedDvd.getRatingMPAA(),
                "Checking DVD MPAA Rating.");
        assertEquals(dvd.getDirectorName(),
                retrievedDvd.getDirectorName(),
                "Checking DVD's Director's Name.");
        assertEquals(dvd.getStudio(),
                retrievedDvd.getStudio(),
                "Checking DVD Studio.");
        assertEquals(dvd.getUserRating(),
                retrievedDvd.getUserRating(),
                "Checking User's Rating.");
    }
}