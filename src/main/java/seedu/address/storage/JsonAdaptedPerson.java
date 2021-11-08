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
import seedu.address.model.done.Done;
import seedu.address.model.interview.Interview;
import seedu.address.model.notes.Notes;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmploymentType;
import seedu.address.model.person.ExpectedSalary;
import seedu.address.model.person.Experience;
import seedu.address.model.person.LevelOfEducation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String role;
    private final String employmentType;
    private final String expectedSalary;
    private final String levelOfEducation;
    private final String experience;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String interview;
    private final String notes;
    private final String done;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(
            @JsonProperty("name") String name,
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email,
            @JsonProperty("role") String role,
            @JsonProperty("employmentType") String employmentType,
            @JsonProperty("expectedSalary") String expectedSalary,
            @JsonProperty("levelOfEducation") String levelOfEducation,
            @JsonProperty("experience") String experience,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("interview") String interview,
            @JsonProperty("notes") String notes,
            @JsonProperty("done") String done) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.employmentType = employmentType;
        this.expectedSalary = expectedSalary;
        this.levelOfEducation = levelOfEducation;
        this.experience = experience;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.interview = interview;
        this.notes = notes;
        this.done = done;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        role = source.getRole().role;
        employmentType = source.getEmploymentType().employmentType;
        expectedSalary = source.getExpectedSalary().value;
        levelOfEducation = source.getLevelOfEducation().levelOfEducation;
        experience = source.getExperience().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        interview = source.getInterview().orElse(Interview.EMPTY_INTERVIEW).parseTime;
        notes = source.getNotes().orElse(Notes.EMPTY_NOTES).information;
        done = source.getDone().getDoneStatus();
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

        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName()));
        }
        if (!Role.isValidRole(role)) {
            throw new IllegalValueException(Role.MESSAGE_CONSTRAINTS);
        }
        final Role modelRole = new Role(role);

        if (employmentType == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, EmploymentType.class.getSimpleName()));
        }
        if (!EmploymentType.isValidEmploymentType(employmentType)) {
            throw new IllegalValueException(EmploymentType.MESSAGE_CONSTRAINTS);
        }
        final EmploymentType modelEmploymentType = new EmploymentType(employmentType);

        if (expectedSalary == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ExpectedSalary.class.getSimpleName()));
        }
        if (!ExpectedSalary.isValidExpectedSalary(expectedSalary)) {
            throw new IllegalValueException(ExpectedSalary.MESSAGE_CONSTRAINTS);
        }
        final ExpectedSalary modelExpectedSalary = new ExpectedSalary(expectedSalary);

        if (levelOfEducation == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, LevelOfEducation.class.getSimpleName()));
        }
        if (!LevelOfEducation.isValidLevelOfEducation(levelOfEducation)) {
            throw new IllegalValueException(LevelOfEducation.MESSAGE_CONSTRAINTS);
        }
        final LevelOfEducation modelLevelOfEducation = new LevelOfEducation(levelOfEducation);

        if (experience == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Experience.class.getSimpleName()));
        }
        if (!Experience.isValidExperience(experience)) {
            throw new IllegalValueException(Experience.MESSAGE_CONSTRAINTS);
        }
        final Experience modelExperience = new Experience(experience);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Optional<Interview> modelInterview;
        if (interview == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Interview.class.getSimpleName()));
        }
        if (interview.equals("-")) {
            modelInterview = Optional.ofNullable(Interview.EMPTY_INTERVIEW);
        } else {
            if (!Interview.isValidInterviewTime(interview)) {
                throw new IllegalValueException(Interview.MESSAGE_CONSTRAINTS);
            }
            modelInterview = Optional.ofNullable(new Interview(interview));
        }


        if (notes == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Interview.class.getSimpleName()));
        }
        final Optional<Notes> modelNotes = Optional.ofNullable(new Notes(notes));

        final Done modelDone = new Done(done);


        return new Person(modelName, modelPhone, modelEmail, modelRole, modelEmploymentType,
                modelExpectedSalary, modelLevelOfEducation, modelExperience, modelTags,
                modelInterview, modelNotes, modelDone);
    }

}
