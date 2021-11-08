package seedu.address.logic.commands.member;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.member.Member;

/**
 * Finds and lists all members in Ailurus with any of their tasks' names containing any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class MtfindCommand extends Command {

    public static final String COMMAND_WORD = "mtfind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all members whose tasks contains any of "
            + "the specified keywords (case-insensitive) and display them as a list of members with index numbers.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " form";

    private final Predicate<Member> predicate;

    public MtfindCommand(Predicate<Member> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredMemberList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_MEMBERS_LISTED_OVERVIEW, model.getFilteredMemberList().size())
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MtfindCommand that = (MtfindCommand) o;

        return Objects.equals(predicate, that.predicate);
    }

    @Override
    public int hashCode() {
        return predicate != null ? predicate.hashCode() : 0;
    }
}
