import HolidayDestination.Continent;
import HolidayDestination.Country;
import HolidayDestination.Destination;

import java.util.ArrayList;
import java.util.Scanner;


public class Booking{
    /* This code defines several constants that hold strings containing messages
     and instructions that will be displayed to the user.
    It also defines an ArrayList that will hold objects of the Continent class.
    */
    public static final String WELCOME_MESSAGE = "What would you like to do?";
    public static final String WELCOME_INSTRUCTION = "Pick any number from 1 to 4:";
    public static final String SERVICE_OPTION_1 = "1. Add a new country to a Continent";
    public static final String SERVICE_OPTION_2 = "2. Add new details of a Destination";
    public static final String SERVICE_OPTION_3 = "3. View Statistics";
    public static final String SERVICE_OPTION_4 = "4. Exit Website";
    public static final String CONTINENT_QUERY = "What Continent would you like to add the Country to?";
    public static final int SERVICE_OPT_3 = 3;
    public static final int SERVICE_OPT_4 = 4;
    private static ArrayList<Continent> listOfContinents;

    //core method of the program and calls all others
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        //Adding continents to the ArrayList listOfContinents
        listOfContinents = new ArrayList<>();
        Continent africa = new Continent("Africa");
        listOfContinents.add(africa);

        Continent europe = new Continent("Europe");
        listOfContinents.add(europe);

        Continent northAmerica = new Continent("North America");
        listOfContinents.add(northAmerica);

        Continent southAmerica = new Continent("South America");
        listOfContinents.add(southAmerica);

        Continent asia = new Continent("Asia");
        listOfContinents.add(asia);

        Continent antarctica = new Continent("Antarctica");
        listOfContinents.add(antarctica);

        Continent australia = new Continent("Australia");
        listOfContinents.add(australia);

