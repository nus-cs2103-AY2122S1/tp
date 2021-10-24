package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.SocialHandle;
import seedu.address.model.person.TutorialGroup;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String nationality;
    private final String tutorialGroup;
    private final String gender;
    private final String remark;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedSocialHandle> socialHandles = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
                             @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email,
                             @JsonProperty("nationality") String nationality,
                             @JsonProperty("tutorialGroup") String tutorialGroup,
                             @JsonProperty("gender") String gender,
                             @JsonProperty("remark") String remark,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("socialHandles") List<JsonAdaptedSocialHandle> socialHandles) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nationality = nationality;
        this.tutorialGroup = tutorialGroup;
        this.gender = gender;
        this.remark = remark;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (socialHandles != null) {
            this.socialHandles.addAll(socialHandles);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        nationality = source.getNationality().value;
        tutorialGroup = source.getTutorialGroup().value;
        gender = source.getGender().gender;
        remark = source.getRemark().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        socialHandles.addAll(source.getSocialHandles().stream()
                .map(JsonAdaptedSocialHandle::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
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

        if (nationality == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Nationality.class.getSimpleName()));
        }
        if (!Nationality.isValidNationality(nationality)) {
            throw new IllegalValueException(Nationality.MESSAGE_CONSTRAINTS);
        }
        final Nationality modelNationality = new Nationality(nationality);

        if (tutorialGroup == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TutorialGroup.class.getSimpleName()));
        }
        if (!TutorialGroup.isValidTutorialGroup(tutorialGroup)) {
            throw new IllegalValueException(TutorialGroup.MESSAGE_CONSTRAINTS);
        }
        final TutorialGroup modelTutorialGroup = new TutorialGroup(tutorialGroup);

        if (gender == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (remark == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        if (!Remark.isValidRemark(remark)) {
            throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
        }
        final Remark modelRemark = new Remark(remark);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final List<SocialHandle> personSocialHandles = new ArrayList<>();
        for (JsonAdaptedSocialHandle socialHandle : socialHandles) {
            personSocialHandles.add(socialHandle.toModelType());
        }
        final Set<SocialHandle> modelSocialHandles = new HashSet<>(personSocialHandles);

        return new Person(modelName, modelPhone, modelEmail, modelNationality,
                modelTutorialGroup, modelGender, modelRemark, modelTags, modelSocialHandles);
    }

}
