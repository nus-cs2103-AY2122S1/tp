package seedu.tracker.logic.parser;

import static seedu.tracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tracker.logic.commands.CommandTestUtil.CODE_DESC_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.CODE_DESC_GEQ1000;
import static seedu.tracker.logic.commands.CommandTestUtil.DESCRIPTION_DESC_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.DESCRIPTION_DESC_GEQ1000;
import static seedu.tracker.logic.commands.CommandTestUtil.INVALID_CODE_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.INVALID_MC_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.tracker.logic.commands.CommandTestUtil.MC_DESC_CP3108A;
import static seedu.tracker.logic.commands.CommandTestUtil.MC_DESC_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.MC_DESC_GEQ1000;
import static seedu.tracker.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.tracker.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.tracker.logic.commands.CommandTestUtil.TAG_DESC_CORE;
import static seedu.tracker.logic.commands.CommandTestUtil.TITLE_DESC_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.TITLE_DESC_GEQ1000;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_CODE_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_MC_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_TAG_CORE;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_TITLE_CS2103T;
import static seedu.tracker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tracker.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tracker.testutil.TypicalModules.CS2103T;
import static seedu.tracker.testutil.TypicalModules.GEQ1000;

import org.junit.jupiter.api.Test;

import seedu.tracker.logic.commands.AddCommand;
import seedu.tracker.model.module.Code;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.Module;
import seedu.tracker.model.module.Title;
import seedu.tracker.model.tag.Tag;
import seedu.tracker.testutil.ModuleBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Module expectedModule = new ModuleBuilder(CS2103T).withTags(VALID_TAG_CORE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + CODE_DESC_CS2103T + TITLE_DESC_CS2103T
                + DESCRIPTION_DESC_CS2103T + MC_DESC_CS2103T + TAG_DESC_CORE, new AddCommand(expectedModule));

        // multiple codes - last code accepted
        assertParseSuccess(parser, CODE_DESC_GEQ1000 + CODE_DESC_CS2103T + TITLE_DESC_CS2103T
                + DESCRIPTION_DESC_CS2103T + MC_DESC_CS2103T + TAG_DESC_CORE, new AddCommand(expectedModule));

        // multiple titles - last title accepted
        assertParseSuccess(parser, CODE_DESC_CS2103T + TITLE_DESC_GEQ1000 + TITLE_DESC_CS2103T
                + DESCRIPTION_DESC_CS2103T + MC_DESC_CS2103T + TAG_DESC_CORE, new AddCommand(expectedModule));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, CODE_DESC_CS2103T + TITLE_DESC_CS2103T + DESCRIPTION_DESC_GEQ1000
                + DESCRIPTION_DESC_CS2103T + MC_DESC_CS2103T + TAG_DESC_CORE, new AddCommand(expectedModule));

        // multiple mcs - last mc accepted
        assertParseSuccess(parser, CODE_DESC_CS2103T + TITLE_DESC_CS2103T + DESCRIPTION_DESC_CS2103T
                + MC_DESC_CP3108A + MC_DESC_CS2103T + TAG_DESC_CORE, new AddCommand(expectedModule));

        // test for multiple tags deleted as a module will not have multiple tags
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Module expectedModule = new ModuleBuilder(GEQ1000).withTags().build();
        assertParseSuccess(parser, CODE_DESC_GEQ1000 + TITLE_DESC_GEQ1000
                + DESCRIPTION_DESC_GEQ1000 + MC_DESC_GEQ1000, new AddCommand(expectedModule));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing code prefix
        assertParseFailure(parser, VALID_CODE_CS2103T + TITLE_DESC_CS2103T
                + DESCRIPTION_DESC_CS2103T + MC_DESC_CS2103T, expectedMessage);

        // missing title prefix
        assertParseFailure(parser, CODE_DESC_CS2103T + VALID_TITLE_CS2103T
                + DESCRIPTION_DESC_CS2103T + MC_DESC_CS2103T, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, CODE_DESC_CS2103T + TITLE_DESC_CS2103T
                + VALID_DESCRIPTION_CS2103T + MC_DESC_CS2103T, expectedMessage);

        // missing mc prefix
        assertParseFailure(parser, CODE_DESC_CS2103T + TITLE_DESC_CS2103T
                + DESCRIPTION_DESC_CS2103T + VALID_MC_CS2103T, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_CODE_CS2103T + VALID_TITLE_CS2103T
                + VALID_DESCRIPTION_CS2103T + VALID_MC_CS2103T, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid code
        assertParseFailure(parser, INVALID_CODE_DESC + TITLE_DESC_CS2103T + DESCRIPTION_DESC_CS2103T
                + MC_DESC_CS2103T + TAG_DESC_CORE, Code.MESSAGE_CONSTRAINTS);

        // invalid title
        assertParseFailure(parser, CODE_DESC_CS2103T + INVALID_TITLE_DESC + DESCRIPTION_DESC_CS2103T
                + MC_DESC_CS2103T + TAG_DESC_CORE, Title.MESSAGE_CONSTRAINTS);

        // invalid description - deleted as there are no restrictions on description

        // invalid mc
        assertParseFailure(parser, CODE_DESC_CS2103T + TITLE_DESC_CS2103T + DESCRIPTION_DESC_CS2103T
                + INVALID_MC_DESC + TAG_DESC_CORE, Mc.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, CODE_DESC_CS2103T + TITLE_DESC_CS2103T + DESCRIPTION_DESC_CS2103T
                + MC_DESC_CS2103T + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_CODE_DESC + TITLE_DESC_CS2103T + DESCRIPTION_DESC_CS2103T
                + INVALID_MC_DESC, Code.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + CODE_DESC_CS2103T + TITLE_DESC_CS2103T
                        + DESCRIPTION_DESC_CS2103T + MC_DESC_CS2103T + TAG_DESC_CORE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
