package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.DONUT;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.model.BookKeeping;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TransactionList;
import seedu.address.model.UserPrefs;
import seedu.address.model.display.DisplayMode;
import seedu.address.model.order.Order;

public class EndAndTransactOrderCommandTest {

    @TempDir
    public Path temporaryFolder;

    private Model modelWithoutOrder = new ModelManager(getTypicalInventory(), new UserPrefs(),
            new TransactionList(), new BookKeeping());

    /**
     * Returns a model with 5 donuts in its unclosed order
     */
    private Model getModelWithOrderedDonut(Path path) {
        UserPrefs userPrefs = new UserPrefs(path);
        Model model = new ModelManager(getTypicalInventory(), userPrefs, new TransactionList(), new BookKeeping());
        model.addItem(DONUT.updateCount(5));
        model.setOrder(new Order());
        model.addToOrder(DONUT.updateCount(1));

        return model;
    }

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_noUnclosedOrder_failure() {
        EndAndTransactOrderCommand command = new EndAndTransactOrderCommand();
        assertCommandFailure(command, modelWithoutOrder, EndAndTransactOrderCommand.MESSAGE_NO_UNCLOSED_ORDER);
    }

    @Test
    public void execute_emptyOrder_success() {
        EndAndTransactOrderCommand command = new EndAndTransactOrderCommand();

        Model modelWithEmptyOrder = new ModelManager(getTypicalInventory(),
                new UserPrefs(), new TransactionList(), new BookKeeping());
        modelWithEmptyOrder.setOrder(new Order());

        Model modelWithoutOrder = new ModelManager(getTypicalInventory(),
                new UserPrefs(), new TransactionList(), new BookKeeping());
        CommandResult expectedResult = new CommandResult(EndAndTransactOrderCommand.MESSAGE_EMPTY_ORDER);
        assertCommandSuccess(command, modelWithEmptyOrder, expectedResult, modelWithoutOrder);
    }

    @Test
    public void execute_normalTransaction_itemRemoved() {
        String expectedMessage = EndAndTransactOrderCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(getTypicalInventory(),
                new UserPrefs(temporaryFolder.resolve("transaction.json")), new TransactionList(), new BookKeeping());
        expectedModel.addItem(DONUT.updateCount(4));

        Model modelTemp = getModelWithOrderedDonut(temporaryFolder.resolve("transaction.json"));

        assertCommandSuccess(new EndAndTransactOrderCommand(), modelTemp, expectedMessage, expectedModel);
    }

    @Test
    public void execute_displayingOrder_itemRemovedAndDisplayInventory() {
        Model modelTemp = getModelWithOrderedDonut(temporaryFolder.resolve("transaction.json"));
        modelTemp.updateFilteredDisplayList(DisplayMode.DISPLAY_OPEN_ORDER, Model.PREDICATE_SHOW_ALL_ITEMS);

        EndAndTransactOrderCommand command = new EndAndTransactOrderCommand();
        String expectedMessage = EndAndTransactOrderCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(getTypicalInventory(),
                new UserPrefs(temporaryFolder.resolve("transaction.json")), new TransactionList(), new BookKeeping());
        expectedModel.addItem(DONUT.updateCount(4));

        assertCommandSuccess(new EndAndTransactOrderCommand(), modelTemp, expectedMessage, expectedModel);
    }
}
