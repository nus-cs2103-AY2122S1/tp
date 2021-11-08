package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.smartnus.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.smartnus.logic.commands.CommandTestUtil.STAT_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.STAT_DESC_2;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_2;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.StatCommand;
import seedu.smartnus.model.statistic.TagStatistic;
import seedu.smartnus.model.statistic.predicates.StatContainsKeywordPredicate;

class StatCommandParserTest {

    private StatCommandParser parser = new StatCommandParser();
    private ArrayList<String> tags = new ArrayList<>();
    private ArrayList<Predicate<TagStatistic>> filterPredicates;

    @BeforeEach
    public void setUp() {
        reset();
    }

    @Test
    void parse_validArgs_success() {
        // user types only the command
        assertParseSuccess(parser, "", new StatCommand(filterPredicates));
        // with whitespace
        assertParseSuccess(parser, PREAMBLE_WHITESPACE, new StatCommand(filterPredicates));

        // user types in 1 stat
        tags.add(VALID_TAG_1);
        filterPredicates.add(new StatContainsKeywordPredicate(tags));
        assertParseSuccess(parser, STAT_DESC_1, new StatCommand(filterPredicates));
        // with whitespace
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + STAT_DESC_1, new StatCommand(filterPredicates));

        // user types in 2 stats
        reset();
        tags.add(VALID_TAG_1);
        tags.add(VALID_TAG_2);
        filterPredicates = new ArrayList<>();
        filterPredicates.add(new StatContainsKeywordPredicate(tags));
        assertParseSuccess(parser, STAT_DESC_2, new StatCommand(filterPredicates));
        // with whitespace
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + STAT_DESC_2, new StatCommand(filterPredicates));

    }

    @Test
    void parse_invalidArgs_throwsParseException() {
        // user types something but without prefix tag
        assertParseFailure(parser, PREAMBLE_NON_EMPTY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        StatCommand.MESSAGE_NO_TAG_PREFIX + StatCommand.MESSAGE_USAGE));
    }

    private void reset() {
        filterPredicates = new ArrayList<>();
        tags = new ArrayList<>();
    }
}
