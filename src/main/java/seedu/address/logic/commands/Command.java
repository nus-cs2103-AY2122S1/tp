package seedu.address.logic.commands;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /** All possible commands. */
    public static List<String> commands = List.of(AddMemberCommand.COMMAND_WORD, EditMemberCommand.COMMAND_WORD,
            DeleteMemberCommand.COMMAND_WORD, DeleteFacilityCommand.COMMAND_WORD,
            EditFacilityCommand.COMMAND_WORD, ClearMembersCommand.COMMAND_WORD,
            ClearFacilitiesCommand.COMMAND_WORD, FindMemberCommand.COMMAND_WORD,
            ListMemberCommand.COMMAND_WORD, ListFacilityCommand.COMMAND_WORD, ExitCommand.COMMAND_WORD,
            HelpCommand.COMMAND_WORD, FindFacilityCommand.COMMAND_WORD, AddFacilityCommand.COMMAND_WORD,
            SetMemberAvailabilityCommand.COMMAND_WORD, SplitCommand.COMMAND_WORD,
            AddAliasCommand.COMMAND_WORD);

    /**
     * Returns true if given string is a command word.
     *
     * @param command string to check.
     * @return true if is command, false if otherwise.
     */
    public static boolean isCommand(String command) {
        return commands.contains(command);
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

}
