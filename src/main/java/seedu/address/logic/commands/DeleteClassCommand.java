package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuition.TuitionClass;

public class DeleteClassCommand extends Command {
    public static final String COMMAND_WORD = "deleteclass";
    public static final String MESSAGE_DELETE_CLASS_SUCCESS = "Deleted Class: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    public DeleteClassCommand(Index i) {
        targetIndex = i;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<TuitionClass> lastShownList = model.getFilteredTuitionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        }
        TuitionClass classToDelete = lastShownList.get(targetIndex.getZeroBased());
        Tag tagToRemove = new Tag(classToDelete.getName().getName());
        for (Person p : model.getFilteredPersonList()) {
            if (p.getTags().contains(tagToRemove)) {
                p.removeTag(tagToRemove);
            }
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.deleteTuition(classToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_CLASS_SUCCESS, classToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteClassCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteClassCommand) other).targetIndex)); // state check
    }
}
