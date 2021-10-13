package com.sg.dvdcollection.dao;

import com.sg.dvdcollection.dto.DVD;

import java.util.List;

public interface DVDLibraryDao {

    List<DVD> getAllDVDs() throws DVDLibraryDaoException;

    DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException;

    DVD getDVD(String title) throws DVDLibraryDaoException;

    DVD removeDVD(String title) throws DVDLibraryDaoException;

    DVD editDVD(String title, DVD dvd) throws DVDLibraryDaoException;

    boolean searchDVD(String title) throws DVDLibraryDaoException;

    public static final String DVDLIBRARY_FILE = "DVDLibrary.txt";
    public static final String DELIMITER = "::";
}
