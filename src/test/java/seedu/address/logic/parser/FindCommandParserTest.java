package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.FindPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_oneName_returnsFindCommand() {
        // no leading and trailing whitespaces
        List<Name> nameList = List.of(new Name("Alice"));
        List<Tag> tagList = List.of();
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(nameList, tagList));
        assertParseSuccess(parser, " n/Alice", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t  \t", expectedFindCommand);
    }

    @Test
    public void parse_multipleNames_returnsFindCommand() {
        // no leading and trailing whitespaces
        List<Name> nameList = List.of(new Name("Alice"), new Name("Bob"));
        List<Tag> tagList = List.of();
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(nameList, tagList));
        assertParseSuccess(parser, " n/Alice n/Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t n/Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_oneTag_returnsFindCommand() {
        // no leading and trailing whitespaces
        List<Name> nameList = List.of();
        List<Tag> tagList = List.of(new Tag("friends"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(nameList, tagList));
        assertParseSuccess(parser, "  t/friends", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n \n \t  \t t/friends", expectedFindCommand);
    }

    @Test
    public void parse_multipleTags_returnsFindCommand() {
        // no leading and trailing whitespaces
        List<Name> nameList = List.of();
        List<Tag> tagList = List.of(new Tag("friends"), new Tag("colleagues"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(nameList, tagList));
        assertParseSuccess(parser, " t/friends t/colleagues", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n \n \t  t/friends \t t/colleagues", expectedFindCommand);
    }

    @Test
    public void parse_oneNamesAndTag_returnsFindCommand() {
        // no leading and trailing whitespaces
        List<Name> nameList = List.of(new Name("Alice"));
        List<Tag> tagList = List.of(new Tag("friends"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(nameList, tagList));
        assertParseSuccess(parser, " n/Alice t/friends", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t  \t t/friends", expectedFindCommand);
    }

    @Test
    public void parse_multipleNamesAndTags_returnsFindCommand() {
        // no leading and trailing whitespaces
        List<Name> nameList = List.of(new Name("Alice"), new Name("Bob"));
        List<Tag> tagList = List.of(new Tag("friends"), new Tag("colleagues"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(nameList, tagList));
        assertParseSuccess(parser, " n/Alice n/Bob t/friends t/colleagues", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t n/Bob  \t t/friends t/colleagues", expectedFindCommand);
    }

}
