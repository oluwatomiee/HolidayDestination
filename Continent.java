package HolidayDestination;



import java.util.ArrayList;


public class Continent {
    ArrayList<Country> countries;
    //Defining the Instance Variables(Attributes)
    private String continentName;

    // Getter and Setter methods
    /* Only Getter method was created for ArrayList countries, this is because,
    we want it to be impossible to change items in the Arraylist */


    public String getContinentName() {

        return continentName;
    }

    public void setContinentName(String continentName) {

        this.continentName = continentName;
    }

    public ArrayList<Country> getCountries() {

        return countries;
    }

    // Constructors to retrieve class attributes

    public Continent(String continentName) {
        this.continentName = continentName;
        countries = new ArrayList<> ();
    }

    /*This method takes in a country name as a string and returns the corresponding Country object
         from the list of countries. If no matching country is found, it returns null*/
    public Country getCountryDetails(String countryName) {
        for (Country currentCountry : countries) {
            String currentCountryName = currentCountry.getCountryName ();
            if ( currentCountryName.equalsIgnoreCase (countryName) ) {
                return currentCountry;
            }
        }
        return null;
    }

    //methods to remove and add countries into the countries ArrayList
    public void addCountry(Country newCountry) {

        countries.add (newCountry);
    }

    public void removeCountry(Country removeCountry) {
        countries.remove (removeCountry);
    }

    // method whose function is to return the Destination with the highest capacity

    public Destination getDestinationWithHighestCapacity() {
        ArrayList<Destination> highCapacityDestinations = new ArrayList<> ();

        for (int i = 0; i < countries.size (); i++) {
            Country currentCountry = countries.get (i);
            Destination highestDestinationForCurrentCountry = currentCountry.highestCapacity ();
            if ( highestDestinationForCurrentCountry != null ) {
                highCapacityDestinations.add (highestDestinationForCurrentCountry);
            }

        }
        if ( highCapacityDestinations.isEmpty () ) {
            return null;
        }
        Destination highestCap = highCapacityDestinations.get (0);
        for (int i = 0; i < highCapacityDestinations.size (); i++) {
            Destination currentDestination = highCapacityDestinations.get (i);
            if ( highestCap.getCapacity () < currentDestination.getCapacity () ) {
                highestCap = currentDestination;
            }
        }
        return highestCap;
    }
    // method whose function is to return country with the highest average Destination capacity

    public Country getCountryWithHighestAvgDesCapacity() {
        if ( countries.size () == 0 ) {
            return null;
        }
        Country countryWithHighestAvgCap = countries.get (0);
        for (int i = 0; i < countries.size (); i++) {
            Country currentCountry = countries.get (i);
            if ( countryWithHighestAvgCap.averageCapacity () < currentCountry.averageCapacity () ) {
                countryWithHighestAvgCap = currentCountry;
            }
        }
        return countryWithHighestAvgCap;
    }

    /*This method takes in a capacity as a long and returns a list of Destination objects
    from all countries whose capacity is greater than the input capacity */
    public ArrayList<Destination> getDestinationWithCapacityGreaterThan(long capacity) {
        // a new Arraylist was created to store the destinations with capacity greater than the input capacity
        ArrayList<Destination> destinations = new ArrayList<> ();
        for (Country country : countries) {
            ArrayList<Destination> greaterDestinations = country.compareCapacity (capacity);
            destinations.addAll (greaterDestinations);
        }
        return destinations;
    }

}





