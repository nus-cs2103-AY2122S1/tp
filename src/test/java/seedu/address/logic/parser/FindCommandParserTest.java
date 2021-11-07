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
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_caseSensitiveFlagAfterName_throwsParseException() {
        assertParseFailure(parser, "find n/John c/", FindCommand.CASE_SENSITIVE_FLAG_FORMAT_MESSAGE);
    }

    @Test
    public void parse_caseSensitiveFlagAfterTag_throwsParseException() {
        assertParseFailure(parser, "find t/Friends c/", FindCommand.CASE_SENSITIVE_FLAG_FORMAT_MESSAGE);
    }

    @Test
    public void parse_caseSensitiveFlagAfterNameBeforeTag_throwsParseException() {
        assertParseFailure(parser, "find n/John c/ t/Friends",
                FindCommand.CASE_SENSITIVE_FLAG_FORMAT_MESSAGE);
    }

    @Test
    public void parse_caseSensitiveFlagAfterTagBeforeName_throwsParseException() {
        assertParseFailure(parser, "find t/Friends c/ n/John",
                FindCommand.CASE_SENSITIVE_FLAG_FORMAT_MESSAGE);
    }

    @Test
    public void parse_caseSensitiveFlagAfterNameAndTag_throwsParseException() {
        assertParseFailure(parser, "find n/John t/Friends c/",
                FindCommand.CASE_SENSITIVE_FLAG_FORMAT_MESSAGE);
    }

    @Test
    public void parse_caseSensitiveFlagAfterTagAndName_throwsParseException() {
        assertParseFailure(parser, "find t/Friends n/Friends c/",
                FindCommand.CASE_SENSITIVE_FLAG_FORMAT_MESSAGE);
    }

    @Test
    public void parse_oneName_returnsFindCommand() {
        List<Name> nameList = List.of(new Name("Alice"));
        List<Tag> tagList = List.of();
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(nameList, tagList, false));
        assertParseSuccess(parser, " n/Alice", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t  \t", expectedFindCommand);
    }

    @Test
    public void parse_multipleNames_returnsFindCommand() {
        List<Name> nameList = List.of(new Name("Alice"), new Name("Bob"));
        List<Tag> tagList = List.of();
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(nameList, tagList, false));
        assertParseSuccess(parser, " n/Alice n/Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t n/Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_oneTagCaseInsensitive_returnsFindCommand() {
        List<Name> nameList = List.of();
        List<Tag> tagList = List.of(new Tag("friends"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(nameList, tagList, false));
        assertParseSuccess(parser, " t/friends", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n \n \t  \t t/friends", expectedFindCommand);
    }

    @Test
    public void parse_oneTagCaseSensitive_returnsFindCommand() {
        List<Name> nameList = List.of();
        List<Tag> tagList = List.of(new Tag("frIends"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(nameList, tagList, true));
        assertParseSuccess(parser, " c/ t/frIends", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " c/\n \n \t  \t t/frIends", expectedFindCommand);
    }

    @Test
    public void parse_multipleTagsCaseInsensitive_returnsFindCommand() {
        List<Name> nameList = List.of();
        List<Tag> tagList = List.of(new Tag("friends"), new Tag("colleagues"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(nameList, tagList, false));
        assertParseSuccess(parser, " t/friends t/colleagues", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n \n \t  t/friends \t t/colleagues", expectedFindCommand);
    }


    @Test
    public void parse_multipleTagsCaseSensitive_returnsFindCommand() {
        List<Name> nameList = List.of();
        List<Tag> tagList = List.of(new Tag("friENds"), new Tag("colleagues"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(nameList, tagList, true));
        assertParseSuccess(parser, " c/ t/friENds t/colleagues", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " c/\n \n \t  t/friENds \t t/colleagues", expectedFindCommand);
    }

    @Test
    public void parse_oneNamesAndTagCaseInsensitive_returnsFindCommand() {
        List<Name> nameList = List.of(new Name("Alice"));
        List<Tag> tagList = List.of(new Tag("friends"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(nameList, tagList, false));
        assertParseSuccess(parser, " n/Alice t/friends", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t  \t t/friends", expectedFindCommand);
    }

    @Test
    public void parse_oneNamesAndTagCaseSensitive_returnsFindCommand() {
        List<Name> nameList = List.of(new Name("Alice"));
        List<Tag> tagList = List.of(new Tag("FRIENDS"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(nameList, tagList, true));
        assertParseSuccess(parser, " c/ n/Alice t/FRIENDS", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " c/\n n/Alice \n \t  \t t/FRIENDS", expectedFindCommand);
    }

    @Test
    public void parse_multipleNamesAndTagsCaseInsensitive_returnsFindCommand() {
        List<Name> nameList = List.of(new Name("Alice"), new Name("Bob"));
        List<Tag> tagList = List.of(new Tag("friends"), new Tag("colleagues"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(nameList, tagList, false));
        assertParseSuccess(parser, " n/Alice n/Bob t/friends t/colleagues", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t n/Bob  \t t/friends t/colleagues", expectedFindCommand);
    }

    @Test
    public void parse_multipleNamesAndTagsCaseSensitive_returnsFindCommand() {
        List<Name> nameList = List.of(new Name("Alice"), new Name("Bob"));
        List<Tag> tagList = List.of(new Tag("FRiends"), new Tag("colleagues"));
        FindCommand expectedFindCommand =
                new FindCommand(new FindPredicate(nameList, tagList, true));
        assertParseSuccess(parser, " c/ n/Alice n/Bob t/FRiends t/colleagues", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " c/\n n/Alice \n \t n/Bob  \t t/FRiends t/colleagues", expectedFindCommand);
    }
}
