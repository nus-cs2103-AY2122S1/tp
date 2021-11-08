package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.core.Messages.HEADER_UPDATE;
import static seedu.tuitione.commons.core.Messages.MESSAGE_LESSON_FOUND_OVERVIEW;
import static seedu.tuitione.commons.core.Messages.MESSAGE_STUDENTS_FOUND_OVERVIEW;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_SUBJECT;

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

    public static final String MESSAGE_USAGE = "Command: "
            + COMMAND_WORD + "\nfilters out students and lessons if they are"
            + " of the specified grade.\n\n"
            + "Parameters: "
            + "[" + PREFIX_GRADE + "GRADE] "
            + "[" + PREFIX_SUBJECT + "SUBJECT] "
            + "\nExample: " + COMMAND_WORD + " " + PREFIX_GRADE + "S2 " + PREFIX_SUBJECT + "English";

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

        // subject and grade will never both be null
        if (grade != null) {
            model.updateFilteredStudentList(new StudentIsOfSpecifiedGrade(grade));
            if (subject != null) {
                model.updateFilteredLessonList(new LessonIsOfSpecifiedGradeAndSubject(grade, subject));
            } else { // subject == null
                model.updateFilteredLessonList(new LessonIsOfSpecifiedGrade(grade));
            }
            output = studentAndLessonFoundOutput(model.getFilteredStudentList().size(),
                    model.getFilteredLessonList().size());

        } else { // subject is not null but grade is null
            model.updateFilteredLessonList(new LessonIsOfSpecifiedSubject(subject));
            output = lessonFoundOutput(model.getFilteredLessonList().size());
        }

        return new CommandResult(output); // output should never be empty string
    }

    private String lessonFoundOutput(int size) {
        return HEADER_UPDATE + String.format(MESSAGE_LESSON_FOUND_OVERVIEW, size);
    }

    private String studentAndLessonFoundOutput(int studentListSize, int lessonListSize) {
        String messageContent = String.format(MESSAGE_STUDENTS_FOUND_OVERVIEW, studentListSize)
                + "\n"
                + String.format(MESSAGE_LESSON_FOUND_OVERVIEW, lessonListSize);
        return HEADER_UPDATE + messageContent;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        } else if (other instanceof FilterCommand) { // instanceof handles nulls
            if (subject == null) {
                return grade.equals(((FilterCommand) other).grade);
            } else if (grade == null) {
                return subject.equals(((FilterCommand) other).subject);
            } else {
                return subject.equals(((FilterCommand) other).subject)
                    && grade.equals(((FilterCommand) other).grade);
            }
        } else {
            return false;
        }
    }
}
