package seedu.address.ui;

public class CommandSummary {
    private String action;
    private String format;
    private String example;

    public CommandSummary(String action, String format, String example) {
        this.action = action;
        this.format = format;
        this.example = example;
    }

    public String getAction() {
        return action;
    }

    public String getFormat() {
        return format;
    }

    public String getExample() {
        return example;
    }
}
