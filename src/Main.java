import java.io.*;
import java.time.ZoneId;
import java.time.Instant;
import java.util.*;
import java.time.LocalTime;

// Interface to define behavior for a door without a lock
interface NoLockDoorBehavior {
    void open(); // Method to open the door
    void close(); // Method to close the door
    boolean isOpen(); // Method to check if the door is open
}

// Interface to define behavior for a locked door that requires a key
interface LockDoorBehavior {
    void open(String key); // Method to open the door with a specific key
    void close(); // Method to close the door
    boolean isOpen(); // Method to check if the door is open
}

// Class that implements NoLockDoorBehavior for a manual door
class ManualDoorBehavior implements NoLockDoorBehavior {
    private boolean _open; // Tracks whether the door is open

    // Constructor initializes the door to closed
    public ManualDoorBehavior() { _open = false; }

    // Constructor initializes the door to a specific state (open/closed)
    public ManualDoorBehavior(boolean b) { _open = b; }

    public void open() {_open = true; } // Set the door state to open

    public void close() { _open = false; } // Set the door state to closed

    public boolean isOpen() { return _open; } // Return the current state of the door

    public String toString() {
        return _open + " "; // Return string representation of the door state
    }
}

// Class that implements NoLockDoorBehavior for an automatic door
class AutomaticDoorBehavior implements NoLockDoorBehavior {
    private boolean _open; // Tracks whether the door is open

    // Constructor initializes the door to closed
    public AutomaticDoorBehavior() { _open = false; }

    // Constructor initializes the door to a specific state (open/closed)
    public AutomaticDoorBehavior(boolean b) { _open = b; }

    public void open() {
        // Automatically opens the door if the current time is between 8 AM and 10 PM
        Instant now = Instant.now();
        LocalTime localTime = LocalTime.ofInstant(now, ZoneId.systemDefault());
        LocalTime startTime = LocalTime.of(8, 0); // Opening time: 8:00 AM
        LocalTime endTime = LocalTime.of(22, 0); // Closing time: 10:00 PM
        if (localTime.isAfter(startTime) && localTime.isBefore(endTime)) {
            _open = true; // Set the door state to open
        }
    }

    public void close() {
        // Automatically closes the door if the current time is outside the open hours
        Instant now = Instant.now();
        LocalTime localTime = LocalTime.ofInstant(now, ZoneId.systemDefault());
        LocalTime startTime = LocalTime.of(22, 0); // Opening time: 10:00 PM
        LocalTime endTime = LocalTime.of(8, 0); // Closing time: 8:00 AM
        if (localTime.isAfter(startTime) && localTime.isBefore(endTime)) {
            _open = false; // Set the door state to closed
        }
    }

    public boolean isOpen() { return _open; } // Return the current state of the door

    public String toString() {
        return _open + " "; // Return string representation of the door state
    }
}

// Class that implements LockDoorBehavior for a door that requires a password
class PasswordDoorBehavior implements LockDoorBehavior {
    private boolean _open; // Tracks whether the door is open
    private String _key; // The password required to open the door

    // Constructor initializes the door to closed and sets a default password
    public PasswordDoorBehavior() {
        _open = false;
        _key = "pwd"; // Default password
    }

    // Constructor initializes the door to a specific state and sets a specific password
    public PasswordDoorBehavior(boolean b, String s) {
        _open = b;
        _key = s;
    }

    public void open(String k) {
        // Opens the door if the provided key matches the stored password
        if (_key.equals(k))
            _open = true; // Set the door state to open
    }

    public void close() { _open = false; } // Set the door state to closed

    public boolean isOpen() { return _open; } // Return the current state of the door

    public String toString() {
        return _open + " " + _key + " "; // Return string representation of the door state and key
    }
}

// Class that implements LockDoorBehavior for a door that requires a combination
class CombinationDoorBehavior implements LockDoorBehavior {
    private boolean _open; // Tracks whether the door is open
    private int _key; // The combination required to open the door

    // Constructor initializes the door to closed and sets a default combination
    public CombinationDoorBehavior() {
        _open = false;
        _key = 1234; // Default combination
    }

