package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.tuitione.commons.core.Messages;
import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.logic.commands.exceptions.CommandException;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.LessonCode;
import seedu.tuitione.model.lesson.LessonIsOfSpecifiedLessonCode;
import seedu.tuitione.model.student.StudentIsOfSpecifiedLessonCode;

/**
 * Display a list of students who are enrolled in a specific lesson in tuitone.
 */
public class RosterCommand extends Command {

    public static final String COMMAND_WORD = "roster";

    public static final String MESSAGE_USAGE = "Command: "
            + COMMAND_WORD + "\ndisplays the list of students who are"
            + " enrolled in a specific lesson registered in the TuitiONE book.\n\n"
            + "Parameters: "
            + "LESSON_INDEX"
            + "\nExample: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ROSTER_LESSON_SUCCESS = "✔\tSuccess:\n\nRoster of %s is displayed.\n"
            + "A total of %s student(s) found.";

    private final Index targetIndex;

    /**
     * Constructor
     * @param targetIndex
     */
    public RosterCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Lesson> lastShownList = model.getFilteredLessonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        String output = "";

        Lesson lessonToShow = lastShownList.get(targetIndex.getZeroBased());
        LessonCode lessonCodeToUse = lessonToShow.getLessonCode();

        if (lessonCodeToUse != null) {
            model.updateFilteredLessonList(new LessonIsOfSpecifiedLessonCode(lessonCodeToUse));
            model.updateFilteredStudentList(new StudentIsOfSpecifiedLessonCode(lessonCodeToUse));
            output = String.format(MESSAGE_ROSTER_LESSON_SUCCESS,
                    lessonCodeToUse,
                    model.getFilteredStudentList().size());
        }

        return new CommandResult(output); // output should never be empty string

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RosterCommand // instanceof handles nulls
                && targetIndex.equals(((RosterCommand) other).targetIndex)); // state check
    }

}
