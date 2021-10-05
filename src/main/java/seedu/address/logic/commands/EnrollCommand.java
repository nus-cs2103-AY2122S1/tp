package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

public class EnrollCommand extends Command{

    public static final String COMMAND_WORD = "enroll";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enrolls a specified student from a given TuitiONE lesson\n"
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

    public EnrollCommand(Index index, String lessonCode) {
        requireNonNull(index, lessonCode);

        this.index = index;
        this.lessonCode = lessonCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person person = lastShownList.get(index.getZeroBased());

        Lesson lesson = model.searchLessons(lessonCode);
        if (lesson == null) {
            throw new CommandException(MESSAGE_LESSON_NOT_FOUND);
        }
        lesson.addStudent(person);

        return new CommandResult(String.format(MESSAGE_SUCCESS, person, lesson));
    }
}
