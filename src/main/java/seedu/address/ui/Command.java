package seedu.address.ui;

import seedu.address.commons.util.Copyable;

/**
 * Represents a copyable command input.
 */
public class Command implements Copyable<Command> {
    private final String command;

    /**
     * Creates a copyable command using the provided String command.
     *
     * @param command The String command.
     */
    protected Command(String command) {
        this.command = command;
    }

    /**
     * Creates a duplicate Command with the same command string.
     *
     * @return The duplicate Command.
     */
    @Override
    public Command copy() {
        return new Command(command);
    }

    /**
     * Gets the String command stored in the instance of Command.
     *
     * @return The String command.
     */
    protected String getCommand() {
        return command;
    }
}
