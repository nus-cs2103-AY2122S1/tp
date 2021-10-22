package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.BAGEL;
import static seedu.address.testutil.TypicalItems.DONUT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.order.Order;
import seedu.address.testutil.ItemDescriptorBuilder;

public class AddToOrderCommandTest {

    private Model modelWithoutOrder = new ModelManager();
    private Model modelWithOrder = getModelWithOrder();

    /**
     * Returns a model with donuts and an empty unclosed order
     */
    private Model getModelWithOrder() {
        Model model = new ModelManager();
        model.addItem(DONUT);
        model.setOrder(new Order());

        return model;
    }

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_modelHasNoUnclosedOrder_giveNoUnclosedOrderMessage() {
        ItemDescriptor toAddDescriptor = new ItemDescriptor(DONUT);

        AddToOrderCommand command = new AddToOrderCommand(toAddDescriptor);

        assertCommandFailure(command, modelWithoutOrder, AddToOrderCommand.MESSAGE_NO_UNCLOSED_ORDER);
    }

    @Test
    public void execute_itemNameInInventory_itemAddedToOrder() {
        ItemDescriptor toAddDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_DONUT)
                .withCount(DONUT.getCount())
                .build();

        AddToOrderCommand addCommand = new AddToOrderCommand(toAddDescriptor);
        CommandResult expectedResult = new CommandResult(
                String.format(AddToOrderCommand.MESSAGE_SUCCESS, DONUT.getCount(), VALID_NAME_DONUT));

        // Item not in order
        Model expectedModel = getModelWithOrder();
        expectedModel.addToOrder(DONUT);

        assertCommandSuccess(addCommand, modelWithOrder, expectedResult, expectedModel);

        // Item already in order
        expectedModel.addToOrder(DONUT);

        assertCommandSuccess(addCommand, modelWithOrder, expectedResult, expectedModel);
    }

    @Test
    public void execute_itemIdInInventory_itemAddedToOrder() {
        ItemDescriptor toAddDescriptor = new ItemDescriptorBuilder()
                .withId(VALID_ID_DONUT)
                .withCount(DONUT.getCount())
                .build();

        AddToOrderCommand addCommand = new AddToOrderCommand(toAddDescriptor);
        CommandResult expectedResult = new CommandResult(
                String.format(AddToOrderCommand.MESSAGE_SUCCESS, DONUT.getCount(), VALID_NAME_DONUT));

        // Item not in order
        Model expectedModel = getModelWithOrder();
        expectedModel.addToOrder(DONUT);

        assertCommandSuccess(addCommand, modelWithOrder, expectedResult, expectedModel);

        // Item already in order
        expectedModel.addToOrder(DONUT);

        assertCommandSuccess(addCommand, modelWithOrder, expectedResult, expectedModel);
    }

    @Test
    public void execute_itemNameAndIdInInventory_itemAddedToOrder() {
        ItemDescriptor toAddDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_DONUT)
                .withId(VALID_ID_DONUT)
                .withCount(DONUT.getCount())
                .build();

        AddToOrderCommand addCommand = new AddToOrderCommand(toAddDescriptor);
        CommandResult expectedResult = new CommandResult(
                String.format(AddToOrderCommand.MESSAGE_SUCCESS, DONUT.getCount(), VALID_NAME_DONUT));

        // Test when item not in order
        Model expectedModel = getModelWithOrder();
        expectedModel.addToOrder(DONUT);

        assertCommandSuccess(addCommand, modelWithOrder, expectedResult, expectedModel);

        // Test when item already in order
        expectedModel.addToOrder(DONUT);

        assertCommandSuccess(addCommand, modelWithOrder, expectedResult, expectedModel);
    }

    @Test
    public void execute_itemNotInInventory_itemAddedToOrder() {
        ItemDescriptor toAddDescriptor = new ItemDescriptor(BAGEL);
        AddToOrderCommand addCommand = new AddToOrderCommand(toAddDescriptor);

        String expectedMessage = AddToOrderCommand.MESSAGE_ITEM_NOT_FOUND;

        assertCommandFailure(addCommand, modelWithOrder, expectedMessage);
    }

    @Test
    public void execute_multipleMatchesInInventory_throwCommandException() {
        modelWithOrder.addItem(BAGEL);
        ItemDescriptor toAddDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_DONUT)
                .withId(VALID_ID_BAGEL)
                .withCount(1)
                .build();

        AddToOrderCommand addCommand = new AddToOrderCommand(toAddDescriptor);

        Model expectedModel = getModelWithOrder();
        expectedModel.addItem(BAGEL);
        expectedModel.updateFilteredItemList(toAddDescriptor::isMatch);
        String expectedMessage = AddToOrderCommand.MESSAGE_MULTIPLE_MATCHES;

        assertCommandFailure(addCommand, modelWithOrder, expectedModel, expectedMessage);
    }

}
