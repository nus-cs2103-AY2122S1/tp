package seedu.modulink.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.modulink.commons.exceptions.IllegalValueException;
import seedu.modulink.model.person.Email;
import seedu.modulink.model.person.GitHubUsername;
import seedu.modulink.model.person.Name;
import seedu.modulink.model.person.Person;
import seedu.modulink.model.person.Phone;
import seedu.modulink.model.person.StudentId;
import seedu.modulink.model.person.TelegramHandle;
import seedu.modulink.model.tag.Mod;


/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String id;
    private final String phone;
    private final String email;
    private final String gitHubUsername;
    private final String telegramHandle;
    private final boolean isFavourite;
    private final List<JsonAdaptedTag> modules = new ArrayList<>();
    private final boolean isMyProfile;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
            @JsonProperty("id") String id, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("gitHubUsername") String gitHubUsername,
            @JsonProperty("telegramHandle") String telegramHandle, @JsonProperty("isFavourite") boolean isFavourite,
            @JsonProperty("modules") List<JsonAdaptedTag> modules, @JsonProperty("isMyProfile") boolean isMyProfile) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.gitHubUsername = gitHubUsername;
        this.telegramHandle = telegramHandle;
        this.isFavourite = isFavourite;
        if (modules != null) {
            this.modules.addAll(modules);
        }
        this.isMyProfile = isMyProfile;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        id = source.getStudentId().value;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        gitHubUsername = source.getGithubUsername().value;
        telegramHandle = source.getTelegramHandle().value;
        isFavourite = source.getIsFavourite();
        modules.addAll(source.getMods().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        isMyProfile = source.getIsMyProfile();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Mod> personMods = new ArrayList<>();
        for (JsonAdaptedTag tag : modules) {
            personMods.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidId(id)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelId = new StudentId(id);

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

        if (!GitHubUsername.isValidUsername(gitHubUsername)) {
            throw new IllegalValueException(GitHubUsername.MESSAGE_CONSTRAINTS);
        }
        final GitHubUsername modelUsername = new GitHubUsername(gitHubUsername);

        if (!TelegramHandle.isValidHandle(telegramHandle)) {
            throw new IllegalValueException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        final TelegramHandle modelHandle = new TelegramHandle(telegramHandle);

        final Set<Mod> modelMods = new HashSet<>(personMods);
        return new Person(modelName, modelId, modelPhone, modelEmail,
                modelUsername, modelHandle, this.isFavourite, modelMods, this.isMyProfile);
    }

}
