package safeforhall.model.event;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;

import safeforhall.logic.parser.exceptions.ParseException;
import safeforhall.model.person.Name;
import safeforhall.model.person.Person;
import safeforhall.model.person.Room;

public class ResidentList {
    public static final String MESSAGE_CONSTRAINTS =
            "Information should be all rooms or all names and each piece of information is separated by a comma";
    public static final String MESSAGE_CONSTRAINTS_ROOM_AND_NAME =
            "Arguments given should be all rooms or all names";
    public static final String DESC = "Residents: ";
    public static final String DEFAULT_LIST = "";

    private final String residents;
    private final ArrayList<String> residentInformation;
    private final boolean isEmpty;
    private final ArrayList<Person> residentList = new ArrayList<>();

    /**
     * Constructs a {@code ResidentList}.
     *
     * @param residents A string of residents.
     */
    public ResidentList(String residents) {
        requireNonNull(residents);
        this.isEmpty = residents.equals(DEFAULT_LIST);
        this.residents = residents;
        residentInformation = new ArrayList<>(Arrays.asList(this.residents.split("\\s*,\\s*")));
    }

    /**
     * Returns true if a given string is a valid date.
     *
     * @param residents A string of residents.
     */
    public static boolean isValidResidentList(String residents) throws ParseException {
        if (residents.equals(DEFAULT_LIST)) {
            return true;
        }
        String[] informationList = residents.split("\\s*,\\s*");
        boolean isRoom = false;
        boolean isName = false;
        for (String str : informationList) {
            if (!(Name.isValidName(str) || Room.isValidRoom(str))) {
                return false;
            } else {
                if (Room.isValidRoom(str) && !isName) {
                    isRoom = true;
                } else if (Name.isValidName(str) && !isRoom) {
                    isName = true;
                } else {
                    throw new ParseException(MESSAGE_CONSTRAINTS_ROOM_AND_NAME);
                }
            }
        }
        return true;
    }

    /**
     * Returns a string consisting of past and new residents for the event.
     *
     * @param current  A string of current residents of the event.
     * @param toAdd    A string of residents to add to the event.
     *
     * @return A String consisting of past and new residents for the event.
     */
    public String addResidentList(ArrayList<Person> current, ArrayList<Person> toAdd) {
        StringBuilder newResidentList = new StringBuilder(residents);
        for (Person person : current) {
            if (!residentList.contains(person)) {
                residentList.add(person);
            }
        }
        for (Person person : toAdd) {
            if (!residentList.contains(person) && !newResidentList.toString().equals("")) {
                newResidentList.append(", ").append(person.getName());
                residentList.add(person);
            } else if (!residentList.contains(person) && newResidentList.toString().equals("")) {
                newResidentList.append(person.getName());
                residentList.add(person);
            }
        }
        return newResidentList.toString();
    }

    /**
     * Returns true if the ResidentList is empty.
     */
    public boolean isEmpty() {
        return this.isEmpty;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResidentList // instanceof handles nulls
                && residents.equals(((ResidentList) other).residents)); // state check
    }

    @Override
    public String toString() {
        return residents;
    }

    public ArrayList<String> getResidentInformation() {
        return this.residentInformation;
    }

    public String getResidents() {
        return this.residents;
    }
}
