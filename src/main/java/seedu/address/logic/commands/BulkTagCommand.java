package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Add Tags to a set of persons in the address book
 */
public class BulkTagCommand extends Command {

    public static final String COMMAND_WORD = "bulk_tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Bulk Tag the filtered list of persons.\n"
            + "Parameters: " + PREFIX_TAG + "TAG\n"
            + "Examples: " + COMMAND_WORD + " " + PREFIX_TAG + " CompletedIp, " + COMMAND_WORD + " " + PREFIX_TAG
            + " Passed";

    public static final String MESSAGE_SUCCESS = "Added the Tags %s to the Persons";

    private final Set<Tag> tagList;

    /**
     * Creates an BulkTagCommand to add the specified set of Tags to the filtered persons list
     */
    public BulkTagCommand(Set<Tag> tagList) {
        requireNonNull(tagList);
        this.tagList = tagList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> personsToTag = new ArrayList<>(model.getFilteredPersonList());

        for (Person person : personsToTag) {
            Set<Tag> newTagList = new HashSet<>(tagList);
            for (Object o : person.getTags().toArray()) {
                Tag tag = (Tag) o;
                newTagList.add(tag);
            }
            Person taggedPerson = new Person(person.getName(), person.getPhone(), person.getEmail(),
                    person.getAddress(), newTagList, person.getGitHubId(),
                    person.getNusNetworkId(), person.getType(), person.getStudentId(), person.getTutorialId());
            model.setPerson(person, taggedPerson, false);
        }

        return getCommandResult(tagList, personsToTag);
    }

    private CommandResult getCommandResult(Set<Tag> tagList, List<Person> personsToTag) {
        int numberOfPersonsTagged = 0;

        StringBuilder tagsSb = new StringBuilder();
        for (Tag tag : tagList) {
            tagsSb.append(tag.toString() + " ");
        }

        String stringTags = tagsSb.toString().trim().substring(0, tagsSb.toString().length() - 1);

        return new CommandResult(String.format(MESSAGE_SUCCESS, stringTags));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BulkTagCommand // instanceof handles nulls
                && tagList.equals(((BulkTagCommand) other).tagList)); // state check
    }
}
