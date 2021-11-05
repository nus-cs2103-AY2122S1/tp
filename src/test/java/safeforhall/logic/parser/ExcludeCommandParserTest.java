package safeforhall.logic.parser;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import safeforhall.commons.core.Messages;
import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.ExcludeCommand;
import safeforhall.model.event.ResidentList;

public class ExcludeCommandParserTest {
    private static final String DEFAULT_INDEX = "1";
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExcludeCommand.MESSAGE_USAGE);
    private ExcludeCommandParser parser = new ExcludeCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "      ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExcludeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsIncludeCommand() {
        // no leading and trailing whitespaces
        ExcludeCommand expectedExcludeCommand = new ExcludeCommand(Index.fromOneBased(1),
                new ResidentList("Alex Yeoh, Bernice Yu"));

        CommandParserTestUtil.assertParseSuccess(parser, " " + "1" + " "
                        + CliSyntax.PREFIX_RESIDENTS + "Alex Yeoh , Bernice Yu",
                expectedExcludeCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccess(parser, " " + "  1  " + " "
                        + CliSyntax.PREFIX_RESIDENTS + " Alex Yeoh , Bernice Yu ",
                expectedExcludeCommand);

        CommandParserTestUtil.assertParseSuccess(parser, " " + "1" + " "
                        + CliSyntax.PREFIX_RESIDENTS + "e417 , a213",
                expectedExcludeCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccess(parser, " " + "  1  " + " "
                        + CliSyntax.PREFIX_RESIDENTS + " e417 , a213 ",
                expectedExcludeCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        // no input
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, DEFAULT_INDEX,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ExcludeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments
        assertParseFailure(parser, "some random string",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ExcludeCommand.MESSAGE_USAGE));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "i/ string",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ExcludeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " " + DEFAULT_INDEX + " "
                + CliSyntax.PREFIX_RESIDENTS + "e417 a213", ResidentList.MESSAGE_CONSTRAINTS); // no comma
        assertParseFailure(parser, " " + DEFAULT_INDEX + " "
                        + CliSyntax.PREFIX_RESIDENTS + "e417, roy",
                ResidentList.MESSAGE_CONSTRAINTS); // room and name
    }
}
