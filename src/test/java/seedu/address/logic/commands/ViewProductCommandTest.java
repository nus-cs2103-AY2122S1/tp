package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.commons.Name;
import seedu.address.model.product.Product;
import seedu.address.model.product.ProductContainsIdPredicate;
import seedu.address.model.product.UnitPrice;



/**
 * Contains integration tests (interaction with the Model) and unit tests for ListClientCommand.
 */
public class ViewProductCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Product product1 = new Product(new Name("Test1"), new UnitPrice("1"), null);
        Product product2 = new Product(new Name("Test2"), new UnitPrice("2"), null);
        Product product3 = new Product(new Name("Test3"), new UnitPrice("3"), null);
        model.addProduct(product1);
        model.addProduct(product2);
        model.addProduct(product3);
        expectedModel.addProduct(product1);
        expectedModel.addProduct(product2);
        expectedModel.addProduct(product3);
    }

    @Test
    public void execute_viewZeroIndex_showsInvalidIndex() {
        String[] index = {"0"};
        ViewProductCommand viewProductCommand = new ViewProductCommand(
                new ProductContainsIdPredicate(Arrays.asList(index))
        );
        assertCommandFailure(viewProductCommand, model, Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_viewInvalidIndex_showsInvalidIndex() {
        int outOfBoundIndex = model.getFilteredProductList().size() + 1;
        String[] index = {String.valueOf(outOfBoundIndex)};
        ViewProductCommand viewProductCommand = new ViewProductCommand(
                new ProductContainsIdPredicate(Arrays.asList(index))
        );
        assertCommandFailure(viewProductCommand, model, Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_viewFirstValidIndex_success() {
        String[] index = {"1"};
        ViewProductCommand viewProductCommand = new ViewProductCommand(
                new ProductContainsIdPredicate(Arrays.asList(index))
        );
        try {
            CommandResult commandResult = viewProductCommand.execute(model);
            assertCommandSuccess(viewProductCommand, model, commandResult, expectedModel);
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void execute_viewLastValidIndex_success() {
        String[] index = {"3"};
        ViewProductCommand viewProductCommand = new ViewProductCommand(
                new ProductContainsIdPredicate(Arrays.asList(index))
        );
        try {
            CommandResult commandResult = viewProductCommand.execute(model);
            assertCommandSuccess(viewProductCommand, model, commandResult, expectedModel);
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void execute_viewMiddleValidIndex_success() {
        String[] index = {"2"};
        ViewProductCommand viewProductCommand = new ViewProductCommand(
                new ProductContainsIdPredicate(Arrays.asList(index))
        );
        try {
            CommandResult commandResult = viewProductCommand.execute(model);
            assertCommandSuccess(viewProductCommand, model, commandResult, expectedModel);
        } catch (CommandException e) {
            fail();
        }
    }
}
