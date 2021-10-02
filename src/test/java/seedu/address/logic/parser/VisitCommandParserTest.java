package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.VisitCommand;
import seedu.address.model.person.Visit;

public class VisitCommandParserTest {
    private VisitCommandParser parser = new VisitCommandParser();
    private final String testVisit = "2020-11-11";

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, VisitCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, VisitCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, VisitCommand.COMMAND_WORD + " " + testVisit, expectedMessage);
    }
}
