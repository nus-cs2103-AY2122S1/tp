package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAnyCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.FindAnyPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;


public class FindAnyCommandParserTest {

    private FindAnyCommandParser parser = new FindAnyCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_oneName_returnsFindOrCommand() {
        List<Name> nameList = List.of(new Name("Alice"));
        List<Tag> tagList = List.of();
        FindAnyCommand expectedFindAnyCommand =
                new FindAnyCommand(new FindAnyPredicate(nameList, tagList));
        assertParseSuccess(parser, " n/Alice", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t  \t", expectedFindAnyCommand);
    }

    @Test
    public void parse_multipleNames_returnsFindOrCommand() {
        List<Name> nameList = List.of(new Name("Alice"), new Name("Bob"));
        List<Tag> tagList = List.of();
        FindAnyCommand expectedFindAnyCommand =
                new FindAnyCommand(new FindAnyPredicate(nameList, tagList));
        assertParseSuccess(parser, " n/Alice n/Bob", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t n/Bob  \t", expectedFindAnyCommand);
    }

    @Test
    public void parse_oneTag_returnsFindOrCommand() {
        List<Name> nameList = List.of();
        List<Tag> tagList = List.of(new Tag("friends"));
        FindAnyCommand expectedFindAnyCommand =
                new FindAnyCommand(new FindAnyPredicate(nameList, tagList));
        assertParseSuccess(parser, " t/friends", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n \n \t  \t t/friends", expectedFindAnyCommand);
    }

    @Test
    public void parse_multipleTags_returnsFindOrCommand() {
        List<Name> nameList = List.of();
        List<Tag> tagList = List.of(new Tag("friends"), new Tag("colleagues"));
        FindAnyCommand expectedFindAnyCommand =
                new FindAnyCommand(new FindAnyPredicate(nameList, tagList));
        assertParseSuccess(parser, " t/friends t/colleagues", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n t/friends \n \t  \t t/colleagues", expectedFindAnyCommand);
    }

    @Test
    public void parse_oneNameAndTag_returnsFindOrCommand() {
        List<Name> nameList = List.of(new Name("Alice"));
        List<Tag> tagList = List.of(new Tag("friends"));
        FindAnyCommand expectedFindAnyCommand =
                new FindAnyCommand(new FindAnyPredicate(nameList, tagList));
        assertParseSuccess(parser, " n/Alice t/friends", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t  \t t/friends", expectedFindAnyCommand);
    }

    @Test
    public void parse_multipleNamesAndTags_returnsFindOrCommand() {
        List<Name> nameList = List.of(new Name("Alice"), new Name("Bob"));
        List<Tag> tagList = List.of(new Tag("friends"), new Tag("colleagues"));
        FindAnyCommand expectedFindAnyCommand =
                new FindAnyCommand(new FindAnyPredicate(nameList, tagList));
        assertParseSuccess(parser, " n/Alice n/Bob t/friends t/colleagues", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t n/Bob  \t t/friends t/colleagues", expectedFindAnyCommand);
    }

}
