package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.util.PersonUtil;

public class LessonAddCommand extends Command {

    public static final String COMMAND_WORD = "ladd";

    public static final String COMMAND_PARAMETERS = "INDEX (must be a positive integer) "
            + "[" + PREFIX_RECURRING + "] "
            + PREFIX_DATE + "dd MMM yyyy "
            + PREFIX_TIME + "HHmm-HHmm "
            + PREFIX_SUBJECT + "SUBJECT "
            + "[" + PREFIX_HOMEWORK + "HOMEWORK]...";

    public static final String COMMAND_EXAMPLE_RECURRING_LESSON = COMMAND_WORD + " 1 "
            + PREFIX_RECURRING + " "
            + PREFIX_DATE + "09 Dec 2021 "
            + PREFIX_TIME + "1030-1230 "
            + PREFIX_SUBJECT + "Math "
            + PREFIX_HOMEWORK + "TYS Page 2";

    public static final String COMMAND_EXAMPLE_MAKEUP_LESSON = COMMAND_WORD + " 1 "
            + PREFIX_DATE + "10 Oct 2021 "
            + PREFIX_TIME + "1430-1600 "
            + PREFIX_SUBJECT + "Science "
            + PREFIX_HOMEWORK + "TYS Page 2 "
            + PREFIX_HOMEWORK + "Textbook Page 52";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lesson to the student identified "
            + "by the index number\n"
            + "Parameters: " + COMMAND_PARAMETERS + "\n"
            + "Examples:\n"
            + "Makeup lesson: " + COMMAND_EXAMPLE_MAKEUP_LESSON + "\n"
            + "Recurring lesson: " + COMMAND_EXAMPLE_RECURRING_LESSON;

    public static final String USER_TIP = "Try adding a lesson to a student using: \n"
            + COMMAND_WORD + " "
            + COMMAND_PARAMETERS + "\n"
            + "Example (recurring lesson): "
            + COMMAND_EXAMPLE_RECURRING_LESSON;

    public static final String MESSAGE_ADD_LESSON_SUCCESS = "Added new lesson: %1$s\nfor student: %2$s";
    public static final String MESSAGE_CLASHING_LESSON = "This lesson clashes with an existing lesson.";

    private final Index index;
    private final Lesson toAdd;

    /**
     * Creates a LessonAddCommand to add the specified {@code Lesson}
     */
    public LessonAddCommand(Index index, Lesson lesson) {
        requireNonNull(lesson);
        this.index = index;
        toAdd = lesson;
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     */
    private static Person createEditedPerson(Person personToEdit, Lesson lesson) {
        assert personToEdit != null;

        Set<Lesson> lessons = new TreeSet<>(personToEdit.getLessons());
        lessons.add(lesson);

        return PersonUtil.createdEditedPerson(personToEdit, lessons);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, toAdd);

        if (model.hasClashingLesson(toAdd)) {
            throw new CommandException(MESSAGE_CLASHING_LESSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_LESSON_SUCCESS, toAdd, editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonAddCommand // instanceof handles nulls
                && index.equals(((LessonAddCommand) other).index)
                && toAdd.equals(((LessonAddCommand) other).toAdd));
    }
}
