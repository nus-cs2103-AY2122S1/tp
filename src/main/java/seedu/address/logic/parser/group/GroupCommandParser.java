package seedu.address.logic.parser.group;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.group.GroupCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.model.group.Group;

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
