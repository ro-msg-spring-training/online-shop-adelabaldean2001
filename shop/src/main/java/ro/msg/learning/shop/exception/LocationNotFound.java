package ro.msg.learning.shop.exception;

public class LocationNotFound extends RuntimeException{
    public LocationNotFound(){
        super("Location not found.");
    }
}
