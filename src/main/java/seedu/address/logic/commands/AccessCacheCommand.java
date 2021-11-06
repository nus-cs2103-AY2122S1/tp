package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class AccessCacheCommand extends Command {

    public static final String COMMAND_WORD = "accesscache";
    public static final String UNKNOWN_KEY = "Unknown Key in AccessCacheCommand#Execute!";

    public final String key;

    /**
     * Constructor for a AccessCacheCommand to access the cache.
     *
     * @param key The key pressed.
     */
    public AccessCacheCommand(String key) {
        requireNonNull(key);
        assert(isValidKey(key));
        this.key = key;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (key.equals("UP")) {
            return new CommandResult("", false, false, true,
                    model.getBefore()
            );
        } else {
            return new CommandResult("", false, false, true,
                    model.getAfter()
            );
        }
    }

    /**
     * Check if a key is valid.
     *
     * @param key The key to check.
     * @return A boolean that shows whether the key is valid.
     */
    public static boolean isValidKey(String key) {
        return (key.equals("UP") || key.equals("DOWN"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccessCacheCommand // instanceof handles nulls
                && key.equals(((AccessCacheCommand) other).key));
    }
}
