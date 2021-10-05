package seedu.address.storage;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.IsDone;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * CSV-friendly version of {@link Person}.
 */
public class CsvAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field in Import file is missing!";
    // This sets the ordering of which person object constructor takes in  data
    private static Map<String, Integer> map = Stream.of(new Object[][] {
            { "Name", 0 }, { "Phone", 1 }, {"Email", 2}, {"Done", 3}
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

    private String name;
    private String phone;
    private String email;
    private String doneString;
    private String address;

    /**
     * Constructs a {@code CsvAdaptedPerson} with the given person details.
     */
    public CsvAdaptedPerson(String personDetails) {
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
        doneString = source.getIsDone().value ? "TRUE" : "FALSE";
        address = source.getAddress().value;
    }

    /**
     * Parses the string into indivdiual deltails
     * Note that this assumes that the CSV datafile is delimited using semicolon
     * Quick guide as to how to do that :https://ashwaniashwin.wordpress
     * .com/2013/04/19/save-excel-file-as-a-csv-semicolon-delimited
     * // -file-along-with-unicode-encoding/
     *
     * @param personDetails
     */
    private void setDetails(String personDetails) {
        String[] details = personDetails.split(";", 4);
        this.name = details[map.get("Name")].trim();
        this.phone = details[map.get("Phone")].trim();
        this.email = details[map.get("Email")].trim();
        this.doneString = details[map.get("Done")].trim().toUpperCase();
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

        if (!(IsDone.isValidIsDone(doneString))) {
            throw new IllegalValueException(IsDone.MESSAGE_CONSTRAINTS);
        }
        final IsDone modelIsDone = new IsDone(doneString);

        //Irfan to edit this address part
        return new Person(modelName, modelPhone, modelEmail, modelIsDone, new Address(address));
    }
}
