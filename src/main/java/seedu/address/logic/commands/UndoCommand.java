package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Undoes the previous task.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = "The command keyed in must be undo.";

    public static final String MESSAGE_CANNOT_UNDO = "This type of command cannot be undone.";
    public static final String MESSAGE_INVALID_PREV_COMMAND = "The previous command was invalid - cannot undo.";
    public static final String MESSAGE_UNDO_NOT_IMPLEMENTED = "Undo has not been implemented for this command.";
    public static final String MESSAGE_SUCCESS = "The task has been undone";

    private static Command prevCommand;

    public void setPrevCommand(Command prevCommand) {
        this.prevCommand = prevCommand;
    }

    /**
     * Checks whether the command can be done
     * and performs the undo operation accordingly.
     *
     * @param model the model in use
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        CommandResult cannotUndoCommand = checkIfCanUndo(prevCommand, model);
        if (cannotUndoCommand != null) {
            return cannotUndoCommand;
        }
        List<Person> currentList = model.getFilteredPersonList();
        if (prevCommand instanceof AddCommand) {
            model.deletePerson(currentList.get(currentList.size() - 1));
        } else if (prevCommand instanceof DeleteCommand) {
            DeleteCommand deleted = (DeleteCommand) prevCommand;
            Person deletedPerson = deleted.getPersonToDelete();
            undoDelete(deleted, deletedPerson, currentList, model);
        } else if (prevCommand instanceof ClearCommand) {
            ClearCommand cleared = (ClearCommand) prevCommand;
            model.setAddressBook(new AddressBook(cleared.getPrevBook()));
        } else if (prevCommand instanceof EditCommand) {
            EditCommand edited = (EditCommand) prevCommand;
            model.setPerson(edited.getEditedPerson(), edited.getOriginalPerson());
        } else if (prevCommand instanceof GroupCommand) {
            GroupCommand group = (GroupCommand) prevCommand;
            String tagName = group.getTagName();
            List<String> nameList = group.getNameList();
            removePeople(currentList, nameList, tagName, model);
        }
        prevCommand = this;
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredPersonList().size()));
    }

    /**
     * Checks whether the previous command
     * is invalid/cannot be undone.
     *
     * @param prev  the previous command
     * @param model the model in use
     * @return the command result or null
     */
    public CommandResult checkIfCanUndo(Command prev, Model model) {
        if (prev == null) {
            return new CommandResult(String.format(MESSAGE_INVALID_PREV_COMMAND,
                    model.getFilteredPersonList().size()));
        } else if (isInstanceOf(prev)) {
            return new CommandResult(String.format(MESSAGE_CANNOT_UNDO,
                    model.getFilteredPersonList().size()));
        } else if (prev instanceof ShortcutCommand
                || prev instanceof AddShortcutCommand
                || prev instanceof RemoveShortcutCommand
                || prev instanceof CountdownCommand) {
            return new CommandResult(String.format(MESSAGE_UNDO_NOT_IMPLEMENTED,
                    model.getFilteredPersonList().size()));
        }
        return null;
    }

    /**
     * Checks whether the command is an instance
     * of any of the following classes.
     *
     * @param command the command to check
     * @return true if any one condition is satisfied
     */
    public boolean isInstanceOf(Command command) {
        return (command instanceof ExitCommand)
                || (command instanceof FindCommand)
                || (command instanceof HelpCommand)
                || (command instanceof ListCommand)
                || (command instanceof UndoCommand);
    }

    /**
     * Undoes the delete, whether it was based
     * on index, name or tag.
     *
     * @param deleted       the command used to delete
     * @param deletedPerson the person deleted
     * @param currentList   the current contact list
     * @param model         the model in use
     */
    public void undoDelete(DeleteCommand deleted, Person deletedPerson,
                           List<Person> currentList, Model model) {
        if (deleted.getTargetIndex() != null) {
            Index index = deleted.getTargetIndex();
            shuffle(currentList, index.getZeroBased(), deletedPerson,
                    model);
        } else if (deleted.getTargetName() != null) {
            Index index = deleted.getIndexName();
            shuffle(currentList, index.getZeroBased(), deletedPerson,
                    model);
        } else if (deleted.getTargetTag() != null) {
            ArrayList<Person> deletedList = deleted.getDeletedList();
            List<Index> deletedIndexes = deleted.getTagIndexes();
            for (int i = 0; i < deletedList.size(); i++) {
                shuffle(currentList,
                        deletedIndexes.get(i).getZeroBased(),
                        deletedList.get(i), model);
            }
        }
    }

    /**
     * If a person has been deleted, reinstates
     * the deleted person at the correct index
     *
     * @param personList the list of all people
     * @param index      the former index of the deleted person
     * @param newPerson  the deleted person
     * @param model      the model in use
     */
    public void shuffle(List<Person> personList, int index, Person newPerson, Model model) {
        Person temp1 = newPerson;
        for (int count = 0; count < personList.size(); count++) {
            if (count == index) {
                temp1 = personList.get(count);
                model.setPerson(temp1, newPerson);
            } else if (count > index) {
                Person temp2 = personList.get(count);
                model.setPerson(temp2, temp1);
                temp1 = temp2;
            }
        }
        model.addPerson(temp1);
    }

    /**
     * Undoes the adding of people to a new group
     *
     * @param personList the list of all people
     * @param names      the names of those who were added to a group
     * @param tag        the group name
     * @param model      the model in use
     */
    public void removePeople(List<Person> personList, List<String> names, String tag, Model model) {
        for (Person p : personList) {
            for (String name : names) {
                if (p.getName().fullName.equals(name)) {
                    HashSet<Tag> tags = new HashSet<>(p.getTags());
                    tags.removeIf(t -> t.toString().contains(tag));
                    Person editedPerson = new Person(p.getName(), p.getPhone(),
                            p.getEmail(), p.getAddress(), p.getPrice(),
                            p.getInfo(), p.getStatus(), tags);
                    model.setPerson(p, editedPerson);
                    break;
                }
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UndoCommand); // state check
    }
}
