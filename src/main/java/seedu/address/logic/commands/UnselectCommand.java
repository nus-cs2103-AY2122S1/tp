package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Unselects a list of persons in the selected list.
 */
public class UnselectCommand extends Command {

    public static final String COMMAND_WORD = "unselect";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unselects person(s) by index "
            + "from the list displayed in the selection panel.\n"
            + "Parameters: FLAG[-a/-i/-e] INDEX (must be a positive integer)\n" + "Example: " + COMMAND_WORD
            + " -i 1 3 4";

    private final List<Index> indexes;
    private final boolean isInclusion;

    /**
     * Takes in all indexes in the selected list.
     */
    public UnselectCommand() {
        this.indexes = null;
        this.isInclusion = false;
    }

    /**
     * Takes in a list of indexes.
     *
     * @param isInclusion {@code false} if the indexes provided are exclusions
     */
    public UnselectCommand(List<Index> indexes, boolean isInclusion) {
        this.indexes = indexes;
        this.isInclusion = isInclusion;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> persons = model.getSelectedPersonList();
        if (indexes == null) {
            int size = persons.size();
            model.removeSelected(persons);
            return new CommandResult(size + " persons unselected!");
        } else {
            List<Person> unselectedPersons;
            try {
                unselectedPersons = getPersons(persons);
            } catch (IndexOutOfBoundsException exp) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            requireNonNull(unselectedPersons);
            model.removeSelected(unselectedPersons);
            return new CommandResult(unselectedPersons.size() + " persons unselected!");
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnselectCommand // instanceof handles nulls
                        && indexes.equals(((UnselectCommand) other).indexes)
                        && isInclusion == ((UnselectCommand) other).isInclusion); // state check
    }

    private List<Person> getPersons(List<Person> list) throws IndexOutOfBoundsException {
        if (!isHigherThanIndexes(list.size())) {
            throw new IndexOutOfBoundsException();
        }
        return isInclusion ? fromInclusion(list) : fromExclusion(list);
    }

    private List<Person> fromInclusion(List<Person> list) {
        List<Person> unselectedList = new ArrayList<>();
        for (Index index : indexes) {
            unselectedList.add(list.get(index.getZeroBased()));
        }
        return unselectedList;
    }

    private List<Person> fromExclusion(List<Person> list) {
        List<Person> unselectedList = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (Index index : indexes) {
            set.add(index.getZeroBased());
        }
        for (int i = 0; i < list.size(); i++) {
            if (!set.contains(i)) {
                unselectedList.add(list.get(i));
            }
        }
        return unselectedList;
    }

    private boolean isHigherThanIndexes(int size) {
        for (Index index : indexes) {
            if (index.getOneBased() > size) {
                return false;
            }
        }
        return true;
    }

}
