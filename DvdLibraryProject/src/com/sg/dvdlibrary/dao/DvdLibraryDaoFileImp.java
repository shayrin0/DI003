package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;

import java.io.*;
import java.util.*;

/**
 * This is the text file-specific implementation of the DVD Library interface.
 */
public class DvdLibraryDaoFileImp implements DvdLibraryDao {

    private final String DVD_FILE;
    public static final String DELIMITER = "::";

    public DvdLibraryDaoFileImp() {
        DVD_FILE = "DvdLibrary.txt";
    }

    public DvdLibraryDaoFileImp(String dvdTextFile) {
        DVD_FILE = dvdTextFile;
    }

    private Map<String, DVD> dvds = new HashMap<>();
    @Override
    public DVD addDvd(String title, DVD dvd) throws DvdLibraryPersistenceException {
        loadDvd();
        DVD newDvd = dvds.put(title, dvd);
        writeDvd();
        return newDvd;
    }

    @Override
    public List<DVD> getAllDvds() throws DvdLibraryPersistenceException {
        loadDvd();
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD getDvd(String title) throws DvdLibraryPersistenceException {
        loadDvd();
        return dvds.get(title);
    }

    @Override
    public DVD removeDVD(String title) throws DvdLibraryPersistenceException {
        loadDvd();
        DVD removedDvd = dvds.remove(title);
        writeDvd();
        return removedDvd;
    }

    @Override
    public Boolean searchDvd(String title) throws DvdLibraryPersistenceException {
        loadDvd();
        return dvds.get(title) != null;
    }

    @Override
    public void editDvd(String newTitle, DVD dvd) throws DvdLibraryPersistenceException {
        loadDvd();
        DVD newDvd = dvds.put(newTitle, dvd);
        writeDvd();
        addDvd(newTitle, dvd);
    }

    private DVD unmarshallDvd(String dvdAsText){
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        // Given the pattern above, the movie title is in index 0 of the array.
        String title = dvdTokens[0];

        // Which we can then use to create a new DVD object to satisfy
        // the requirements of the DVD constructor.
        DVD dvdFromFile = new DVD(title);

        // However, there are some remaining tokens that need to be set into the
        // new DVD object. Do this manually by using the appropriate setters.

        // Index 1 - ReleaseDate
        dvdFromFile.setReleaseDate(dvdTokens[1]);

        // Index 2 - RatingMPAA
        dvdFromFile.setRatingMPAA(dvdTokens[2]);

        // Index 3 - DirectorName
        dvdFromFile.setDirectorName(dvdTokens[3]);

        // Index 4 - Studio
        dvdFromFile.setStudio(dvdTokens[4]);

        // Index 5 - userRating
        dvdFromFile.setUserRating(dvdTokens[5]);

        // We have now created a student! Return it!
        return dvdFromFile;
    }

    private void loadDvd() throws DvdLibraryPersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryPersistenceException("-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        DVD currentDvd;
        // Go through DVD_FILE line by line, decoding each line into a
        // DVD object by calling the unmarshallDvd method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a DVD
            currentDvd = unmarshallDvd(currentLine);

            // We are going to use the title as the map key for our DVD object.
            // Put currentDvd into the map using title as the key
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        // close scanner
        scanner.close();
    }

    private String marshallDvd(DVD aDvd){
        // We need to turn a DVD object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // Title::ReleaseDate::RatingMPAA::DirectorName::Studio::UserRating

        // Title
        String dvdAsText = aDvd.getTitle() + DELIMITER;

        // add the rest of the properties in the correct order:
        // ReleaseDate
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;

        // RatingMPAA
        dvdAsText += aDvd.getRatingMPAA() + DELIMITER;

        // DirectorName
        dvdAsText += aDvd.getDirectorName() + DELIMITER;

        // Studio
        dvdAsText += aDvd.getStudio() + DELIMITER;

        // UserRating
        dvdAsText += aDvd.getUserRating();

        // We have now turned a DVD to text! Return it!
        return dvdAsText;
    }

    private void writeDvd() throws DvdLibraryPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DvdLibraryPersistenceException("Could not save student data.", e);
        }

        // Write out the DVD objects to the DVD file.
        String dvdAsText;
        List<DVD> dvdList = this.getAllDvds();
        for (DVD currentDvd : dvdList) {
            // turn a DVD into a String
            dvdAsText = marshallDvd(currentDvd);
            // write the DVD object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
