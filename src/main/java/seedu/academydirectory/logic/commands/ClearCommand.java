package seedu.academydirectory.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.academydirectory.model.AcademyDirectory;
import seedu.academydirectory.model.VersionedModel;

/**
 * Clears the academy directory.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String HELP_MESSAGE = "#### Clearing all entries : `clear`\n"
            + "\n"
            + "Clears all entries from the academy directory.\n"
            + "\n"
            + "Format: `clear`";

    public static final String COMMIT_MESSAGE = "Academy Directory has been cleared!";
    public static final String MESSAGE_SUCCESS = "Academy Directory has been cleared!";


    @Override
    public CommandResult execute(VersionedModel model) {
        requireNonNull(model);
        model.setAcademyDirectory(new AcademyDirectory());
        return new CommandResult(MESSAGE_SUCCESS, Optional.of(COMMIT_MESSAGE));
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ClearCommand);
    }
}
