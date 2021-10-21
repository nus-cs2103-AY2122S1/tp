package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindOrCommand;
import seedu.address.model.person.FindOrPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;


public class FindOrCommandParserTest {

    private FindOrCommandParser parser = new FindOrCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_oneName_returnsFindOrCommand() {
        // no leading and trailing whitespaces
        List<Name> nameList = List.of(new Name("Alice"));
        List<Tag> tagList = List.of();
        FindOrCommand expectedFindOrCommand =
                new FindOrCommand(new FindOrPredicate(nameList, tagList));
        assertParseSuccess(parser, " n/Alice", expectedFindOrCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t  \t", expectedFindOrCommand);
    }

    @Test
    public void parse_multipleNames_returnsFindOrCommand() {
        // no leading and trailing whitespaces
        List<Name> nameList = List.of(new Name("Alice"), new Name("Bob"));
        List<Tag> tagList = List.of();
        FindOrCommand expectedFindOrCommand =
                new FindOrCommand(new FindOrPredicate(nameList, tagList));
        assertParseSuccess(parser, " n/Alice n/Bob", expectedFindOrCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t n/Bob  \t", expectedFindOrCommand);
    }

    @Test
    public void parse_oneTag_returnsFindOrCommand() {
        // no leading and trailing whitespaces
        List<Name> nameList = List.of();
        List<Tag> tagList = List.of(new Tag("friends"));
        FindOrCommand expectedFindOrCommand =
                new FindOrCommand(new FindOrPredicate(nameList, tagList));
        assertParseSuccess(parser, " t/friends", expectedFindOrCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n \n \t  \t t/friends", expectedFindOrCommand);
    }

    @Test
    public void parse_multipleTags_returnsFindOrCommand() {
        // no leading and trailing whitespaces
        List<Name> nameList = List.of();
        List<Tag> tagList = List.of(new Tag("friends"), new Tag("colleagues"));
        FindOrCommand expectedFindOrCommand =
                new FindOrCommand(new FindOrPredicate(nameList, tagList));
        assertParseSuccess(parser, " t/friends t/colleagues", expectedFindOrCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n t/friends \n \t  \t t/colleagues", expectedFindOrCommand);
    }

    @Test
    public void parse_oneNameAndTag_returnsFindOrCommand() {
        // no leading and trailing whitespaces
        List<Name> nameList = List.of(new Name("Alice"));
        List<Tag> tagList = List.of(new Tag("friends"));
        FindOrCommand expectedFindOrCommand =
                new FindOrCommand(new FindOrPredicate(nameList, tagList));
        assertParseSuccess(parser, " n/Alice t/friends", expectedFindOrCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t  \t t/friends", expectedFindOrCommand);
    }

    @Test
    public void parse_multipleNamesAndTags_returnsFindOrCommand() {
        // no leading and trailing whitespaces
        List<Name> nameList = List.of(new Name("Alice"), new Name("Bob"));
        List<Tag> tagList = List.of(new Tag("friends"), new Tag("colleagues"));
        FindOrCommand expectedFindOrCommand =
                new FindOrCommand(new FindOrPredicate(nameList, tagList));
        assertParseSuccess(parser, " n/Alice n/Bob t/friends t/colleagues", expectedFindOrCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t n/Bob  \t t/friends t/colleagues", expectedFindOrCommand);
    }

}
