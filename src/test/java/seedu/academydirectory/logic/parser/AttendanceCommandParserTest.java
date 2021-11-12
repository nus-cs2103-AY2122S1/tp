package seedu.academydirectory.logic.parser;

import static seedu.academydirectory.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_STUDIO_ATTENDANCE;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_STUDIO_SESSION;
import static seedu.academydirectory.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.academydirectory.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.academydirectory.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.commons.core.index.Index;
import seedu.academydirectory.logic.commands.AttendanceCommand;
import seedu.academydirectory.model.student.StudioRecord;

public class AttendanceCommandParserTest {
    private AttendanceCommandParser parser = new AttendanceCommandParser();
    private final String nonEmptySession = " 1 ";
    private final String attendanceStatusInt = " 1 ";
    private final boolean attendanceStatus = true;

    @Test
    public void parse_indexSpecified_success() {
        // have attendance
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + " "
                + PREFIX_STUDIO_SESSION + nonEmptySession
                + PREFIX_STUDIO_ATTENDANCE
                + attendanceStatusInt;
        ArrayList<Index> indexArrayList = new ArrayList<>();
        indexArrayList.add(INDEX_FIRST_STUDENT);
        AttendanceCommand expectedCommand =
                new AttendanceCommand(attendanceStatus, 1, indexArrayList);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE);
        String expectedInvalidStudioSessionMessage = StudioRecord.MESSAGE_CONSTRAINTS;
        String expectedInvalidAttendanceMessage = ParserUtil.MESSAGE_INVALID_ATTENDANCE_STATUS;

        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInputNoIndex = " "
                + PREFIX_STUDIO_SESSION
                + nonEmptySession
                + PREFIX_STUDIO_ATTENDANCE
                + attendanceStatusInt;
        String userInputNoSession = targetIndex.getOneBased()
                + " "
                + PREFIX_STUDIO_SESSION
                + " "
                + PREFIX_STUDIO_ATTENDANCE
                + attendanceStatusInt;
        String userInputNoAttendance = targetIndex.getOneBased()
                + " "
                + PREFIX_STUDIO_SESSION
                + nonEmptySession
                + PREFIX_STUDIO_ATTENDANCE;

        // no parameters
        assertParseFailure(parser, AttendanceCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, userInputNoIndex, expectedMessage);

        // no session
        assertParseFailure(parser, userInputNoSession, expectedInvalidStudioSessionMessage);

        // no attendance
        assertParseFailure(parser, userInputNoAttendance, expectedInvalidAttendanceMessage);

    }
}
