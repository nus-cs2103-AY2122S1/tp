package safeforhall.model.event;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;

import safeforhall.logic.parser.exceptions.ParseException;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.Name;
import safeforhall.model.person.Person;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;


public class ResidentList {
    public static final String MESSAGE_CONSTRAINTS =
            "Information should be all rooms or all names and each piece of information is separated by a comma";
    public static final String MESSAGE_CONSTRAINTS_ROOM_AND_NAME =
            "Arguments given should be all rooms or all names";
    public static final String DESC = "Residents: ";
    public static final String DEFAULT_LIST = "None";
    public static final String EMPTY_STRING = "";
    public static final int NUMBER_OF_RESIDENT_FIELD = 8;

    private final String residentsDisplay;
    private final String residentsStorage;
    private final ArrayList<String> stringResidentList;
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
        this.residentsDisplay = residents;
        this.residentsStorage = EMPTY_STRING;
        stringResidentList = new ArrayList<>(Arrays.asList(this.residentsDisplay.split("\\s*,\\s*")));
    }

    /**
     * Constructs a {@code ResidentList}.
     *
     * @param residents A string of residents.
     */
    public ResidentList(String residents, String residentList) {
        requireNonNull(residents);

        this.isEmpty = residents.equals(DEFAULT_LIST);
        this.residentsDisplay = residents;
        this.residentsStorage = residentList;

        if (!residentList.equals(ResidentList.DEFAULT_LIST)) {
            String[] residentInformationList = residentList.split("\\s*,\\s*");
            for (String residentInformation : residentInformationList) {
                String[] information = residentInformation.split(";\\s*\\w*(\\w*\\s*)*:\\s*");

                Name name = new Name(information[0]);
                Room room = new Room(information[1]);
                Phone phone = new Phone(information[2]);
                Email email = new Email(information[3]);
                VaccStatus vaccStatus = new VaccStatus(information[4]);
                Faculty faculty = new Faculty(information[5]);
                LastDate lastFetDate = new LastDate(information[6]);
                LastDate lastCollectionDate = new LastDate(information[7]);

                Person p = new Person(name, room, phone, email, vaccStatus, faculty, lastFetDate, lastCollectionDate);
                this.residentList.add(p);
            }
        }
        stringResidentList = new ArrayList<>(Arrays.asList(this.residentsDisplay.split("\\s*,\\s*")));
    }

    /**
     * Returns true if a given string is a valid list of residents.
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
     * Returns true if a given string is a valid date.
     *
     * @param residents A string of residents.
     */
    public static boolean isValidResidentStorage(String residents) {
        if (residents.equals(DEFAULT_LIST)) {
            return true;
        }
        String[] residentList = residents.split("\\s*,\\s*");
        for (String resident : residentList) {
            String[] information = resident.split(";\\s*\\w*(\\w*\\s*)*:\\s*");
            if (information.length != NUMBER_OF_RESIDENT_FIELD) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a string of person details, consisting of past and new residents for the event.
     *
     * @param toAdd    A string of residents to add to the event.
     *
     * @return A String consisting of past and new residents for the event.
     */
    public String getCombinedStorageString(ArrayList<Person> toAdd) {
        StringBuilder newResidentList;

        if (residentsStorage.equals(DEFAULT_LIST)) {
            newResidentList = new StringBuilder(EMPTY_STRING);
        } else {
            newResidentList = new StringBuilder(residentsStorage);
        }

        for (Person person : toAdd) {
            if (!newResidentList.toString().equals(EMPTY_STRING)) {
                newResidentList.append(", ").append(person);
            } else {
                newResidentList.append(person);
            }
        }

        if (newResidentList.toString().equals(EMPTY_STRING)) {
            return DEFAULT_LIST;
        } else {
            return newResidentList.toString();
        }
    }

    /**
     * Returns a string of names, consisting of past and new residents for the event.
     *
     * @param toAdd    A string of residents to add to the event.
     *
     * @return A String consisting of past and new residents for the event.
     */
    public String getCombinedDisplayString(ArrayList<Person> toAdd) {
        StringBuilder newResidentList;

        if (residentsDisplay.equals(DEFAULT_LIST)) {
            newResidentList = new StringBuilder(EMPTY_STRING);
        } else {
            newResidentList = new StringBuilder(residentsDisplay);
        }

        for (Person person : toAdd) {
            if (!newResidentList.toString().equals(EMPTY_STRING)) {
                newResidentList.append(", ").append(person.getName());
            } else {
                newResidentList.append(person.getName());
            }
        }

        if (newResidentList.toString().equals(EMPTY_STRING)) {
            return DEFAULT_LIST;
        } else {
            return newResidentList.toString();
        }
    }

    /**
     * Checks if any {@code resident} is not vaccinated
     * @return Returns true if any {@code resident} in the {@code ResidentList} is not vaccinated
     */
    public boolean hasUnvaccinatedResident() {
        for (Person person : residentList) {
            if (!person.isVaccinated()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Counts the number of unvaccinated residents in the {@code ResidentList}
     * @return Returns the number of unvaccinated residents in the {@code ResidentList}
     */
    public int numOfUnvaccinatedResidents() {
        int total = 0;
        for (Person person : residentList) {
            if (!person.isVaccinated()) {
                total++;
            }
        }
        return total;
    }

    /**
     * Returns a string of person details, consisting of the remaining residents for the event.
     *
     * @param toRemove    A string of residents to add to the event.
     *
     * @return A String consisting of past and new residents for the event.
     */
    public String getRemovedStorageString(ArrayList<Person> toRemove) {
        StringBuilder newResidentList = new StringBuilder("");

        for (Person person : residentList) {
            if (!toRemove.contains(person) && !newResidentList.toString().equals(EMPTY_STRING)) {
                newResidentList.append(", ").append(person);
            } else if (!toRemove.contains(person) && newResidentList.toString().equals(EMPTY_STRING)) {
                newResidentList.append(person);
            }
        }

        if (newResidentList.toString().equals(EMPTY_STRING)) {
            return DEFAULT_LIST;
        } else {
            return newResidentList.toString();
        }
    }

    /**
     * Returns a string of names, consisting of the remaining residents for the event.
     *
     * @param toRemove    A string of residents to remove from the event.
     *
     * @return A String consisting of the remaining residents for the event.
     */
    public String getRemovedDisplayString(ArrayList<Person> toRemove) {
        StringBuilder newResidentList = new StringBuilder("");

        for (Person person : residentList) {
            if (!toRemove.contains(person) && !newResidentList.toString().equals(EMPTY_STRING)) {
                newResidentList.append(", ").append(person.getName());
            } else if (!toRemove.contains(person) && newResidentList.toString().equals(EMPTY_STRING)) {
                newResidentList.append(person.getName());
            }
        }

        if (newResidentList.toString().equals(EMPTY_STRING)) {
            return DEFAULT_LIST;
        } else {
            return newResidentList.toString();
        }
    }

    /**
     * Returns true if the ResidentList is empty.
     */
    public boolean isEmpty() {
        return this.isEmpty;
    }

    /**
     * Returns the number of residents currently in the event.
     */
    public int getResidentListSize() {
        return this.residentList.size();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResidentList // instanceof handles nulls
                && residentsDisplay.equals(((ResidentList) other).residentsDisplay)); // state check
    }

    @Override
    public String toString() {
        return residentList.toString();
    }

    public ArrayList<String> getStringResidentList() {
        return this.stringResidentList;
    }

    public String getResidentsStorage() {
        return this.residentsStorage;
    }

    public String getResidentsDisplay() {
        return this.residentsDisplay;
    }

    public ArrayList<Person> getResidents() {
        return this.residentList;
    }
}
