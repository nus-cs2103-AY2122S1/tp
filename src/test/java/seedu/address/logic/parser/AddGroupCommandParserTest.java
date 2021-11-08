package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GROUPNAME_DESC_G1;
import static seedu.address.logic.commands.CommandTestUtil.GROUPNAME_DESC_G2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GROUPNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_G1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.testutil.GroupBuilder;

public class AddGroupCommandParserTest {
    private final AddGroupCommandParser parser = new AddGroupCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Group expectedGroup = new GroupBuilder().withName(VALID_GROUPNAME_G1).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GROUPNAME_DESC_G1 + TAG_DESC_FRIEND,
                new AddGroupCommand(expectedGroup));

        // multiple names - last name accepted
        assertParseSuccess(parser, GROUPNAME_DESC_G2 + GROUPNAME_DESC_G1 + TAG_DESC_FRIEND,
                new AddGroupCommand(expectedGroup));

        // multiple tags - all accepted
        Group expectedGroupMultipleTags = new GroupBuilder().withName(VALID_GROUPNAME_G1)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser, GROUPNAME_DESC_G1 + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddGroupCommand(expectedGroupMultipleTags));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupCommand.MESSAGE_USAGE);

        // missing group prefix
        assertParseFailure(parser, VALID_GROUPNAME_G1, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid group name
        assertParseFailure(parser, INVALID_GROUPNAME_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                GroupName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + GROUPNAME_DESC_G1 + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupCommand.MESSAGE_USAGE));
    }
}
