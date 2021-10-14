package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Remark;
import seedu.address.model.tuition.TuitionClass;
import seedu.address.testutil.TuitionClassBuilder;

class RemarkClassCommandTest {
    private static final String REMARK_STUB = "Some remark";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRemarkClassUnfilteredList_success() {
        TuitionClass firstClass = model.getFilteredTuitionList().get(INDEX_FIRST_PERSON.getZeroBased());
        TuitionClass editedClass = new TuitionClassBuilder(firstClass).withRemark(REMARK_STUB).build();

        RemarkClassCommand remarkClassCommand = new RemarkClassCommand(INDEX_FIRST_PERSON,
                new Remark(editedClass.getRemark().value));

        String expectedMessage = String.format(RemarkClassCommand.MESSAGE_ADD_REMARK_SUCCESS, editedClass);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstClass, editedClass);

        assertCommandSuccess(remarkClassCommand, model, expectedMessage, expectedModel);
    }
}