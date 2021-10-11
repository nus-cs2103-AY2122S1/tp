package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_LESSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonCode;
import seedu.address.model.person.Student;

public class EnrollCommand extends Command {

    public static final String COMMAND_WORD = "enroll";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enrolls a specified student "
            + "from a given TuitiONE lesson\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "LESSONCODE\n"
            + "Example: " + "enroll 1 " + PREFIX_LESSON + "Science-P5-Wed-1230";

    public static final String MESSAGE_STUDENT_IN_LESSON = "%1$s is already enrolled in the existing %2$s";
    public static final String MESSAGE_LESSON_NOT_FOUND = "Lesson does not exist, please try again";
    public static final String MESSAGE_SUCCESS = "%1$s enrolled into lesson: %2$s";

    private final Index targetIndex;
    private final String lessonCode;

    /**
     * Creates an EnrollCommand for a Student with a given index to a specified {@code Lesson}.
     */
    public EnrollCommand(Index targetIndex, String lessonCode) {
        requireNonNull(targetIndex, lessonCode);

        this.targetIndex = targetIndex;
        this.lessonCode = lessonCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        LessonCode code = new LessonCode(lessonCode);
        Optional<Lesson> lessonOptional = model.searchLessons(code);
        Lesson lesson = lessonOptional.orElseThrow(() -> new CommandException(Messages.MESSAGE_INVALID_LESSON_CODE));

        List<Student> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Student studentToEnroll = lastShownList.get(targetIndex.getZeroBased());

        if (lesson.containsStudent(studentToEnroll)) {
            throw new CommandException(String.format(MESSAGE_STUDENT_IN_LESSON,
                    studentToEnroll.getName(),
                    lesson));
        }

        Student newStudent = studentToEnroll.createClone();
        lesson.addStudent(newStudent);
        model.setPerson(studentToEnroll, newStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, newStudent.getName(), lesson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EnrollCommand
                && targetIndex.equals(((EnrollCommand) other).targetIndex)
                && lessonCode.equals(((EnrollCommand) other).lessonCode));
    }
}
