package HolidayDestination;

public class Destination {
    //defining Instance Variables(attributes)
    private String destinationName;
    private double latitude;
    private double longitude;
    private long capacity;


    //Getter and Setter methods(accessors and mutators)

    public String getDestinationName() {

        return destinationName;
    }

    public void setDestinationName(String destinationName) {

        this.destinationName = destinationName;
    }

    public double getLatitude() {

        return latitude;
    }

    public void setLatitude(double latitude) {

        this.latitude = latitude;
    }

    public double getLongitude() {

        return longitude;
    }

    public void setLongitude(double longitude) {

        this.longitude = longitude;
    }

    public long getCapacity() {

        return capacity;
    }

    public void setCapacity(long capacity) {

        this.capacity = capacity;
    }

    //constructor to retrieve information
    public Destination(String destinationName, double latitude, double longitude, long capacity) {
        this.destinationName = destinationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.capacity = capacity;
    }

    // To String() method to represent Destination object as a string
    @Override
    public String toString() {
        return "Destination{" +
                "destinationName='" + destinationName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", capacity=" + capacity +
                '}';
    }
}





