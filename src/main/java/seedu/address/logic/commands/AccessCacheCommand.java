package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

import java.util.List;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class AccessCacheCommand extends Command {

    private final ;

    /**
     * @param index of the person in the filtered person list to edit the task List of
     * @param newTasks to add to the person's task list
     */
    public AddTaskCommand(Index index, List<Task> newTasks) {
        requireAllNonNull(index, newTasks);
        th
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        ;
    }
}
