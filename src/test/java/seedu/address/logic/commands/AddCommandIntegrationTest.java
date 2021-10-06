package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;
import seedu.address.testutil.ItemBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInventory(), new UserPrefs());
    }

    @Test
    public void execute_newItem_success() {
        Item validItem = new ItemBuilder().build();

        Model expectedModel = new ModelManager(model.getInventory(), new UserPrefs());
        expectedModel.addItem(validItem);

        assertCommandSuccess(new AddCommand(validItem), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validItem), expectedModel);
    }

//    @Test
//    public void execute_duplicateItem_throwsCommandException() {
//        Item itemInList = model.getInventory().getItemList().get(0);
//        assertCommandFailure(new AddCommand(itemInList), model, AddCommand.MESSAGE_DUPLICATE_ITEM);
//    }
    // TODO: ACCOUNT FOR DUPLICATE ITEMS

}