        /*This block of code is a loop that displays a menu of service options to the user
        It allows the user to select one of the options by entering a number.
        The loop continues until the user selects the option to exit the service*/
        boolean customerService = true;
        System.out.println(WELCOME_MESSAGE);
        while (customerService){//variable is a boolean that controls the loop
            System.out.println(WELCOME_INSTRUCTION);
            System.out.println(SERVICE_OPTION_1);
            System.out.println(SERVICE_OPTION_2);
            System.out.println(SERVICE_OPTION_3);
            System.out.println(SERVICE_OPTION_4);

            System.out.println("Enter option below");

            int serviceOption = in.nextInt();
            if (serviceOption == 1) {
                addCountry(in);
            } else if (serviceOption == 2) {
                addDestination(in);
            } else if (serviceOption == SERVICE_OPT_3) {
                statistics(in);
            } else if (serviceOption == SERVICE_OPT_4) {
                customerService = exit(in, customerService);
            }

        }
    }


    /* ServiceOption 1 This defines a method called "addCountry" that accepts a Scanner object as an input parameter.
    This method gets a Continent object, the name and language of a country,
    and a boolean value indicating whether the country is open to tourists.
    It then creates a new Country object using these values and adds it */
    private static void addCountry(Scanner in) {
        in.nextLine();
        Continent customerContinent = getCustomerContinent(in);
        if (customerContinent == null) {
            System.out.println("Invalid input, please try again");
        } else {
            String countryName = getCountryNameAndValidate(in);


            boolean countryLanguageValid = false;
            String countryLanguage = getCountryLanguageAndValidate(in, countryLanguageValid);

            boolean openToTouristsValid = false;
            boolean openToTourists = isOpenToTouristsAndValidate(in, openToTouristsValid);


            Country newCountry = new Country(countryName, countryLanguage, openToTourists);
            System.out.println("Please see details of your " + newCountry);
            customerContinent.addCountry(newCountry);


            System.out.println("Would you like to add another Country?(Yes or No)");
            String addCountry = in.next();
            if (addCountry.equalsIgnoreCase("yes")) {
                addCountry(in); //recursive method that to allows the user to add another country
            } else if (addCountry.equalsIgnoreCase("no")){
                System.out.println("Thanks for using our service");
            }
        }
    }

    /* serviceOption 2, defines a method that prompts the user for the name of a country
     and input values for a new destination, checks that the input values are valid
     and adds the new destination to the country*/
    private static void addDestination(Scanner in) {
        in.nextLine();

        Country country = null;
        while (country == null){
            System.out.println("What country do you want to add a destination to? "
                   + "enter exit to go back to add a country");
            try {
                String countryName = in.nextLine();
                if (countryName.equalsIgnoreCase("exit")){
                    return;
                }
                country = searchForCountry(countryName);
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }


        boolean destinationNameValid = false;
        String destinationName = "";
        while (!destinationNameValid){
            System.out.println("What is the Name of the Destination");
            destinationName = in.nextLine();
            //Verification
            //check if  String matches the regular input expected
            if (destinationName.matches("[a-zA-Z ]*")){
                destinationNameValid = true;
            } else {
                System.out.println("Invalid input, try again");
            }
        }


        boolean isLatitudeValid = false;
        double latitude = 0;
        while (!isLatitudeValid){
            System.out.println("What is the Latitude of the Destination");
            try {//to handle Runtime exceptions
                latitude = in.nextDouble();
                isLatitudeValid = true;
            } catch (RuntimeException e){
                System.out.println("Invalid Input, please try again");
                in.nextLine();
            }

        }

        boolean isLongitudeValid = false;
        double longitude = 0;
        while (!isLongitudeValid){
            System.out.println("What is the Longitude of the Destination");
            try {//to handle Runtime exceptions
                longitude = in.nextDouble();
                isLongitudeValid = true;
            } catch (RuntimeException e){
                System.out.println("Invalid Input, please try again");
                in.nextLine();
            }
        }

        boolean isCapacityValid = false;
        long capacity = 0;
        while (!isCapacityValid){
            System.out.println("What is the capacity of your Destination,");
            try {//to handle Runtime exceptions
                capacity = in.nextLong();
                isCapacityValid = true;
            } catch (RuntimeException e){
                System.out.println("Invalid Input, please try again");
                // in.next();
            }

        }

        Destination newDestination = new Destination(destinationName, latitude, longitude, capacity);
        country.addDestination(newDestination);

        System.out.println("Please see details of your " + newDestination);

        System.out.println("Would you like to add another Destination?(Yes or No)");
        String addDestination = in.next();
        if (addDestination.equalsIgnoreCase("yes")){
            addDestination(in); //recursive method that to allows the user to add another country
        } else if (addDestination.equalsIgnoreCase("no")){

            System.out.println("Thanks for using our service");

        }

    }

    //This method is a utility for displaying various Statistics(SERVICE_OPTION_3)about destinations in the system.
    // The statisticsOption variable is used to determine which Statistic to display.
    private static void statistics(Scanner in){
        in.nextLine();
        System.out.println("What Information will you like to know? Pick from 1 to 3");
        System.out.println("1. Country with the highest average capacity of Destination");
        System.out.println("2. Highest Capacity Destination recorded in the System");
        System.out.println("3. Destinations recorded with capacity larger than a given number");

        System.out.println("Enter option below");

        int statisticsOption = in.nextInt();

        /*if  statisticsOption is 1, the method finds the country with the
         highest average capacity of destinations
         among all the continents and prints the name of the country*/
        if (statisticsOption == 1){
            Country highestOverall = listOfContinents.get(0).getCountryWithHighestAvgDesCapacity();
            for (int i = 1; i < listOfContinents.size(); i++) {
                Country highestAvgInContinent = listOfContinents.get(i).getCountryWithHighestAvgDesCapacity();
                if (highestAvgInContinent != null){
                    if (highestOverall.averageCapacity() < highestAvgInContinent.averageCapacity()) {
                        highestOverall = highestAvgInContinent;
                    }
                }
            }
            if (highestOverall == null){
                System.out.println("There are no destinations in the system yet");
            } else {
                System.out.println("Highest country is " + highestOverall.getCountryName());
            }

        /* if  statisticsOption is 2, the method finds the destination
        with the highest capacity among all the continents and prints the name of the destination.*/
        } else if (statisticsOption == 2){
            Destination highest = listOfContinents.get(0).getDestinationWithHighestCapacity();
            for (int i = 1; i < listOfContinents.size(); i++) {
            Destination highestDestinationInContinent = listOfContinents.get(i).getDestinationWithHighestCapacity();
                if (highestDestinationInContinent != null){
                    if (highest.getCapacity() < highestDestinationInContinent.getCapacity()){
                        highest = highestDestinationInContinent;
                    }
                }
            }
            if (highest == null){
                System.out.println("There are no destinations in the system yet");
            } else {
                System.out.println("Highest destination is " + highest.getDestinationName());
            }

            /*if  statisticsOption is 3, the  method prompts the user for a capacity value
             and then finds all the destinations in the system that have a capacity greater
             than the specif ied value. */
        } else if (statisticsOption == SERVICE_OPT_3){

            long capacity = -1;
            while (capacity < 0){
                System.out.println("What capacity will you like to compare with? ");
                try {
                    capacity = in.nextLong();
                } catch (RuntimeException e){
                    System.out.println("Invalid value, please try again");
                    in.nextLine();
                }
            }
            ArrayList<Destination> destinations = new ArrayList<>();
            for (Continent continent : listOfContinents) {
             ArrayList<Destination> greaterDestinations = continent.getDestinationWithCapacityGreaterThan(capacity);
                destinations.addAll(greaterDestinations);
            }
            if (destinations.isEmpty()){
                System.out.println("There are no destinations ");
            } else {
                System.out.println("The destinations with the higher capacity are " + destinations);
            }

        } else {
            System.out.println("please pick an option between 1 to 4");
        }
    }

    // Exit function(serviceOption 4)returns a boolean value
    // indicating whether the customer service loop should continue.
    private static boolean exit(Scanner in, boolean customerService){
        System.out.println("Would you like to exit?, Yes or No");
        String exitOption = in.next();
        if (exitOption.equalsIgnoreCase("yes")){
            System.out.println("Thanks for your patronage");
            customerService = false;
        } else if (exitOption.equalsIgnoreCase("no")){
            customerService = true;
        }
        return customerService;
    }

    //This method is used in method "addCountry" to return and validate if  country is open to tourist
    private static boolean isOpenToTouristsAndValidate(Scanner in, boolean openToTouristsValid){
        boolean openToTourists = false;
        while (!openToTouristsValid){
            System.out.println("Is the country open to tourists(yes or no)");
            String userAnswer = in.nextLine();
            if (userAnswer.equalsIgnoreCase("yes") || userAnswer.equalsIgnoreCase("no")){
                openToTouristsValid = true;
                if (userAnswer.equalsIgnoreCase("yes")){
                    openToTourists = true;
                } else if (userAnswer.equalsIgnoreCase("no")){
                    openToTourists = false;
                }
            } else {
                System.out.println("please input yes or no");
            }
        }
        return openToTourists;
    }

    //This method is used in method "addCountry" to return and validate the country language.
    private static String getCountryLanguageAndValidate(Scanner in, boolean countryLanguageValid){
        String countryLanguage = "";
        while (!countryLanguageValid){
            System.out.println("What is the language of the country ");
            countryLanguage = in.nextLine();
            if (countryLanguage.matches("[a-zA-Z ]*")){
                countryLanguageValid = true;
            } else {
                System.out.println("invalid input, please try again");
            }
        }
        return countryLanguage;
    }

    //This method is used in method "addCountry" to return and validate the country name
    private static String getCountryNameAndValidate(Scanner in){
        boolean countryNameValid = false;
        String countryName = "";
        while (!countryNameValid){
            System.out.println("What Country will you like to add?");
            countryName = in.nextLine(); //Verification
            //check if  String matches the regular input expected
            if (countryName.matches("[a-zA-Z ]*")){
                countryNameValid = true;
            } else {
                System.out.println("invalid input, please try again");
            }
        }
        return countryName;
    }

    /*This method is used in method "addCountry" to return customerContinent
    this method, searches for a matching Continent object in a list of Continent objects.
    if  a match is found, the method returns the Continent object; otherwise, it returns null*/
    private static Continent getCustomerContinent(Scanner in){
        System.out.println(CONTINENT_QUERY);
        String customerResponse = in.nextLine();
        Continent customerContinent = null;
        for (Continent currentContinent : listOfContinents){
            if (currentContinent.getContinentName().equalsIgnoreCase(customerResponse)){
                customerContinent = currentContinent;
                break;
            }
        }
        return customerContinent;
    }

    /*This method searches for a country in a list of continents and returns it if found.
     if  the country is not found, an exception is thrown.*/
    private static Country searchForCountry(String countryName){
        if (listOfContinents == null){
            throw new RuntimeException("Program not initialized correctly");
        }
        for (Continent currentContinent : listOfContinents){
            Country searchResult = currentContinent.getCountryDetails(countryName);
            if (searchResult != null){
                return searchResult;
            }
        }
        throw new IllegalArgumentException("Country not found");
    }
}



