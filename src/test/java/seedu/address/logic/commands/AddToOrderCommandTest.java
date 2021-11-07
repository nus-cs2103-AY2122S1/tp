package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddToOrderCommand.MESSAGE_EXTRA_PRICE_FLAG;
import static seedu.address.logic.commands.AddToOrderCommand.MESSAGE_EXTRA_TAG_FLAG;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;
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
    public void constructor() {
        // EP: null parameters
        assertThrows(NullPointerException.class, () -> new AddToOrderCommand(null));
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
        String message = new AddToOrderCommand(new ItemDescriptor())
                .itemExceedsCount(DONUT, 10, 5);
        assertCommandFailure(addCommand, modelWithOrder, expectedModel, message);
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
        String message = new AddToOrderCommand(new ItemDescriptor())
                .itemExceedsCount(DONUT, 10, 5);
        assertCommandFailure(addCommand, modelWithOrder, expectedModel, message);
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
        String message = new AddToOrderCommand(new ItemDescriptor())
                .itemExceedsCount(DONUT, 10, 5);
        assertCommandFailure(addCommand, modelWithOrder, expectedModel, message);
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
        expectedModel.updateFilteredItemList(DISPLAY_INVENTORY, toAddDescriptor::isMatch);
        String expectedMessage = AddToOrderCommand.MESSAGE_MULTIPLE_MATCHES;

        assertCommandFailure(addCommand, modelWithOrder, expectedModel, expectedMessage);
    }

    @Test
    public void execute_eitherNameOrIdExistsNotBoth_throwCommandException() {
        ItemDescriptor toAddDescriptorCorrectName = new ItemDescriptorBuilder()
                .withName(VALID_NAME_DONUT)
                .withId(VALID_ID_BAGEL)
                .withCount(1)
                .build();

        ItemDescriptor toAddDescriptorCorrectId = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withId(VALID_ID_DONUT)
                .withCount(1)
                .build();

        AddToOrderCommand addCommandCorrectName = new AddToOrderCommand(toAddDescriptorCorrectName);
        AddToOrderCommand addCommandCorrectId = new AddToOrderCommand(toAddDescriptorCorrectId);

        Model expectedModel = getModelWithOrder();

        String expectedMessageCorrectName = AddToOrderCommand.MESSAGE_ID_NOT_FOUND;

        String expectedMessageCorrectId = AddToOrderCommand.MESSAGE_NAME_NOT_FOUND;

        assertCommandFailure(addCommandCorrectName, modelWithOrder, expectedModel, expectedMessageCorrectName);
        assertCommandFailure(addCommandCorrectId, modelWithOrder, expectedModel, expectedMessageCorrectId);
    }

    @Test
    public void execute_extraPriceFlag_itemAddedToOrder() {
        ItemDescriptor toAddDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_DONUT)
                .withCount(DONUT.getCount())
                .withCostPrice(VALID_COSTPRICE_DONUT)
                .build();

        AddToOrderCommand addCommand = new AddToOrderCommand(toAddDescriptor);
        CommandResult expectedResult = new CommandResult(
                String.format(AddToOrderCommand.MESSAGE_SUCCESS + "\n" + MESSAGE_EXTRA_PRICE_FLAG,
                        DONUT.getCount(), VALID_NAME_DONUT));

        // Item not in order
        Model expectedModel = getModelWithOrder();
        expectedModel.addToOrder(DONUT);

        assertCommandSuccess(addCommand, modelWithOrder, expectedResult, expectedModel);
    }

    @Test
    public void execute_extraTagFlag_itemAddedToOrder() {
        ItemDescriptor toAddDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_DONUT)
                .withCount(DONUT.getCount())
                .withTags(VALID_TAG_BAKED)
                .build();

        AddToOrderCommand addCommand = new AddToOrderCommand(toAddDescriptor);
        CommandResult expectedResult = new CommandResult(
                String.format(AddToOrderCommand.MESSAGE_SUCCESS + "\n" + MESSAGE_EXTRA_TAG_FLAG,
                        DONUT.getCount(), VALID_NAME_DONUT));

        // Item not in order
        Model expectedModel = getModelWithOrder();
        expectedModel.addToOrder(DONUT);

        assertCommandSuccess(addCommand, modelWithOrder, expectedResult, expectedModel);
    }

}
