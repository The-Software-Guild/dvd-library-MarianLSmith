package com.sg.dvdcollection;

import com.sg.dvdcollection.controller.DVDLibraryController;
import com.sg.dvdcollection.dao.DVDLibraryDao;
import com.sg.dvdcollection.dao.DVDLibraryDaoException;
import com.sg.dvdcollection.dao.DVDLibraryDaoFileImpl;
import com.sg.dvdcollection.ui.DVDLibraryView;
import com.sg.dvdcollection.ui.UserIO;
import com.sg.dvdcollection.ui.UserIOConsoleImpl;

import java.text.ParseException;

public class App {

    public static void main(String[] args) throws ParseException, DVDLibraryDaoException {
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
        controller.run();
    }
}
