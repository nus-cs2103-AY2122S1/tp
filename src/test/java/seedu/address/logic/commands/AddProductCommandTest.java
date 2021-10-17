package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.AddProductCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.commons.Name;
import seedu.address.model.product.Product;
import seedu.address.model.product.UnitPrice;

public class AddProductCommandTest {
    private Name name = new Name("pen");
    private UnitPrice unitPrice = new UnitPrice("15");
    private AddProductCommand.AddProductDescriptor descriptor =
            new AddProductCommand.AddProductDescriptor(name, unitPrice);
    private AddProductCommand addProductCommand = new AddProductCommand(descriptor);

    @Test
    public void constructor_nullDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddProductCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addProductCommand.execute(null));
    }

    @Test
    public void execute_duplicateProduct_throwsCommandException() {
        assertThrows(CommandException.class, () -> addProductCommand.execute(new ModelDuplicateProductStub()));
    }

    @Test
    public void execute_newProduct_returnsCommandResult() {
        Product productToAdd = new Product(name, unitPrice, null);
        CommandResult expectedResult = new CommandResult(String.format(MESSAGE_SUCCESS, productToAdd));
        try {
            CommandResult actualResult = addProductCommand.execute(new ModelStub());
            // compare the feedback to user excluding the id.
            String actualString = actualResult.getFeedbackToUser();
            actualString = actualString.substring(actualString.indexOf("Name"));
            String expectedString = expectedResult.getFeedbackToUser();
            expectedString = expectedString.substring(expectedString.indexOf("Name"));
            assertEquals(expectedString, actualString);
            assertEquals(expectedResult.isShowHelp(), actualResult.isShowHelp());
            assertEquals(expectedResult.isExit(), actualResult.isExit());
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void equals_null_returnsFalse() {
        assertFalse(addProductCommand.equals(null));
    }

    @Test void equals_itself_returnsTrue() {
        assertTrue(addProductCommand.equals(addProductCommand));
    }

    public class ModelDuplicateProductStub extends ModelManager {
        /**
         * Assume there are duplicate products, return true.
         *
         * @param product the product to be checked.
         * @return True.
         */
        @Override
        public boolean hasProduct(Product product) {
            return true;
        }
    }

    public class ModelStub extends ModelManager {
        /**
         * Assume there are no duplicate products, return false.
         *
         * @param product the product to be checked.
         * @return False.
         */
        @Override
        public boolean hasProduct(Product product) {
            return false;
        }
    }
}
