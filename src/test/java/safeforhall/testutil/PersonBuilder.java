package safeforhall.testutil;

import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.Name;
import safeforhall.model.person.Person;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_ROOM = "A100";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_VACCSTATUS = "T";
    public static final String DEFAULT_FACULTY = "SoC";
    public static final String DEFAULT_FETDATE = "10-09-2021";
    public static final String DEFAULT_COLLECTDATE = "10-09-2021";

    private Name name;
    private Room room;
    private Phone phone;
    private Email email;
    private VaccStatus vaccStatus;
    private Faculty faculty;
    private LastDate lastFetDate;
    private LastDate lastCollectionDate;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        room = new Room(DEFAULT_ROOM);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        vaccStatus = new VaccStatus(DEFAULT_VACCSTATUS);
        faculty = new Faculty(DEFAULT_FACULTY);
        lastFetDate = new LastDate(DEFAULT_FETDATE);
        lastCollectionDate = new LastDate(DEFAULT_COLLECTDATE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        room = personToCopy.getRoom();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        vaccStatus = personToCopy.getVaccStatus();
        faculty = personToCopy.getFaculty();
        lastFetDate = personToCopy.getLastFetDate();
        lastCollectionDate = personToCopy.getLastCollectionDate();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Room} of the {@code Person} that we are building.
     */
    public PersonBuilder withRoom(String room) {
        this.room = new Room(room);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code VaccStatus} of the {@code Person} that we are building.
     */
    public PersonBuilder withVaccStatus(String vaccStatus) {
        this.vaccStatus = new VaccStatus(vaccStatus);
        return this;
    }

    /**
     * Sets the {@code Faculty} of the {@code Person} that we are building.
     */
    public PersonBuilder withFaculty(String faculty) {
        this.faculty = new Faculty(faculty);
        return this;
    }

    /**
     * Sets the {@code LastDate} of the {@code Person} that we are building.
     */
    public PersonBuilder withFet(String lastFetDate) {
        this.lastFetDate = new LastDate(lastFetDate);
        return this;
    }

    /**
     * Sets the {@code LastDate} of the {@code Person} that we are building.
     */
    public PersonBuilder withCollection(String lastCollectionDate) {
        this.lastCollectionDate = new LastDate(lastCollectionDate);
        return this;
    }

    public Person build() {
        return new Person(name, room, phone, email, vaccStatus, faculty, lastFetDate, lastCollectionDate);
    }

}
