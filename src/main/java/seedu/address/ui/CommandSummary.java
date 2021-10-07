package seedu.address.ui;

/**
 * Container for command summary.
 */
public class CommandSummary {
    /** Description of what the command does. */
    private String action;

    /** Command format. */
    private String format;

    /** Example of command usage. */
    private String example;

    /**
     * Constructs a CommandSummary object.
     *
     * @param action Description of what the command does.
     * @param format Format of a valid command.
     * @param example Example of command usage.
     */
    public CommandSummary(String action, String format, String example) {
        this.action = action;
        this.format = format;
        this.example = example;
    }

    /**
     * Returns the description of what the command does.
     *
     * @return Description of what the command does.
     */
    public String getAction() {
        return action;
    }

    /**
     * Returns the format of the valid command with command word and parameters.
     *
     * @return The format of the valid command.
     */
    public String getFormat() {
        return format;
    }

    /**
     * Returns an example usage of the command.
     *
     * @return Example usage of the command.
     */
    public String getExample() {
        return example;
    }
}
