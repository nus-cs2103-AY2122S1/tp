package seedu.tracker.logic.parser;

import static seedu.tracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tracker.logic.commands.CommandTestUtil.CODE_DESC_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.DESCRIPTION_DESC_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.DESCRIPTION_DESC_GEQ1000;
import static seedu.tracker.logic.commands.CommandTestUtil.INVALID_CODE_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.INVALID_MC_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.MC_DESC_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.MC_DESC_GEQ1000;
import static seedu.tracker.logic.commands.CommandTestUtil.TAG_DESC_CORE;
import static seedu.tracker.logic.commands.CommandTestUtil.TAG_DESC_GE;
import static seedu.tracker.logic.commands.CommandTestUtil.TITLE_DESC_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.TITLE_DESC_GEQ1000;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_CODE_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_GEQ1000;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_MC_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_MC_GEQ1000;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_TAG_CORE;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_TAG_GE;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_TITLE_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_TITLE_GEQ1000;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.tracker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tracker.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tracker.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.tracker.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.tracker.testutil.TypicalIndexes.INDEX_THIRD_MODULE;

import org.junit.jupiter.api.Test;

import seedu.tracker.commons.core.index.Index;
import seedu.tracker.logic.commands.EditCommand;
import seedu.tracker.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.tracker.model.module.Code;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.Title;
import seedu.tracker.model.tag.Tag;
import seedu.tracker.testutil.EditModuleDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_CODE_CS2103T, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + CODE_DESC_CS2103T, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + CODE_DESC_CS2103T, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_CODE_DESC, Code.MESSAGE_CONSTRAINTS); // invalid code
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC, Title.MESSAGE_CONSTRAINTS); // invalid title
        assertParseFailure(parser, "1" + INVALID_MC_DESC, Mc.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid title followed by valid email
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + MC_DESC_CS2103T, Title.MESSAGE_CONSTRAINTS);

        // valid title followed by invalid title. The test case for invalid title followed by valid title
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + TITLE_DESC_GEQ1000 + INVALID_TITLE_DESC, Title.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Module} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_CORE + TAG_DESC_GE + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_CORE + TAG_EMPTY + TAG_DESC_GE, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_CORE + TAG_DESC_GE, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                "1" + INVALID_CODE_DESC + INVALID_MC_DESC + VALID_DESCRIPTION_CS2103T + VALID_TITLE_CS2103T,
                Code.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_MODULE;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_CS2103T + TAG_DESC_CORE
                + MC_DESC_CS2103T + DESCRIPTION_DESC_CS2103T + CODE_DESC_CS2103T + TAG_DESC_CORE;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCode(VALID_CODE_CS2103T)
                .withTitle(VALID_TITLE_CS2103T).withMc(VALID_MC_CS2103T).withDescription(VALID_DESCRIPTION_CS2103T)
                .withTags(VALID_TAG_CORE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_MODULE;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_CS2103T + MC_DESC_CS2103T;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withTitle(VALID_TITLE_CS2103T)
                .withMc(VALID_MC_CS2103T).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // code
        Index targetIndex = INDEX_THIRD_MODULE;
        String userInput = targetIndex.getOneBased() + CODE_DESC_CS2103T;
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCode(VALID_CODE_CS2103T).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // title
        userInput = targetIndex.getOneBased() + TITLE_DESC_CS2103T;
        descriptor = new EditModuleDescriptorBuilder().withTitle(VALID_TITLE_CS2103T).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + MC_DESC_CS2103T;
        descriptor = new EditModuleDescriptorBuilder().withMc(VALID_MC_CS2103T).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tracker
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_CS2103T;
        descriptor = new EditModuleDescriptorBuilder().withDescription(VALID_DESCRIPTION_CS2103T).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_CORE;
        descriptor = new EditModuleDescriptorBuilder().withTags(VALID_TAG_CORE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_MODULE;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_CS2103T + DESCRIPTION_DESC_CS2103T + MC_DESC_CS2103T
                + TAG_DESC_CORE + TITLE_DESC_CS2103T + DESCRIPTION_DESC_CS2103T + MC_DESC_CS2103T + TAG_DESC_CORE
                + TITLE_DESC_GEQ1000 + DESCRIPTION_DESC_GEQ1000 + MC_DESC_GEQ1000 + TAG_DESC_GE;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withTitle(VALID_TITLE_GEQ1000)
                .withMc(VALID_MC_GEQ1000).withDescription(VALID_DESCRIPTION_GEQ1000)
                .withTags(VALID_TAG_GE, VALID_TAG_CORE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_MODULE;
        String userInput = targetIndex.getOneBased() + INVALID_TITLE_DESC + TITLE_DESC_GEQ1000;
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withTitle(VALID_TITLE_GEQ1000).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + MC_DESC_GEQ1000 + INVALID_TITLE_DESC + DESCRIPTION_DESC_GEQ1000
                + TITLE_DESC_GEQ1000;
        descriptor = new EditModuleDescriptorBuilder().withTitle(VALID_TITLE_GEQ1000).withMc(VALID_MC_GEQ1000)
                .withDescription(VALID_DESCRIPTION_GEQ1000).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_MODULE;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
