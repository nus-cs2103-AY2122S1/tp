package seedu.edrecord.logic.parser;

import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edrecord.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.edrecord.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.edrecord.commons.core.index.Index;
import seedu.edrecord.logic.commands.DeleteGradeCommand;

public class DeleteGradeCommandParserTest {
    private static final Index INDEX_ONE = Index.fromOneBased(1);
    private static final Index INDEX_THREE = Index.fromOneBased(3);

    private final DeleteGradeCommandParser parser = new DeleteGradeCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteGradeCommand() {
        assertParseSuccess(parser, "1 " + PREFIX_ID + "3", new DeleteGradeCommand(INDEX_ONE, INDEX_THREE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a " + PREFIX_ID + "3",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGradeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidId_throwsParseException() {
        assertParseFailure(parser, "3 " + PREFIX_ID + "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGradeCommand.MESSAGE_USAGE));
    }
}
