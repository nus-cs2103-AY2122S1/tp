package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;
import seedu.address.model.person.Status;

/**
 * Deletes a person identified using it's displayed index or name from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final int INVALID_INDEX = -1;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number or name used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) OR NAME but not both\n"
            + "Examples: " + COMMAND_WORD + " " + PREFIX_INDEX + "1\n"
            + COMMAND_WORD + " " + PREFIX_NAME + "Alex Yeoh";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_PEOPLE_SUCCESS = "Deleted these people:\n\n";

    private Index targetIndex = null;
    private Name name = null;
    private Role role = null;
    private Status status = null;

    /**
     * Constructs delete command using a targetIndex.
     *
     * @param targetIndex The index of a person to be deleted
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Constructs delete command using a name.
     *
     * @param name The name of a person to be deleted
     */
    public DeleteCommand(Name name) {
        this.name = name;
    }

    /**
     * Constructs delete command using a role.
     *
     * @param role The role of the people to be deleted
     */
    public DeleteCommand(Role role) {
        this.role = role;
    }

    /**
     * Constructs delete command using a status.
     *
     * @param status The status of the people to be deleted
     */
    public DeleteCommand(Status status) {
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Person> staffs = new ArrayList<>(model.getUnFilteredPersonList());

        if (role != null) {
            return executeBasedOnRole(model, staffs);
        } else if (status != null) {
            return executeBasedOnStatus(model, staffs);
        } else if (targetIndex != null) {
            return executeBasedOnTargetIndex(model, lastShownList);
        } else {
            return executeBasedOnName(model, lastShownList);
        }
    }

    private CommandResult executeBasedOnRole(Model model, List<Person> staffs) {
        StringBuilder deletedPeople = new StringBuilder(MESSAGE_DELETE_PEOPLE_SUCCESS);
        for (Person staff : staffs) {
            if (staff.getRoles().contains(role)) {
                model.deletePerson(staff);
                deletedPeople.append(staff).append("\n\n");
            }
        }
        return new CommandResult(deletedPeople.toString());
    }

    private CommandResult executeBasedOnStatus(Model model, List<Person> staffs) {
        StringBuilder deletedPeople = new StringBuilder(MESSAGE_DELETE_PEOPLE_SUCCESS);
        for (Person staff : staffs) {
            if (staff.getStatus() == status) {
                model.deletePerson(staff);
                deletedPeople.append(staff).append("\n\n");
            }
        }
        return new CommandResult(deletedPeople.toString());
    }

    private CommandResult executeBasedOnTargetIndex(Model model, List<Person> lastShownList)
            throws CommandException {
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person staffToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(staffToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, staffToDelete));
    }

    private CommandResult executeBasedOnName(Model model, List<Person> lastShownList)
            throws CommandException {
        int index = getIndexByName(name, lastShownList);
        if (index == INVALID_INDEX) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_SEARCHED);
        }

        Person staffToDelete = lastShownList.get(index);
        model.deletePerson(staffToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, staffToDelete));
    }


    private int getIndexByName(Name name, List<Person> lastShownList) {
        for (int i = 0; i < lastShownList.size(); i++) {
            if (lastShownList.get(i).getName().equals(name)) {
                return i;
            }
        }
        return INVALID_INDEX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeleteCommand that = (DeleteCommand) o;
        return Objects.equals(targetIndex, that.targetIndex) && Objects.equals(name, that.name)
                && role == that.role && status == that.status;
    }
}
