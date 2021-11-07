package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.model.person.predicates.PersonContainsFieldsPredicate;

public class MarkCommandParserTest {
    private static final MarkCommandParser PARSER = new MarkCommandParser();
    private static final LocalDate START_DATE = LocalDate.of(1, 1, 1);
    private static final LocalDate END_DATE = LocalDate.of(1, 1, 7);

    @Test
    public void parse_emptyArg_throwsParseException() {
        String userInput = "       ";
        assertParseFailure(PARSER, userInput, MarkCommand.MESSAGE_USAGE);

    }

    @Test
    public void parse_wrongDateOrder_throwsParseException() {
        assertParseFailure(PARSER, " -i 1 da/2020-01-01 da/2019-01-01", Messages.DATES_IN_WRONG_ORDER);
    }

    @Test
    public void parse_validLookup() {
        String userInput = " " + PREFIX_DASH_INDEX + " 1";
        assertParseSuccess(PARSER, userInput, new MarkCommand(Index.fromOneBased(1),
                DateTimeUtil.getDisplayedPeriod(), new PersonContainsFieldsPredicate()));

    }


}
