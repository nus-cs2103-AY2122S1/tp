package seedu.edrecord.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.module.ModuleSet;
import seedu.edrecord.model.name.Name;
import seedu.edrecord.model.person.AssignmentGradeMap;
import seedu.edrecord.model.person.Email;
import seedu.edrecord.model.person.Info;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.model.person.Phone;
import seedu.edrecord.model.tag.Tag;
import seedu.edrecord.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_INFO = "2 weeks ahead in assignments";
    public static final String DEFAULT_MODULE = "CS2103";
    public static final String DEFAULT_GROUP = "T03";

    private Name name;
    private Phone phone;
    private Email email;
    private Info info;
    private ModuleSet modules;
    private Set<Tag> tags;
    private final AssignmentGradeMap grades = new AssignmentGradeMap();


    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        info = new Info(DEFAULT_INFO);

        Module module = new Module(DEFAULT_MODULE);
        Group group = new Group(DEFAULT_GROUP);
        modules = new ModuleSet();
        modules.add(module, group);

        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        info = personToCopy.getInfo();
        modules = new ModuleSet();
        modules.addAll(personToCopy.getModules());
        tags = new HashSet<>(personToCopy.getTags());
        grades.addAll(personToCopy.getGrades());
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
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Info} of the {@code Person} that we are building.
     */
    public PersonBuilder withInfo(String info) {
        this.info = new Info(info);
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
     * Sets the {@code Module} of the {@code Person} that we are building.
     */
    public PersonBuilder withModuleAndGroup(String mod, String grp) {
        Group group = new Group(grp);
        Module module = new Module(mod);
        this.modules = new ModuleSet();
        modules.add(module, group);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, info, modules, tags, grades);
    }

}
