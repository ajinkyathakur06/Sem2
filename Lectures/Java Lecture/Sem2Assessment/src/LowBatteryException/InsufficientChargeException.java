package LowBatteryException;

public class InsufficientChargeException extends Exception {
    public InsufficientChargeException(String message) {
        super(message);
    }
}