package seedu.times.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.times.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.times.model.Model;
import seedu.times.ui.TabName;

/**
 * Lists all persons in the Timestable to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all students";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        updateView(TabName.STUDENTS);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
