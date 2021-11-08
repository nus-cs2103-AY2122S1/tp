package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.core.Messages.HEADER_UPDATE;

import java.util.List;

import seedu.tuitione.commons.core.Messages;
import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.logic.commands.exceptions.CommandException;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.LessonCode;
import seedu.tuitione.model.lesson.LessonIsOfSpecifiedLessonCode;
import seedu.tuitione.model.student.Student;
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
            + "LESSON_INDEX (must be a positive integer)"
            + "\nExample: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ROSTER_LESSON_SUCCESS = HEADER_UPDATE + "Roster of %s is displayed.\n"
            + "A total of %s student(s) found.";
    public static final String MESSAGE_ENROLLED_STUDENT_HEADER = "\nStudent(s): "; // add when there are students

    private final Index targetIndex;

    /**
     * Creates an RosterCommand a given index to a specified {@code Lesson}.
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
        Lesson lessonToShow = lastShownList.get(targetIndex.getZeroBased());
        LessonCode lessonCodeToUse = lessonToShow.getLessonCode();

        model.updateFilteredLessonList(new LessonIsOfSpecifiedLessonCode(lessonCodeToUse));
        model.updateFilteredStudentList(new StudentIsOfSpecifiedLessonCode(lessonCodeToUse));

        List<Student> filteredStudents = model.getFilteredStudentList();
        StringBuilder outputSb = new StringBuilder();
        outputSb.append(String.format(MESSAGE_ROSTER_LESSON_SUCCESS, lessonCodeToUse, filteredStudents.size()));

        if (!filteredStudents.isEmpty()) {
            outputSb.append(MESSAGE_ENROLLED_STUDENT_HEADER);
            filteredStudents.stream()
                    .map(s -> s.getName().fullName)
                    .sorted()
                    .forEach(n -> outputSb.append(n).append(", "));
            outputSb.delete(outputSb.length() - 2, outputSb.length()); // remove trailing ", "
        }
        return new CommandResult(outputSb.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RosterCommand // instanceof handles nulls
                && targetIndex.equals(((RosterCommand) other).targetIndex)); // state check
    }

}
