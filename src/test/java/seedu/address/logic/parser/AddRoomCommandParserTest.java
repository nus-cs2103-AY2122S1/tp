package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.util.SampleDataUtil.getTagSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddRoomCommand;
import seedu.address.model.tag.Tag;

public class AddRoomCommandParserTest {

    private AddRoomCommandParser parser = new AddRoomCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRoomCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsAddRoomCommand() {
        // no leading and trailing whitespaces
        AddRoomCommand expectedAddRoomCommand =
                new AddRoomCommand(5, getTagSet("normal"));
        assertParseSuccess(parser, "5 t/normal", expectedAddRoomCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 5 \n \t t/  normal  \t", expectedAddRoomCommand);
    }

    @Test
    public void parse_invalidPreamble_throwsParseException() {
        String userInputZero = "0 t/normal";
        String userInputNegative = "-5 t/normal";
        String userInputNan = "a t/normal";

        assertParseFailure(parser, userInputZero, AddRoomCommand.MESSAGE_INVALID_INTEGER);
        assertParseFailure(parser, userInputNegative, AddRoomCommand.MESSAGE_INVALID_INTEGER);
        assertParseFailure(parser, userInputNan, AddRoomCommand.MESSAGE_INVALID_INTEGER);
    }

    @Test
    public void parse_missingPreamble_throwsParseException() {
        // no leading and trailing whitespaces
        String userInput = "t/normal";

        // multiple whitespaces between keywords
        String userInputWithWhitespaces = "\n t/normal    ";

        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRoomCommand.MESSAGE_USAGE));
        assertParseFailure(parser, userInputWithWhitespaces,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRoomCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingTags_throwsParseException() {
        String userInput = "   5    ";

        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRoomCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidTags_throwsParseException() {
        String userInputWithEmptyTag = "1 t/";
        String userInputWithInvalidTag = "1 t/type A";

        assertParseFailure(parser, userInputWithEmptyTag,
                String.format(Tag.MESSAGE_CONSTRAINTS));
        assertParseFailure(parser, userInputWithInvalidTag,
                String.format(Tag.MESSAGE_CONSTRAINTS));
    }

}
