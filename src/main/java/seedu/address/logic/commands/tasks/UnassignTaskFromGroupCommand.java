package seedu.address.logic.commands.tasks;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskAssignable;
import seedu.address.model.ViewingType;
import seedu.address.model.group.Group;

public class UnassignTaskFromGroupCommand extends UnassignTaskCommand {

    public static final String COMMAND_WORD = "-unag";

    public static final String MESSAGE_USAGE = String.format(
            UnassignTaskCommand.MESSAGE_USAGE, COMMAND_WORD, "group", "GROUP");

    /**
     * Constructs a {@code UnassignTaskFromGroupCommand}
     *
     * @param groupIndex of the group in the filtered group list to unassign from
     * @param taskIndex of the task in the filtered task list to unassign
     */
    public UnassignTaskFromGroupCommand(Index groupIndex, Index taskIndex) {
        super(groupIndex, taskIndex);
    }

    @Override
    protected String invalidDisplayedIndexMessage() {
        return Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX;
    }

    @Override
    protected List<Group> getTaskAssignableListFromModel(Model model) {
        return model.getFilteredGroupList();
    }

    @Override
    protected void updateModel(Model model, TaskAssignable taskAssignableToEdit, TaskAssignable
            newTaskAssignable) {
        requireAllNonNull(model, taskAssignableToEdit, newTaskAssignable);

        if (!(taskAssignableToEdit instanceof Group && newTaskAssignable instanceof Group)) {
            return;
        }
        Group groupToEdit = (Group) taskAssignableToEdit;
        Group newGroup = (Group) newTaskAssignable;

        model.setGroup(groupToEdit, newGroup);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        model.setGroupToView(newGroup);
        model.setViewingType(ViewingType.GROUP);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return executeWithGivenMessage(model, "group");
    }
}

