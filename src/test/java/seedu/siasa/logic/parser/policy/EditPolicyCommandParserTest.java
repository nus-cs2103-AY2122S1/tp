package seedu.siasa.logic.parser.policy;

import static seedu.siasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.siasa.logic.commands.CommandTestUtil.TITLE_DESC;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_TITLE_CRITICAL;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.siasa.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.siasa.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.siasa.testutil.TypicalIndexes.INDEX_SECOND_POLICY;
import static seedu.siasa.testutil.TypicalIndexes.INDEX_THIRD_POLICY;

import org.junit.jupiter.api.Test;

import seedu.siasa.commons.core.index.Index;
import seedu.siasa.logic.commands.policy.EditPolicyCommand;
import seedu.siasa.logic.commands.policy.EditPolicyCommand.EditPolicyDescriptor;
import seedu.siasa.testutil.EditPolicyDescriptorBuilder;

public class EditPolicyCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPolicyCommand.MESSAGE_USAGE);

    private EditPolicyCommandParser parser = new EditPolicyCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditPolicyCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TITLE_DESC, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TITLE_DESC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_POLICY;
        String userInput = targetIndex.getOneBased() + TITLE_DESC;

        EditPolicyDescriptor descriptor =
                new EditPolicyDescriptorBuilder().withTitle(VALID_POLICY_TITLE_CRITICAL).build();

        EditPolicyCommand expectedCommand = new EditPolicyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_POLICY;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPolicyDescriptor descriptor = new EditPolicyDescriptorBuilder().withTags().build();
        EditPolicyCommand expectedCommand = new EditPolicyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
