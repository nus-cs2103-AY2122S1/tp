package seedu.fast.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.fast.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fast.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.fast.testutil.Assert.assertThrows;
import static seedu.fast.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.fast.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.fast.testutil.TypicalPersons.getTypicalFast;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fast.commons.core.Messages;
import seedu.fast.commons.core.index.Index;
import seedu.fast.model.Fast;
import seedu.fast.model.Model;
import seedu.fast.model.ModelManager;
import seedu.fast.model.UserPrefs;
import seedu.fast.model.person.Person;
import seedu.fast.model.tag.Tag;
import seedu.fast.testutil.PersonBuilder;

public class TagCommandTest {

    private Model model;
    private Model modelLastPersonTagsAdded;

    private Tag normalTag = Tag.createTag("test");
    private Tag priorityTag = Tag.createTag("pr/low");
    private Tag investmentPlanTag = Tag.createTag("ip/property");

    private Set<Tag> emptySet = new HashSet<>();
    private Set<Tag> setWithPriorityTag = new HashSet<>();
    private Set<Tag> setWithInvestmentPlanTag = new HashSet<>();
    private Set<Tag> setWithNormalTag = new HashSet<>();
    private Set<Tag> setWithMixedTag = new HashSet<>();

    @BeforeEach
    public void setUp() {
        //set up model
        model = new ModelManager(getTypicalFast(), new UserPrefs());

        //set up last person
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person personToEdit = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
        PersonBuilder personInList = new PersonBuilder(personToEdit);
        Person editedPerson = personInList.withTags(
                normalTag.tagName, priorityTag.tagName, investmentPlanTag.tagName).build();
        modelLastPersonTagsAdded = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        modelLastPersonTagsAdded.setPerson(personToEdit, editedPerson);

        //set up tag sets
        setWithNormalTag.add(normalTag);
        setWithPriorityTag.add(priorityTag);
        setWithInvestmentPlanTag.add(investmentPlanTag);
        setWithMixedTag.add(normalTag);
        setWithMixedTag.add(priorityTag);
        setWithMixedTag.add(investmentPlanTag);
    }

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        //null index
        assertThrows(NullPointerException.class, () -> new TagCommand(null,
                setWithMixedTag, setWithMixedTag));

        //null addSet
        assertThrows(NullPointerException.class, () -> new TagCommand(INDEX_FIRST_PERSON,
                null, setWithMixedTag));

