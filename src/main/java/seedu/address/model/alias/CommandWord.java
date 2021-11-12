package seedu.address.model.alias;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.Serializable;
import java.util.List;

import seedu.address.logic.commands.AddAliasCommand;
import seedu.address.logic.commands.AddFacilityCommand;
import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.logic.commands.AllocateMemberCommand;
import seedu.address.logic.commands.ClearAttendanceCommand;
import seedu.address.logic.commands.ClearFacilitiesCommand;
import seedu.address.logic.commands.ClearMembersCommand;
import seedu.address.logic.commands.DeallocateMemberCommand;
import seedu.address.logic.commands.DeleteAliasCommand;
import seedu.address.logic.commands.DeleteFacilityCommand;
import seedu.address.logic.commands.DeleteMemberCommand;
import seedu.address.logic.commands.EditFacilityCommand;
import seedu.address.logic.commands.EditMemberCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.FindFacilityCommand;
import seedu.address.logic.commands.FindMemberCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.ListFacilityCommand;
import seedu.address.logic.commands.ListMemberCommand;
import seedu.address.logic.commands.MarkAttendanceCommand;
import seedu.address.logic.commands.SetMemberAvailabilityCommand;
import seedu.address.logic.commands.ShowAliasesCommand;
import seedu.address.logic.commands.SortMemberCommand;
import seedu.address.logic.commands.SplitCommand;
import seedu.address.logic.commands.UnmarkAttendanceCommand;

/**
 * Represents a CommandWord for an alias.
 * Guarantees: is valid as declared in {@link #isValidCommandWord(String)}
 */
public class CommandWord implements Serializable {

    public static final String MESSAGE_CONSTRAINTS =
            "Command Word should be valid command, and it should not be blank";

    /** All possible commands. */
    private static final List<String> LIST_OF_COMMANDS = List.of(AddMemberCommand.COMMAND_WORD,
            EditMemberCommand.COMMAND_WORD, DeleteMemberCommand.COMMAND_WORD, DeleteFacilityCommand.COMMAND_WORD,
            EditFacilityCommand.COMMAND_WORD, ClearMembersCommand.COMMAND_WORD, ClearFacilitiesCommand.COMMAND_WORD,
            FindMemberCommand.COMMAND_WORD, ListMemberCommand.COMMAND_WORD, ListFacilityCommand.COMMAND_WORD,
            ExitCommand.COMMAND_WORD, HelpCommand.COMMAND_WORD, FindFacilityCommand.COMMAND_WORD,
            AddFacilityCommand.COMMAND_WORD, SetMemberAvailabilityCommand.COMMAND_WORD, SplitCommand.COMMAND_WORD,
            MarkAttendanceCommand.COMMAND_WORD, UnmarkAttendanceCommand.COMMAND_WORD,
            ClearAttendanceCommand.COMMAND_WORD, SortMemberCommand.COMMAND_WORD, AllocateMemberCommand.COMMAND_WORD,
            DeallocateMemberCommand.COMMAND_WORD, ImportCommand.COMMAND_WORD, ExportCommand.COMMAND_WORD,
            AddAliasCommand.COMMAND_WORD, DeleteAliasCommand.COMMAND_WORD, ShowAliasesCommand.COMMAND_WORD);

    private String commandWord;

    /**
     * Constructs a {@code CommandWord}.
     */
    public CommandWord(String commandWord) {
        requireNonNull(commandWord);
        checkArgument(isValidCommandWord(commandWord), MESSAGE_CONSTRAINTS);
        this.commandWord = commandWord;
    }

    /** Dummy constructor for Json to work. */
    public CommandWord() {}

    public String getCommandWord() {
        return commandWord;
    }

    /**
     * Returns true if given string is a valid commandWord.
     */
    public static boolean isValidCommandWord(String test) {
        return LIST_OF_COMMANDS.contains(test);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommandWord // instanceof handles nulls
                && commandWord.equals(((CommandWord) other).commandWord)); // state check
    }

    @Override
    public String toString() {
        return commandWord;
    }

    @Override
    public int hashCode() {
        return commandWord.hashCode();
    }

}
