package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICE_DIFFERENT_PHONE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class ImportCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_personNotInAddressBook_listOfPersonAddedWithNoReplacement() {
        ArrayList<Person> dataToImport = new ArrayList<>(Arrays.asList(HOON, IDA));
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(HOON);
        expectedModel.addPerson(IDA);
        assertCommandSuccess(new ImportCommand(dataToImport), model, ImportCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_personInAddressBook_listOfPersonAddedWithReplacement() {
        ArrayList<Person> dataToImport = new ArrayList<>(Arrays.asList(HOON, ALICE_DIFFERENT_PHONE));
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(HOON);
        expectedModel.setPerson(ALICE, ALICE_DIFFERENT_PHONE);
        assertCommandSuccess(new ImportCommand(dataToImport), model, ImportCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
