package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.healthcondition.HealthCondition;
import seedu.address.model.person.Address;
import seedu.address.model.person.Frequency;
import seedu.address.model.person.Language;
import seedu.address.model.person.LastVisit;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occurrence;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Visit;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String language;
    private final String address;
    private final String lastVisit;
    private final String visit;
    private final String frequency;
    private final String occurrence;
    private final List<JsonAdaptedHealthCondition> healthConditions = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("language") String language, @JsonProperty("address") String address,
            @JsonProperty("lastVisit") String lastVisit, @JsonProperty("visit") String visit,
            @JsonProperty("frequency") String frequency, @JsonProperty("occurrence") String occurrence,
            @JsonProperty("healthConditions") List<JsonAdaptedHealthCondition> healthConditions) {
        this.name = name;
        this.phone = phone;
        this.language = language;
        this.address = address;
        this.lastVisit = lastVisit;
        this.visit = visit;
        this.frequency = frequency;
        this.occurrence = occurrence;
        if (healthConditions != null) {
            this.healthConditions.addAll(healthConditions);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        language = source.getLanguage().value;
        address = source.getAddress().value;
        lastVisit = source.getLastVisit().orElse(new LastVisit("")).value;
        visit = source.getVisit().orElse(new Visit("")).value;
        frequency = source.getFrequency().orElse(Frequency.EMPTY).value;
        occurrence = String.valueOf(source.getOccurrence().orElse(new Occurrence(1)).value);
        healthConditions.addAll(source.getHealthConditions().stream()
                .map(JsonAdaptedHealthCondition::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<HealthCondition> personHealthConditions = new ArrayList<>();
        for (JsonAdaptedHealthCondition healthCondition : healthConditions) {
            personHealthConditions.add(healthCondition.toModelType());
        }

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

        if (language == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Language.class.getSimpleName()));
        }
        if (!Language.isValidLanguage(language)) {
            throw new IllegalValueException(Language.MESSAGE_CONSTRAINTS);
        }
        final Language modelLanguage = new Language(language);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (lastVisit == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Visit.class.getSimpleName()));
        }
        final Optional<LastVisit> modelLastVisit = Optional.ofNullable(new LastVisit(lastVisit));

        if (visit == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Visit.class.getSimpleName()));
        }
        final Optional<Visit> modelVisit = Optional.ofNullable(new Visit(visit));

        if (frequency == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Frequency.class.getSimpleName()));
        }
        if (!Frequency.isValidFrequency(frequency)) {
            throw new IllegalValueException(Frequency.MESSAGE_CONSTRAINTS);
        }
        final Optional<Frequency> modelFrequency = Optional.ofNullable(Frequency.find(frequency));

        if (occurrence == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Occurrence.class.getSimpleName()));
        }
        if (!Occurrence.isValidOccurrence(occurrence)) {
            throw new IllegalValueException(Frequency.MESSAGE_CONSTRAINTS);
        }
        // TODO: abstract all convert occurrence from string to int into a method
        int convertedOccurrence = Integer.parseInt(occurrence);
        final Optional<Occurrence> modelOccurrence = Optional.ofNullable(new Occurrence(convertedOccurrence));

        final Set<HealthCondition> modelHealthConditions = new HashSet<>(personHealthConditions);
        return new Person(modelName, modelPhone, modelLanguage, modelAddress, modelLastVisit, modelVisit,
                modelFrequency, modelOccurrence, modelHealthConditions);
    }

}
