package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;

import java.util.List;

/**
 * This class handles all the UI logic
 */

public class DvdLibraryView {

    private UserIO io;

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add a New DVD to the Collection");
        io.print("2. Remove a DVD from the Collection");
        io.print("3. Edit information of a DVD in the Collection");
        io.print("4. List the DVDs in the Collection");
        io.print("5. Display information for a particular DVD");
        io.print("6. Search for a DVD by title");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public DVD getNewDvdInfo(String title) {
        String releaseDate = io.readString("Please enter the release date of the movie");
        String ratingMPAA = io.readString("Please enter the MPAA rating of the movie");
        String directorName = io.readString("Please enter the movie director's name");
        String studio = io.readString("Please enter the studio");
        String userRating = io.readString("Please enter the user rating about this movie");
        DVD currentDvd = new DVD(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setRatingMPAA(ratingMPAA);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }

    public String getDvdTitle () {
        return io.readString("Please enter DVD title");
    }

    public String displayEditDvdChoice() {
        return io.readString("Please enter DVD title you want to edit");
    }

    public DVD editDvdInfo (String title, DVD prevDvd) {
        String releaseDate = prevDvd.getReleaseDate();
        String ratingMPAA = prevDvd.getRatingMPAA();
        String directorName = prevDvd.getDirectorName();
        String studio = prevDvd.getStudio();
        String userRating = prevDvd.getUserRating();

        if (askUser("the title"))
            title = io.readString("Please enter the title of the movie");
        if (askUser("the release date"))
            releaseDate = io.readString("Please enter the release date of the movie");
        if (askUser("the MPAA rating"))
            ratingMPAA = io.readString("Please enter the MPAA rating of the movie");
        if (askUser("the director's name"))
            directorName = io.readString("Please enter the movie director's name");
        if (askUser("the studio"))
            studio = io.readString("Please enter the studio");
        if (askUser("the user's rating"))
            userRating = io.readString("Please enter the user rating about this movie");

        DVD currentDvd = new DVD(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setRatingMPAA(ratingMPAA);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }

    public Boolean askUser (String message) {
        String userChoice = io.readString("Do you want to change the " + message + "?");
        return userChoice.equals("Yes") || userChoice.equals("yes");
    }

    public void displayAddDvdBanner() {
        io.print("=== Add DVD ===");
    }

    public void displayAddSuccessBanner() {
        io.readString("DVD successfully added.  Please hit enter to continue");
    }

    public void displayEditSuccessBanner() {
        io.readString("DVD information successfully edited.  Please hit enter to continue");
    }

    public void displayDvdList (List<DVD> dvdList) {
        for (DVD currentDvd : dvdList) {
            String dvdInfo = String.format("Movie Title: %s \nRelease Date: %s\nMPAA Rating: %s" +
                                           "\nDirector's Name: %s \nStudio: %s \nUser Rating: %s \n" +
                                            "================================",
                                            currentDvd.getTitle(),
                                            currentDvd.getReleaseDate(),
                                            currentDvd.getRatingMPAA(),
                                            currentDvd.getDirectorName(),
                                            currentDvd.getStudio(),
                                            currentDvd.getUserRating());

            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public String getDvdTitleChoice() {
        return io.readString("Please enter the DVD title.");
    }

    public void displayDvd(DVD dvd) {
        if (dvd != null) {
            String dvdInfo = String.format("Movie Title: %s \nRelease Date: %s\nMPAA Rating: %s" +
                            "\nDirector's Name: %s \nStudio: %s \nUser Rating: %s \n" +
                            "================================",
                    dvd.getTitle(),
                    dvd.getReleaseDate(),
                    dvd.getRatingMPAA(),
                    dvd.getDirectorName(),
                    dvd.getStudio(),
                    dvd.getUserRating());

            io.print(dvdInfo);
        } else {
            displayDvdNotExist();
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayDisplayDvdBanner () {
        io.print("=== Display DVD ===");
    }

    public void displayRemoveDvdBanner () {
        io.print("=== Remove DVD ===");
    }

    public void displayEditDvdBanner () {
        io.print("=== Edit DVD ===");
    }

    public void displayRemoveResult(DVD dvdRecord) {
        if(dvdRecord != null){
            io.print("DVD successfully removed.");
            io.print("================================");
        }else{
            displayDvdNotExist();
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
        io.print("================================");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
        io.print("================================");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
        io.print("================================");
    }

    public void displayDvdNotExist () {
        io.readString("No such DVD! Please hit enter to continue.");
    }

    public void displayDvdExist () {
        io.readString("DVD already exists in the collection! Please hit enter to continue.");
    }
}
