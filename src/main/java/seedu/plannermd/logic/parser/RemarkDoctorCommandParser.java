package seedu.plannermd.logic.parser;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.remarkcommand.RemarkCommand;
import seedu.plannermd.logic.commands.remarkcommand.RemarkDoctorCommand;
import seedu.plannermd.model.person.Remark;

/**
 * Parses input arguments and creates a RemarkDoctorCommand.
 */
public class RemarkDoctorCommandParser extends RemarkCommandParser {

    @Override
    protected String getUsageMessage() {
        return RemarkDoctorCommand.MESSAGE_USAGE;
    }

    @Override
    protected RemarkCommand getPersonRemarkCommand(Index index, Remark remark) {
        return new RemarkDoctorCommand(index, remark);
    }
}
