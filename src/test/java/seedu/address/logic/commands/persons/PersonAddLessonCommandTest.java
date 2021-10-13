package seedu.address.logic.commands.persons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalLessons.MON_10_12_BIOLOGY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.NoOverlapLessonList;

public class PersonAddLessonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullParams_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonAddLessonCommand(null, MON_10_12_BIOLOGY));
        assertThrows(NullPointerException.class, () -> new PersonAddLessonCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_validInput_success() throws Exception {
        PersonAddLessonCommand command = new PersonAddLessonCommand(INDEX_FIRST_PERSON, MON_10_12_BIOLOGY);
        command.execute(model);
        NoOverlapLessonList list = model.getFilteredPersonList().get(0).getLessonsList();
        NoOverlapLessonList expectedList = new NoOverlapLessonList().addLesson(MON_10_12_BIOLOGY);
        assertEquals(list, expectedList);
    }

    @Test
    public void execute_overlappingLesson_throwsCommandException() throws Exception {
        PersonAddLessonCommand command = new PersonAddLessonCommand(INDEX_THIRD_PERSON, MON_10_12_BIOLOGY);
        command.execute(model);
        assertCommandFailure(command, model, PersonAddLessonCommand.CANNOT_ATTEND);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        PersonAddLessonCommand command = new PersonAddLessonCommand(Index.fromZeroBased(10000), MON_10_12_BIOLOGY);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

}
