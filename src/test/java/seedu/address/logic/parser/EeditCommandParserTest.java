package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.*;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.EeditCommand;
import seedu.address.logic.commands.member.MeditCommand;
import seedu.address.logic.parser.event.EeditCommandParser;
import seedu.address.model.module.Name;
import seedu.address.model.module.event.EventDate;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.EditMemberDescriptorBuilder;

import java.util.HashSet;
import java.util.Set;

public class EeditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EeditCommand.MESSAGE_USAGE);

    private EeditCommandParser parser = new EeditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, CliSyntax.PREFIX_NAME + VALID_EVENT_NAME_BADMINTON, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, " " + CliSyntax.PREFIX_EVENT_INDEX + VALID_EVENT_INDEX,
                EeditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + EVENT_NAME_DESC_BADMINTON, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + EVENT_NAME_DESC_CHESS, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        String userInput = " " + CliSyntax.PREFIX_EVENT_INDEX + VALID_EVENT_INDEX;
        assertParseFailure(parser, userInput + INVALID_EVENT_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, userInput + INVALID_EVENT_DATE_DESC, EventDate.MESSAGE_CONSTRAINTS); // invalid date

        // invalid field entered with invalid field.
        assertParseFailure(parser, userInput + INVALID_EVENT_NAME_DESC + EVENT_DATE_DESC_BADMINTON,
                Name.MESSAGE_CONSTRAINTS); // invalid name with valid date
        assertParseFailure(parser, userInput + EVENT_NAME_DESC_CHESS + INVALID_EVENT_DATE_DESC,
                EventDate.MESSAGE_CONSTRAINTS); // invalid date with valid name
        assertParseFailure(parser, userInput + EVENT_DATE_DESC_BADMINTON + INVALID_EVENT_DATE_DESC,
                EventDate.MESSAGE_CONSTRAINTS); // valid name followed by invalid name.

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, userInput + INVALID_EVENT_NAME_DESC + INVALID_EVENT_DATE_DESC
                + VALID_EVENT_NAME_BADMINTON + VALID_EVENT_DATE_BADMINTON, Name.MESSAGE_CONSTRAINTS);
    }
    
}