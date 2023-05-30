package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.service.DvdLibraryDataValidationException;
import com.sg.dvdlibrary.service.DvdLibraryDuplicateTitleException;
import com.sg.dvdlibrary.service.DvdLibraryServiceLayer;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImp;

import java.util.List;

/**
 * This is the orchestrator of the application. It knows what needs to be done,
 * when it needs to be done, and what component can do the job.
 * Instructions:
 *         Allow the user to add a DVD to the collection
 *         Allow the user to remove a DVD from the collection
 *         Allow the user to edit the information for an existing DVD in the collection
 *         Allow the user to list the DVDs in the collection
 *         Allow the user to display the information for a particular DVD
 *         Allow the user to search for a DVD by title
 *         Load the DVD library from a file
 *         Save the DVD library back to the file when the program completes
 *         Allow the user to add, edit, or delete many DVDs in one session
 */
public class DvdLibraryController {
    private UserIO io = new UserIOConsoleImp();
    private DvdLibraryView view;
    private DvdLibraryServiceLayer service;

    public DvdLibraryController(DvdLibraryServiceLayer service, DvdLibraryView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        editDvd();
                        break;
                    case 4:
                        listDvds();
                        break;
                    case 5:
                        viewDvd();
                        break;
                    case 6:
//                        searchDvd();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (DvdLibraryPersistenceException | DvdLibraryDataValidationException | DvdLibraryDuplicateTitleException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addDvd() throws DvdLibraryPersistenceException {
//        if (!dao.searchDvd(title)) {
            view.displayAddDvdBanner();
            boolean hasErrors = false;
            do {
                DVD currentDvd = view.getNewDvdInfo();
                try {
                    service.createDvd(currentDvd);
                    view.displayAddSuccessBanner();
                    hasErrors = false;
                }
                catch (DvdLibraryDuplicateTitleException | DvdLibraryDataValidationException e) {
                    hasErrors = true;
                    view.displayErrorMessage(e.getMessage());
                }
            } while (hasErrors);
//            DVD newDvd = view.getNewDvdInfo(title);
//            dao.addDvd(newDvd.getTitle(), newDvd);
//            view.displayAddSuccessBanner();
//        }
//        else {
//            view.displayDvdExist();
//        }
    }

    private void listDvds() throws DvdLibraryPersistenceException {
        view.displayDisplayAllBanner();
        List<DVD> studentList = service.getAllDvds();
        view.displayDvdList(studentList);
    }

    private void viewDvd() throws DvdLibraryPersistenceException {
        view.displayDisplayDvdBanner();
        String title = view.getDvdTitleChoice();
//        if (dao.searchDvd(title)) {
            DVD dvd = service.getDvd(title);
            view.displayDvd(dvd);
//        }
//        else {
//            view.displayDvdNotExist();
//        }
    }

    private void removeDvd() throws DvdLibraryPersistenceException {
        view.displayRemoveDvdBanner();
        String title = view.getDvdTitleChoice();
//        if (dao.searchDvd(title)) {
            DVD removedDvd = service.removeDVD(title);
            view.displayRemoveResult(removedDvd);
//        }
//        else {
//            view.displayDvdNotExist();
//        }
    }

    private void editDvd() throws DvdLibraryPersistenceException, DvdLibraryDataValidationException, DvdLibraryDuplicateTitleException {
        view.displayEditDvdBanner();
        String title = view.displayEditDvdChoice();
//        if (dao.searchDvd(title)) {
            DVD prevDvd = service.getDvd(title);
            DVD newDvd = view.editDvdInfo(title, prevDvd);
            service.removeDVD(title);
            service.createDvd(newDvd);
//            dao.editDvd(newDvd.getTitle(), newDvd);
            view.displayEditSuccessBanner();
//        }
//        else {
//            view.displayDvdNotExist();
//        }
    }

//    public void searchDvd () throws DvdLibraryPersistenceException {
//        String title = view.getDvdTitle();
//
//        if (dao.searchDvd(title)) {
//            view.displayDvd(dao.getDvd(title));
//        }
//        else {
//            view.displayDvdNotExist();
//        }
//    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
