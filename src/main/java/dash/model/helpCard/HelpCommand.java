package dash.model.helpCard;

import dash.ui.HelpCard;

/**
 * Represents a HelpCommand in the help tab.
 */
public class HelpCommand {
    private String header = "";
    private String content = "";

    public HelpCommand(String header, String content) {
        this.header = header;
        this.content = content;
    }

    public String getHeader() {
        return this.header;
    }

    public String getContent() {
        return this.content;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HelpCard)) {
            return false;
        }

        // state check
        HelpCommand helpCommand = (HelpCommand) other;
        return header.equals(helpCommand.header)
                && content.equals(helpCommand.content);
    }
}
