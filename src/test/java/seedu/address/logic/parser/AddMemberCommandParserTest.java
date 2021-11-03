package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GROUPNAME_DESC_G1;
import static seedu.address.logic.commands.CommandTestUtil.GROUPNAME_DESC_G2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GROUPNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_G1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.model.group.GroupName;

public class AddMemberCommandParserTest {
    private final AddMemberCommandParser parser = new AddMemberCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        GroupName expectedGroupName = new GroupName(VALID_GROUPNAME_G1);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDEX_FIRST_STUDENT.getOneBased() + GROUPNAME_DESC_G1,
                new AddMemberCommand(INDEX_FIRST_STUDENT, expectedGroupName));

        // multiple group names - last name accepted
        assertParseSuccess(parser, INDEX_FIRST_STUDENT.getOneBased() + GROUPNAME_DESC_G2 + GROUPNAME_DESC_G1,
                new AddMemberCommand(INDEX_FIRST_STUDENT, expectedGroupName));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMemberCommand.MESSAGE_USAGE);

        // missing group prefix
        assertParseFailure(parser, INDEX_FIRST_STUDENT + VALID_GROUPNAME_G1, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid group name
        assertParseFailure(parser, INDEX_FIRST_STUDENT.getOneBased() + INVALID_GROUPNAME_DESC,
                GroupName.MESSAGE_CONSTRAINTS);

        // invalid index
        assertParseFailure(parser, "a" + GROUPNAME_DESC_G1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMemberCommand.MESSAGE_USAGE));

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + INDEX_FIRST_STUDENT.getOneBased() + GROUPNAME_DESC_G1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMemberCommand.MESSAGE_USAGE));
    }
}
