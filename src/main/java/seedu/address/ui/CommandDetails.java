package seedu.address.ui;

import javafx.beans.property.SimpleStringProperty;

/**
 * A data model for the table in the help window.
 */
public class CommandDetails {
    private final SimpleStringProperty featureName = new SimpleStringProperty("");
    private final SimpleStringProperty commandStructure = new SimpleStringProperty("");

    /**
     * Initializes a {@code CommandDetails} object with the respective
     * name and structure of the command.
     *
     * @param featureName The name of the feature.
     * @param commandStructure The command structure of the feature.
     */
    public CommandDetails(String featureName, String commandStructure) {
        setFeatureName(featureName);
        setCommandStructure(commandStructure);
    }

    public CommandDetails() {
        this("", "");
    }

    public String getFeatureName() {
        return featureName.get();
    }

    public String getCommandStructure() {
        return commandStructure.get();
    }

    public void setFeatureName(String featureName) {
        this.featureName.set(featureName);
    }

    public void setCommandStructure(String commandStructure) {
        this.commandStructure.set(commandStructure);
    }
}
