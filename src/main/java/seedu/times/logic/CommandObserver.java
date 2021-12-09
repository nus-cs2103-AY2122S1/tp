package seedu.times.logic;

import seedu.times.ui.TabName;

/**
 * Interface to represent the observers of command class.
 */
public interface CommandObserver {

    /**
     * Updates the view of the observer.
     *
     * @param tabToView tab to change the view to.
     */
    public void updateView(TabName tabToView);

    /**
     * Updates the class selected of the observer.
     *
     * @param indexOfClassToSelect class index to change the selected to.
     */
    public void updateClass(Integer indexOfClassToSelect);

    /**
     * Hides the tuition class student list.
     */
    public void hideTuitionClassStudentList();
}
