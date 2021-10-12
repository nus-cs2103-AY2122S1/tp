package seedu.address.logic.commands.persons;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.NoOverlapLessonList;
import seedu.address.model.person.Person;

/**
 * Adds a lesson to a specified person in the address book
 */
public class PersonAddLessonCommand extends Command {

    public static final String COMMAND_WORD = "-al";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lesson to the person identified "
            + "by the index number used in the displayed person list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_SUBJECT + "SUBJECT] "
            + "[" + PREFIX_START_TIME + "HH:MM START TIME] "
            + "[" + PREFIX_END_TIME + "HH:MM END TIME] "
            + "[" + PREFIX_DAY + "DAY] ";

    public static final String CANNOT_ATTEND = "Lesson overlaps with current lessons!";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
    public static final String MESSAGE_SUCCESS = "Lesson added: %1$s";

    private final Index index;
    private final Lesson lessonToAdd;

    /**
     * Constructs a {@code PersonAddLessonCommand}
     *
     * @param index of the person in the filtered person list to edit
     * @param lessonToAdd lesson to add to the person at index
     */
    public PersonAddLessonCommand(Index index, Lesson lessonToAdd) {
        requireNonNull(index);
        requireNonNull(lessonToAdd);

        this.index = index;
        this.lessonToAdd = lessonToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (!personToEdit.canAttendLesson(lessonToAdd)) {
            throw new CommandException(CANNOT_ATTEND);
        }

        NoOverlapLessonList newList = personToEdit.getLessonsList();
        newList = newList.addLesson(lessonToAdd);
        Person newPerson = personToEdit.updateLessonsList(newList);

        if (!personToEdit.isSamePerson(newPerson) && model.hasPerson(newPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, newPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newPerson));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof PersonAddLessonCommand)) {
            return false;
        }
        PersonAddLessonCommand that = (PersonAddLessonCommand) o;
        return index.equals(that.index) && lessonToAdd.equals(that.lessonToAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, lessonToAdd);
    }
}
