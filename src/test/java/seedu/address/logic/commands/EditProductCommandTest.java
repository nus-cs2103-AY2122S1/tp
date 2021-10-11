package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CANNON;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIT_PRICE_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProductAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PRODUCT;
import static seedu.address.testutil.TypicalProducts.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditProductCommand.EditProductDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.product.Product;
import seedu.address.testutil.EditProductDescriptorBuilder;
import seedu.address.testutil.ProductBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditProductCommand.
 * Edit filtered list where index is larger than size of filtered list,
 * but smaller than size of address book
 */

public class EditProductCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Product editedProduct = new ProductBuilder().build();
        EditProductDescriptor descriptor = new EditProductDescriptorBuilder(editedProduct).build();
        EditProductCommand editProductCommand = new EditProductCommand(INDEX_FIRST_PRODUCT, descriptor);

        String expectedMessage = String.format(EditProductCommand.MESSAGE_EDIT_PRODUCT_SUCCESS, editedProduct);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setProduct(model.getFilteredProductList().get(0), editedProduct);

        assertCommandSuccess(editProductCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastProduct = Index.fromOneBased(model.getFilteredProductList().size());
        Product lastProduct = model.getFilteredProductList().get(indexLastProduct.getZeroBased());

        ProductBuilder productInList = new ProductBuilder(lastProduct);
        Product editedProduct = productInList
                .withName(VALID_NAME_DAISY)
                .withUnitPrice(VALID_UNIT_PRICE_DAISY)
                .build();

        EditProductDescriptor descriptor = new EditProductDescriptorBuilder()
                .withName(VALID_NAME_DAISY)
                .withUnitPrice(VALID_UNIT_PRICE_DAISY)
                .build();
        EditProductCommand editProductCommand = new EditProductCommand(indexLastProduct, descriptor);

        String expectedMessage = String.format(EditProductCommand.MESSAGE_EDIT_PRODUCT_SUCCESS, editedProduct);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setProduct(lastProduct, editedProduct);

        assertCommandSuccess(editProductCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditProductCommand editProductCommand =
                new EditProductCommand(INDEX_FIRST_PRODUCT, new EditProductDescriptor());
        Product editedProduct = model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased());

        String expectedMessage = String.format(EditProductCommand.MESSAGE_EDIT_PRODUCT_SUCCESS, editedProduct);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editProductCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);

        Product productInFilteredList = model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased());
        Product editedProduct = new ProductBuilder(productInFilteredList).withName(VALID_NAME_DAISY).build();
        EditProductCommand editProductCommand = new EditProductCommand(INDEX_FIRST_PRODUCT,
                new EditProductDescriptorBuilder().withName(VALID_NAME_DAISY).build());

        String expectedMessage = String.format(EditProductCommand.MESSAGE_EDIT_PRODUCT_SUCCESS, editedProduct);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setProduct(model.getFilteredProductList().get(0), editedProduct);

        assertCommandSuccess(editProductCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateProductUnfilteredList_failure() {
        Product firstProduct = model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased());
        EditProductDescriptor descriptor = new EditProductDescriptorBuilder(firstProduct).build();
        EditProductCommand editProductCommand = new EditProductCommand(INDEX_SECOND_PRODUCT, descriptor);

        assertCommandFailure(editProductCommand, model, EditProductCommand.MESSAGE_DUPLICATE_PRODUCT);
    }

    @Test
    public void execute_duplicateProductFilteredList_failure() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);

        // edit product in filtered list into a duplicate in address book
        Product productInList = model.getAddressBook().getProductList().get(INDEX_SECOND_PRODUCT.getZeroBased());
        EditProductCommand editProductCommand = new EditProductCommand(INDEX_FIRST_PRODUCT,
                new EditProductDescriptorBuilder(productInList).build());

        assertCommandFailure(editProductCommand, model, EditProductCommand.MESSAGE_DUPLICATE_PRODUCT);
    }

    @Test
    public void execute_invalidProductIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProductList().size() + 1);
        EditProductDescriptor descriptor = new EditProductDescriptorBuilder().withName(VALID_NAME_DAISY).build();
        EditProductCommand editProductCommand = new EditProductCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editProductCommand, model, Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list, but smaller than size of address book
     */
    @Test
    public void execute_invalidProductIndexFilteredList_failure() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);
        Index outOfBoundIndex = INDEX_SECOND_PRODUCT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getProductList().size());

        EditProductCommand editProductCommand = new EditProductCommand(outOfBoundIndex,
                new EditProductDescriptorBuilder().withName(VALID_NAME_DAISY).build());

        assertCommandFailure(editProductCommand, model, Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditProductCommand standardCommand = new EditProductCommand(INDEX_FIRST_PRODUCT, DESC_CANNON);

        // same values -> returns true
        EditProductDescriptor copyDescriptor = new EditProductDescriptor(DESC_CANNON);
        EditProductCommand commandWithSameValues = new EditProductCommand(INDEX_FIRST_PRODUCT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditProductCommand(INDEX_SECOND_PRODUCT, DESC_CANNON)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditProductCommand(INDEX_FIRST_PRODUCT, DESC_DAISY)));
    }
}
