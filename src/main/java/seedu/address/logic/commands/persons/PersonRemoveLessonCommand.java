package seedu.address.logic.commands.persons;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.NoOverlapLessonList;
import seedu.address.model.person.Person;

public class PersonRemoveLessonCommand extends Command {

    public static final String COMMAND_WORD = "-dl";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes the lesson identified by the index number "
            + "from the person identified by the index number used in the displayed list"
            + "Parameters: INDEX1 INDEX2 (both must be a positive integer)";

    public static final String INVALID_LESSON_INDEX = "Lesson index provided is invalid!";
    public static final String MESSAGE_SUCCESS = "Lesson deleted: %s1$s";

    private final Index personIndex;
    private final Index lessonIndex;

    /**
     * Constructs a {@code PersonRemoveLessonCommand}
     *
     * @param personIndex to edit
     * @param lessonIndex to remove
     */
    public PersonRemoveLessonCommand(Index personIndex, Index lessonIndex) {
        requireAllNonNull(personIndex, lessonIndex);
        this.personIndex = personIndex;
        this.lessonIndex = lessonIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(personIndex.getZeroBased());
        NoOverlapLessonList list = personToEdit.getLessonsList();

        if (!list.isValidIndex(lessonIndex.getZeroBased())) {
            throw new CommandException(INVALID_LESSON_INDEX);
        }

        NoOverlapLessonList newList = list.removeLesson(lessonIndex.getZeroBased());
        Person newPerson = personToEdit.updateLessonsList(newList);

        model.setPerson(personToEdit, newPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newPerson));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof PersonRemoveLessonCommand)) {
            return false;
        }
        PersonRemoveLessonCommand that = (PersonRemoveLessonCommand) o;
        return Objects.equals(personIndex, that.personIndex) && Objects.equals(lessonIndex, that.lessonIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personIndex, lessonIndex);
    }
}
