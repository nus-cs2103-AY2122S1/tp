package seedu.track2gather.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import seedu.track2gather.commons.core.Messages;
import seedu.track2gather.commons.core.index.Index;
import seedu.track2gather.logic.commands.exceptions.CommandException;
import seedu.track2gather.model.Model;
import seedu.track2gather.model.person.Person;

/**
 * Deletes the person(s) identified by the specified index number(s) used in the displayed persons list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person(s) identified by the specified index number(s) "
            + "used in the displayed persons list.\n"
            + "Parameters: INDEX [MORE_INDICES] (must be positive integers)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person(s):\n";

    private final List<Index> targetIndices;

    /**
     * Creates a DeleteCommand to delete the specified {@code Person}(s)
     */
    public DeleteCommand(List<Index> targetIndices) {
        this.targetIndices = targetIndices.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        String commandResult = MESSAGE_DELETE_PERSON_SUCCESS;
        ArrayList<String> commandResultList = new ArrayList<>();

        for (Index targetIndex : targetIndices) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
            commandResultList.add(0, personToDelete.toString());
        }
        commandResult += String.join("\n", commandResultList);

        return new CommandResult(commandResult);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndices.equals(((DeleteCommand) other).targetIndices)); // state check
    }
}
