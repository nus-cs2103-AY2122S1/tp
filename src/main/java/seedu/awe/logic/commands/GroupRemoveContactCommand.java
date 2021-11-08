package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.core.Messages.MESSAGE_GROUPREMOVECONTACTCOMMAND_GROUP_DELETED;
import static seedu.awe.commons.core.Messages.MESSAGE_GROUPREMOVECONTACTCOMMAND_NONEXISTENT_PERSON;
import static seedu.awe.commons.core.Messages.MESSAGE_GROUPREMOVECONTACTCOMMAND_SUCCESS;
import static seedu.awe.commons.core.Messages.MESSAGE_NONEXISTENT_GROUP;
import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Objects;

import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Model;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.person.Person;

public class GroupRemoveContactCommand extends Command {
    public static final String COMMAND_WORD = "groupremovecontact";

    private final GroupName groupName;
    private final ArrayList<Person> membersToBeRemoved;
    private final boolean isValidCommand;

    /**
     * Creates a GroupRemoveContactCommand to add the specified {@code Person}
     */
    public GroupRemoveContactCommand(GroupName groupName,
                                     ArrayList<Person> membersToBeRemoved,
                                     boolean isValidCommand) {
        requireAllNonNull(groupName, membersToBeRemoved, isValidCommand);
        this.groupName = groupName;
        this.membersToBeRemoved = membersToBeRemoved;
        this.isValidCommand = isValidCommand;
    }

    /**
     * Returns int object tracking the number of non-matching members between membersToBeRemoved and otherMembers.
     *
     * @param numberOfNonMatchingMembers int value to track the number of members in otherMembers
     *                                   that are not present in membersToBeRemoved.
     * @param member Person object that is being searched for in otherMembers.
     * @param otherMembers List of Person objects from another instance of CreateGroupCommand.
     * @return int object to track the number of members in otherMembers that are not present in membersToBeRemoved.
     */
    public int checkForMember(int numberOfNonMatchingMembers, Person member, ArrayList<Person> otherMembers) {
        for (Person otherMember : otherMembers) {
            if (member.equals(otherMember)) {
                numberOfNonMatchingMembers--;
                break;
            }
        }
        return numberOfNonMatchingMembers;
    }

    /**
     * Returns a boolean object representing if membersToBeRemoved contains the same Person objects as otherMembers.
     *
     * @param otherMembers List of Person objects from another instance of GroupRemoveContactCommand.
     * @return boolean object representing if membersToBeRemoved contains the same Person objects as otherMembers.
     */
    public boolean checkSameMembers(ArrayList<Person> otherMembers) {
        if (this.membersToBeRemoved.size() != otherMembers.size()) {
            return false;
        }
        int numberOfNonMatchingMembers = otherMembers.size();
        for (Person member : this.membersToBeRemoved) {
            numberOfNonMatchingMembers = checkForMember(numberOfNonMatchingMembers, member, otherMembers);
        }
        return numberOfNonMatchingMembers == 0;
    }

    /**
     * Checks if the list of person to be remove is contained in the original list.
     *
     * @param membersFromOldGroup List of Person objects representing original members in a group.
     * @param membersToBeRemoved List of Person objects representing members to be removed from a group.
     * @return Whether the list to remove person is valid
     */
    public boolean checkRemoveMembers(ArrayList<Person> membersFromOldGroup,
                                           ArrayList<Person> membersToBeRemoved) {
        ArrayList<Person> remainingMembers = new ArrayList<>(membersFromOldGroup);
        for (Person memberToBeRemoved : membersToBeRemoved) {
            boolean memberToBeRemovedIsPresent = remainingMembers.remove(memberToBeRemoved);
            if (!memberToBeRemovedIsPresent) {
                return false;
            }
        }
        return true;
    }

    public GroupName getGroupName() {
        return groupName;
    }

    public ArrayList<Person> getMembersToBeRemoved() {
        return membersToBeRemoved;
    }

    public boolean isValidCommand() {
        return isValidCommand;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Group oldGroup = model.getGroupByName(groupName);
        if (Objects.isNull(oldGroup)) {
            throw new CommandException(String.format(MESSAGE_NONEXISTENT_GROUP, groupName.getName()));
        }
        if (!isValidCommand) {
            throw new CommandException(MESSAGE_GROUPREMOVECONTACTCOMMAND_NONEXISTENT_PERSON);
        }
        ArrayList<Person> membersFromOldGroup = oldGroup.getMembers();
        if (!checkRemoveMembers(membersFromOldGroup, membersToBeRemoved)) {
            throw new CommandException(MESSAGE_GROUPREMOVECONTACTCOMMAND_NONEXISTENT_PERSON);
        }

        Group newGroup = oldGroup;
        for (Person member: membersToBeRemoved) {
            newGroup = newGroup.removeMember(member);
        }
        if (newGroup.getMembers().size() == 0) {
            model.deleteGroup(oldGroup);
            model.setAllMembersOfGroup(oldGroup);
            return new CommandResult(MESSAGE_GROUPREMOVECONTACTCOMMAND_SUCCESS
                    + String.format(MESSAGE_GROUPREMOVECONTACTCOMMAND_GROUP_DELETED,
                    oldGroup.getGroupName().getName()));
        } else {
            model.setGroup(oldGroup, newGroup);
            model.setAllMembersOfGroup(oldGroup);
            return new CommandResult(MESSAGE_GROUPREMOVECONTACTCOMMAND_SUCCESS);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof GroupRemoveContactCommand)) {
            return false;
        }
        GroupRemoveContactCommand otherCommand = (GroupRemoveContactCommand) other;
        if (this.isValidCommand == otherCommand.isValidCommand()
                && checkSameMembers(otherCommand.getMembersToBeRemoved())
                && this.groupName.equals(otherCommand.getGroupName())) {
            return true;
        }
        return false;
    }
}
