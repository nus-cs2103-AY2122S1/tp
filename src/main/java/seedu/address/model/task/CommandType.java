package seedu.address.model.task;

public enum CommandType {

    ADD("add"), DELETE("delete"), EDIT("edit");

    private final String usedCommand;

    CommandType(String usedCommand) {
        this.usedCommand = usedCommand;
    }

    @Override
    public String toString() {
        return "Task was" + usedCommand + "ed";
    }

}

