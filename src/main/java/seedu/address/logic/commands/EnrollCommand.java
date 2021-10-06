package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Student;

public class EnrollCommand extends Command {

    public static final String COMMAND_WORD = "enroll";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enrolls a specified student "
            + "from a given TuitiONE lesson\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_SUBJECT + "SUBJECT "
            + PREFIX_GRADE + "GRADE "
            + PREFIX_DAY + "DAY "
            + PREFIX_TIME + "START_TIME\n"
            + "Example: " + "enroll 1 s/Science g/P5 d/Wed t/1230";

    public static final String MESSAGE_LESSON_NOT_FOUND = "Lesson does not exist, please try again";
    public static final String MESSAGE_SUCCESS = "New %1$s enrolled into lesson: %2$s";

    private Lesson lesson;
    private Index index;
    private String lessonCode;

    /**
     * Creates an EnrollCommand for a Student with a given index to a specified {@code Lesson}.
     */
    public EnrollCommand(Index index, String lessonCode) {
        requireNonNull(index, lessonCode);

        this.index = index;
        this.lessonCode = lessonCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Student student = lastShownList.get(index.getZeroBased());

        Lesson lesson = model.searchLessons(lessonCode);
        if (lesson == null) {
            throw new CommandException(MESSAGE_LESSON_NOT_FOUND);
        }
        lesson.addStudent(student);

        return new CommandResult(String.format(MESSAGE_SUCCESS, student, lesson));
    }
}
