package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MailingListCommand;

class MailingListCommandParserTest {
    private static final String INVALID_PREFIX = "k/";
    private MailingListCommandParser parser = new MailingListCommandParser();

    @Test
    public void parse_emptyArg_defaultArgs() {
        MailingListCommand expectedCommand = new MailingListCommand(MailingListCommandParser.DEFAULT_PREFIXES);
        assertParseSuccess(parser, "", expectedCommand);
    }

    @Test
    public void parse_emptyArg_oneArg() {
        Set<Prefix> prefixes = Set.of(PREFIX_PHONE);
        MailingListCommand expectedCommand = new MailingListCommand(prefixes);
        assertParseSuccess(parser, " " + PREFIX_PHONE.getPrefix(), expectedCommand);
    }

    @Test
    public void parse_preambleExists_failure() {
        assertParseFailure(parser, "preamble " + PREFIX_PHONE.getPrefix(),
                MailingListCommand.MESSAGE_EXTRA_ARGUMENTS_FAILURE);
    }

    @Test
    public void parse_extraDataExists_failure() {
        assertParseFailure(parser, INVALID_PREFIX + " " + PREFIX_PHONE.getPrefix(),
                MailingListCommand.MESSAGE_EXTRA_ARGUMENTS_FAILURE);
        assertParseFailure(parser, PREFIX_PHONE.getPrefix() + " " + INVALID_PREFIX,
                MailingListCommand.MESSAGE_EXTRA_ARGUMENTS_FAILURE);
    }
}
