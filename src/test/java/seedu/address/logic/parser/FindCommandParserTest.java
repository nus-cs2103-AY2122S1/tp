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
    public void parse_validArgs_returnsFindCommand() {
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
