package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class ClaimCommand extends Command {

    public static final String COMMAND_WORD = "claim";

    // TODO Add a message usage
    public static final String MESSAGE_USAGE = "This should be a message usage";

    public static final String MESSAGE_ARGUMENTS = "Index %1$d, Title: %2$s, Description: %2$s, Status: %2$s";

    private final Index index;
    private final String title;
    private final String description;
    private final String status;

    public ClaimCommand(Index index, String title, String description, String status) {
        requireAllNonNull(index, title, description, status);
        this.index = index;
        this.title = title;
        this.description  = description;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
//        return new CommandResult("Hello from Claim Command");
        // TODO add a handler
        throw new CommandException(this.title + this.description + this.status);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ClaimCommand)) {
            return false;
        }

        ClaimCommand e = (ClaimCommand) other;
        return index.equals(e.index) && title.equals(e.title)
                && description.equals(e.description) && status.equals(e.status);
    }
}
