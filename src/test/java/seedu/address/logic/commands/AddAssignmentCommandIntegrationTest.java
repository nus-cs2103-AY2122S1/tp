package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.clonePerson;
import static seedu.address.logic.commands.CommandTestUtil.clonePersonInModel;
import static seedu.address.logic.commands.CommandTestUtil.setUpNewModelWithClonedPerson;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;
import seedu.address.testutil.AssignmentBuilder;

class AddAssignmentCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPersonAssignment_success() {
        Assignment validAssignment = new AssignmentBuilder().build();
        Person selectedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person clonedPersonForExpectedModel = clonePerson(selectedPerson);
        Person clonedPersonForActualModel = clonePerson(selectedPerson);

        Model expectedModel = setUpNewModelWithClonedPerson(selectedPerson, clonedPersonForExpectedModel);
        expectedModel.addAssignment(clonedPersonForExpectedModel, validAssignment);
        Model actualModel = clonePersonInModel(model, selectedPerson, clonedPersonForActualModel);

        assertCommandSuccess(new AddAssignmentCommand(INDEX_FIRST_PERSON, validAssignment), actualModel,
                String.format(AddAssignmentCommand.MESSAGE_SUCCESS, validAssignment), expectedModel);
    }

    @Test
    public void execute_duplicateAssignment_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList()
                .get(INDEX_THIRD_PERSON.getZeroBased());
        model.updateAssignmentList(personInList);
        Assignment assignmentInList = model.getAddressBook().getAssignmentsList()
                .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        assertCommandFailure(new AddAssignmentCommand(INDEX_THIRD_PERSON, assignmentInList),
                model, AddAssignmentCommand.MESSAGE_DUPLICATE_ASSIGNMENT);
    }
}
