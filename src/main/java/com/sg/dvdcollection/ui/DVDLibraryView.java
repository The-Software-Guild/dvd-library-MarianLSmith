package com.sg.dvdcollection.ui;

import com.sg.dvdcollection.dto.DVD;

import java.util.List;

public class DVDLibraryView {

    private UserIO io;

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }


    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVDs");
        io.print("2. Add a New DVD");
        io.print("3. View a DVD");
        io.print("4. Delete a DVD");
        io.print("5. Edit Existing DVD Information");
        io.print("6. Search For a DVD");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public DVD getNewDVDInfo() {
        String title = io.readString("Please enter DVD title");

        String releaseDate = "";
        do {
            releaseDate = io.readString("Please enter release date in the format dd/MM/yyyy");
        } while (!releaseDate.matches("^\\d{2}/\\d{2}/\\d{4}$"));

        String MPAARating = io.readString("Please enter MPAA rating");
        String directorsName = io.readString("Please enter the director's name");
        String studioName = io.readString("Please enter the studio name");
        String userNotes = io.readString("Please enter any notes or additional ratings");

        DVD currentDVD = new DVD(title);

        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMPAARating(MPAARating);
        currentDVD.setDirectorsName(directorsName);
        currentDVD.setStudioName(studioName);
        currentDVD.setUserNotes(userNotes);

        return currentDVD;
    }

    public void displayCreateDVDCollectionBanner() {
        io.print(" === Create DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString("New DVD successfully created. Please hit enter to continue");

    }

    public void displayDisplayAllBanner() {
        io.print(" === Display All DVDs ===");
    }

    public void displayDVDCollectionList(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList) {
            String dvdInfo = String.format("%s : %s : %s : %s : %s : %s",
                    currentDVD.getTitle(),
                    currentDVD.getReleaseDate(),
                    currentDVD.getMPAARating(),
                    currentDVD.getDirectorsName(),
                    currentDVD.getStudioName(),
                    currentDVD.getUserNotes());

            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayDVDCollectionBanner() {
        io.print("=== Display DVD ===");
    }

    public String getTitleChoice() {
        return io.readString("Please enter the DVD title.");
    }

    public void displayDVDCollection(DVD dvd) {
        if (dvd != null) {
            io.print("Title: " + dvd.getTitle());
            io.print("Release Date: " + dvd.getReleaseDate());
            io.print("MPAA Rating: " + dvd.getMPAARating());
            io.print("Directors Name: " + dvd.getDirectorsName());
            io.print("Studio Name: " + dvd.getStudioName());
            io.print("User Notes: " + dvd.getUserNotes());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDVDCollectionBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveResult(DVD dvdRecord) {
        if (dvdRecord != null) {
            io.print("DVD successfully removed.");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayEditDVDCollectionBanner() {
        io.print("=== Edit DVD ===");
    }

    public void displayEditDVDResult(DVD dvd) {
        if (dvd != null) {
            io.print("DVD successfully replaced.");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public DVD getEditDVDInfo(DVD currentDVD) {
        String releaseDate = io.readString("Please enter release date in the format dd/MM/yyyy");
        String MPAARating = io.readString("Please enter MPAA rating");
        String directorsName = io.readString("Please enter the director's name");
        String studioName = io.readString("Please enter the studio name");
        String userNotes = io.readString("Please enter any notes or additional ratings");

        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMPAARating(MPAARating);
        currentDVD.setDirectorsName(directorsName);
        currentDVD.setStudioName(studioName);
        currentDVD.setUserNotes(userNotes);

        return currentDVD;
    }

    public void displaySearchDVDCollectionBanner() {
        io.print("=== SEARCH FOR DVD===");
    }

    public void displaySearchDVDResult(boolean searchDVDCollection) {
        if (searchDVDCollection) {
            io.print("DVD successfully found.");
        } else {
            io.print("No such DVD found.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

}