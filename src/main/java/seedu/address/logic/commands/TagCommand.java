package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class TagCommand extends Command {
    public static final String COMMAND_WORD = "tag";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": adds the specified tag(s) for the required contact.\n"
            + "Parameters: INDEX (must be a positive integer) TAG [MORE_TAGS]..\n"
            + "Example: " + COMMAND_WORD + " 1 friends ";

    public static final String MESSAGE_TAGGED_PERSON_SUCCESS = "Successfully added required tag(s)!";

    private final Index targetIndex;

    public TagCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person p = lastShownList.get(targetIndex.getZeroBased());
        model.getPersonListControl().refreshPersonListUI();
        return new CommandResult(String.format(MESSAGE_TAGGED_PERSON_SUCCESS, p));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagCommand // instanceof handles nulls
                && targetIndex.equals(((TagCommand) other).targetIndex)); // state check
    }
}
