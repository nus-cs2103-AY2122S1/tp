package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "rm";

    public static final String DESCRIPTION = "Deletes the person identified by the index number used in "
            + "the displayed person list.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer less than or equal to " + Integer.MAX_VALUE + ")\n"
            + "Example: " + COMMAND_WORD + " 1";


    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    public static final String MESSAGE_DELETE_PEOPLE_SUCCESS = "Delete People: ";

    public static final String MESSAGE_DELETE_ALL_SUCCESS = "Address book has been cleared!";

    private final List<Index> targetIndexes;

    private boolean isMultipleDelete = false;

    private boolean isAllDelete = false;

    /**
     * Constructor for a DeleteCommand to delete one person.
     *
     * @param targetIndex The {@code Index} of the target person.
     */
    public DeleteCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndexes = List.of(targetIndex);
    }

    /**
     * Constructor for a DeleteCommand to delete a number of people.
     *
     * @param targetIndexes The {@code List<Index>} for multiple people.
     */
    public DeleteCommand(List<Index> targetIndexes) {
        requireNonNull(targetIndexes);
        this.targetIndexes = targetIndexes;
        isMultipleDelete = true;
    }

    /**
     * Constructor for a DeleteCommand to delete all contacts.
     */
    public DeleteCommand() {
        this.targetIndexes = null;
        isAllDelete = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (isAllDelete) {
            model.setAddressBook(new AddressBook());

            CommandResult commandResult = new CommandResult(MESSAGE_DELETE_ALL_SUCCESS);
            commandResult.setWriteCommand();
            return commandResult;
        } else if (!isMultipleDelete) {
            return executeDelete(model, lastShownList, targetIndexes.get(0));
        } else {
            return executeMultipleDelete(model, lastShownList, targetIndexes);
        }
    }

    /**
     * Executes the delete command for one index and returns the result.
     *
     * @param model The in-memory {@code Model} of the address book data.
     * @param lastShownList The most recently updated {@code List}.
     * @param targetIndex The {@code Index} of the target person.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult executeDelete(Model model, List<Person> lastShownList, Index targetIndex)
            throws CommandException {
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);

        CommandResult commandResult = new CommandResult(
                String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
        commandResult.setWriteCommand();
        return commandResult;
    }

    /**
     * Executes the delete command for multiple indexes and returns the result.
     *
     * @param model The in-memory {@code Model} of the address book data.
     * @param lastShownList The most recently updated {@code List}.
     * @param targetIndexes The {@code List<Index>} for the target people.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult executeMultipleDelete(Model model, List<Person> lastShownList, List<Index> targetIndexes)
            throws CommandException {
        StringBuilder sb = new StringBuilder(MESSAGE_DELETE_PEOPLE_SUCCESS);
        for (Index index : targetIndexes) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownList.get(index.getZeroBased());
            model.deletePerson(personToDelete);
            sb.append(System.lineSeparator()).append(personToDelete);
        }

        return new CommandResult(sb.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndexes.equals(((DeleteCommand) other).targetIndexes)); // state check
    }

    public String getCommand() {
        return COMMAND_WORD;
    }

    public String getDescription() {
        return DESCRIPTION;
    }
}
