package seedu.anilist.ui;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.util.AppUtil.checkArgument;

/**
 * This class keeps track of which tab is the current active tab.
 * The current active tab will be the currentTab value.
 */
public class TabOption {
    public static final String MESSAGE_CONSTRAINTS =
            "Status should only be one of 'all', 'towatch', 'watching' or 'finished'";
    public static final String[] VALID_TAB_STRING = {"towatch", "t", "watching", "w", "finished", "f", "all", "a"};

    private TabOptions currentTab;

    public enum TabOptions {
        TOWATCH,
        WATCHING,
        FINISHED,
        ALL
    }

    /**
     * Constructs a {@code TabOption}.
     *
     * @param currentTabString A valid tab status.
     */
    public TabOption(String currentTabString) {
        requireNonNull(currentTabString);
        checkArgument(isValidTabOption(currentTabString), MESSAGE_CONSTRAINTS);
        this.currentTab = parseTabStatus(currentTabString);
    }

    /**
     * Returns true if a given string is a valid tab status.
     */
    public static boolean isValidTabOption(String test) {
        for (String tabString: VALID_TAB_STRING) {
            if (test.toLowerCase().equals(tabString)) {
                return true;
            }
        }
        return false;
    }

    private TabOptions parseTabStatus(String tabString) {
        switch(tabString.toLowerCase()) {
        case "t":
        case "towatch":
            return TabOptions.TOWATCH;
        case "w":
        case "watching":
            return TabOptions.WATCHING;
        case "f":
        case "finished":
            return TabOptions.FINISHED;
        case "a":
        case "all":
            return TabOptions.ALL;
        default:
            assert false : "Invalid tab option";
            return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TabOption // instanceof handles nulls
                && currentTab == ((TabOption) other).getCurrentTab()); // state check
    }

    @Override
    public String toString() {
        switch (this.currentTab) {
        case TOWATCH:
            return VALID_TAB_STRING[0];
        case WATCHING:
            return VALID_TAB_STRING[2];
        case FINISHED:
            return VALID_TAB_STRING[4];
        case ALL:
            return VALID_TAB_STRING[6];
        default:
            assert false : "Invalid tab option";
            return "Invalid tab option";
        }
    }

    /**
     * @return The tab status value denoting the current state of which tab is active
     */
    public TabOptions getCurrentTab() {
        return currentTab;
    }

    public void setCurrentTab(TabOptions currentTab) {
        this.currentTab = currentTab;
    }
}
