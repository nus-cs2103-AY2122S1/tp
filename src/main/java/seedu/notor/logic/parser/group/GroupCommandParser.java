package seedu.notor.logic.parser.group;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.group.GroupCommand;
import seedu.notor.logic.parser.Parser;
import seedu.notor.model.group.Group;

public abstract class GroupCommandParser extends Parser<GroupCommand> {
    /**
     * TODO: Stub method.
     */
    public Group parseGroup(Index index) {
        // List<Group> lastShownList = model.getFilteredGroupList();
        // if (index.getZeroBased() >= lastShownList.size()) {
        //     throw new ParseException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        // }
        // return lastShownList.get(index.getZeroBased());
        return null;
    }
}
