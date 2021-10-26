package seedu.address.logic.commands;

/**
 * Filters applicants in the address book based on past or future interviews.
 */
public abstract class FilterInterviewCommand extends Command {

    public enum ValidFilterInterviewArgs {
        PAST("past", new FilterInterviewPastCommand()),
        FUTURE("future", new FilterInterviewFutureCommand());

        private final String arg;
        private final FilterInterviewCommand filterInterviewCommand;

        ValidFilterInterviewArgs(String arg, FilterInterviewCommand filterInterviewCommand) {
            this.arg = arg;
            this.filterInterviewCommand = filterInterviewCommand;
        }

        public static String getRegex() {
            StringBuilder regex = new StringBuilder();
            for (ValidFilterInterviewArgs validArg : ValidFilterInterviewArgs.values()) {
                regex.append(validArg.arg);
                regex.append("|");
            }
            return regex.deleteCharAt(regex.length() - 1).toString(); // To remove the last "|"
        }

        public FilterInterviewCommand getFilterInterviewCommand() {
            return this.filterInterviewCommand;
        }

        @Override
        public String toString() {
            return this.arg;
        }
    }

    public static final String COMMAND_WORD = "filter_interview";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + " " + ValidFilterInterviewArgs.PAST
                    + ": Shows applicants with interviews that have passed. \n"
                    + COMMAND_WORD + " " + ValidFilterInterviewArgs.FUTURE
                    + ": Shows applicants with upcoming interviews. ";

}
