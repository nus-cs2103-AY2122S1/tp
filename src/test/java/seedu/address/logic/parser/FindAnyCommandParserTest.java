package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAnyCommand;
import seedu.address.model.person.FindAnyPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;


public class FindAnyCommandParserTest {

    private FindAnyCommandParser parser = new FindAnyCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindAnyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_caseSensitiveFlagAfterName_throwsParseException() {
        assertParseFailure(parser, "findAny n/John c/",
                FindAnyCommand.CASE_SENSITIVE_FLAG_FORMAT_MESSAGE);
    }

    @Test
    public void parse_caseSensitiveFlagAfterTag_throwsParseException() {
        assertParseFailure(parser, "findAny t/Friends c/",
                FindAnyCommand.CASE_SENSITIVE_FLAG_FORMAT_MESSAGE);
    }

    @Test
    public void parse_caseSensitiveFlagAfterNameBeforeTag_throwsParseException() {
        assertParseFailure(parser, "findAny n/John c/ t/Friends",
                FindAnyCommand.CASE_SENSITIVE_FLAG_FORMAT_MESSAGE);
    }

    @Test
    public void parse_caseSensitiveFlagAfterTagBeforeName_throwsParseException() {
        assertParseFailure(parser, "findAny t/Friends c/ n/John",
                FindAnyCommand.CASE_SENSITIVE_FLAG_FORMAT_MESSAGE);
    }

    @Test
    public void parse_caseSensitiveFlagAfterNameAndTag_throwsParseException() {
        assertParseFailure(parser, "find n/John t/Friends c/",
                FindAnyCommand.CASE_SENSITIVE_FLAG_FORMAT_MESSAGE);
    }

    @Test
    public void parse_caseSensitiveFlagAfterTagAndName_throwsParseException() {
        assertParseFailure(parser, "find t/Friends n/Friends c/",
                FindAnyCommand.CASE_SENSITIVE_FLAG_FORMAT_MESSAGE);
    }

    @Test
    public void parse_oneNameCase_returnsFindAnyCommand() {
        List<Name> nameList = List.of(new Name("Alice"));
        List<Tag> tagList = List.of();
        FindAnyCommand expectedFindAnyCommand =
                new FindAnyCommand(new FindAnyPredicate(nameList, tagList, false));
        assertParseSuccess(parser, " n/Alice", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t  \t", expectedFindAnyCommand);
    }

    @Test
    public void parse_multipleNames_returnsFindAnyCommand() {
        List<Name> nameList = List.of(new Name("Alice"), new Name("Bob"));
        List<Tag> tagList = List.of();
        FindAnyCommand expectedFindAnyCommand =
                new FindAnyCommand(new FindAnyPredicate(nameList, tagList, false));
        assertParseSuccess(parser, " n/Alice n/Bob", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t n/Bob  \t", expectedFindAnyCommand);
    }

    @Test
    public void parse_oneTagCaseInsensitive_returnsFindAnyCommand() {
        List<Name> nameList = List.of();
        List<Tag> tagList = List.of(new Tag("friends"));
        FindAnyCommand expectedFindAnyCommand =
                new FindAnyCommand(new FindAnyPredicate(nameList, tagList, false));
        assertParseSuccess(parser, " t/friends", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n \n \t  \t t/friends", expectedFindAnyCommand);
    }

    @Test
    public void parse_oneTagCaseSensitive_returnsFindAnyCommand() {
        List<Name> nameList = List.of();
        List<Tag> tagList = List.of(new Tag("frIEnds"));
        FindAnyCommand expectedFindAnyCommand =
                new FindAnyCommand(new FindAnyPredicate(nameList, tagList, true));
        assertParseSuccess(parser, " c/ t/frIEnds", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " c/\n \n \t  \t t/frIEnds", expectedFindAnyCommand);
    }

    @Test
    public void parse_multipleTagsCaseInsensitive_returnsFindAnyCommand() {
        List<Name> nameList = List.of();
        List<Tag> tagList = List.of(new Tag("friends"), new Tag("colleagues"));
        FindAnyCommand expectedFindAnyCommand =
                new FindAnyCommand(new FindAnyPredicate(nameList, tagList, false));
        assertParseSuccess(parser, " t/friends t/colleagues", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n t/friends \n \t  \t t/colleagues", expectedFindAnyCommand);
    }

    @Test
    public void parse_multipleTagsCaseSensitive_returnsFindAnyCommand() {
        List<Name> nameList = List.of();
        List<Tag> tagList = List.of(new Tag("frienDS"), new Tag("coLLeagues"));
        FindAnyCommand expectedFindAnyCommand =
                new FindAnyCommand(new FindAnyPredicate(nameList, tagList, true));
        assertParseSuccess(parser, " c/ t/frienDS t/coLLeagues", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " c/ \n t/frienDS \n \t  \t t/coLLeagues", expectedFindAnyCommand);
    }

    @Test
    public void parse_oneNameAndTagCaseInsensitive_returnsFindAnyCommand() {
        List<Name> nameList = List.of(new Name("Alice"));
        List<Tag> tagList = List.of(new Tag("friends"));
        FindAnyCommand expectedFindAnyCommand =
                new FindAnyCommand(new FindAnyPredicate(nameList, tagList, false));
        assertParseSuccess(parser, " n/Alice t/friends", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t  \t t/friends", expectedFindAnyCommand);
    }

    @Test
    public void parse_oneNameAndTagCaseSensitive_returnsFindAnyCommand() {
        List<Name> nameList = List.of(new Name("Alice"));
        List<Tag> tagList = List.of(new Tag("FRIENDs"));
        FindAnyCommand expectedFindAnyCommand =
                new FindAnyCommand(new FindAnyPredicate(nameList, tagList, true));
        assertParseSuccess(parser, "c/ n/Alice t/FRIENDs", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " c/\n n/Alice \n \t  \t t/FRIENDs", expectedFindAnyCommand);
    }

    @Test
    public void parse_multipleNamesAndTagsCaseInsensitive_returnsFindAnyCommand() {
        List<Name> nameList = List.of(new Name("Alice"), new Name("Bob"));
        List<Tag> tagList = List.of(new Tag("friends"), new Tag("colleagues"));
        FindAnyCommand expectedFindAnyCommand =
                new FindAnyCommand(new FindAnyPredicate(nameList, tagList, false));
        assertParseSuccess(parser, " n/Alice n/Bob t/friends t/colleagues", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t n/Bob  \t t/friends t/colleagues", expectedFindAnyCommand);
    }

    @Test
    public void parse_multipleNamesAndTagsCaseSensitive_returnsFindAnyCommand() {
        List<Name> nameList = List.of(new Name("Alice"), new Name("Bob"));
        List<Tag> tagList = List.of(new Tag("fRIends"), new Tag("colLEagues"));
        FindAnyCommand expectedFindAnyCommand =
                new FindAnyCommand(new FindAnyPredicate(nameList, tagList, true));
        assertParseSuccess(parser, " c/ n/Alice n/Bob t/fRIends t/colLEagues", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " c/ \n n/Alice \n \t n/Bob  \t t/fRIends t/colLEagues", expectedFindAnyCommand);
    }

}
