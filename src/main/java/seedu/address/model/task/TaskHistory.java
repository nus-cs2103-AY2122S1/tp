package seedu.address.model.task;

/**
 * Represents a TaskHistory in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class TaskHistory {

    private final TaskName name;
    private final CommandType commandType;

    /**
     * Constructs a {@code Task}.
     *
     * @param name A valid TaskName.
     */
    public TaskHistory(TaskName name, CommandType commandType) {
        this.name = name;
        this.commandType = commandType;
    }

    public TaskName getName() {
        return name;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(commandType.toString());
        return builder.toString();
    }
}
