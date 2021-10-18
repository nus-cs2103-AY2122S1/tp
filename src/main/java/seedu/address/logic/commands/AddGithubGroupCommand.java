package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commons.RepoName;
import seedu.address.model.group.Group;
import seedu.address.model.group.LinkYear;

/**
 * Adds an existing student in the student list to an existing group in the group list.
 */
public class AddGithubGroupCommand extends Command {

    public static final String COMMAND_WORD = "addGG";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Github link to an existing group "
            + "by the index number used in the displayed group list.\n"
            + "Parameters: INDEX (must be a positive integer)"
            + PREFIX_YEAR + "YEAR "
            + PREFIX_REPO + "REPO NAME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_YEAR + "AY20212022 "
            + PREFIX_REPO + "tp";

    public static final String MESSAGE_ADD_LINK_SUCCESS = "Link: %1$s has been added to group %2$s!";

    private final Index index;
    private final LinkYear year;
    private final RepoName repoName;

    /**
     * @param index of the student in the filtered student list to add to the group
     */
    public AddGithubGroupCommand(Index index, LinkYear year, RepoName repoName) {
        requireAllNonNull(index, year, repoName);

        this.index = index;
        this.year = year;
        this.repoName = repoName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Group> lastShownList = model.getFilteredGroupList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }

        Group groupToUpdate = lastShownList.get(index.getZeroBased());

        model.addGithubGroup(year, repoName, groupToUpdate);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        return new CommandResult(String.format(MESSAGE_ADD_LINK_SUCCESS,
                groupToUpdate.getGroupGithubLink(year, repoName), groupToUpdate.getName().name));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddGithubGroupCommand)) {
            return false;
        }

        // state check
        AddGithubGroupCommand e = (AddGithubGroupCommand) other;
        return index.equals(e.index);
    }
}
