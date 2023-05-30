package com.sg.dvdlibrary;

/**
 * Title: DVD Library Project
 * Programmer: Shirin Panahi Moghadam
 * Date: 2023-05-25
 */

import com.sg.dvdlibrary.controller.DvdLibraryController;
import com.sg.dvdlibrary.dao.DvdLibraryAuditDao;
import com.sg.dvdlibrary.dao.DvdLibraryAuditDaoFileImp;
import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoFileImp;
import com.sg.dvdlibrary.service.DvdLibraryServiceLayer;
import com.sg.dvdlibrary.service.DvdLibraryServiceLayerImp;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImp;

public class App {
    public static void main(String[] args) {
        // Instantiate the UserIO implementation
        UserIO myIo = new UserIOConsoleImp();
        // Instantiate the View and wire the UserIO implementation into it
        DvdLibraryView myView = new DvdLibraryView(myIo);
        // Instantiate the DAO
        DvdLibraryDao myDao = new DvdLibraryDaoFileImp();
        // Instantiate the Audit DAO
        DvdLibraryAuditDao myAuditDao = new DvdLibraryAuditDaoFileImp();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        DvdLibraryServiceLayer myService = new DvdLibraryServiceLayerImp(myDao, myAuditDao);
        // Instantiate the Controller and wire the Service Layer into it
        DvdLibraryController controller = new DvdLibraryController(myService, myView);
        // Kick off the Controller
        controller.run();
    }
}
