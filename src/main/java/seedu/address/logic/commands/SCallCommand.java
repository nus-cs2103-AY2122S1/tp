package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Indicates a person identified using its displayed index from the address book as called for the session.
 */
public class SCallCommand extends Command {

    public static final String COMMAND_WORD = "scall";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Indicates that the person has been successfully called\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CALL_PERSON_SUCCESS =
        "Successfully called Person: %s; non-compliance counter: %d";

    private final Index targetIndex;

    /**
     * Default constructor to create a new {@code SCallCommand}
     *
     * @param targetIndex index of target person.
     */
    public SCallCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToIncrement = lastShownList.get(targetIndex.getZeroBased());
        Person newPerson = new Person(personToIncrement, personToIncrement.getCallStatus().call());
        model.setPerson(personToIncrement, newPerson);

        return new CommandResult(String.format(MESSAGE_CALL_PERSON_SUCCESS, newPerson.getCaseNumber(),
            newPerson.getCallStatus().getNumFailedCalls()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SCallCommand // instanceof handles nulls
            && targetIndex.equals(((SCallCommand) other).targetIndex)); // state check
    }
}
