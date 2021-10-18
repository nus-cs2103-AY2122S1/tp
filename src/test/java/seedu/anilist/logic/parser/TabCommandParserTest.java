package seedu.anilist.logic.parser;

import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.logic.commands.CommandTestUtil.EPISODE_DESC_EPISODE_ONE;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_STATUS_DESC_ALPHA;
import static seedu.anilist.logic.commands.CommandTestUtil.NAME_DESC_AKIRA;
import static seedu.anilist.logic.commands.CommandTestUtil.STATUS_DESC_TOWATCH;
import static seedu.anilist.logic.commands.CommandTestUtil.STATUS_DESC_WATCHING;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_STATUS_WATCHING;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.anilist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.anilist.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.anilist.logic.commands.TabCommand;
import seedu.anilist.model.anime.Status;
import seedu.anilist.model.anime.StatusEqualsPredicate;

public class TabCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TabCommand.MESSAGE_USAGE);

    private TabCommandParser parser = new TabCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no status specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, NAME_DESC_AKIRA, MESSAGE_INVALID_FORMAT);
    }

    @Test void parse_invalidValue_failure() {
        // invalid status
        assertParseFailure(parser, INVALID_STATUS_DESC_ALPHA, Status.MESSAGE_CONSTRAINTS);
        // extra fields specified after parameter
        assertParseFailure(parser, STATUS_DESC_WATCHING + EPISODE_DESC_EPISODE_ONE, Status.MESSAGE_CONSTRAINTS);
        // empty status
        assertParseFailure(parser, PREFIX_STATUS + "", MESSAGE_INVALID_FORMAT);
    }

    @Test void parse_validStatusSpecified_success() {
        StatusEqualsPredicate expectedPredicate = new StatusEqualsPredicate(new Status(VALID_STATUS_WATCHING));
        TabCommand expectedCommand = new TabCommand(expectedPredicate);

        assertParseSuccess(parser, STATUS_DESC_WATCHING, expectedCommand);
        //extra fields specified before status
        assertParseSuccess(parser, EPISODE_DESC_EPISODE_ONE + STATUS_DESC_WATCHING, expectedCommand);
        //multiple status specified, last status taken
        assertParseSuccess(parser, STATUS_DESC_TOWATCH + STATUS_DESC_WATCHING, expectedCommand);
    }
}
