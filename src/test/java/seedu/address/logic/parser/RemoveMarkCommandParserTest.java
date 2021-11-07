package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.RemoveMarkCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;
import seedu.address.model.person.predicates.PersonContainsFieldsPredicate;

public class RemoveMarkCommandParserTest {
    private static final RemoveMarkCommandParser PARSER = new RemoveMarkCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(PARSER, "    ", RemoveMarkCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_datesInWrongOrder_throwsParseException() {
        assertParseFailure(PARSER,
                " " + PREFIX_DASH_INDEX + " 1 " + PREFIX_DATE + "2020-01-01 " + PREFIX_DATE + "2019-01-01",
                Messages.DATES_IN_WRONG_ORDER);
    }

    @Test
    public void parse_noDates_success() {
        assertParseSuccess(PARSER, " " + PREFIX_DASH_INDEX + " " + "1",
                new RemoveMarkCommand(new PersonContainsFieldsPredicate(), Index.fromOneBased(1),
                        DateTimeUtil.getDisplayedPeriod()));
    }

    @Test
    public void parse_fields_success() {
        String userInput = " " + PREFIX_DASH_PHONE + " 999 " + PREFIX_DASH_EMAIL + " helpme@mail.com";
        PersonContainsFieldsPredicate predicate = new PersonContainsFieldsPredicate(new Phone("999"),
                new Email("helpme@mail.com"));
        assertParseSuccess(PARSER, userInput, new RemoveMarkCommand(predicate,
                DateTimeUtil.getDisplayedPeriod()));
    }
}
