package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.StudentId;

public class DeleteStudentCommandParserTest {

    private DeleteStudentCommandParser parser = new DeleteStudentCommandParser();
    private ModuleName moduleName = new ModuleName(MODULE_NAME_0);
    private StudentId studentId = new StudentId(VALID_STUDENT_ID_AMY);

    @Test
    public void parse_validArgs_returnsDeleteStudentCommand() {
        assertParseSuccess(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY,
                new DeleteStudentCommand(studentId, moduleName));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // invalid module name
        assertParseFailure(parser, INVALID_MODULE_NAME_DESC + STUDENT_ID_DESC_AMY,
                ModuleName.MESSAGE_CONSTRAINTS);

        // invalid student id
        assertParseFailure(parser, MODULE_NAME_DESC_0 + INVALID_STUDENT_ID_DESC,
                StudentId.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStudentCommand.MESSAGE_USAGE);
        // missing module name prefix
        assertParseFailure(parser, MODULE_NAME_0 + STUDENT_ID_DESC_AMY, expectedMessage);

        // missing student id prefix
        assertParseFailure(parser, MODULE_NAME_DESC_0 + VALID_STUDENT_ID_AMY, expectedMessage);
    }
}
