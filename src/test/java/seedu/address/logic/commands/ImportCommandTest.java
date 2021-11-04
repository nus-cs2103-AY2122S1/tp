package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.ModelStubAcceptingPersonAdded;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

public class ImportCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportCommand(null));
    }

    @Test
    public void execute_personsAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        List<Person> typicalPersons = TypicalPersons.getTypicalPersons();

        CommandResult commandResult = new ImportCommand(typicalPersons).execute(modelStub);

        assertEquals(typicalPersons.size()
                + " "
                + ImportCommand.MESSAGE_SUCCESS
                + ".", commandResult.getFeedbackToUser());
        assertEquals(typicalPersons, modelStub.getPersonsAdded());
    }

    @Test
    public void execute_duplicatePerson_addSuccessfulWithoutDuplicates() throws Exception {
        List<Person> typicalPersons = TypicalPersons.getTypicalPersons();
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();

        Person validPerson = new PersonBuilder().build();
        modelStub.addPerson(validPerson);
        typicalPersons.add(0, validPerson);

        CommandResult commandResult = new ImportCommand(typicalPersons).execute(modelStub);

        assertEquals(typicalPersons.size() - 1
                + " "
                + ImportCommand.MESSAGE_SUCCESS
                + ". "
                + ImportCommand.MESSAGE_DUPLICATE
                + ".", commandResult.getFeedbackToUser());
        assertEquals(typicalPersons, modelStub.getPersonsAdded());
    }
}
