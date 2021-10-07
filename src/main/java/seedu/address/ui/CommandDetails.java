package seedu.address.ui;

import javafx.beans.property.SimpleStringProperty;

public class CommandDetails {
    private final SimpleStringProperty featureName = new SimpleStringProperty("");
    private final SimpleStringProperty commandStructure = new SimpleStringProperty("");

    /**
     *
     * @param featureName
     * @param commandStructure
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
