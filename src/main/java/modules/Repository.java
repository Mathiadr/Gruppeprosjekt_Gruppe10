package modules;

import tools.RepositoryFileHandler;

import java.util.ArrayList;
import java.util.Objects;

public class Repository {
    private String repositoryName; // This holds the name of the repository being accessed.
    private ArrayList<Listing> listingArrayList = new ArrayList<Listing>();

    private final RepositoryFileHandler repositoryFileHandler = new RepositoryFileHandler();

    public Repository(String repositoryName) {
        this.repositoryName = repositoryName;

        listingArrayList = readFromJSON();
    }



    // Reads all objects from JSON file and returns an ArrayList of Listing objects
    public ArrayList<Listing> readFromJSON(){
        return repositoryFileHandler.readFromFile(repositoryName);
    }


    public void addNewListing(Listing listing){
        boolean exists = false;
        for (Listing i : listingArrayList){
            //TODO: IMPLEMENT
            if(Objects.equals(i.getCar().getRegistrationNumber(), listing.getCar().getRegistrationNumber())){
                exists = true;
                break;
            }
        }
        if(!exists){
            listingArrayList.add(listing);
        }

    }

    // Removes Car object from listingArrayList
    public void removeExistingListing(Car car){
        // TODO: IMPLEMENT
    }

    // It rents da car, dumbass
    public void acceptListing(Car car){
        //TODO: REMOVE & RENAME
    }

    // Returns all cars that are not currently occupied
    public ArrayList<Listing> GetAllAvailableListings(){
        // TODO: Add date as parameter argument
        ArrayList<Listing> allAvailableListings = new ArrayList<>();
        for (Listing i : listingArrayList){
            if (i.isAvailable()){
                allAvailableListings.add(i);
            }
        }
        return allAvailableListings;
    }

    public void SaveCarsToJSON(){
        repositoryFileHandler.writeArrayListToFile(getListingArrayList(), repositoryName);
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public ArrayList<Listing> getListingArrayList() {
        return listingArrayList;
    }

    public void setListingArrayList(ArrayList<Listing> listingArrayList) {
        this.listingArrayList = listingArrayList;
    }
}