        //null deleteSet
        assertThrows(NullPointerException.class, () -> new TagCommand(INDEX_FIRST_PERSON,
                setWithMixedTag, null));

    }

    @Test
    public void execute_filteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        showPersonAtIndex(model, indexLastPerson);

        TagCommand tagCommand = new TagCommand(INDEX_FIRST_PERSON, setWithNormalTag, emptySet);
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(personToEdit);
        Person editedPerson = personInList.withTags(normalTag.tagName).build();

        String expectedMessage = String.format(TagCommand.MESSAGE_EDIT_TAG_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexSpecifiedUnfilteredList_success() {
        TagCommand tagCommand = new TagCommand(INDEX_FIRST_PERSON, emptySet, emptySet);
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(TagCommand.MESSAGE_EDIT_TAG_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexAndNormalAddSetSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        TagCommand tagCommand = new TagCommand(indexLastPerson, setWithNormalTag, emptySet);
        Person personToEdit = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(personToEdit);
        Person editedPerson = personInList.withTags(normalTag.tagName).build();

        String expectedMessage = String.format(TagCommand.MESSAGE_EDIT_TAG_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexAndPriorityAddSetSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        TagCommand tagCommand = new TagCommand(indexLastPerson, setWithPriorityTag, emptySet);
        Person personToEdit = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(personToEdit);
        Person editedPerson = personInList.withTags(priorityTag.tagName).build();

        String expectedMessage = String.format(TagCommand.MESSAGE_EDIT_TAG_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexAndInvestmentPlanAddSetSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        TagCommand tagCommand = new TagCommand(indexLastPerson, setWithInvestmentPlanTag, emptySet);
        Person personToEdit = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(personToEdit);
        Person editedPerson = personInList.withTags(investmentPlanTag.tagName).build();

        String expectedMessage = String.format(TagCommand.MESSAGE_EDIT_TAG_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexAndMixedAddSetSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        TagCommand tagCommand = new TagCommand(indexLastPerson, setWithMixedTag, emptySet);
        Person personToEdit = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(personToEdit);
        Person editedPerson = personInList.withTags(
                normalTag.tagName, priorityTag.tagName, investmentPlanTag.tagName).build();

        String expectedMessage = String.format(TagCommand.MESSAGE_EDIT_TAG_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new Fast(model.getFast()), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexAndNormalDeleteSetSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(modelLastPersonTagsAdded.getFilteredPersonList().size());
        TagCommand tagCommand = new TagCommand(indexLastPerson, emptySet, setWithNormalTag);
        Person personToEdit = modelLastPersonTagsAdded.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(personToEdit);
        Person editedPerson = personInList.withTags(priorityTag.tagName, investmentPlanTag.tagName).build();

        String expectedMessage = String.format(TagCommand.MESSAGE_EDIT_TAG_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new Fast(modelLastPersonTagsAdded.getFast()), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(tagCommand, modelLastPersonTagsAdded, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexAndPriorityDeleteSetSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(modelLastPersonTagsAdded.getFilteredPersonList().size());
        TagCommand tagCommand = new TagCommand(indexLastPerson, emptySet, setWithPriorityTag);
        Person personToEdit = modelLastPersonTagsAdded.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(personToEdit);
        Person editedPerson = personInList.withTags(normalTag.tagName, investmentPlanTag.tagName).build();

        String expectedMessage = String.format(TagCommand.MESSAGE_EDIT_TAG_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new Fast(modelLastPersonTagsAdded.getFast()), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(tagCommand, modelLastPersonTagsAdded, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexAndInvestmentPlanDeleteSetSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(modelLastPersonTagsAdded.getFilteredPersonList().size());
        TagCommand tagCommand = new TagCommand(indexLastPerson, emptySet, setWithInvestmentPlanTag);
        Person personToEdit = modelLastPersonTagsAdded.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(personToEdit);
        Person editedPerson = personInList.withTags(priorityTag.tagName, normalTag.tagName).build();

        String expectedMessage = String.format(TagCommand.MESSAGE_EDIT_TAG_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new Fast(modelLastPersonTagsAdded.getFast()), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(tagCommand, modelLastPersonTagsAdded, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexAndMixedDeleteSetSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(modelLastPersonTagsAdded.getFilteredPersonList().size());
        TagCommand tagCommand = new TagCommand(indexLastPerson, setWithMixedTag, setWithMixedTag);
        Person personToEdit = modelLastPersonTagsAdded.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        String expectedMessage = String.format(TagCommand.MESSAGE_EDIT_TAG_SUCCESS, personToEdit);

        assertCommandSuccess(tagCommand, modelLastPersonTagsAdded, expectedMessage, modelLastPersonTagsAdded);
    }

    @Test
    public void execute_simultaneousAddAndDeleteUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(modelLastPersonTagsAdded.getFilteredPersonList().size());
        TagCommand tagCommand = new TagCommand(indexLastPerson, emptySet, setWithMixedTag);
        Person personToEdit = modelLastPersonTagsAdded.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(personToEdit);
        Person editedPerson = personInList.withTags().build();

        String expectedMessage = String.format(TagCommand.MESSAGE_EDIT_TAG_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new Fast(modelLastPersonTagsAdded.getFast()), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(tagCommand, modelLastPersonTagsAdded, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        TagCommand tagCommand = new TagCommand(outOfBoundIndex, emptySet, emptySet);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFast().getPersonList().size());
        TagCommand tagCommand = new TagCommand(outOfBoundIndex, emptySet, emptySet);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_addRepeatedTagUnfilteredList_failure() {
        Index indexLastPerson = Index.fromOneBased(modelLastPersonTagsAdded.getFilteredPersonList().size());
        TagCommand tagCommand = new TagCommand(indexLastPerson, setWithInvestmentPlanTag, emptySet);
        Person personToEdit = modelLastPersonTagsAdded.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        String expectedMessage = String.format(TagCommand.MESSAGE_TAGS_ARE_REPEATED, investmentPlanTag.tagName);

        assertCommandFailure(tagCommand, modelLastPersonTagsAdded, expectedMessage);
    }

    @Test
    public void execute_deleteInvalidTagUnfilteredList_failure() {
        TagCommand tagCommand = new TagCommand(INDEX_FIRST_PERSON, emptySet, setWithPriorityTag);
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(TagCommand.MESSAGE_TAG_DOES_NOT_EXIST, priorityTag.tagName);

        assertCommandFailure(tagCommand, model, expectedMessage);
    }

    @Test
    public void execute_addMultiplePriorityTagsUnfilteredList_failure() {
        //set up addSet
        Tag additionalPriorityTag = Tag.createTag("pr/med");
        Set<Tag> additionalPriorityTagSet = new HashSet<>();
        additionalPriorityTagSet.add(additionalPriorityTag);

        Index indexLastPerson = Index.fromOneBased(modelLastPersonTagsAdded.getFilteredPersonList().size());
        TagCommand tagCommand = new TagCommand(indexLastPerson, additionalPriorityTagSet, emptySet);
        String expectedMessage = TagCommand.MESSAGE_MULTIPLE_PRIORITY_TAGS;

        assertCommandFailure(tagCommand, modelLastPersonTagsAdded, expectedMessage);
    }

    @Test
    public void equals() {
        final TagCommand standardCommand = new TagCommand(INDEX_FIRST_PERSON, emptySet, setWithMixedTag);

        // same values -> returns true
        TagCommand commandWithSameValues = new TagCommand(INDEX_FIRST_PERSON, emptySet, setWithMixedTag);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new TagCommand(INDEX_SECOND_PERSON, emptySet, setWithMixedTag)));

        // different sets -> returns false
        assertFalse(standardCommand.equals(new TagCommand(INDEX_FIRST_PERSON, setWithMixedTag, setWithMixedTag)));
        assertFalse(standardCommand.equals(new TagCommand(INDEX_FIRST_PERSON, emptySet, emptySet)));
        assertFalse(standardCommand.equals(new TagCommand(INDEX_FIRST_PERSON, setWithMixedTag, emptySet)));

    }

}
