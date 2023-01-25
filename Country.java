package HolidayDestination;


import java.util.ArrayList;

public class Country {
    // An ArrayList to store/list the Destinations
    ArrayList<Destination> destinations = new ArrayList<> ();
    //Defining Instance Variables(attributes)
    private String countryName;
    private String language;
    private boolean availableToTourist;

    //Constructors to retrieve class attributes
    public Country(String countryName, String language, boolean availableToTourist) {
        this.countryName = countryName;
        this.language = language;
        this.availableToTourist = availableToTourist;
    }

    //Getter and Setter methods(accessors and mutators)
    public String getCountryName() {

        return countryName;
    }

    public void setCountryName(String countryName) {

        this.countryName = countryName;
    }

    public String getLanguage() {

        return language;
    }

    public void setLanguage(String language) {

        this.language = language;
    }

    public boolean isAvailableToTourist() {

        return availableToTourist;
    }

    public void setAvailableToTourist(boolean availableToTourist) {

        this.availableToTourist = availableToTourist;
    }

    // method whose function is to return the Average Capacity of the destinations.
    public double averageCapacity() {
        double sum = 0;
        for (int i = 0; i < destinations.size (); i++) {
            Destination currentDestination = destinations.get (i);
            sum = sum + currentDestination.getCapacity ();
        }
        double avg = sum / destinations.size ();
        return avg;
    }

    // method whose function is to return the Destination with the highest capacity
    public Destination highestCapacity() {
        if ( destinations.size () == 0 ) {
            return null;
        }
        Destination highestCap = destinations.get (0);
        for (int i = 0; i < destinations.size (); i++) {
            Destination currentDestination = destinations.get (i);
            if ( highestCap.getCapacity () < currentDestination.getCapacity () ) {
                highestCap = currentDestination;
            }
        }
        return highestCap;

    }
    /* method to compare the largest capacity with a given capacity
     A temporary storage arraylist (tempDestinations) was created to store the list of destinations
       with the highest capacity when compared with a given capacity */

    public ArrayList<Destination> compareCapacity(long capacity) {
        ArrayList<Destination> tempDestinations = new ArrayList<> ();
        for (int i = 0; i < destinations.size (); i++) {
            Destination currentDestination = destinations.get (i);
            if ( currentDestination.getCapacity () > capacity ) {
                tempDestinations.add (currentDestination);
            }

        }
        return tempDestinations;
    }

    // This method allows the user to add a new destination to the list of destinations
    public void addDestination(Destination destination) {

        destinations.add (destination);
    }

    // To String() method to represent Country object as a string
    @Override
    public String toString() {
        return "Country{" +
                "countryName='" + countryName + '\'' +
                ", language='" + language + '\'' +
                ", availableToTourist=" + availableToTourist +
                ", destinations=" + destinations +
                '}';
    }
}