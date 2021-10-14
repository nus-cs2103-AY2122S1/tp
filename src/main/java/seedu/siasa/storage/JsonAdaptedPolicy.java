package seedu.siasa.storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.siasa.commons.exceptions.IllegalValueException;
import seedu.siasa.model.person.Person;
import seedu.siasa.model.policy.Commission;
import seedu.siasa.model.policy.ExpiryDate;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.Price;
import seedu.siasa.model.policy.Title;


/**
 * Jackson-friendly version of {@link Policy}.
 */
public class JsonAdaptedPolicy {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Policy's %s field is missing!";

    private final String title;
    private final String price;
    private final String expiryDate;
    private final String commission;
    private final JsonAdaptedPerson owner;

    /**
     * Constructs a {@code JsonAdaptedPolicy} with the given policy details.
     */
    @JsonCreator
    public JsonAdaptedPolicy(@JsonProperty("title") String title, @JsonProperty("price") String price,
                             @JsonProperty("expiryDate") String expiryDate,
                             @JsonProperty("commission") String commission,
                             @JsonProperty("owner") JsonAdaptedPerson owner) {
        this.title = title;
        this.price = price;
        this.expiryDate = expiryDate;
        this.commission = commission;
        this.owner = owner;
    }

    /**
     * Converts a given {@code Policy} into this class for Jackson use.
     */
    public JsonAdaptedPolicy(Policy source) {
        title = source.getTitle().toString();
        price = source.getPrice().toString();
        expiryDate = source.getExpiryDate().toString();
        commission = source.getCommission().toString();
        owner = new JsonAdaptedPerson(source.getOwner());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Policy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted policy.
     */
    public Policy toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        try {
            if (price == null) {
                throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
            }

            if (!Price.isValidPrice((int) Double.parseDouble(price.substring(1)))) {
                throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
            }
        } catch (IllegalValueException | NumberFormatException e) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelPrice = new Price((int) Double.parseDouble(price.substring(1)));


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(expiryDate, formatter);
            if (expiryDate == null) {
                throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ExpiryDate.class.getSimpleName()));
            }

            if (!ExpiryDate.isValidExpiryDate(date)) {
                throw new IllegalValueException(ExpiryDate.MESSAGE_CONSTRAINTS);
            }
        } catch (IllegalValueException | DateTimeParseException e) {
            throw new IllegalValueException(ExpiryDate.MESSAGE_CONSTRAINTS);
        }

        LocalDate date = LocalDate.parse(expiryDate, formatter);
        final ExpiryDate modelExpiryDate = new ExpiryDate(date);

        try {
            if (commission == null) {
                throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Commission.class.getSimpleName()));
            }
            String temp2 = commission.substring(0, commission.length() - 1);
            if (!Commission.isValidCommission(Integer.parseInt(temp2))) {
                throw new IllegalValueException(Commission.MESSAGE_CONSTRAINTS);
            }
        } catch (IllegalValueException | NumberFormatException e) {
            throw new IllegalValueException(Commission.MESSAGE_CONSTRAINTS);
        }

        final Commission modelCommission =
            new Commission(Integer.parseInt(commission.substring(0, commission.length() - 1)));

        if (owner == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName()));
        }

        final Person modelOwner = owner.toModelType();

        return new Policy(modelTitle, modelPrice, modelExpiryDate, modelCommission, modelOwner);
    }
}
