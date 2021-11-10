package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TagInfoCommand;
import seedu.address.logic.commands.exceptions.CommandException;

public class TagInfoCommandParserTest {

    private TagInfoCommandParser parser = new TagInfoCommandParser();

    @Test
    public void assert_allValuesCorrect_success() throws CommandException {
        assertParseSuccess(parser, "tagInfo t/Ben Tan test yall",
                new TagInfoCommand("Ben Tan test yall".split(" "), false));
    }

    @Test
    public void assert_emptyTagValues_fail() throws CommandException {
        assertParseFailure(parser, "tagInfo t/", "Empty tag is inputted!");
    }

    @Test
    public void assert_invalidTagValues_fail() throws CommandException {
        assertParseFailure(parser, "tagInfo t/Photographer, test",
                "Tags names should be alphanumeric");
    }

}
