package seedu.address.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.LessonCode;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.ParentContact;
import seedu.address.model.person.Student;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String grade;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<String> lessonCodes = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("grade") String grade, @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("lessons") List<String> lessonCodes) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.grade = grade;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (lessonCodes != null) {
            this.lessonCodes.addAll(lessonCodes);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Student source) {
        name = source.getName().fullName;
        phone = source.getParentContact().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        grade = source.getGrade().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        lessonCodes.addAll(source.getLessonCodes().stream()
                .map(LessonCode::toString)
                .collect(Collectors.toList()));
    }

    /**
     * Returns the list of lesson codes the encoded Student is enrolled to.
     */
    public List<String> getLessonCodes() {
        return Collections.unmodifiableList(lessonCodes);
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     * Lesson codes will only be attached in {@link JsonAddressBookStorage} when it can interact
     * with both lesson and students.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ParentContact.class.getSimpleName()));
        }
        if (!ParentContact.isValidPhone(phone)) {
            throw new IllegalValueException(ParentContact.MESSAGE_CONSTRAINTS);
        }
        final ParentContact modelParentContact = new ParentContact(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (grade == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName()));
        }
        if (!Grade.isValidGrade(grade)) {
            throw new IllegalValueException(Grade.GRADE_MESSAGE_CONSTRAINTS);
        }
        final Grade modelGrade = new Grade(grade);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Student(modelName, modelParentContact, modelEmail, modelAddress, modelGrade, modelTags);
    }
}
