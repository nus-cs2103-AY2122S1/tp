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
 * Selects a list of persons in the filtered list and adds them into the
 * selection panel's list.
 */
public class SelectCommand extends Command {

    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Selects person(s) by index "
            + "and adds them into the list displayed in the selection panel.\n"
            + "Parameters: FLAG[-a/-i/-e] INDEX (must be a positive integer)\n" + "Example: " + COMMAND_WORD
            + " -i 1 3 4";

    private final List<Index> indexes;
    private final boolean isInclusion;

    /**
     * Takes in all indexes in the filtered list.
     */
    public SelectCommand() {
        this.indexes = null;
        this.isInclusion = false;
    }

    /**
     * Takes in a list of indexes.
     *
     * @param isInclusion {@code false} if the indexes provided are exclusions
     */
    public SelectCommand(List<Index> indexes, boolean isInclusion) {
        this.indexes = indexes;
        this.isInclusion = isInclusion;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> persons = model.getFilteredPersonList();
        if (indexes == null) {
            model.addSelected(persons);
        } else {
            List<Person> selectedPersons;
            try {
                selectedPersons = getPersons(persons);
            } catch (IndexOutOfBoundsException exp) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            requireNonNull(selectedPersons);
            model.addSelected(selectedPersons);
        }
        return new CommandResult(model.getSelectedPersonList().size() + " persons selected!");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectCommand // instanceof handles nulls
                        && indexes.equals(((SelectCommand) other).indexes)
                        && isInclusion == ((SelectCommand) other).isInclusion); // state check
    }

    private List<Person> getPersons(List<Person> list) throws IndexOutOfBoundsException {
        if (!isHigherThanIndexes(list.size())) {
            throw new IndexOutOfBoundsException();
        }
        return isInclusion ? fromInclusion(list) : fromExclusion(list);
    }

    private List<Person> fromInclusion(List<Person> list) {
        List<Person> selectedList = new ArrayList<>();
        for (Index index : indexes) {
            selectedList.add(list.get(index.getZeroBased()));
        }
        return selectedList;
    }

    private List<Person> fromExclusion(List<Person> list) {
        List<Person> selectedList = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (Index index : indexes) {
            set.add(index.getZeroBased());
        }
        for (int i = 0; i < list.size(); i++) {
            if (!set.contains(i)) {
                selectedList.add(list.get(i));
            }
        }
        return selectedList;
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
