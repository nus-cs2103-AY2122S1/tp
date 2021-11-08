package seedu.track2gather.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.track2gather.commons.core.Messages;
import seedu.track2gather.commons.core.index.Index;
import seedu.track2gather.logic.commands.exceptions.CommandException;
import seedu.track2gather.model.Model;
import seedu.track2gather.model.person.Person;

/**
 * Updates a person as successfully called in the current SHN enforcement session.
 */
public class SCallCommand extends Command {

    public static final String COMMAND_WORD = "scall";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates a person as successfully called in the current SHN enforcement session.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CALL_PERSON_SUCCESS =
            "Successfully called Person: %s (Case No. %s) with %d past failed call attempt(s)";

    public static final String MESSAGE_INVALID_MULTIPLE_CALLS =
            "Person has already been called in the current session.";

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
        if (personToIncrement.getCallStatus().isCalledInCurrentSession()) {
            throw new CommandException(MESSAGE_INVALID_MULTIPLE_CALLS);
        }

        Person newPerson = new Person(personToIncrement, personToIncrement.getCallStatus().call());
        model.setPerson(personToIncrement, newPerson);

        return new CommandResult(String.format(MESSAGE_CALL_PERSON_SUCCESS, newPerson.getName(),
                newPerson.getCaseNumber(), newPerson.getCallStatus().getNumFailedCalls()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SCallCommand // instanceof handles nulls
            && targetIndex.equals(((SCallCommand) other).targetIndex)); // state check
    }
}
