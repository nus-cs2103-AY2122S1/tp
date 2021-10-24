package seedu.modulink.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.modulink.model.person.Email;
import seedu.modulink.model.person.GitHubUsername;
import seedu.modulink.model.person.Name;
import seedu.modulink.model.person.Person;
import seedu.modulink.model.person.Phone;
import seedu.modulink.model.person.StudentId;
import seedu.modulink.model.person.TelegramHandle;
import seedu.modulink.model.tag.Mod;
import seedu.modulink.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_ID = "A1234567Z";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_GITHUB_USERNAME = "amybee";
    public static final String DEFAULT_TELEGRAM_HANDLE = "amybee22";
    public static final boolean DEFAULT_IS_FAVOURITE = false;
    public static final boolean DEFAULT_IS_MY_PROFILE = false;

    private Name name;
    private StudentId id;
    private Phone phone;
    private Email email;
    private GitHubUsername gitHubUsername;
    private TelegramHandle telegramHandle;
    private boolean isFavourite;
    private Set<Mod> mods;
    private boolean isMyProfile;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        id = new StudentId(DEFAULT_ID);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        gitHubUsername = new GitHubUsername(DEFAULT_GITHUB_USERNAME);
        telegramHandle = new TelegramHandle(DEFAULT_TELEGRAM_HANDLE);
        isFavourite = DEFAULT_IS_FAVOURITE;
        mods = new HashSet<>();
        isMyProfile = DEFAULT_IS_MY_PROFILE;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        id = personToCopy.getStudentId();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        gitHubUsername = personToCopy.getGithubUsername();
        telegramHandle = personToCopy.getTelegramHandle();
        isFavourite = personToCopy.getIsFavourite();
        mods = new HashSet<>(personToCopy.getMods());
        isMyProfile = personToCopy.getIsMyProfile();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code Person} that we are building.
     */
    public PersonBuilder withStudentId(String studentId) {
        this.id = new StudentId(studentId);
        return this;
    }

    /**
     * Parses the {@code mods} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... mods) {
        this.mods = SampleDataUtil.getTagSet(mods);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code GitHubUsername} of the {@code Person} that we are building.
     */
    public PersonBuilder withGitHubUsername(String gitHubUsername) {
        this.gitHubUsername = new GitHubUsername(gitHubUsername);
        return this;
    }

    /**
     * Sets the {@code TelegramHandle} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegramHandle(String telegramHandle) {
        this.telegramHandle = new TelegramHandle(telegramHandle);
        return this;
    }

    /**
     * Sets the {@code isFavourite} of the {@code Person} that we are building.
     */
    public PersonBuilder withFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
        return this;
    }

    /**
     * Builds a {@Code Person} object
     *
     */
    public Person build() {
        return new Person(name, id, phone, email,
                gitHubUsername, telegramHandle, isFavourite, mods, isMyProfile);
    }

    /**
     * Builds the user's {@Code Person} personal profile
     *
     */
    public Person buildProfile() {
        return new Person(name, id, phone, email,
                gitHubUsername, telegramHandle, isFavourite, mods, true);
    }

}
