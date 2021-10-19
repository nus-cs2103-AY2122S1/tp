package seedu.plannermd.logic.parser;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.remarkcommand.RemarkCommand;
import seedu.plannermd.logic.commands.remarkcommand.RemarkPatientCommand;
import seedu.plannermd.model.person.Remark;

/**
 * Parses input arguments and creates a RemarkPatientCommand.
 */
public class RemarkPatientCommandParser extends RemarkCommandParser {

    @Override
    protected String getUsageMessage() {
        return RemarkPatientCommand.MESSAGE_USAGE;
    }

    @Override
    protected RemarkCommand getPersonRemarkCommand(Index index, Remark remark) {
        return new RemarkPatientCommand(index, remark);
    }
}
