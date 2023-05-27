package com.sg.dvdlibrary;

/**
 * Title: DVD Library Project
 * Programmer: Shirin Panahi Moghadam
 * Date: 2023-05-25
 */

import com.sg.dvdlibrary.controller.DvdLibraryController;
import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoFileImp;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImp;

public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImp();
        DvdLibraryView myView = new DvdLibraryView(myIo);
        DvdLibraryDao myDao = new DvdLibraryDaoFileImp();
        DvdLibraryController controller = new DvdLibraryController(myDao, myView);
        controller.run();
    }
}