    // Constructor initializes the door to a specific state and sets a specific combination
    public CombinationDoorBehavior(boolean b, int k) {
        _open = b;
        _key = k;
    }

    public void open(String k) {
        try {
            int t = Integer.parseInt(k); // Parse the provided key to an integer
            if (_key == t)
                _open = true; // Set the door state to open if the combination matches
        } catch (NumberFormatException e) {
            // Do nothing if the key is not a valid integer
        }
    }

    public void close() { _open = false; } // Set the door state to closed

    public boolean isOpen() { return _open; } // Return the current state of the door

    public String toString() {
        return _open + " " + _key + " "; // Return string representation of the door state and key
    }
}

// Class to represent a standard door without a lock
class StandardDoor {
    private NoLockDoorBehavior _doorBehavior; // Behavior for the door
    private String _location; // Location of the door

    public StandardDoor() { // Default constructor initializes to manual door behavior
        _doorBehavior = new ManualDoorBehavior();
        _location = ""; // Default location
    }

    public StandardDoor(NoLockDoorBehavior n, String loc) { // Constructor with specific behavior and location
        _doorBehavior = n;
        _location = loc;
    }

    public void open() { _doorBehavior.open(); } // Open the door using its behavior

    public void close() {
        _doorBehavior.close(); // Close the door using its behavior
    }

    public boolean isOpen() { return _doorBehavior.isOpen(); } // Return the current state of the door

    public String toString() {
        return _doorBehavior.toString() + _location;
    }
}

// Class to represent a locked door
class LockedDoor {
    private LockDoorBehavior _doorBehavior; // Behavior for the locked door
    private String _location; // Location of the door

    public LockedDoor() { // Default constructor initializes to combination door behavior
        _doorBehavior = new CombinationDoorBehavior();
        _location = ""; // Default location
    }

    public LockedDoor(LockDoorBehavior c, String loc) { // Constructor with specific behavior and location
        _doorBehavior = c;
        _location = loc;
    }

    public void open(String k) { _doorBehavior.open(k); } // Open the door using its behavior with a key

    public void close() { _doorBehavior.close(); } // Close the door using its behavior

    public boolean isOpen() { return _doorBehavior.isOpen(); } // Return the current state of the door

    public String toString() {
        return _doorBehavior.toString() + _location; // Return string representation of the door state and location
    }
}

// Main class to test the door behaviors
public class Main {
    public static void main(String[] args) throws IOException {
        LocalTime now = LocalTime.now(); // Get the current time
        LocalTime open = LocalTime.of(8, 0); // Set opening time to 8:00 AM
        int compare = now.compareTo(open); // Compare current time to opening time
        System.out.println(compare); // Print comparison result

        // Create lists to hold standard and locked doors
        ArrayList<StandardDoor> sdoors = new ArrayList<StandardDoor>();
        ArrayList<LockedDoor> ldoors = new ArrayList<LockedDoor>();

        // Add standard doors with different behaviors
        sdoors.add(new StandardDoor(new ManualDoorBehavior(false), "room1"));
        sdoors.add(new StandardDoor(new AutomaticDoorBehavior(false), "room2"));

        // Add locked doors with different behaviors
        ldoors.add(new LockedDoor(new CombinationDoorBehavior(false, 343), "office1"));
        ldoors.add(new LockedDoor(new PasswordDoorBehavior(false, "pwd123"), "office2"));

        System.out.println("*** Testing StandardDoor ***");
        for (StandardDoor d : sdoors) {
            System.out.println(d.toString()); // Print initial state of each standard door
        }
        sdoors.get(0).open(); // Open the first standard door
        sdoors.get(1).open(); // Open the second standard door
        for (StandardDoor d : sdoors) {
            System.out.println(d.toString()); // Print state of each standard door after opening
        }

        System.out.println("*** Testing LockedDoor ***");
        for (LockedDoor d : ldoors) {
            System.out.println(d.toString()); // Print initial state of each locked door
        }
        ldoors.get(0).open("243"); // Attempt to open the first locked door with incorrect combination
        ldoors.get(1).open("pwd123"); // Attempt to open the second locked door with correct password
        for (LockedDoor d : ldoors) {
            System.out.println(d.toString()); // Print state of each locked door after attempts to open
        }
    }
}