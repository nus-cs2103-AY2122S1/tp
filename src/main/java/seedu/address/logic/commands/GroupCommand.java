package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Add tags to a group of contacts at the same time. Command stops if one of the names is invalid.
 */
public class GroupCommand extends Command {

    public static final String COMMAND_WORD = "group";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds tags to all the selected contacts. "
            + "Parameters: "
            + PREFIX_TAG + "friends "
            + PREFIX_NAME + "NAME1, NAME2, NAME3, NAME4 ... no limit to number";
    public static final String MESSAGE_ERROR_TAG = "Invalid tag inputted";
    public static final String MESSAGE_ERROR_NAMES = "Invalid names inputted";
    public static final String MESSAGE_FAILURE = "Group command has failed";

    private boolean isFirstAdded = true;
    private String tag;
    private List<String> nameList;
    private UndoCommand commandToUndo;

    /**
     * Constructs a group command based on the inputs.
     */
    public GroupCommand(String inputTag, String inputNameList) {
        commandToUndo = new UndoCommand();
        commandToUndo.setPrevCommand(this);
        tag = inputTag;
        nameList = Arrays.asList(inputNameList.split(", "));
    }

    public String getTagName() {
        return tag;
    }

    public List<String> getNameList() {
        return nameList;
    }


    /**
     * Creates a string that indicates all the names that have been added with the tag.
     * It also indicates if all the name have been added or if some names have failed.
     *
     * @param model      is the main ModelManager class used by the current user
     * @param result     is the string that contains the final message to be sent
     * @param targetName is the string name that is used to identify the target person to be edited
     * @return the message string
     */
    private String addTagToContact(Model model, String result, String targetName) {
        for (Person temp : model.getAddressBook().getPersonList()) {
            if (temp.isSamePerson(Person.createTempFakePerson(targetName))) {
                HashSet<Tag> newTags = new HashSet<>(temp.getTags());
                newTags.add(new Tag(tag));
                model.setPerson(temp, new Person(temp.getName(), temp.getPhone(),
                        temp.getEmail(), temp.getAddress(), temp.getPrice(), temp.getInfo(),
                        temp.getStatus(), newTags));
                if (isFirstAdded) {
                    result += " " + targetName;
                    isFirstAdded = false;
                } else {
                    result += ", " + targetName;
                }
            }
        }
        return result;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String nameStringLists = "";
        boolean isTagAdded = false;
        String returnMessage = "The tag "
                + tag
                + " has been added to the name :";
        for (String name : nameList) {
            if (model.hasPersonName(name)) {
                isTagAdded = true;
                returnMessage = addTagToContact(model, returnMessage, name);
            } else if (isTagAdded) {
                commandToUndo.setPrevCommand(null);
                return new CommandResult(returnMessage
                        + nameStringLists
                        + "\n"
                        + MESSAGE_FAILURE);
            } else {
                commandToUndo.setPrevCommand(null);
                return new CommandResult(MESSAGE_FAILURE);
            }
        }
        return new CommandResult(returnMessage + nameStringLists);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GroupCommand) {
            GroupCommand testCommand = (GroupCommand) obj;
            if (testCommand.getTagName().equals(tag)
                    && testCommand.getNameList().equals(nameList)) {
                return true;
            }
        }
        return false;
    }
}

