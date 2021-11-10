package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_INDEX_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_NAME_USAGE = COMMAND_WORD
            + ": Deletes the person identified with specified {@code targetName} in the displayed person list.\n"
            + "Parameters: NAME (must be a non-empty and non-blank string)\n"
            + "Example: " + COMMAND_WORD + " n/Alex Tan";

    public static final String MESSAGE_TAG_USAGE = COMMAND_WORD
            + ": Deletes persons identified with specified {@code targetTag} in the displayed person list.\n"
            + "Parameters: TAG (must be a non-empty and non-blank string)\n"
            + "Example: " + COMMAND_WORD + " t/photographer";

    public static final String MESSAGE_EMPTY_USAGE = COMMAND_WORD
            + ": Deletes the person identified either by index or name.\n"
            + "Parameters (cannot be blank/empty): n/NAME or INDEX\n"
            + "Example: \n"
            + "1. " + COMMAND_WORD + " n/Alex Tan\n"
            + "2. " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNKNOWN_PERSON_NAME = "The person named %s is not found.";
    public static final String MESSAGE_UNKNOWN_PERSON_TAG = "Persons with tag %s are not found.";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted Contacts Under Tag: %1$s";

    private final Index targetIndex;
    private final Name targetName;
    private final Predicate<Person> targetTagPerson;
    private final Tag targetTag;
    private UndoCommand commandToUndo;
    private Person personToDelete;
    private Index nameIndex;
    private ArrayList<Person> deletedList;
    private List<Index> tagIndexes;

    /**
     * Creates an DeleteCommand to delete the person identified with specified {@code targetIndex}
     */
    public DeleteCommand(Index targetIndex) {
        commandToUndo = new UndoCommand();
        commandToUndo.setPrevCommand(this);
        this.targetIndex = targetIndex;
        this.targetTagPerson = null;
        this.targetName = null;
        this.targetTag = null;
        this.personToDelete = null;
        this.deletedList = null;
        this.tagIndexes = null;
    }

    /**
     * Creates an DeleteCommand to delete the person identified with specified {@code targetName}
     */
    public DeleteCommand(Name targetName) {
        commandToUndo = new UndoCommand();
        commandToUndo.setPrevCommand(this);
        this.targetName = targetName;
        this.targetTagPerson = null;
        this.targetIndex = null;
        this.targetTag = null;
        this.personToDelete = null;
        this.deletedList = new ArrayList<>();
        this.tagIndexes = new ArrayList<>();
    }

    /**
     * Creates an DeleteCommand to delete the person identified with specified {@code targetTag}
     */
    public DeleteCommand(Predicate<Person> targetTagPerson, String targetTag) {
        commandToUndo = new UndoCommand();
        commandToUndo.setPrevCommand(this);
        this.targetTagPerson = targetTagPerson;
        this.targetName = null;
        this.targetIndex = null;
        this.targetTag = new Tag(targetTag);
        this.personToDelete = null;
        this.deletedList = new ArrayList<>();
        this.tagIndexes = new ArrayList<>();
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (targetName == null && targetIndex == null) {
            return executeDeleteByTag(model);
        } else if (targetTag == null && targetName == null && targetTagPerson == null) {
            return executeDeleteByIndex(model);
        } else {
            return executeDeleteByName(model);
        }
    }

    /**
     * Deletes the persons identified with specified {@code targetTag}.
     */
    private CommandResult executeDeleteByTag(Model model) throws CommandException {
        assert targetTag != null : "targetTag should not be null";
        requireNonNull(model);

        boolean hasTag = false;
        List<Person> originalList = model.getFilteredPersonList();

        for (int i = 0; i < originalList.size(); i++) {
            personToDelete = originalList.get(i);
            if (personToDelete.getTags().contains(targetTag)) {
                deletedList.add(personToDelete);
                tagIndexes.add(Index.fromZeroBased(i));
                hasTag = true;
            }
        }

        for (Person person : deletedList) {
            model.deletePerson(person);
        }

        if (!hasTag) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            commandToUndo.setPrevCommand(null);
            throw new CommandException(String.format(MESSAGE_UNKNOWN_PERSON_TAG, targetTag));
        }

        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, targetTag));
    }

    /**
     * Deletes the person identified with specified {@code targetIndex}.
     */
    private CommandResult executeDeleteByIndex(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        assert targetIndex != null : "targetIndex should not be null";
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            commandToUndo.setPrevCommand(null);
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    /**
     * Deletes the person named {@code targetName}.
     */
    private CommandResult executeDeleteByName(Model model) throws CommandException {
        assert targetName != null : "targetName should not be null";

        boolean hasPerson = false;
        List<Person> originalList = model.getFilteredPersonList();

        Person deletedPerson = null;
        for (int i = 0; i < originalList.size(); i++) {
            personToDelete = originalList.get(i);
            Name nameToDelete = personToDelete.getName();
            if (nameToDelete.equals(targetName)) {
                deletedPerson = personToDelete;
                model.deletePerson(personToDelete);
                nameIndex = Index.fromZeroBased(i);
                hasPerson = true;
                break;
            }
        }

        if (!hasPerson) {
            commandToUndo.setPrevCommand(null);
            throw new CommandException(String.format(MESSAGE_UNKNOWN_PERSON_NAME, targetName));
        }

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, deletedPerson));
    }

    public Person getPersonToDelete() {
        return personToDelete;
    }

    public Index getTargetIndex() {
        return targetIndex;
    }

    public Name getTargetName() {
        return targetName;
    }

    public Index getIndexName() {
        return nameIndex;
    }

    public List<Index> getTagIndexes() {
        return tagIndexes;
    }

    public Tag getTargetTag() {
        return targetTag;
    }

    public ArrayList<Person> getDeletedList() {
        return deletedList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        }

        if (!(other instanceof DeleteCommand)) {
            return false; // instanceof handles nulls
        }

        // state check
        if (targetIndex == null && targetTag == null) {
            assert targetName != null : "targetName should not be null";
            return targetName.equals(((DeleteCommand) other).targetName);
        } else if (targetIndex == null && targetName == null) {
            assert targetTag != null : "targetTag should not be null";
            return targetTag.equals(((DeleteCommand) other).targetTag);
        } else {
            assert targetIndex != null : "targetIndex should not be null";
            return targetIndex.equals(((DeleteCommand) other).targetIndex);
        }
    }
}
