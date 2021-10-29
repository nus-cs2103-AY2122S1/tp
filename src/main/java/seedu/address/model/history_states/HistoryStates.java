package seedu.address.model.history_states;

import java.util.LinkedList;

import seedu.address.model.history_states.exceptions.NoHistoryStatesException;

public class HistoryStates {
    private LinkedList<State> historyStates;

    public HistoryStates() {
        this.historyStates = new LinkedList<>();
    }

    public void addNewState(State state) {
        this.historyStates.addLast(state);
    }

    public void undo() {
        this.historyStates.removeLast();
    }

    public State getCurrentState() throws NoHistoryStatesException {
        return this.historyStates.getLast();
    }

    public Boolean hasHistoryStates() {
        return this.historyStates.size() > 1;
    }
}
