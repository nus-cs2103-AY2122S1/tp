package manageme.time;

import manageme.model.ReadOnlyManageMe;

/**
 * API of the Time component
 */
public interface Time {

    /** Starts the Time component to constantly check tasks' start and end time. */
    void startTime();

    /** Updates Time component to have the latest state of Model */
    void updateTasks(ReadOnlyManageMe manageMe);

    /** Stops the Time component */
    void stopTime();
}
