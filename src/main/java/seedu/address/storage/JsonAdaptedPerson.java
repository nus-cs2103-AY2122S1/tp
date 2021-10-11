package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String gitHubId;
    private final String nusNetworkId;
    private final String type;
    private final String studentId;
    private final String tutorialId;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged, @JsonProperty("gitHubId") String gitHubId,
            @JsonProperty("nusNetworkId") String nusNetworkId,@JsonProperty("type") String type,
            @JsonProperty("studentId") String studentId, @JsonProperty("tutorialId") String tutorialId) {

        this.name = name;
        this.email = email;
        this.gitHubId = gitHubId;
        this.nusNetworkId = nusNetworkId;
        this.type = type;
        this.studentId = studentId;
        this.tutorialId = tutorialId;
        this.phone = phone;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }

    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        gitHubId = source.getGitHubId().value;
        nusNetworkId = source.getNusNetworkId().value;
        type = source.getType().value;
        studentId = source.getStudentId().value;
        tutorialId = source.getTutorialId().value;
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

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (gitHubId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GitHubId.class.getSimpleName()));
        }
        if (!GitHubId.isValidGitHubId(gitHubId)) {
            throw new IllegalValueException(GitHubId.MESSAGE_CONSTRAINTS);
        }
        final GitHubId modelGitHubId = new GitHubId(gitHubId);

        if (nusNetworkId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NusNetworkId.class.getSimpleName()));
        }
        if (!NusNetworkId.isValidNusNetworkId(nusNetworkId)) {
            throw new IllegalValueException(NusNetworkId.MESSAGE_CONSTRAINTS);
        }
        final NusNetworkId modelNusNetworkId = new NusNetworkId(nusNetworkId);

        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Type.class.getSimpleName()));
        }
        if (!Type.isValidType(type)) {
            throw new IllegalValueException(Type.MESSAGE_CONSTRAINTS);
        }
        final Type modelType = new Type(type);

        if (studentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidStudentId(studentId)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelStudentId = new StudentId(studentId);

        if (tutorialId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TutorialId.class.getSimpleName()));
        }
        if (!TutorialId.isValidTutorialId(tutorialId)) {
            throw new IllegalValueException(TutorialId.MESSAGE_CONSTRAINTS);
        }
        final TutorialId modelTutorialId = new TutorialId(tutorialId);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelTags, modelGitHubId, modelNusNetworkId,
                modelType, modelStudentId, modelTutorialId);
    }

}
