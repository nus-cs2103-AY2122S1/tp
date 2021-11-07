package seedu.edrecord.logic.parser;

import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edrecord.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.GROUP_DESC_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.INFO_DESC_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.edrecord.logic.commands.CommandTestUtil.MODULE_DESC_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.edrecord.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.edrecord.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.edrecord.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.edrecord.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.edrecord.testutil.TypicalModules.setTypicalModuleSystem;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import seedu.edrecord.commons.core.index.Index;
import seedu.edrecord.logic.commands.AddCommand;
import seedu.edrecord.logic.commands.GradeCommand;
import seedu.edrecord.model.assignment.Grade;
import seedu.edrecord.model.assignment.Score;
import seedu.edrecord.model.name.Name;

public class GradeCommandParserTest {
    private final Index INDEX_ONE = Index.fromOneBased(1);
    private final Index INDEX_TWO = Index.fromOneBased(2);
    private final Index INDEX_THREE = Index.fromOneBased(3);
    private final Index INDEX_FIVE = Index.fromOneBased(5);
    private final Index INDEX_NINETY_NINE = Index.fromOneBased(99);

    private final GradeCommandParser parser = new GradeCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        GradeCommand expectedCommand1 = new GradeCommand(INDEX_ONE, INDEX_THREE,
                new Grade(Optional.of(new Score("30")), Grade.GradeStatus.GRADED));
        assertParseSuccess(parser, "1 " + PREFIX_ID + "3 " + PREFIX_STATUS + "grAdEd " + PREFIX_SCORE + "30",
                expectedCommand1);

        GradeCommand expectedCommand2 = new GradeCommand(INDEX_TWO, INDEX_ONE,
                new Grade(Optional.of(new Score("0")), Grade.GradeStatus.GRADED));
        assertParseSuccess(parser, "2 " + PREFIX_ID + "1 " + PREFIX_STATUS + "graded " + PREFIX_SCORE + "0",
                expectedCommand2);
    }

    @Test
    public void parse_optionalFieldMissing_success() {
        GradeCommand expectedCommand1 = new GradeCommand(INDEX_ONE, INDEX_THREE,
                new Grade(Optional.empty(), Grade.GradeStatus.NOT_SUBMITTED));
        assertParseSuccess(parser, "1 " + PREFIX_ID + "3 " + PREFIX_STATUS + "not SubmiTTed", expectedCommand1);

        GradeCommand expectedCommand2 = new GradeCommand(INDEX_FIVE, INDEX_NINETY_NINE,
                new Grade(Optional.empty(), Grade.GradeStatus.SUBMITTED));
        assertParseSuccess(parser, "5 " + PREFIX_ID + "99 " + PREFIX_STATUS + "submiTTed", expectedCommand2);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE);

        // missing ID prefix
        assertParseFailure(parser, "1 " + "3 " + PREFIX_STATUS + "grAdEd " + PREFIX_SCORE + "30", expectedMessage);

        // missing status prefix
        assertParseFailure(parser, "1 " + PREFIX_ID + "3 " + "grAdEd " + PREFIX_SCORE + "30", expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, "1 " + "3 " + "grAdEd " + "30", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid ID
        assertParseFailure(parser, "1 " + PREFIX_ID + "a " + PREFIX_STATUS + "grAdEd " + PREFIX_SCORE + "30",
                ParserUtil.MESSAGE_INVALID_ID);

        // invalid status
        assertParseFailure(parser, "1 " + PREFIX_ID + "3 " + PREFIX_STATUS + "not_submitted " + PREFIX_SCORE + "30",
                Grade.MESSAGE_CONSTRAINTS);

        //TODO add test for status-score mismatch
    }
}
