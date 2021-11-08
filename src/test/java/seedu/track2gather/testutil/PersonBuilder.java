package seedu.track2gather.testutil;

import java.util.Optional;

import seedu.track2gather.model.person.Person;
import seedu.track2gather.model.person.attributes.Address;
import seedu.track2gather.model.person.attributes.CallStatus;
import seedu.track2gather.model.person.attributes.CaseNumber;
import seedu.track2gather.model.person.attributes.Email;
import seedu.track2gather.model.person.attributes.Name;
import seedu.track2gather.model.person.attributes.NextOfKinAddress;
import seedu.track2gather.model.person.attributes.NextOfKinName;
import seedu.track2gather.model.person.attributes.NextOfKinPhone;
import seedu.track2gather.model.person.attributes.Period;
import seedu.track2gather.model.person.attributes.Phone;
import seedu.track2gather.model.person.attributes.QuarantineAddress;
import seedu.track2gather.model.person.attributes.ShnPeriod;
import seedu.track2gather.model.person.attributes.WorkAddress;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_CASE_NUMBER = "456";
    public static final String DEFAULT_HOME_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_CALL_STATUS = "0 false";

    private Name name;
    private Phone phone;
    private Email email;
    private CaseNumber caseNumber;
    private Address homeAddress;
    private WorkAddress workAddress;
    private QuarantineAddress quarantineAddress;
    private ShnPeriod shnPeriod;
    private NextOfKinName nextOfKinName;
    private NextOfKinPhone nextOfKinPhone;
    private NextOfKinAddress nextOfKinAddress;
    private CallStatus callStatus;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        caseNumber = new CaseNumber(DEFAULT_CASE_NUMBER);
        homeAddress = new Address(DEFAULT_HOME_ADDRESS);
        workAddress = new WorkAddress();
        quarantineAddress = new QuarantineAddress();
        shnPeriod = new ShnPeriod();
        nextOfKinName = new NextOfKinName();
        nextOfKinPhone = new NextOfKinPhone();
        nextOfKinAddress = new NextOfKinAddress();
        callStatus = new CallStatus(DEFAULT_CALL_STATUS);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        caseNumber = personToCopy.getCaseNumber();
        homeAddress = personToCopy.getHomeAddress();
        workAddress = personToCopy.getWorkAddress();
        quarantineAddress = personToCopy.getQuarantineAddress();
        shnPeriod = personToCopy.getShnPeriod();
        nextOfKinName = personToCopy.getNextOfKinName();
        nextOfKinPhone = personToCopy.getNextOfKinPhone();
        nextOfKinAddress = personToCopy.getNextOfKinAddress();
        callStatus = personToCopy.getCallStatus();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
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
     * Sets the {@code CaseNumber} of the {@code Person} that we are building.
     */
    public PersonBuilder withCaseNumber(String caseNumber) {
        this.caseNumber = new CaseNumber(caseNumber);
        return this;
    }

    /**
     * Sets the {@code homeAddress} of the {@code Person} that we are building.
     */
    public PersonBuilder withHomeAddress(String homeAddress) {
        this.homeAddress = new Address(homeAddress);
        return this;
    }

    /**
     * Sets the {@code workAddress} of the {@code Person} that we are building.
     */
    public PersonBuilder withWorkAddress(String workAddress) {
        this.workAddress = new WorkAddress(Optional.ofNullable(workAddress).map(Address::new));
        return this;
    }

    /**
     * Sets the {@code quarantineAddress} of the {@code Person} that we are building.
     */
    public PersonBuilder withQuarantineAddress(String quarantineAddress) {
        this.quarantineAddress = new QuarantineAddress(Optional.ofNullable(quarantineAddress).map(Address::new));
        return this;
    }

    /**
     * Sets the {@code shnPeriod} of the {@code Person} that we are building.
     */
    public PersonBuilder withShnPeriod(String shnPeriod) {
        this.shnPeriod = new ShnPeriod(Optional.ofNullable(shnPeriod).map(Period::new));
        return this;
    }

    /**
     * Sets the {@code nextOfKinName} of the {@code Person} that we are building.
     */
    public PersonBuilder withNextOfKinName(String nextOfKinName) {
        this.nextOfKinName = new NextOfKinName(Optional.ofNullable(nextOfKinName).map(Name::new));
        return this;
    }

    /**
     * Sets the {@code nextOfKinPhone} of the {@code Person} that we are building.
     */
    public PersonBuilder withNextOfKinPhone(String nextOfKinPhone) {
        this.nextOfKinPhone = new NextOfKinPhone(Optional.ofNullable(nextOfKinPhone).map(Phone::new));
        return this;
    }

    /**
     * Sets the {@code nextOfKinAddress} of the {@code Person} that we are building.
     */
    public PersonBuilder withNextOfKinAddress(String nextOfKinAddress) {
        this.nextOfKinAddress = new NextOfKinAddress(Optional.ofNullable(nextOfKinAddress).map(Address::new));
        return this;
    }

    /**
     * Sets the {@code callStatus} of the {@code Person} that we are building.
     */
    public PersonBuilder withCallStatus(String callStatus) {
        this.callStatus = new CallStatus(callStatus);
        return this;
    }

    /**
     * Creates {@code Person} with attributes corresponding to those set by the builder.
     *
     * @return built custom {@code Person}
     */
    public Person build() {
        return new Person(name, phone, email, caseNumber, homeAddress, workAddress, quarantineAddress,
                shnPeriod, nextOfKinName, nextOfKinPhone, nextOfKinAddress, callStatus);
    }

}
