package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CREDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.status.SortStatus;
import seedu.address.logic.commands.SortCommand;
import seedu.address.model.member.CreditSortComparator;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SortCommandParser}.
 */
public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));
    }

    @Test
        public void parse_validSortCreditAscArgs_returnsSortCommand() {
        // no leading and trailing whitespaces
        SortCommand expectedFindCommand =
                new SortCommand(new CreditSortComparator(SortStatus.ASC));
        assertParseSuccess(parser, " " + PREFIX_MEMBER + " " + PREFIX_CREDIT + " " + PREFIX_ASC,
                expectedFindCommand);
    }

    @Test
    public void parse_validSortCreditDescArgs_returnsSortCommand() {
        // no leading and trailing whitespaces
        SortCommand expectedFindCommand =
                new SortCommand(new CreditSortComparator(SortStatus.DESC));
        assertParseSuccess(parser, " " + PREFIX_MEMBER + " " + PREFIX_CREDIT + " " + PREFIX_DESC,
                expectedFindCommand);
    }
}
