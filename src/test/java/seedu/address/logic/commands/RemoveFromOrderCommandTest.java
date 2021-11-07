package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.RemoveFromOrderCommand.MESSAGE_ID_NOT_FOUND;
import static seedu.address.logic.commands.RemoveFromOrderCommand.MESSAGE_NAME_NOT_FOUND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.BAGEL;
import static seedu.address.testutil.TypicalItems.DONUT;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.order.Order;
import seedu.address.testutil.ItemDescriptorBuilder;
import seedu.address.testutil.TypicalBookkeeping;
import seedu.address.testutil.TypicalTransactions;

public class RemoveFromOrderCommandTest {

    private Model modelWithoutOrder = new ModelManager(getTypicalInventory(), new UserPrefs(),
            TypicalTransactions.getTypicalTransactionList(),
            TypicalBookkeeping.getTypicalBookkeeping());
    private Model modelWithOrder = getModelWithOrderedDonut();

    /**
     * Returns a model with 5 donuts in its unclosed order
     */
    private Model getModelWithOrderedDonut() {
        Model model = new ModelManager(getTypicalInventory(), new UserPrefs(),
                TypicalTransactions.getTypicalTransactionList(),
                TypicalBookkeeping.getTypicalBookkeeping());
        model.addItem(DONUT.updateCount(5));
        model.setOrder(new Order());
        model.addToOrder(DONUT.updateCount(5));

        return model;
    }

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_modelHasNoUnclosedOrder_giveNoUnclosedOrderMessage() {
        ItemDescriptor toAddDescriptor = new ItemDescriptor(DONUT);

        RemoveFromOrderCommand command = new RemoveFromOrderCommand(toAddDescriptor);

        assertCommandFailure(command, modelWithoutOrder, RemoveFromOrderCommand.MESSAGE_NO_UNCLOSED_ORDER);
    }

    @Test
    public void execute_nameInOrderRemoveSome_itemCountDecreasedInOrder() {
        ItemDescriptor toRemoveDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_DONUT)
                .withCount(1)
                .build();
        RemoveFromOrderCommand command = new RemoveFromOrderCommand(toRemoveDescriptor);
        CommandResult expectedResult = new CommandResult(
                String.format(RemoveFromOrderCommand.MESSAGE_SUCCESS, 1, VALID_NAME_DONUT));

        Model expectedModel = getModelWithOrderedDonut();
        expectedModel.removeFromOrder(DONUT, 1);

