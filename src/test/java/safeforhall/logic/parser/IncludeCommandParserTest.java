package safeforhall.logic.parser;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import safeforhall.commons.core.Messages;
import safeforhall.logic.commands.IncludeCommand;
import safeforhall.model.event.EventName;
import safeforhall.model.event.ResidentList;

public class IncludeCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncludeCommand.MESSAGE_USAGE);
    private IncludeCommandParser parser = new IncludeCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "      ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncludeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsIncludeCommand() {
        // no leading and trailing whitespaces
        IncludeCommand expectedIncludeCommand = new IncludeCommand(new EventName("Training"),
                new ResidentList("Alex Yeoh, Bernice Yu"));

        CommandParserTestUtil.assertParseSuccess(parser, " " + CliSyntax.PREFIX_EVENT + "Training" + " "
                        + CliSyntax.PREFIX_INFORMATION + "Alex Yeoh , Bernice Yu",
                expectedIncludeCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccess(parser, " " + CliSyntax.PREFIX_EVENT + "  Training  " + " "
                        + CliSyntax.PREFIX_INFORMATION + " Alex Yeoh , Bernice Yu ",
                expectedIncludeCommand);

        CommandParserTestUtil.assertParseSuccess(parser, " " + CliSyntax.PREFIX_EVENT + "Training" + " "
                        + CliSyntax.PREFIX_INFORMATION + "e417 , a213",
                expectedIncludeCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccess(parser, " " + CliSyntax.PREFIX_EVENT + "  Training  " + " "
                        + CliSyntax.PREFIX_INFORMATION + " e417 , a213 ",
                expectedIncludeCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        // no input
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, IncludeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments
        assertParseFailure(parser, "some random string",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, IncludeCommand.MESSAGE_USAGE));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "i/ string",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, IncludeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " " + CliSyntax.PREFIX_EVENT + "Training" + " "
                + CliSyntax.PREFIX_INFORMATION + "e417 a213", ResidentList.MESSAGE_CONSTRAINTS); // no comma
        assertParseFailure(parser, " " + CliSyntax.PREFIX_EVENT + "Training" + " "
                + CliSyntax.PREFIX_INFORMATION + "e417, roy",
                ResidentList.MESSAGE_CONSTRAINTS_ROOM_AND_NAME); // room and name
    }
}
