package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_LESSON;

import java.util.List;

import seedu.tuitione.commons.core.Messages;
import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.logic.commands.exceptions.CommandException;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.LessonCode;
import seedu.tuitione.model.lesson.LessonIsOfSpecifiedLessonCode;
import seedu.tuitione.model.student.StudentsIsOfSpecifiedLessonCode;

/**
 * Display a list of students who are enrolled in a specific lesson in tuitone.
 */
public class RosterCommand extends Command {

    public static final String COMMAND_WORD = "roster";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": displays the list of students who are"
            + "enrolled in a specific lesson registered in TuitiONE.\n"
            + "Parameters: "
            + "[" + PREFIX_LESSON + "LESSON_INDEX] "
            + "Example: " + COMMAND_WORD + " " + PREFIX_LESSON + "1";

    public static final String MESSAGE_ROSTER_LESSON_SUCCESS = "Roster of Lesson : %1$s is successfully displayed.\n"
            + "A total of %d student(s) found.";

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
            model.updateFilteredStudentList(new StudentsIsOfSpecifiedLessonCode(lessonCodeToUse));
            output = String.format(MESSAGE_ROSTER_LESSON_SUCCESS,
                    lessonCodeToUse.toString(),
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
