package seedu.track2gather.model.person;

import static seedu.track2gather.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.track2gather.model.person.attributes.Address;
import seedu.track2gather.model.person.attributes.CallStatus;
import seedu.track2gather.model.person.attributes.CaseNumber;
import seedu.track2gather.model.person.attributes.Email;
import seedu.track2gather.model.person.attributes.Name;
import seedu.track2gather.model.person.attributes.NextOfKinAddress;
import seedu.track2gather.model.person.attributes.NextOfKinName;
import seedu.track2gather.model.person.attributes.NextOfKinPhone;
import seedu.track2gather.model.person.attributes.Phone;
import seedu.track2gather.model.person.attributes.QuarantineAddress;
import seedu.track2gather.model.person.attributes.ShnPeriod;
import seedu.track2gather.model.person.attributes.WorkAddress;

/**
 * Represents a Person in the contacts list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements Comparable<Person> {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final CaseNumber caseNumber;
    private final Address homeAddress;
    private final WorkAddress workAddress;
    private final QuarantineAddress quarantineAddress;
    private final ShnPeriod shnPeriod;
    private final NextOfKinName nextOfKinName;
    private final NextOfKinPhone nextOfKinPhone;
    private final NextOfKinAddress nextOfKinAddress;
    private final CallStatus callStatus;

    /**
     * Constructor for Person.
     * <p>
     * All fields must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, CaseNumber caseNumber, Address homeAddress,
                  WorkAddress workAddress, QuarantineAddress quarantineAddress, ShnPeriod shnPeriod,
                  NextOfKinName nextOfKinName, NextOfKinPhone nextOfKinPhone, NextOfKinAddress nextOfKinAddress,
                  CallStatus callStatus) {
        requireAllNonNull(name, phone, email, caseNumber, homeAddress, workAddress, quarantineAddress,
                shnPeriod, nextOfKinName, nextOfKinPhone, nextOfKinAddress, callStatus);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.caseNumber = caseNumber;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.quarantineAddress = quarantineAddress;
        this.shnPeriod = shnPeriod;
        this.nextOfKinName = nextOfKinName;
        this.nextOfKinPhone = nextOfKinPhone;
        this.nextOfKinAddress = nextOfKinAddress;
        this.callStatus = callStatus;
    }

    /**
     * Default constructor for Person that uses a default call status.
     */
    public Person(Name name, Phone phone, Email email, CaseNumber caseNumber, Address homeAddress,
                  WorkAddress workAddress, QuarantineAddress quarantineAddress, ShnPeriod shnPeriod,
                  NextOfKinName nextOfKinName, NextOfKinPhone nextOfKinPhone, NextOfKinAddress nextOfKinAddress) {
        this(name, phone, email, caseNumber, homeAddress, workAddress, quarantineAddress, shnPeriod,
                nextOfKinName, nextOfKinPhone, nextOfKinAddress, new CallStatus(0, false));
    }

    /**
     * Constuctor for Person to create a copy that uses a new counter. Used to update immutable call status.
     * @param old the previous version of the person.
     * @param newCallStatus the new counter to be used.
     */
    public Person(Person old, CallStatus newCallStatus) {
        this(old.name, old.phone, old.email, old.caseNumber, old.homeAddress, old.workAddress, old.quarantineAddress,
             old.shnPeriod, old.nextOfKinName, old.nextOfKinPhone, old.nextOfKinAddress, newCallStatus);
    }

    /**
     * Short constructor for Person with no optional attributes.
     */
    public Person(Name name, Phone phone, Email email, CaseNumber caseNumber, Address homeAddress) {
        this(name, phone, email, caseNumber, homeAddress, new WorkAddress(), new QuarantineAddress(), new ShnPeriod(),
                new NextOfKinName(), new NextOfKinPhone(), new NextOfKinAddress());
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public CaseNumber getCaseNumber() {
        return caseNumber;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public WorkAddress getWorkAddress() {
        return workAddress;
    }

    public QuarantineAddress getQuarantineAddress() {
        return quarantineAddress;
    }

    public ShnPeriod getShnPeriod() {
        return shnPeriod;
    }

    public NextOfKinName getNextOfKinName() {
        return nextOfKinName;
    }

    public NextOfKinPhone getNextOfKinPhone() {
        return nextOfKinPhone;
    }

    public NextOfKinAddress getNextOfKinAddress() {
        return nextOfKinAddress;
    }

    public CallStatus getCallStatus() {
        return callStatus;
    }

    /**
     * Returns true if both persons have the same case number.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null && otherPerson.getCaseNumber().equals(getCaseNumber());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getCaseNumber().equals(getCaseNumber())
                && otherPerson.getHomeAddress().equals(getHomeAddress())
                && otherPerson.getWorkAddress().equals(getWorkAddress())
                && otherPerson.getQuarantineAddress().equals(getQuarantineAddress())
                && otherPerson.getShnPeriod().equals(getShnPeriod())
                && otherPerson.getNextOfKinName().equals(getNextOfKinName())
                && otherPerson.getNextOfKinPhone().equals(getNextOfKinPhone())
                && otherPerson.getNextOfKinAddress().equals(getNextOfKinAddress())
                && otherPerson.getCallStatus().equals(getCallStatus());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, caseNumber, homeAddress, workAddress, quarantineAddress, shnPeriod,
            nextOfKinName, nextOfKinPhone, nextOfKinAddress, callStatus);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Case Number: ")
                .append(getCaseNumber())
                .append("; Home Address: ")
                .append(getHomeAddress());

        getWorkAddress().value.ifPresent(address -> builder.append("; Work Address: ").append(address));
        getQuarantineAddress().value.ifPresent(address -> builder.append("; Quarantine Address: ").append(address));
        getShnPeriod().value.ifPresent(period -> builder.append("; SHN Period: ").append(period));
        getNextOfKinName().value.ifPresent(value -> builder.append("; Next-of-Kin's Name: ").append(value));
        getNextOfKinPhone().value.ifPresent(value -> builder.append("; Next-of-Kin's Phone: ").append(value));
        getNextOfKinAddress().value.ifPresent(address -> builder.append("; Next-of-Kin's Address: ").append(address));

        return builder.toString();
    }

    @Override
    public int compareTo(Person otherPerson) {
        return getCaseNumber().compareTo(otherPerson.getCaseNumber());
    }
}
