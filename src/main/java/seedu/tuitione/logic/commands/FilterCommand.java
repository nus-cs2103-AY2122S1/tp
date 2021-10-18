package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_SUBJECT;

import seedu.tuitione.commons.core.Messages;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.lesson.LessonIsOfSpecifiedGrade;
import seedu.tuitione.model.lesson.LessonIsOfSpecifiedGradeAndSubject;
import seedu.tuitione.model.lesson.LessonIsOfSpecifiedSubject;
import seedu.tuitione.model.lesson.Subject;
import seedu.tuitione.model.student.Grade;
import seedu.tuitione.model.student.StudentIsOfSpecifiedGrade;

/**
 * Filters out students and lessons in tuitione book whose grade is equal to the specified grade.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": filters out students and lessons if they are"
            + " of the specified grade.\n"
            + "Parameters: "
            + "[" + PREFIX_GRADE + "GRADE] "
            + "[" + PREFIX_SUBJECT + "SUBJECT] "
            + "Example: " + COMMAND_WORD + " " + PREFIX_GRADE + "S2 " + PREFIX_SUBJECT + "English" ;

    private final Grade grade;
    private final Subject subject;

    /**
     * One field can be null, but not both.
     */
    public FilterCommand(Grade grade, Subject subject) {
        this.grade = grade;
        this.subject = subject;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String output = "";
        if (grade != null) {
            model.updateFilteredStudentList(new StudentIsOfSpecifiedGrade(grade));
            model.updateFilteredLessonList(new LessonIsOfSpecifiedGrade(grade));
            output = studentAndLessonFoundOutput(model.getFilteredStudentList().size(),
                    model.getFilteredLessonList().size());
        }
        if (subject != null) {
            model.updateFilteredLessonList(new LessonIsOfSpecifiedSubject(subject));
            output = lessonFoundOutput(model.getFilteredLessonList().size());

        }
        if (subject != null && grade != null) {
            model.updateFilteredStudentList(new StudentIsOfSpecifiedGrade(grade));
            model.updateFilteredLessonList(new LessonIsOfSpecifiedGradeAndSubject(grade, subject));
            output = studentAndLessonFoundOutput(model.getFilteredStudentList().size(),
                    model.getFilteredLessonList().size());
        }

        return new CommandResult(output); // output should never be empty string
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && grade.equals(((FilterCommand) other).grade)); // state check
    }

    private String lessonFoundOutput (int size) {
        return String.format(Messages.MESSAGE_LESSON_FOUND_OVERVIEW, size);
    }

    private String studentAndLessonFoundOutput (int studentListSize, int lessonListSize) {
        return String.format(Messages.MESSAGE_STUDENTS_FOUND_OVERVIEW,
                studentListSize)
                + "\n"
                + String.format(Messages.MESSAGE_LESSON_FOUND_OVERVIEW,
                lessonListSize);
    }
}
