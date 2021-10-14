package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.util.EditUtil.EditPersonDescriptor;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_WIFE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getNoTagTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTaggedTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.TagCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;



/**
 * Integration test with TagCommand, TagCommandParser, Model and CommandResult.
 */
public class TagCommandIntegrationTest {

    private TagCommandParser parser = new TagCommandParser();

    private EditPersonDescriptor descriptorAddFriend = new EditPersonDescriptorBuilder()
            .withTags(VALID_TAG_FRIEND).build();
    private EditPersonDescriptor descriptorAddHusband = new EditPersonDescriptorBuilder()
            .withTags(VALID_TAG_HUSBAND).build();
    private EditPersonDescriptor descriptorAddBoth = new EditPersonDescriptorBuilder()
            .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();

    private Model generateNoTagModel() {
        return new ModelManager(getNoTagTypicalAddressBook(), new UserPrefs());
    }
    private Model generateDefaultTagModel() {
        return new ModelManager(getTaggedTypicalAddressBook(VALID_TAG_WIFE), new UserPrefs());
    }

    @Test
    public void execute_singleTagNoExistingUnfilteredList_success() {
        try {
            Model model = generateNoTagModel();
            TagCommand tagCommand =
                    parser.parse(INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_TAG + VALID_TAG_FRIEND);
            CommandResult parserResult = tagCommand.execute(model);
            Person defaultFirst = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
            Person editedPerson = new PersonBuilder(defaultFirst).withTags(VALID_TAG_FRIEND).build();
            String expectedMessage = String.format(TagCommand.MESSAGE_TAG_ADD_SUCCESS,
                    editedPerson.getName(), editedPerson.getTags());
            CommandResult expectedResult = new CommandResult(expectedMessage);
            assertEquals(expectedResult, parserResult);
        } catch (ParseException e) {
            assert false;
        } catch (CommandException e) {
            assert false;
        }
    }

    @Test
    public void execute_singleTagPreExistingTagUnfilteredList_success() {
        Model model = generateDefaultTagModel();
        Person defaultFirst = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(defaultFirst).withTags(VALID_TAG_WIFE, VALID_TAG_FRIEND).build();

        try {
            TagCommand tagCommand = parser.parse(INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_TAG + VALID_TAG_WIFE
                    + " " + PREFIX_TAG + VALID_TAG_FRIEND);
            CommandResult parserResult = tagCommand.execute(model);

            String expectedMessage = String.format(TagCommand.MESSAGE_TAG_ADD_SUCCESS,
                    editedPerson.getName(), editedPerson.getTags());
            CommandResult expectedResult = new CommandResult(expectedMessage);
            assertEquals(expectedResult, parserResult);
        } catch (ParseException e) {
            assert false;
        } catch (CommandException e) {
            assert false;
        }
    }

    @Test
    public void execute_multipleTagPreExistingTagUnfilteredList_success() {
        Model model = generateDefaultTagModel();
        Person defaultFirst = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(defaultFirst)
                .withTags(VALID_TAG_WIFE, VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        try {
            TagCommand tagCommand = parser.parse(INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_TAG + VALID_TAG_WIFE
                    + " " + PREFIX_TAG + VALID_TAG_FRIEND + " " + PREFIX_TAG + VALID_TAG_HUSBAND);
            CommandResult parserCommandResult = tagCommand.execute(model);

            String expectedMessage = String.format(TagCommand.MESSAGE_TAG_ADD_SUCCESS,
                    editedPerson.getName(), editedPerson.getTags());

            CommandResult expectedCommandResult = new CommandResult(expectedMessage);
            assertEquals(expectedCommandResult, parserCommandResult);
        } catch (ParseException e) {
            assert false;
        } catch (CommandException e) {
            assert false;
        }
    }
}
