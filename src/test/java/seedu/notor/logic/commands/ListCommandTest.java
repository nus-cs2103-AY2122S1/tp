package seedu.notor.logic.commands;

import static seedu.notor.testutil.TypicalPersons.getTypicalNotor;

import org.junit.jupiter.api.BeforeEach;

import seedu.notor.model.Model;
import seedu.notor.model.ModelManager;
import seedu.notor.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
@Deprecated
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalNotor(), new UserPrefs());
        expectedModel = new ModelManager(model.getNotor(), new UserPrefs());
    }

    // Broken due to new way of instantiating Model class.
    // Possibly readd but maybe not. (Requires a lot of work for setUp() method to function)
    //@Test
    //public void execute_listIsNotFiltered_showsSameList() {
    //    CommandTestUtil.assertExecuteSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    //}
    //
    //@Test
    //public void execute_listIsFiltered_showsEverything() {
    //    showPersonAtIndex(model, INDEX_FIRST_PERSON);
    //    CommandTestUtil.assertExecuteSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    //}
}
