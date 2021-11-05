package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_COMMAND_DESCRIPTION_CANNOT_BE_EMPTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;


public class TagCommandParserTest {

    private String messageMissingIndex = ParserUtil.MESSAGE_MISSING_INDEX + "\n" + TagCommand.MESSAGE_USAGE;
    private String messageInvalidCommandFormat = ParserUtil.MESSAGE_INVALID_COMMAND_FORMAT + "\n"
            + TagCommand.MESSAGE_USAGE;
    private String messageInvalidIndex = ParserUtil.MESSAGE_INVALID_INDEX + "\n" + TagCommand.MESSAGE_USAGE;
    private TagCommandParser parser = new TagCommandParser();

    //Tag command description is empty
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                TagCommand.COMMAND_WORD + MESSAGE_COMMAND_DESCRIPTION_CANNOT_BE_EMPTY);
    }

    //Index of person to be tagged is missing
    @Test
    public void parse_missingIndex_throwsParseException() {
        //Index of person to be tagged is missing
        assertParseFailure(parser, " a/friends", messageMissingIndex);
        //Index of person to remove tag from is missing
        assertParseFailure(parser, " r/friends", messageMissingIndex);
    }

    //Tag(s) to be added and/or removed from required contact is missing
    @Test
    public void parse_missingTagsToAddAndRemove_throwsParseException() {
        //Add tag and remove tag prefixes are missing
        assertParseFailure(parser, "1", TagCommand.MESSAGE_MISSING_ADD_AND_REMOVE_TAG_ARGS);
        //Add and remove tag prefixes are present, but parameters are missing
        assertParseFailure(parser, "1 a/ r/", TagCommand.MESSAGE_MISSING_ADD_AND_REMOVE_TAG_ARGS);
    }

    //Tag(s) to be added to required contact is missing
    @Test
    public void parse_missingTagsToAdd_throwsParseException() {
        //User only wants to add tag(s) but tags are missing
        assertParseFailure(parser, "1 a/", TagCommand.MESSAGE_MISSING_ADD_TAG_ARGS);
        //User wants to add and remove tag(s) but tags to be added are missing
        assertParseFailure(parser, "1 a/ r/colleagues", TagCommand.MESSAGE_MISSING_ADD_TAG_ARGS);
    }

    //Tag(s) to be removed from a required contact is missing
    @Test
    public void parse_missingTagsToRemove_throwsParseException() {
        //User only wants to remove tag(s) but tags are missing
        assertParseFailure(parser, "1 r/", TagCommand.MESSAGE_MISSING_REMOVE_TAG_ARGS);
        //User wants to add and remove tag(s) but tags to be removed are missing
        assertParseFailure(parser, "1 a/teammates r/", TagCommand.MESSAGE_MISSING_REMOVE_TAG_ARGS);
    }

    //Invalid prefix for adding/removing a tag
    @Test
    public void parse_invalidArg1_throwsParseException() {
        //User enters wrong prefix to add a tag
        assertParseFailure(parser, "1 add/friends", messageInvalidCommandFormat);
        //User enters wrong prefix to remove tag
        assertParseFailure(parser, "1 remove/friends", messageInvalidCommandFormat);
    }

    //Invalid format for adding/removing multiple tags
    @Test
    public void parse_invalidArg2_throwsParseException() {
        //User enters wrong format to add multiple tags
        assertParseFailure(parser, "1 add/friends CS2103T", messageInvalidCommandFormat);
        //User enters wrong format to remove multiple tags
        assertParseFailure(parser, "1 remove/teammates CS2100", messageInvalidCommandFormat);
    }

    //Invalid index (not a positive integer value) for adding/removing tag(s)
    @Test
    public void parse_invalidArg3_throwsParseException() {
        //User enters negative index
        assertParseFailure(parser, "-5 a/friends r/family", messageInvalidIndex);
        //User enters index 0
        assertParseFailure(parser, "0 a/friends r/family", messageInvalidIndex);
        //User enters non-integer index
        assertParseFailure(parser, "1.5 a/friends r/family", messageInvalidIndex);
        //User enters non-numeric index
        assertParseFailure(parser, "ab*! a/friends r/family", messageInvalidIndex);
    }

    //Valid arguments for adding tag(s)
    @Test
    public void parse_validAddTagArgs_returnsTagCommand() throws ParseException {
        //User adds single tag correctly
        TagCommand expectedTagCommand1 =
                new TagCommand(ParserUtil.parseIndex("1"), new ArrayList<Tag>(Arrays
                        .asList(new Tag("friends"))), new ArrayList<Tag>());
        assertParseSuccess(parser, "1 a/friends", expectedTagCommand1);

        //User adds multiple tags correctly
        TagCommand expectedTagCommand2 =
                new TagCommand(ParserUtil.parseIndex("1"),
                        new ArrayList<Tag>(Arrays.asList(new Tag("friends"),
                                new Tag("CS2103T"))),
                        new ArrayList<Tag>());
        assertParseSuccess(parser, "1 a/friends a/CS2103T", expectedTagCommand2);
    }

    //Valid arguments for removing tag(s)
    @Test
    public void parse_validRemoveTagArgs_returnsTagCommand() throws ParseException {
        //User removes single tag correctly
        TagCommand expectedTagCommand1 =
                new TagCommand(ParserUtil.parseIndex("2"),
                        new ArrayList<Tag>(),
                        new ArrayList<Tag>(Arrays.asList(new Tag("friends"))));
        assertParseSuccess(parser, "2 r/friends", expectedTagCommand1);

        //User removes multiple tags correctly
        TagCommand expectedTagCommand2 =
                new TagCommand(ParserUtil.parseIndex("2"),
                        new ArrayList<Tag>(),
                        new ArrayList<Tag>(Arrays.asList(new Tag("friends"),
                                new Tag("CS2103T"))));
        assertParseSuccess(parser, "2 r/friends r/CS2103T", expectedTagCommand2);
    }
}
