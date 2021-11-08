package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddEmployeeCommand;
import seedu.address.logic.commands.EditEmployeeCommand.EditEmployeeDescriptor;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.person.employee.Shift;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class EmployeeUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddEmployeeCommand(Employee employee) {
        return AddEmployeeCommand.COMMAND_WORD + " " + getEmployeeDetails(employee);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getEmployeeDetails(Employee employee) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + employee.getName().fullName + " ");
        sb.append(PREFIX_PHONE + employee.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + employee.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + employee.getAddress().value + " ");
        sb.append(PREFIX_LEAVES + employee.getLeaves().currentLeaves + " ");
        sb.append(PREFIX_SALARY + employee.getSalary().currentSalary + " ");
        sb.append(PREFIX_JOB_TITLE + employee.getJobTitle().jobTitle + " ");
        employee.getTags().stream().forEach(s ->
                sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditEmployeeDescriptor}'s details.
     */
    public static String getEditEmployeeDescriptorDetails(EditEmployeeDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getLeaves().ifPresent(leaves -> sb.append(PREFIX_LEAVES).append(leaves.currentLeaves).append(" "));
        descriptor.getSalary().ifPresent(salary -> sb.append(PREFIX_SALARY).append(salary.currentSalary).append(" "));
        descriptor.getJobTitle().ifPresent(jobTitle -> sb.append(PREFIX_JOB_TITLE).append(jobTitle.jobTitle)
                                                                                    .append(" "));
        if (descriptor.getShifts().isPresent()) {
            Set<Shift> shifts = descriptor.getShifts().get();
            if (shifts.isEmpty()) {
                sb.append(PREFIX_SHIFT).append(" ");
            } else {
                shifts.forEach(s -> sb.append(PREFIX_SHIFT).append(s.shiftString).append(" "));
            }
        }
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
