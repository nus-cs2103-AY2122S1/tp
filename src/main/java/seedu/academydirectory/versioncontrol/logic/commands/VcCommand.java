package seedu.academydirectory.versioncontrol.logic.commands;

import java.io.IOException;
import java.text.ParseException;

import seedu.academydirectory.logic.commands.CommandResult;
import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.versioncontrol.OptionalVersion;
import seedu.academydirectory.versioncontrol.Version;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class VcCommand {

    /**
     * Executes the command and returns the result message.
     *
     * @param version {@code OptionalVersion<Version>} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(OptionalVersion<Version> version) throws CommandException,
            IOException, ParseException;

}
