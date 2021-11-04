package seedu.track2gather.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.track2gather.commons.exceptions.IllegalValueException;
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
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String caseNumber;
    private final String homeAddress;
    private final String workAddress;
    private final String quarantineAddress;
    private final String shnPeriod;
    private final String nextOfKinName;
    private final String nextOfKinPhone;
    private final String nextOfKinAddress;
    private final String callStatus;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("caseNumber") String caseNumber,
                             @JsonProperty("homeAddress") String homeAddress,
                             @JsonProperty("workAddress") String workAddress,
                             @JsonProperty("quarantineAddress") String quarantineAddress,
                             @JsonProperty("shnPeriod") String shnPeriod,
                             @JsonProperty("nextOfKinName") String nextOfKinName,
                             @JsonProperty("nextOfKinPhone") String nextOfKinPhone,
                             @JsonProperty("nextOfKinAddress") String nextOfKinAddress,
                             @JsonProperty("callStatus") String callStatus) {
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
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().value;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        caseNumber = source.getCaseNumber().value;
        homeAddress = source.getHomeAddress().value;
        workAddress = source.getWorkAddress().value.map(Object::toString).orElse(null);
        quarantineAddress = source.getQuarantineAddress().value.map(Object::toString).orElse(null);
        shnPeriod = source.getShnPeriod().value.map(Object::toString).orElse(null);
        nextOfKinName = source.getNextOfKinName().value.map(Object::toString).orElse(null);
        nextOfKinPhone = source.getNextOfKinPhone().value.map(Object::toString).orElse(null);
        nextOfKinAddress = source.getNextOfKinAddress().value.map(Object::toString).orElse(null);
        callStatus = source.getCallStatus().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (caseNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CaseNumber.class.getSimpleName()));
        }
        if (!CaseNumber.isValidCaseNumber(caseNumber)) {
            throw new IllegalValueException(CaseNumber.MESSAGE_CONSTRAINTS);
        }
        final CaseNumber modelCaseNumber = new CaseNumber(caseNumber);

        if (homeAddress == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(homeAddress)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelHomeAddress = new Address(homeAddress);

        if (workAddress != null && !Address.isValidAddress(workAddress)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final WorkAddress modelWorkAddress = new WorkAddress(Optional.ofNullable(workAddress).map(Address::new));

        if (quarantineAddress != null && !Address.isValidAddress(quarantineAddress)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final QuarantineAddress modelQuarantineAddress =
                new QuarantineAddress(Optional.ofNullable(quarantineAddress).map(Address::new));

        if (shnPeriod != null && !Period.isValidPeriodString(shnPeriod)) {
            throw new IllegalValueException(Period.MESSAGE_CONSTRAINTS);
        }
        final ShnPeriod modelShnPeriod = new ShnPeriod(Optional.ofNullable(shnPeriod).map(Period::new));

        if (nextOfKinName != null && !Name.isValidName(nextOfKinName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final NextOfKinName modelNextOfKinName = new NextOfKinName(Optional.ofNullable(nextOfKinName).map(Name::new));

        if (nextOfKinPhone != null && !Phone.isValidPhone(nextOfKinPhone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final NextOfKinPhone modelNextOfKinPhone =
                new NextOfKinPhone(Optional.ofNullable(nextOfKinPhone).map(Phone::new));

        if (nextOfKinAddress != null && !Address.isValidAddress(nextOfKinAddress)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final NextOfKinAddress modelNextOfKinAddress =
                new NextOfKinAddress(Optional.ofNullable(nextOfKinAddress).map(Address::new));

        if (callStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CallStatus.class.getSimpleName()));
        }
        if (!CallStatus.isValidCallStatus(callStatus)) {
            throw new IllegalValueException(CallStatus.MESSAGE_CONSTRAINTS);
        }
        final CallStatus modelCallStatus = new CallStatus(callStatus);

        return new Person(modelName, modelPhone, modelEmail, modelCaseNumber, modelHomeAddress, modelWorkAddress,
                modelQuarantineAddress, modelShnPeriod, modelNextOfKinName, modelNextOfKinPhone,
                modelNextOfKinAddress, modelCallStatus);
    }

}
