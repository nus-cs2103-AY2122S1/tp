package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

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
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_NATIONALITY = "Singaporean";
    public static final String DEFAULT_TUTORIAL_GROUP = "T01";
    public static final String DEFAULT_REMARK = "She likes aardvarks.";

    private Name name;
    private Gender gender;
    private Phone phone;
    private Email email;
    private Nationality nationality;
    private TutorialGroup tutorialGroup;
    private Remark remark;
    private Set<Tag> tags;
    private Set<SocialHandle> socialHandles;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        gender = new Gender(DEFAULT_GENDER);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        nationality = new Nationality(DEFAULT_NATIONALITY);
        tutorialGroup = new TutorialGroup(DEFAULT_TUTORIAL_GROUP);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
        socialHandles = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        gender = personToCopy.getGender();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        nationality = personToCopy.getNationality();
        tutorialGroup = personToCopy.getTutorialGroup();
        remark = personToCopy.getRemark();
        tags = new HashSet<>(personToCopy.getTags());
        socialHandles = new HashSet<>(personToCopy.getSocialHandles());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code socialHandles} into a {@code Set<SocialHandle>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withSocialHandles(String ... socialHandles) {
        this.socialHandles = SampleDataUtil.getSocialHandleSet(socialHandles);
        return this;
    }

    /**
     * Sets the {@code Nationality} of the {@code Person} that we are building.
     */
    public PersonBuilder withNationality(String nationality) {
        this.nationality = new Nationality(nationality);
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
     * Sets the {@code TutorialGroup} of the {@code Person} that we are building.
     */
    public PersonBuilder withTutorialGroup(String tutorialGroup) {
        this.tutorialGroup = new TutorialGroup(tutorialGroup);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Returns a new {@code Person} that we are building.
     */
    public Person build() {
        return new Person(name, phone, email, nationality, tutorialGroup,
                gender, remark, tags, socialHandles);
    }

}
