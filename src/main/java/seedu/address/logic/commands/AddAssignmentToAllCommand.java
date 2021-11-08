package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Module;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_MODULE;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Adds an assignment to all persons' assignment list if under specified module.
 */
public class AddAssignmentToAllCommand extends Command {

    public static final String COMMAND_WORD = "giveall";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an assignment to assignment list of all persons under same module "
            + "Parameters: "
            + PREFIX_MODULE + "MODULE "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DUEDATE + "DUEDATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS2100 "
            + PREFIX_DESCRIPTION + "assignment2 "
            + PREFIX_DUEDATE + " 11/11/2021 ";

    public static final String MESSAGE_SUCCESS = "New assignment added to all persons in %1$s: %2$s";
    public static final String MESSAGE_ALL_HAS_ASSIGNMENT = "All persons in %1$s has the specified assignment already!";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT_DIFFERENT_DUE_DATE =
            "Please make sure that the deadline is consistent!\n"
            + "Your deadline input: %1s\n"
            + "Existing assignment deadline: %2$s";

    private final Assignment toAdd;
    private final Module module;

    /**
     * Creates an AddAssignmentToAllCommand to add the specified {@code Assignment}
     */
    public AddAssignmentToAllCommand(Module module, Assignment assignment) {
        requireNonNull(assignment);
        requireNonNull(module);
        toAdd = assignment;
        this.module = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // Get Person that match the Module
        List<Person> personListInModule =
                model.getAddressBook().getPersonList()
                        .stream()
                        .filter(person -> person.hasModule(module))
                        .collect(Collectors.toList());

        if (personListInModule.size() == 0) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_MODULE);
        }

        //Split the persons into those with or without the assignment
        List<List<Person>> filteredPersonList = new ArrayList<>(personListInModule
                .stream()
                .collect(Collectors.partitioningBy(person -> model.hasAssignment(person, toAdd))).values());

        List<Person> personListWithoutAssignment = filteredPersonList.get(0);
        List<Person> personListWithAssignment = filteredPersonList.get(1);

        if (personListWithoutAssignment.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_ALL_HAS_ASSIGNMENT, module));
        }

        Assignment existingAssignment = getAssignmentIfExists(personListWithAssignment);

        // Create a new assignment with the same description as the existing one
        // to prevent inconsistencies in letter cases
        Assignment standardisedAssignment = new Assignment(existingAssignment.getDescription(),
                toAdd.getDueDate(), toAdd.getStatus());
        model.addAllAssignments(personListWithoutAssignment, standardisedAssignment);

        return new CommandResult(String.format(MESSAGE_SUCCESS, module, standardisedAssignment));
    }


    /**
     * Checks if there are persons with {@code toAdd} and returns it
     *
     * @param personListWithAssignment The list of persons that has the assignment
     * @return The existing assignment if any persons have the assignment or {@code toAdd}
     * @throws CommandException Indicates that the due dates of
     * existing assignment and {@code toAdd} are different
     */
    public Assignment getAssignmentIfExists(List<Person> personListWithAssignment) throws CommandException {
        if (!personListWithAssignment.isEmpty()) {
            Assignment existingAssignment = personListWithAssignment.get(0).getAssignments()
                    .getAssignment(toAdd.getDescription());
            if (!existingAssignment.isSameDueDate(toAdd)) {
                throw new CommandException(String.format(MESSAGE_DUPLICATE_ASSIGNMENT_DIFFERENT_DUE_DATE,
                        toAdd.getDueDate(), existingAssignment.getDueDate()));
            }
            return existingAssignment;
        } else {
            return toAdd;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAssignmentToAllCommand // instanceof handles nulls
                && toAdd.equals(((AddAssignmentToAllCommand) other).toAdd)
                && module.equals(((AddAssignmentToAllCommand) other).module));
    }
}
