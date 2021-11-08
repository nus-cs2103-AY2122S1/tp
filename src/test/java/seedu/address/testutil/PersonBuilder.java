package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Module;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_MODULE = "CS2103T";

    private Name name;
    private Email email;
    private Module module;
    private List<Assignment> assignmentList;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        email = new Email(DEFAULT_EMAIL);
        module = new Module(DEFAULT_MODULE);
        assignmentList = new ArrayList<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        email = personToCopy.getEmail();
        module = personToCopy.getModule();
        tags = new HashSet<>(personToCopy.getTags());

        assignmentList = new ArrayList<>();
        for (Assignment assignment: personToCopy.getAssignments()) {
            assignmentList.add(assignment);
        }
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
     * Sets the {@code Module} of the {@code Person} that we are building.
     */
    public PersonBuilder withModule(String address) {
        this.module = new Module(address);
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
     * Parses the {@code assignmentList} into a {@code List<Assignment>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withAssignmentList(String[]... assignmentList) {
        this.assignmentList = SampleDataUtil.getAssignmentList(assignmentList);
        return this;
    }

    public Person build() {
        return new Person(name, email, module, assignmentList, tags);
    }

}
