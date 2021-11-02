package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import seedu.address.model.id.UniqueId;
import seedu.address.model.lesson.NoOverlapLessonList;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Exam;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private UniqueId id;
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Set<UniqueId> assignedTaskIds;
    private Map<UniqueId, Boolean> tasksCompletion;
    private NoOverlapLessonList lessonsList;
    private List<Exam> exams;
    private Set<UniqueId> assignedGroupIds;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        id = UniqueId.generateId(UUID.randomUUID().toString());
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        assignedTaskIds = new HashSet<>();
        tasksCompletion = new HashMap<>();
        lessonsList = new NoOverlapLessonList();
        exams = new ArrayList<>();
        assignedGroupIds = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        id = personToCopy.getId();
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        assignedTaskIds = new HashSet<>(personToCopy.getAssignedTaskIds());
        tasksCompletion = new HashMap<>(personToCopy.getTasksCompletion());
        lessonsList = personToCopy.getLessonsList();
        exams = new ArrayList<>(personToCopy.getExams());
        assignedGroupIds = new HashSet<>(personToCopy.getAssignedGroupIds());
    }

    /**
     * Sets the {@code Id} of the {@code Person} that we are building.
     */
    public PersonBuilder withId(String id) {
        this.id = UniqueId.generateId(id);
        return this;
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
     * Parses the {@code ids} into a {@code Set<UniqueId>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withAssignedTaskIds(String ... assignedTaskIds) {
        this.assignedTaskIds = SampleDataUtil.getUniqueIdSet(assignedTaskIds);
        return this;
    }

    /**
     * Parses the {@code ids} into a {@code Set<UniqueId>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withAssignedGroupIds(String ... assignedGroupIds) {
        this.assignedGroupIds = SampleDataUtil.getUniqueIdSet(assignedGroupIds);
        return this;
    }

    /**
     * Copies the task completion map into {@code tasksCompletion} of the {@code Person} that we are building.
     */
    public PersonBuilder withTasksCompletion(Map<UniqueId, Boolean> tasksCompletion) {
        this.tasksCompletion = new HashMap<>(tasksCompletion);
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
     * Sets the {@code NoOverlapLessonsList} of the {@code Person} that we are building.
     */
    public PersonBuilder withLessonsList(NoOverlapLessonList lessonsList) {
        this.lessonsList = lessonsList;
        return this;
    }

    /**
     * Sets the {@code List<Exam} of the {@code Person} that we are building.
     */
    public PersonBuilder withExams(List<Exam> exams) {
        this.exams = new ArrayList<>(exams);
        return this;
    }

    /**
     * Builds a {@code Person} object from the {@code PersonBuilder}.
     *
     * @return A {@code Person} object.
     */
    public Person build() {
        return new Person(id, name, phone, email, address, tags, assignedTaskIds, tasksCompletion,
                lessonsList, exams, assignedGroupIds);
    }

}
