package safeforhall.logic.parser.sort;

import static safeforhall.logic.commands.sort.SortEventCommand.ALLOWED_FIELDS;
import static safeforhall.logic.commands.sort.SortEventCommand.ALLOWED_ORDER;
import static safeforhall.logic.commands.sort.SortEventCommand.ASCENDING;
import static safeforhall.logic.parser.CliSyntax.PREFIX_ORDER;
import static safeforhall.logic.parser.CliSyntax.PREFIX_SORT;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import safeforhall.commons.core.Messages;
import safeforhall.logic.commands.sort.SortEventCommand;
import safeforhall.model.event.EventName;

public class SortEventCommandParserTest {
    private SortEventCommandParser parser = new SortEventCommandParser();

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                SortEventCommand.MESSAGE_USAGE);

        //missing order
        assertParseFailure(parser, PREFIX_SORT + EventName.FIELD + " " + ASCENDING,
                expectedMessage);

        //missing field
        assertParseFailure(parser, EventName.FIELD + " " + PREFIX_ORDER + ASCENDING,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid order
        assertParseFailure(parser, " " + PREFIX_SORT + EventName.FIELD + " " + PREFIX_ORDER + "z",
                ALLOWED_ORDER);

        //invalid field
        assertParseFailure(parser, " " + PREFIX_SORT + "z " + PREFIX_ORDER + ASCENDING,
                ALLOWED_FIELDS);
    }

    @Test
    public void parse_validValue_success() {
        SortEventCommand expectedCommand = new SortEventCommand(EventName.FIELD, ASCENDING);
        assertParseSuccess(parser, " " + PREFIX_SORT + EventName.FIELD + " " + PREFIX_ORDER + ASCENDING,
                expectedCommand);
    }

}
