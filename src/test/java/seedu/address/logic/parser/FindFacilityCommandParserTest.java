package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindFacilityCommand;
import seedu.address.model.facility.LocationContainsKeywordsPredicate;

public class FindFacilityCommandParserTest {
    private FindFacilityCommandParser parser = new FindFacilityCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindFacilityCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindFacilityCommand() {
        //No leading and trailing whitespaces
        FindFacilityCommand expectedFindFacilityCommand =
                new FindFacilityCommand(new LocationContainsKeywordsPredicate(Arrays.asList("Utown", "Redhill")));
        assertParseSuccess(parser, "Utown Redhill", expectedFindFacilityCommand);

        //Multiple whitespaces between keywords
        assertParseSuccess(parser, "  \n Utown \n \t Redhill  \t", expectedFindFacilityCommand);
    }
}
