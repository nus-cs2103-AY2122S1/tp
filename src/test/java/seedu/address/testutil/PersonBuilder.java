package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitHubId;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetworkId;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TutorialId;
import seedu.address.model.person.Type;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "e0123456@u.nus.edu";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_GITHUB_ID = "amy-bee";
    public static final String DEFAULT_NUS_NETWORK_ID = "e0123456";
    public static final String DEFAULT_TYPE = "student";
    public static final String DEFAULT_STUDENT_ID = "A0123456X";
    public static final String DEFAULT_TUTORIAL_ID = "00";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private GitHubId gitHubId;
    private NusNetworkId nusNetworkId;
    private Type type;
    private StudentId studentId;
    private TutorialId tutorialId;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        gitHubId = new GitHubId(DEFAULT_GITHUB_ID);
        nusNetworkId = new NusNetworkId(DEFAULT_NUS_NETWORK_ID);
        type = new Type(DEFAULT_TYPE);
        studentId = new StudentId(DEFAULT_STUDENT_ID);
        tutorialId = new TutorialId(DEFAULT_TUTORIAL_ID);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        gitHubId = personToCopy.getGitHubId();
        nusNetworkId = personToCopy.getNusNetworkId();
        type = personToCopy.getType();
        studentId = personToCopy.getStudentId();
        tutorialId = personToCopy.getTutorialId();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
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
     * Sets the {@code GitHubId} of the {@code Person} that we are building.
     */
    public PersonBuilder withGitHubId(String gitHubId) {
        this.gitHubId = new GitHubId(gitHubId);
        return this;
    }

    /**
     * Sets the {@code NusNetworkId} of the {@code Person} that we are building.
     */
    public PersonBuilder withNusNetworkId(String nusNetworkId) {
        this.nusNetworkId = new NusNetworkId(nusNetworkId);
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code Person} that we are building.
     */
    public PersonBuilder withType(String type) {
        this.type = new Type(type);
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code Person} that we are building.
     */
    public PersonBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    /**
     * Sets the {@code TutorialId} of the {@code Person} that we are building.
     */
    public PersonBuilder withTutorialId(String tutorialId) {
        this.tutorialId = new TutorialId(tutorialId);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, tags, gitHubId, nusNetworkId, type, studentId, tutorialId);
    }

}
