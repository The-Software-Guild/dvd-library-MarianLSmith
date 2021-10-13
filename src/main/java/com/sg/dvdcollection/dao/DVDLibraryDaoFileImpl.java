package com.sg.dvdcollection.dao;

import com.sg.dvdcollection.dto.DVD;

import java.io.*;
import java.util.*;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao {


    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
        loadDVDCollection();
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        loadDVDCollection();
        DVD prevDVD = dvds.put(title, dvd);
        writeDVDCollection();
        return prevDVD;
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryDaoException {
        loadDVDCollection();
        return dvds.get(title);
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException {
        loadDVDCollection();
        DVD removedDVD = dvds.remove(title);
        writeDVDCollection();
        return removedDVD;
    }


    @Override
    public DVD editDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        loadDVDCollection();
        DVD editDVD = dvds.replace(title, dvd);
        writeDVDCollection();
        return editDVD;
    }

    @Override
    public boolean searchDVD(String title) throws DVDLibraryDaoException {
        loadDVDCollection();
        return dvds.containsKey(title);
    }

    private Map<String, DVD> dvds = new HashMap<>();

    private DVD unmarshallDVDCollection(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String title = dvdTokens[0];
        DVD dvdFromFile = new DVD(title);

        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setMPAARating(dvdTokens[2]);
        dvdFromFile.setDirectorsName(dvdTokens[3]);
        dvdFromFile.setStudioName(dvdTokens[4]);
        dvdFromFile.setUserNotes(dvdTokens[5]);

        return dvdFromFile;
    }

    private void loadDVDCollection() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVDLIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "Could not load DVD data into memory.", e);
        }
        String currentLine;

        DVD currentDVD;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVDCollection(currentLine);
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        scanner.close();
    }

    private String marshallDVDCollection(DVD aDVD) {

        String dvdAsText = aDVD.getTitle() + DELIMITER;


        dvdAsText += aDVD.getReleaseDate() + DELIMITER;

        dvdAsText += aDVD.getMPAARating() + DELIMITER;

        dvdAsText += aDVD.getDirectorsName() + DELIMITER;

        dvdAsText += aDVD.getStudioName() + DELIMITER;

        dvdAsText += aDVD.getUserNotes();

        return dvdAsText;
    }


    private void writeDVDCollection() throws DVDLibraryDaoException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVDLIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException(
                    "Could not save DVD data.", e);
        }

        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for (DVD currentDVD : dvdList) {

            dvdAsText = marshallDVDCollection(currentDVD);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }

}
