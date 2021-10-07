package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SortCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICIA;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.JOHN;
import static seedu.address.testutil.TypicalPersons.NOAH;
import static seedu.address.testutil.TypicalPersons.OLIVIA;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.SortCommandParser;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class SortCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_sortByName_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS);
        model.addPerson(IDA);
        model.addPerson(DANIEL);
        model.addPerson(ALICE);

        expectedModel.addPerson(ALICE);
        expectedModel.addPerson(DANIEL);
        expectedModel.addPerson(IDA);

        assertCommandSuccess(new SortCommand(SortCommandParser.SortableField.NAME),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_sortByModuleCode_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS);

        model.addPerson(JOHN);
        model.addPerson(OLIVIA);
        model.addPerson(ALICIA);
        model.addPerson(NOAH);

        expectedModel.addPerson(NOAH);
        expectedModel.addPerson(ALICIA);
        expectedModel.addPerson(OLIVIA);
        expectedModel.addPerson(JOHN);

        model.sortAddressBook(SortCommandParser.SortableField.MODULE_CODES);

        assertCommandSuccess(new SortCommand(SortCommandParser.SortableField.MODULE_CODES),
                model, expectedCommandResult, expectedModel);
    }
}
