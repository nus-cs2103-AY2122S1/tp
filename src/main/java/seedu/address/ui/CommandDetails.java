package seedu.address.ui;

public class CommandDetails {
    private String featureName;
    private String commandStructure;

    public CommandDetails(String featureName, String commandStructure) {
        this.featureName = featureName;
        this.commandStructure = commandStructure;
    }

    public String getFeatureName() {
        return featureName;
    }

    public String getCommandStructure() {
        return commandStructure;
    }
}
