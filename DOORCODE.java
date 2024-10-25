import java.io.IOException;

// Class representing a basic Door
class Door {
    private boolean _open; // Indicates whether the door is open or closed

    // Default constructor for Door
    public Door() {
        System.out.println("in Door n-arg");
        _open = false; // Door is initially closed
    }

    // Method to open the door
    public void open() { _open = true; } // Set the door state to open

    // Constructor that allows setting the door's open state
    public Door(boolean x) { _open = x; } // Set the door state based on the parameter

    // Method to check if the door is open
    public boolean isOpen() { return _open; } // Return the open state of the door

    // Override toString() method to represent the door's state
    public String toString() {
        return _open ? "true" : "false"; // Return "true" if open, "false" if closed
    }
}

// Class representing a locked Door, inheriting from Door
class LockedDoor extends Door {
    private boolean _locked; // Indicates whether the door is locked

    // Default constructor for LockedDoor
    public LockedDoor() {
        System.out.println("in LockedDoor default");
        _locked = false; // Door is initially unlocked
    }

    // Constructor that allows setting the locked state
    public LockedDoor(boolean x) {
        System.out.println("in LockedDoor 2-arg");
        _locked = x; // Set the locked state based on the parameter
    }

    // Constructor allowing to set both open and locked states
    public LockedDoor(boolean x, boolean y) {
        super(x); // Call the parent Door constructor to set open state
        System.out.println("in LockedDoor 2-arg");
        _locked = y; // Set the locked state based on the parameter
    }

    // Method to check if the door is locked
    public boolean isLocked() { return _locked; } // Return the locked state of the door

    // Alternative method to check if the door is locked (not necessary)
    public boolean islocked() { return _locked; } // Return the locked state of the door

    // Method to lock the door
    public void lock() { _locked = true; } // Set the locked state to true

    // Method to unlock the door
    public void unlock() { _locked = false; } // Set the locked state to false

    // Override open() method to consider locked state
    public void open() {
        if (!_locked) super.open(); // Open the door only if it is not locked
    }

    // Override toString() method to include locked state
    public String toString() {
        String s = super.toString(); // Get the open state from the parent class
        return s + " " + (_locked ? "true" : "false"); // Append locked state
    }
}

// Class representing a safe door, inheriting from LockedDoor
class SafeDoor extends LockedDoor {
    private int _pin; // PIN code for the safe door

    // Default constructor for SafeDoor
    public SafeDoor() {
        System.out.println("in SafeDoor default");
        _pin = 0; // Initialize PIN to 0
    }

    // Constructor allowing to set the open, locked states and the PIN
    public SafeDoor(boolean x, boolean y, int p) {
        super(x, y); // Call the parent constructor to set open and locked states
        System.out.println("in SafeDoor 2-arg");
        _pin = p; // Set the PIN based on the parameter
    }

    // Override open() method (does nothing)
    public void open() {
        // Do nothing; must use open(int p) method to unlock
    }

    // Method to open the safe door using a PIN
    public void open(int p) {
        if (p == _pin) { // Check if provided PIN matches the stored PIN
            super.unlock(); // Unlock the door
            super.open(); // Open the door
        }
    }

    // Override toString() method to include the PIN
    public String toString() {
        return super.toString() + " " + _pin; // Append the PIN to the state representation
    }
}

// Main class to test the door classes
public class DOORCODE {
    public static void main(String[] args) throws IOException {
        SafeDoor sd = new SafeDoor(); // Create a new SafeDoor instance
        sd.lock(); // Lock the safe door
        sd.unlock(); // Unlock the safe door
        sd.open(); // Attempt to open the safe door (does nothing since open() is overridden)

        // Print the state of the safe door
        System.out.println(sd.toString()); // Display the state of the door
    }
}