        assertCommandSuccess(command, modelWithOrder, expectedResult, expectedModel);
    }

    @Test
    public void execute_idInInventoryRemoveSome_itemCountDecreasedInOrder() {
        ItemDescriptor toRemoveDescriptor = new ItemDescriptorBuilder()
                .withId(VALID_ID_DONUT)
                .withCount(1)
                .build();
        RemoveFromOrderCommand command = new RemoveFromOrderCommand(toRemoveDescriptor);
        CommandResult expectedResult = new CommandResult(
                String.format(RemoveFromOrderCommand.MESSAGE_SUCCESS, 1, VALID_NAME_DONUT));

        Model expectedModel = getModelWithOrderedDonut();
        expectedModel.removeFromOrder(DONUT, 1);

        assertCommandSuccess(command, modelWithOrder, expectedResult, expectedModel);
    }

    @Test
    public void execute_nameAndIdInInventoryRemoveSome_itemCountDecreasedInOrder() {
        ItemDescriptor toRemoveDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_DONUT)
                .withId(VALID_ID_DONUT)
                .withCount(1)
                .build();
        RemoveFromOrderCommand command = new RemoveFromOrderCommand(toRemoveDescriptor);
        CommandResult expectedResult = new CommandResult(
                String.format(RemoveFromOrderCommand.MESSAGE_SUCCESS, 1, VALID_NAME_DONUT));

        Model expectedModel = getModelWithOrderedDonut();
        expectedModel.removeFromOrder(DONUT, 1);

        assertCommandSuccess(command, modelWithOrder, expectedResult, expectedModel);
    }

    @Test
    public void execute_nameExistsIdDoesNot_commandFailure() {
        ItemDescriptor toRemoveDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_DONUT)
                .withId(VALID_ID_BAGEL)
                .withCount(1)
                .build();
        RemoveFromOrderCommand command = new RemoveFromOrderCommand(toRemoveDescriptor);

        Model expectedModel = getModelWithOrderedDonut();
        expectedModel.removeFromOrder(DONUT, 1);

        assertCommandFailure(command, modelWithOrder, MESSAGE_ID_NOT_FOUND);
    }

    @Test
    public void execute_idExistsNameDoesNot_commandFailure() {
        ItemDescriptor toRemoveDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withId(VALID_ID_DONUT)
                .withCount(1)
                .build();
        RemoveFromOrderCommand command = new RemoveFromOrderCommand(toRemoveDescriptor);

        Model expectedModel = getModelWithOrderedDonut();
        expectedModel.removeFromOrder(DONUT, 1);

        assertCommandFailure(command, modelWithOrder, MESSAGE_NAME_NOT_FOUND);
    }

    @Test
    public void execute_nameInOrderRemoveAll_itemRemoved() {
        ItemDescriptor toRemoveDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_DONUT)
                .withCount(DONUT.getCount())
                .build();
        RemoveFromOrderCommand command = new RemoveFromOrderCommand(toRemoveDescriptor);
        CommandResult expectedResult = new CommandResult(
                String.format(RemoveFromOrderCommand.MESSAGE_SUCCESS, DONUT.getCount(), VALID_NAME_DONUT));

        Model expectedModel = getModelWithOrderedDonut();
        expectedModel.removeFromOrder(DONUT, DONUT.getCount());

        assertCommandSuccess(command, modelWithOrder, expectedResult, expectedModel);
    }

    @Test
    public void execute_idInInventoryRemoveAll_itemRemoved() {
        ItemDescriptor toRemoveDescriptor = new ItemDescriptorBuilder()
                .withId(VALID_ID_DONUT)
                .withCount(DONUT.getCount())
                .build();
        RemoveFromOrderCommand command = new RemoveFromOrderCommand(toRemoveDescriptor);
        CommandResult expectedResult = new CommandResult(
                String.format(RemoveFromOrderCommand.MESSAGE_SUCCESS, DONUT.getCount(), VALID_NAME_DONUT));

        Model expectedModel = getModelWithOrderedDonut();
        expectedModel.removeFromOrder(DONUT, DONUT.getCount());

        assertCommandSuccess(command, modelWithOrder, expectedResult, expectedModel);
    }

    @Test
    public void execute_nameAndIdInInventoryRemoveAll_itemRemoved() {
        ItemDescriptor toRemoveDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_DONUT)
                .withId(VALID_ID_DONUT)
                .withCount(DONUT.getCount())
                .build();
        RemoveFromOrderCommand command = new RemoveFromOrderCommand(toRemoveDescriptor);
        CommandResult expectedResult = new CommandResult(
                String.format(RemoveFromOrderCommand.MESSAGE_SUCCESS, DONUT.getCount(), VALID_NAME_DONUT));

        Model expectedModel = getModelWithOrderedDonut();
        expectedModel.removeFromOrder(DONUT, DONUT.getCount());

        assertCommandSuccess(command, modelWithOrder, expectedResult, expectedModel);
    }

    @Test
    public void execute_itemNotInOrder_throwCommandException() {
        ItemDescriptor toRemoveDescriptor = new ItemDescriptor(BAGEL);
        RemoveFromOrderCommand command = new RemoveFromOrderCommand(toRemoveDescriptor);

        String expectedMessage = RemoveFromOrderCommand.MESSAGE_ITEM_NOT_FOUND;

        assertCommandFailure(command, modelWithOrder, expectedMessage);
    }

    @Test
    public void execute_overRemoveItem_throwCommandException() {
        ItemDescriptor toRemoveDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_DONUT)
                .withCount(BAGEL.getCount() + 1)
                .build();

        RemoveFromOrderCommand command = new RemoveFromOrderCommand(toRemoveDescriptor);

        assertCommandFailure(command, modelWithOrder, RemoveFromOrderCommand.MESSAGE_INSUFFICIENT_ITEM);
    }

    @Test
    public void execute_multipleMatchesInOrder_throwCommandException() {
        modelWithOrder.addToOrder(BAGEL);
        ItemDescriptor toRemoveDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_DONUT)
                .withId(VALID_ID_BAGEL)
                .withCount(1)
                .build();

        RemoveFromOrderCommand command = new RemoveFromOrderCommand(toRemoveDescriptor);

        assertCommandFailure(command, modelWithOrder, RemoveFromOrderCommand.MESSAGE_MULTIPLE_MATCHES);
    }


}
