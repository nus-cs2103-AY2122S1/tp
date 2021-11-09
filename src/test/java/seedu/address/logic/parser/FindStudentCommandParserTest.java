package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.StudentId;

public class FindStudentCommandParserTest {

    private FindStudentCommandParser parser = new FindStudentCommandParser();

    @Test
    public void parse_noStudentId_throwsParseException() {
        assertParseFailure(parser, MODULE_NAME_DESC_0, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noModuleName_throwsParseException() {
        assertParseFailure(parser, STUDENT_ID_DESC_AMY, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindStudentCommand() {
        // no leading and trailing whitespaces
        FindStudentCommand expectedFindStudentCommand =
                new FindStudentCommand(new ModuleName(MODULE_NAME_0), new StudentId(VALID_STUDENT_ID_AMY));
        assertParseSuccess(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY, expectedFindStudentCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, MODULE_NAME_DESC_0 + " " + PREFIX_STUDENT_ID + " " + STUDENT_ID_DESC_AMY,
                expectedFindStudentCommand);
    }
}
