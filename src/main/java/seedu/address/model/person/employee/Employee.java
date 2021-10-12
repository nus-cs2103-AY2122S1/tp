package seedu.address.model.person.employee;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents an Employee in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Employee extends Person {
    private Leaves leaves;
    private Salary salary;
    private JobTitle jobTitle;

    /**
     * Constructor of the Employee class.
     */
    public Employee(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Leaves leaves,
                    Salary salary, JobTitle jobTitle) {
        super(name, phone, email, address, tags);
        this.leaves = leaves;
        this.salary = salary;
        this.jobTitle = jobTitle;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public Leaves getLeaves() {
        return leaves;
    }

    public Salary getSalary() {
        return salary;
    }

    public Name getName() {
        return super.getName();
    }

    public Phone getPhone() {
        return super.getPhone();
    }

    public Email getEmail() {
        return super.getEmail();
    }

    public Address getAddress() {
        return super.getAddress();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return super.getTags();
    }

    /**
     * Returns true if both employees have the same name.
     * This defines a weaker notion of equality between two employees.
     */
    public boolean isSameEmployee(Employee otherEmployee) {
        if (otherEmployee == this) {
            return true;
        }

        return otherEmployee != null
                && otherEmployee.getName().equals(getName());
    }

    /**
     * Returns true if both employees have the same identity and data fields.
     * This defines a stronger notion of equality between two employees.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Employee)) {
            return false;
        }

        Employee otherEmployee = (Employee) other;
        return otherEmployee.getName().equals(getName())
                && otherEmployee.getPhone().equals(getPhone())
                && otherEmployee.getEmail().equals(getEmail())
                && otherEmployee.getAddress().equals(getAddress())
                && otherEmployee.getLeaves().equals(getLeaves())
                && otherEmployee.getSalary().equals(getSalary())
                && otherEmployee.getJobTitle().equals(getJobTitle())
                && otherEmployee.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return super.hashCode() + Objects.hash(leaves, salary, jobTitle);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Leaves: ")
                .append(getLeaves())
                .append("; Salary: ")
                .append(getSalary())
                .append("; Job Title: ")
                .append(getJobTitle());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
