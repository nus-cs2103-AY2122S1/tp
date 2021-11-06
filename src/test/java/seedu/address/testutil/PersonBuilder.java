package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Period;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.Salary;
import seedu.address.model.person.Schedule;
import seedu.address.model.person.Status;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_SALARY = "9";
    public static final String DEFAULT_ROLE = "floor";
    public static final String DEFAULT_STATUS = "parttime";

    private Name name;
    private Phone phone;
    private Email email;
    private Set<Tag> tags;
    private Set<Role> roles;
    private Salary salary;
    private Status status;
    private Schedule schedule;
    private Set<Period> absentDates;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        tags = new HashSet<>();
        absentDates = new HashSet<>();
        roles = new HashSet<>();
        roles.add(Role.translateStringToRole(DEFAULT_ROLE));
        salary = new Salary(DEFAULT_SALARY);
        status = Status.translateStringToStatus(DEFAULT_STATUS);
        schedule = new Schedule();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        tags = new HashSet<>(personToCopy.getTags());
        roles = new HashSet<>(personToCopy.getRoles());
        salary = personToCopy.getSalary();
        status = personToCopy.getStatus();
        absentDates = personToCopy.getAbsentDates();
        schedule = personToCopy.getSchedule();
    }

    /**
     * Parses the {@code periods} into a {@code Set<Period>} and set it to the {@code Person}
     * that we are building.
     */
    public PersonBuilder withAbsentDates(String ... periods) {
        this.absentDates = SampleDataUtil.getPeriodSet(periods);
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
     * Parses the {@code roles} into a {@code Set<Role>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withRoles(String ... roles) {
        this.roles = SampleDataUtil.getRoleSet(roles);
        return this;
    }

    /**
     * Sets the {@code Salary} of the {@code Person} that we are building.
     */
    public PersonBuilder withSalary(String salary) {
        this.salary = new Salary(salary);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Person} that we are building.
     */
    public PersonBuilder withStatus(String status) {
        this.status = Status.translateStringToStatus(status);
        return this;
    }

    /**
     * Sets the {@code Schedule} of the {@code Person} that we are building.
     */
    public PersonBuilder withSchedule(ScheduleBuilder builder) {
        this.schedule = builder.build();
        return this;
    }

    /**
     * Returns a new Person object with the fields set in this PersonBuilder object.
     */
    public Person build() {
        Person builtPerson = new Person(name, phone, email, roles, salary, status, tags, absentDates);
        builtPerson.setSchedule(schedule);
        return builtPerson;
    }
}
