package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ID_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ID_LENGTH_AND_SIGN;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_LETTER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_NEGATIVE_NUMBER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BAKED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BAKED;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalItems.BAGEL;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.item.IdContainsNumberPredicate;
import seedu.address.model.item.NameContainsKeywordsPredicate;
import seedu.address.model.item.TagContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_singleArgs_returnsFindCommand() {
        // name prefix
        List<String> nameList = List.of(VALID_NAME_BAGEL);
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new NameContainsKeywordsPredicate(nameList)));
        assertParseSuccess(parser, NAME_DESC_BAGEL, expectedFindCommand);

        // name prefix with spacing
        nameList = List.of(VALID_NAME_BAGEL + " " + VALID_NAME_DONUT);
        expectedFindCommand =
                new FindCommand(List.of(new NameContainsKeywordsPredicate(nameList)));
        assertParseSuccess(parser,
                NAME_DESC_BAGEL + " " + VALID_NAME_DONUT, expectedFindCommand);

        // id prefix
        HashSet<Integer> idSet = new HashSet<>();
        idSet.add(BAGEL.getId());
        expectedFindCommand =
                new FindCommand(List.of(new IdContainsNumberPredicate(idSet)));
        assertParseSuccess(parser, ID_DESC_BAGEL, expectedFindCommand);

        // tag prefix
        HashSet<Tag> tagSet = new HashSet<>();
        tagSet.add(new Tag(VALID_TAG_BAKED));
        expectedFindCommand =
                new FindCommand(List.of(new TagContainsKeywordsPredicate(tagSet)));
        assertParseSuccess(parser, TAG_DESC_BAKED, expectedFindCommand);
    }

    @Test
    public void parse_multipleValidArgs_returnsFindCommand() {
        // Repeated prefixes
        List<String> nameList = List.of(VALID_NAME_BAGEL, VALID_NAME_DONUT);
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new NameContainsKeywordsPredicate(nameList)));
        assertParseSuccess(parser,
                NAME_DESC_BAGEL + " " + NAME_DESC_DONUT, expectedFindCommand);

        // different prefixes
        nameList = List.of(VALID_NAME_BAGEL);
        HashSet<Integer> idSet = new HashSet<>();
        idSet.add(BAGEL.getId());

        expectedFindCommand =
                new FindCommand(List.of(
                    new NameContainsKeywordsPredicate(nameList),
                    new IdContainsNumberPredicate(idSet)
                ));

        assertParseSuccess(parser, NAME_DESC_BAGEL + "   " + ID_DESC_BAGEL, expectedFindCommand);
    }

    @Test
    public void parse_negativeIdArgs_throwsParseException() {
        assertParseFailure(parser, INVALID_ID_NEGATIVE_NUMBER, String.format(
                MESSAGE_INVALID_ID_LENGTH_AND_SIGN, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_notDigitsInArg_throwsParseException() {
        assertParseFailure(parser, INVALID_ID_LETTER, String.format(
                MESSAGE_INVALID_ID_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidTag_throwsParseException() {
        assertParseFailure(parser, INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_itemUnspecified_throwsParseException() {
        assertParseFailure(parser, "gibberish",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

}
