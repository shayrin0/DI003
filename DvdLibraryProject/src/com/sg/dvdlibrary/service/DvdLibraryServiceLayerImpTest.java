package com.sg.dvdlibrary.service;


import com.sg.dvdlibrary.dao.*;
import com.sg.dvdlibrary.dto.DVD;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DvdLibraryServiceLayerImpTest {

    private DvdLibraryServiceLayerImp service;

    public DvdLibraryServiceLayerImpTest() {
        DvdLibraryDao dao = new DvdLibraryDaoStupImp();
        DvdLibraryAuditDao auditDao = new DvdLibraryAuditDaoStupImp();

        service = new DvdLibraryServiceLayerImp(dao, auditDao);
    }

    @Test
    public void testCreateValidDvd() {
        // ARRANGE
        DVD dvd = new DVD("Shirin");
        dvd.setReleaseDate("2022");
        dvd.setRatingMPAA("R");
        dvd.setDirectorName("Steven");
        dvd.setStudio("WBD");
        dvd.setUserRating("Awesome!");

        // ACT
        try {
            service.createDvd(dvd);
        } catch (DvdLibraryDuplicateTitleException
                 | DvdLibraryDataValidationException
                 | DvdLibraryPersistenceException e) {
            // ASSERT
            fail("DVD wasn't valid. No exception should have been thrown.");
        }
    }

    @Test
    public void testCreateDuplicateTitleDvd() {
        // ARRANGE
        DVD dvd = new DVD("0001");
        dvd.setReleaseDate("2022");
        dvd.setRatingMPAA("R");
        dvd.setDirectorName("Steven");
        dvd.setStudio("WBD");
        dvd.setUserRating("Awesome!");

        // ACT
        try {
            service.createDvd(dvd);
            fail("Expected DupeTitle Exception was not thrown.");
        } catch (DvdLibraryDataValidationException
                 | DvdLibraryPersistenceException e) {
            // ASSERT
            fail("Incorrect exception was thrown.");
        } catch (DvdLibraryDuplicateTitleException e){
            return;
        }
    }

    @Test
    public void testCreateDvdInvalidData() throws Exception {
        // ARRANGE
        DVD dvd = new DVD("Shirin");
        dvd.setReleaseDate("");
        dvd.setRatingMPAA("R");
        dvd.setDirectorName("Steven");
        dvd.setStudio("WBD");
        dvd.setUserRating("Awesome!");

        // ACT
        try {
            service.createDvd(dvd);
            fail("Expected Validation Exception was not thrown.");
        } catch (DvdLibraryDuplicateTitleException
                 | DvdLibraryPersistenceException e) {
            // ASSERT
            fail("Incorrect exception was thrown.");
        } catch (DvdLibraryDataValidationException e){
            return;
        }
    }

    @Test
    public void testGetAllDvds() throws Exception {
        // ARRANGE
        DVD testClone = new DVD("0001");
        testClone.setReleaseDate("2022");
        testClone.setRatingMPAA("R");
        testClone.setDirectorName("Steven");
        testClone.setStudio("WBD");
        testClone.setUserRating("Awesome!");


        // ACT & ASSERT
        assertEquals( 1, service.getAllDvds().size(),
                "Should only have one DVD.");
        assertTrue( service.getAllDvds().contains(testClone),
                "The one DVD should be 0001.");
    }

    @Test
    public void testGetDvd() throws Exception {
        // ARRANGE
        DVD testClone = new DVD("0001");
        testClone.setReleaseDate("2022");
        testClone.setRatingMPAA("R");
        testClone.setDirectorName("Steven");
        testClone.setStudio("WBD");
        testClone.setUserRating("Awesome!");

        // ACT & ASSERT
        DVD shouldBe0001 = service.getDvd("0001");
        assertNotNull(shouldBe0001, "Getting 0001 should be not null.");
        assertEquals( testClone, shouldBe0001,
                "DVD stored here should be 0001.");

        DVD shouldBeNull = service.getDvd("0042");
        assertNull( shouldBeNull, "Getting 0042 should be null.");

    }

    @Test
    public void testRemoveDvd() throws Exception {
        // ARRANGE
        DVD testClone = new DVD("0001");
        testClone.setReleaseDate("2022");
        testClone.setRatingMPAA("R");
        testClone.setDirectorName("Steven");
        testClone.setStudio("WBD");
        testClone.setUserRating("Awesome!");

        // ACT & ASSERT
        DVD shouldBe0001 = service.removeDVD("0001");
        assertNotNull( shouldBe0001, "Removing 0001 should be not null.");
        assertEquals( testClone, shouldBe0001, "DVD removed should be 0001.");

        DVD shouldBeNull = service.removeDVD("0042");
        assertNull( shouldBeNull, "Removing 0042 should be null.");

    }
}