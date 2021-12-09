package seedu.times.ui;

/**
 * Represents the name of the tabs of Timestable.
 */
public enum TabName {
    STUDENTS(0),
    CLASSES(1),
    TIMETABLE(2);

    private final int index;

    TabName(int index) {
        this.index = index;
    }

    /**
     * Gets the index of the tab.
     *
     * @return Index of the tab in Timestable
     */
    public int getIndex() {
        return index;
    }
}
