package seedu.edrecord.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.core.Messages.MESSAGE_NO_MODULE_SELECTED;

import java.util.List;
import java.util.stream.Collectors;

import seedu.edrecord.model.Model;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.module.Module;

/**
 * Lists all classes in the selected module to the user.
 */
public class ListClassesCommand extends Command {

    public static final String COMMAND_WORD = "lsclass";
    public static final String MESSAGE_SUCCESS = "Here are your classes: %s.";
    public static final String CLASS_LIST_DELIM = ", ";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Module m = model.getSelectedModule().getValue();
        if (m == null) {
            return new CommandResult(MESSAGE_NO_MODULE_SELECTED);
        }

        List<String> ls = m.getGroupList().stream().map(Group::toString).collect(Collectors.toList());
        return new CommandResult(String.format(MESSAGE_SUCCESS, String.join(CLASS_LIST_DELIM, ls)));
    }

}
