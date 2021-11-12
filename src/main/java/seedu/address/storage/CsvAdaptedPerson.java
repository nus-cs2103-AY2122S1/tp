package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.IsCalled;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.interests.Interest;
import seedu.address.model.person.interests.InterestsList;

/**
 * CSV-friendly version of {@link Person}.
 */
public class CsvAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field in Import file is missing!";
    // This sets the ordering of which person object constructor takes in  data
    public static final Map<String, Integer> ATTRIBUTE_ORDERING = Stream.of(new Object[][] {
            {"Name", 0}, { "Phone", 1 }, {"Email", 2}, {"Address", 3}, {"Gender", 4}, {"Age", 5}, {"Interest", 6},
            {"Called", 7},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

    private String name;
    private String phone;
    private String email;
    private String calledString;
    private String address;
    private String gender;
    private String age;
    private List<CsvAdaptedInterest> interestList = new ArrayList<>();

    /**
     * Constructs a {@code CsvAdaptedPerson} with the given person details.
     */
    public CsvAdaptedPerson(String personDetails) throws DataConversionException {
        setDetails(personDetails);
    }

    /**
     * Constructs a {@code CsvAdaptedPerson} from a {@code Person} object
     *
     * @param source
     */
    public CsvAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        gender = source.getGender().value;
        age = source.getAge().value;
        interestList.addAll(source.getAllInterests()
                .stream()
                .map(CsvAdaptedInterest::new)
                .collect(Collectors.toList()));
        calledString = source.getIsCalled().value ? "TRUE" : "FALSE";
    }

    /**
     * Parses the string into individual details
     * Note that this assumes that the CSV datafile is delimited using semicolon
     * Quick guide as to how to do that :https://ashwaniashwin.wordpress
     * .com/2013/04/19/save-excel-file-as-a-csv-semicolon-delimited
     * // -file-along-with-unicode-encoding/
     *
     * @param personDetails
     */
    private void setDetails(String personDetails) throws DataConversionException {
        String[] details = personDetails.split(";", CsvAdaptedPerson.ATTRIBUTE_ORDERING.keySet().size());

        if (details.length != ATTRIBUTE_ORDERING.size()) {
            throw new DataConversionException(new Exception("Delimiter Missing"
                    + "\nEach row should have "
                    + (CsvAdaptedPerson.ATTRIBUTE_ORDERING.keySet().size() - 1) + " ';' "));
        }

        this.name = details[ATTRIBUTE_ORDERING.get("Name")].trim();
        this.phone = details[ATTRIBUTE_ORDERING.get("Phone")].trim();
        this.email = details[ATTRIBUTE_ORDERING.get("Email")].trim();
        this.address = details[ATTRIBUTE_ORDERING.get("Address")].trim();
        this.gender = details[ATTRIBUTE_ORDERING.get("Gender")].trim();
        this.age = details[ATTRIBUTE_ORDERING.get("Age")].trim();
        setInterestList(details[ATTRIBUTE_ORDERING.get("Interest")].trim());
        this.calledString = details[ATTRIBUTE_ORDERING.get("Called")].trim().toUpperCase();
    }

    private void setInterestList (String csvInterestString) {
        String[] interestStringList = csvInterestString.split(",");
        for (String interestString : interestStringList) {
            if (!interestString.trim().equals("")) {
                CsvAdaptedInterest addInterest = new CsvAdaptedInterest(interestString.trim());
                this.interestList.add(addInterest);
            }
        }
    }

    /**
     * String array with headers in the correct order
     */
    public static String[] headerOrder() {
        String[] headerKeySet = ATTRIBUTE_ORDERING.keySet().toArray(new String[0]);
        String[] validOrder = new String[headerKeySet.length];
        for (String key : headerKeySet) {
            int pos = ATTRIBUTE_ORDERING.get(key);
            validOrder[pos] = key;
        }
        return validOrder;
    }

    /**
     * Converts CSV adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        if (name.equals("")) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone.equals("")) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email.equals("")) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (!(IsCalled.isValidIsCalled(calledString))) {
            throw new IllegalValueException(IsCalled.MESSAGE_CONSTRAINTS);
        }
        final IsCalled modelIsCalled = new IsCalled(calledString);

        String checkAddress = address.equals("") ? null : address;

        if (!Address.isValidAddress(checkAddress)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }

        final Address modelAddress = new Address(checkAddress);

        String checkGender = gender.equals("") ? null : gender;

        if (!Gender.isValidGender(checkGender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }

        final Gender modelGender = new Gender(checkGender);

        String checkAge = age.equals("") ? null : age;

        if (!Age.isValidAge(checkAge)) {
            throw new IllegalValueException(Age.MESSAGE_CONSTRAINTS);
        }

        final Age modelAge = new Age(checkAge);

        final InterestsList modelInterests = new InterestsList();

        for (CsvAdaptedInterest interest : interestList) {
            Interest i = interest.toModelType();
            if (modelInterests.checkDuplicate(i)) {
                throw new IllegalValueException(InterestsList.MESSAGE_CONSTRAINTS);
            } else {
                modelInterests.addInterest(i);
            }
        }

        return new Person(modelName, modelPhone, modelEmail, modelIsCalled, modelAddress,
                modelGender, modelAge, modelInterests);
    }

}
