package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.model.Model.PREDICATE_SHOW_ALL_LESSONS;
import static seedu.tuitione.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.tuitione.model.Model;

/**
 * Lists all students in the tuitione book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all students & all lessons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
