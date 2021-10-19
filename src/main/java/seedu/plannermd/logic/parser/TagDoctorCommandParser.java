package seedu.plannermd.logic.parser;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.tagcommand.AddDoctorTagCommand;
import seedu.plannermd.logic.commands.tagcommand.DeleteDoctorTagCommand;
import seedu.plannermd.logic.commands.tagcommand.TagCommand;
import seedu.plannermd.model.tag.Tag;

public class TagDoctorCommandParser extends TagCommandParser {
    @Override
    protected String getUsageMessage() {
        return AddDoctorTagCommand.MESSAGE_USAGE + "\n" + DeleteDoctorTagCommand.MESSAGE_USAGE;
    }

    @Override
    protected TagCommand getDeleteTagCommand(Index index, Tag tag) {
        return new DeleteDoctorTagCommand(index, tag);
    }

    @Override
    protected TagCommand getAddTagCommand(Index index, Tag tag) {
        return new AddDoctorTagCommand(index, tag);
    }
}
