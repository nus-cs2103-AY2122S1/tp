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
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.skill.Framework;
import seedu.address.model.skill.Language;
import seedu.address.model.skill.Skill;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String email;
    private final String faculty;
    private final String major;

    private final List<JsonAdaptedSkill> skills = new ArrayList<>();
    private final List<JsonAdaptedLanguage> languages = new ArrayList<>();
    private final List<JsonAdaptedFramework> frameworks = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("email") String email,
                             @JsonProperty("faculty") String faculty,
                             @JsonProperty("major") String major,
                             @JsonProperty("skills") List<JsonAdaptedSkill> skills,
                             @JsonProperty("languages") List<JsonAdaptedLanguage> languages,
                             @JsonProperty("frameworks") List<JsonAdaptedFramework> frameworks,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.email = email;
        this.faculty = faculty;
        this.major = major;

        if (skills != null) {
            this.skills.addAll(skills);
        }

        if (languages != null) {
            this.languages.addAll(languages);
        }

        if (frameworks != null) {
            this.frameworks.addAll(frameworks);
        }

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        email = source.getEmail().value;
        faculty = source.getFaculty().value;
        major = source.getMajor().value;

        skills.addAll(source.getSkills().stream()
                .map(JsonAdaptedSkill::new)
                .collect(Collectors.toList()));

        languages.addAll(source.getLanguages().stream()
                .map(JsonAdaptedLanguage::new)
                .collect(Collectors.toList()));

        frameworks.addAll(source.getFrameworks().stream()
                .map(JsonAdaptedFramework::new)
                .collect(Collectors.toList()));

        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Skill> personSkills = new ArrayList<>();
        for (JsonAdaptedSkill skill : skills) {
            personSkills.add(skill.toModelType());
        }

        final List<Language> personLanguages = new ArrayList<>();
        for (JsonAdaptedLanguage language : languages) {
            personLanguages.add(language.toModelType());
        }

        final List<Framework> personFrameworks = new ArrayList<>();
        for (JsonAdaptedFramework framework : frameworks) {
            personFrameworks.add(framework.toModelType());
        }

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

        if (faculty == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Faculty.class.getSimpleName()));
        }
        if (!Faculty.isValidFaculty(faculty)) {
            throw new IllegalValueException(Faculty.MESSAGE_CONSTRAINTS);
        }
        final Faculty modelFaculty = new Faculty(faculty);

        if (major == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Major.class.getSimpleName()));
        }
        if (!Major.isValidMajor(major)) {
            throw new IllegalValueException(Major.MESSAGE_CONSTRAINTS);
        }
        final Major modelMajor = new Major(major);

        final Set<Skill> modelSkills = new HashSet<>(personSkills);
        final Set<Language> modelLanguages = new HashSet<>(personLanguages);
        final Set<Framework> modelFrameworks = new HashSet<>(personFrameworks);
        final Set<Tag> modelTags = new HashSet<>(personTags);


        return new Person(modelName, modelEmail, modelFaculty, modelMajor,
                modelSkills, modelLanguages, modelFrameworks, modelTags);
    }

}
