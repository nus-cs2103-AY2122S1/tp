package seedu.plannermd.logic.parser;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.tagcommand.AddPatientTagCommand;
import seedu.plannermd.logic.commands.tagcommand.DeletePatientTagCommand;
import seedu.plannermd.logic.commands.tagcommand.TagCommand;
import seedu.plannermd.model.tag.Tag;

public class TagPatientCommandParser extends TagCommandParser {

    @Override
    protected String getUsageMessage() {
        return AddPatientTagCommand.MESSAGE_USAGE + "\n" + DeletePatientTagCommand.MESSAGE_USAGE;
    }

    @Override
    protected TagCommand getDeleteTagCommand(Index index, Tag tag) {
        return new DeletePatientTagCommand(index, tag);
    }

    @Override
    protected TagCommand getAddTagCommand(Index index, Tag tag) {
        return new AddPatientTagCommand(index, tag);
    }
}
