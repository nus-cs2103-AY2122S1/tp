package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTagCaseInsensitiveCommand;
import seedu.address.model.person.PersonTagsContainsCaseInsensitiveTagsPredicate;
import seedu.address.model.tag.Tag;

public class FindTagCaseInsensitiveCommandParserTest {

    private FindTagCaseInsensitiveCommandParser parser = new FindTagCaseInsensitiveCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTagCaseInsensitiveCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_oneTags_returnsFindTagCommand() {
        // no leading and trailing whitespaces
        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag("friends"));
        PersonTagsContainsCaseInsensitiveTagsPredicate predicate =
                new PersonTagsContainsCaseInsensitiveTagsPredicate(tagList);
        FindTagCaseInsensitiveCommand expectedFindCommand =
                new FindTagCaseInsensitiveCommand(predicate);
        assertParseSuccess(parser, "friends", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n friends \n  \t", expectedFindCommand);
    }

    @Test
    public void parse_invalidTags_throwsParseException() {
        assertParseFailure(parser, "a!", Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_multipleTags_returnsFindTagCommand() {
        // no leading and trailing whitespaces
        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag("friends"));
        tagList.add(new Tag("husband"));
        PersonTagsContainsCaseInsensitiveTagsPredicate predicate =
                new PersonTagsContainsCaseInsensitiveTagsPredicate(tagList);
        FindTagCaseInsensitiveCommand expectedFindCommand =
                new FindTagCaseInsensitiveCommand(predicate);
        assertParseSuccess(parser, "friends husband", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n friends \n \t husband  \t", expectedFindCommand);
    }
}
