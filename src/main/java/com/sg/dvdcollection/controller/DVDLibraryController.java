package com.sg.dvdcollection.controller;

import com.sg.dvdcollection.dao.DVDLibraryDao;
import com.sg.dvdcollection.dao.DVDLibraryDaoException;
import com.sg.dvdcollection.dto.DVD;
import com.sg.dvdcollection.ui.DVDLibraryView;

import java.util.List;

public class DVDLibraryController {

    private DVDLibraryView view;
    private DVDLibraryDao dao;

    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

//    private UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDVDCollection();
                        break;
                    case 2:
                        createDVDCollection();
                        break;
                    case 3:
                        viewDVDCollection();
                        break;
                    case 4:
                        removeDVDCollection();
                        break;
                    case 5:
                        editDVDCollection();
                        break;
                    case 6:
                        searchDVDCollection();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createDVDCollection() throws DVDLibraryDaoException {
        view.displayCreateDVDCollectionBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }

    private void listDVDCollection() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDCollectionList(dvdList);
    }

    private void viewDVDCollection() throws DVDLibraryDaoException {
        view.displayDisplayDVDCollectionBanner();
        String title = view.getTitleChoice();
        DVD dvd = dao.getDVD(title);
        view.displayDVDCollection(dvd);
    }

    private void removeDVDCollection() throws DVDLibraryDaoException {
        view.displayRemoveDVDCollectionBanner();
        String title = view.getTitleChoice();
        DVD removedDVD = dao.removeDVD(title);
        view.displayRemoveResult(removedDVD);
    }

    private void editDVDCollection() throws DVDLibraryDaoException {
        view.displayEditDVDCollectionBanner();
        String title = view.getTitleChoice();
        DVD dvd = dao.getDVD(title);
        DVD editedDVD = view.getEditDVDInfo(dvd);
        dao.editDVD(title, editedDVD);
        view.displayEditDVDResult(editedDVD);
    }

    private void searchDVDCollection() throws DVDLibraryDaoException {
        view.displaySearchDVDCollectionBanner();
        String title = view.getTitleChoice();
        boolean ifDVDExist = dao.searchDVD(title);
        view.displaySearchDVDResult(ifDVDExist);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }


}