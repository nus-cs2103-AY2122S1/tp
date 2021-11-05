package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fast.model.tag.Tag.MESSAGE_CONSTRAINTS;
import static seedu.fast.model.tag.Tag.MESSAGE_SPECIAL_TAG_ENTERED;
import static seedu.fast.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Collections;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.fast.logic.commands.TagCommand;
import seedu.fast.model.tag.Tag;

public class TagCommandParserTest {

    private final String addTagModifier = "a/";
    private final String deleteTagModifier = "d/";
    private final String invalidModifier = "k/";
    private final String validTagNameNormal = "fat ";
    private final String invalidTagName = "idolM@STER";
    private final String invalidPriorityTagName = "lowpriority";
    private final String validTagNamePriority = "pr/low ";
    private final String validTagNameInvestmentPlan = "ip/life ";
    private final String validIndex = "1 ";
    private final String invalidIndex = "-1 ";
    private final String whitespace = " ";
    private final String errorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);

    private TagCommandParser parser = new TagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        //invalid command
        String invalidInput = "invalidd";
        assertParseFailure(parser, invalidInput, errorMessage);

        //invalid components
        String invalidIndexInput = invalidIndex + addTagModifier + validTagNameNormal;
        String invalidTagNameInput = validIndex + addTagModifier + invalidTagName;
        String invalidPriorityTagNameInput = validIndex + addTagModifier + invalidPriorityTagName;
        String invalidModifierInput = validIndex + invalidModifier + validTagNameNormal;
        assertParseFailure(parser, invalidIndexInput, errorMessage);
        assertParseFailure(parser, invalidTagNameInput, MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, invalidPriorityTagNameInput, MESSAGE_SPECIAL_TAG_ENTERED);
        assertParseFailure(parser, invalidModifierInput, errorMessage);
    }

    @Test
    public void parse_validArgs_returnsTagCommand() {
        String addTagInput = validIndex + addTagModifier + validTagNameNormal;
        String addPriorityTagInput = validIndex + addTagModifier + validTagNamePriority;
        String addInvestmentPlanTagInput = validIndex + addTagModifier + validTagNameInvestmentPlan;

        String deleteTagInput = validIndex + deleteTagModifier + validTagNameNormal;
        String deletePriorityTagInput = validIndex + deleteTagModifier + validTagNamePriority;
        String deleteInvestmentPlanTagInput = validIndex + deleteTagModifier + validTagNameInvestmentPlan;

        String mixedInput = validIndex + addTagModifier + validTagNamePriority
                + deleteTagModifier + validTagNameInvestmentPlan;
        String mixedInputWithWhitespace = validIndex + addTagModifier + whitespace + validTagNamePriority
                + deleteTagModifier + whitespace + validTagNameInvestmentPlan;

        HashSet<Tag> emptyTagSet = new HashSet<>();

        //expected instances of TagCommand
        TagCommand expectedTagCommandAddTag = new TagCommand(
                INDEX_FIRST_PERSON, Collections.singleton(Tag.createTag("fat")), emptyTagSet);
        TagCommand expectedTagCommandAddPriorityTag = new TagCommand(
                INDEX_FIRST_PERSON, Collections.singleton(Tag.createTag("pr/low")), emptyTagSet);
        TagCommand expectedTagCommandAddInvestmentPlanTag = new TagCommand(
                INDEX_FIRST_PERSON, Collections.singleton(Tag.createTag("ip/life")), emptyTagSet);
        TagCommand expectedTagCommandDeleteTag = new TagCommand(
                INDEX_FIRST_PERSON, emptyTagSet, Collections.singleton(Tag.createTag("fat")));
        TagCommand expectedTagCommandDeletePriorityTag = new TagCommand(
                INDEX_FIRST_PERSON, emptyTagSet, Collections.singleton(Tag.createTag("pr/low")));
        TagCommand expectedTagCommandDeleteInvestmentPlanTag = new TagCommand(
                INDEX_FIRST_PERSON, emptyTagSet, Collections.singleton(Tag.createTag("ip/life")));
        TagCommand expectedTagCommandMixed = new TagCommand(
                INDEX_FIRST_PERSON,
                Collections.singleton(Tag.createTag("pr/low")),
                Collections.singleton(Tag.createTag("ip/life")));

        assertParseSuccess(parser, addTagInput, expectedTagCommandAddTag);
        assertParseSuccess(parser, addPriorityTagInput, expectedTagCommandAddPriorityTag);
        assertParseSuccess(parser, addInvestmentPlanTagInput, expectedTagCommandAddInvestmentPlanTag);
        assertParseSuccess(parser, deleteTagInput, expectedTagCommandDeleteTag);
        assertParseSuccess(parser, deletePriorityTagInput, expectedTagCommandDeletePriorityTag);
        assertParseSuccess(parser, deleteInvestmentPlanTagInput, expectedTagCommandDeleteInvestmentPlanTag);
        assertParseSuccess(parser, mixedInput, expectedTagCommandMixed);
        assertParseSuccess(parser, mixedInputWithWhitespace, expectedTagCommandMixed);
    }

}
