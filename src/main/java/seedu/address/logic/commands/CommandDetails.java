package seedu.address.logic.commands;

import javafx.beans.property.SimpleStringProperty;

/**
 * A data model for the table in the help window.
 */
public class CommandDetails {
    private final SimpleStringProperty commandName = new SimpleStringProperty("");
    private final SimpleStringProperty commandStructure = new SimpleStringProperty("");

    /**
     * Initializes a {@code CommandDetails} object with the respective
     * name and structure of the command.
     *
     * @param commandName The name of the feature.
     * @param commandStructure The command structure of the feature.
     */
    public CommandDetails(String commandName, String commandStructure) {
        setCommandName(commandName);
        setCommandStructure(commandStructure);
    }

    public CommandDetails() {
        this("", "");
    }

    public String getCommandName() {
        return commandName.get();
    }

    public String getCommandStructure() {
        return commandStructure.get();
    }

    public void setCommandName(String commandName) {
        this.commandName.set(commandName);
    }

    public void setCommandStructure(String commandStructure) {
        this.commandStructure.set(commandStructure);
    }
}
