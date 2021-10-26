package seedu.address.logic.commands;

/**
 * Sorts applicants in the address book based on passed or future interviews.
 */
public abstract class SortInterviewCommand extends Command {

    public enum ValidSortInterviewArgs {
        PAST("past", new SortInterviewPastCommand()),
        FUTURE("future", new SortInterviewFutureCommand());

        private final String arg;
        private final SortInterviewCommand sortInterviewCommand;

        ValidSortInterviewArgs(String arg, SortInterviewCommand sortInterviewCommand) {
            this.arg = arg;
            this.sortInterviewCommand = sortInterviewCommand;
        }

        public static String getRegex() {
            StringBuilder regex = new StringBuilder();
            for (ValidSortInterviewArgs validArg : ValidSortInterviewArgs.values()) {
                regex.append(validArg.arg);
                regex.append("|");
            }
            return regex.toString();
        }

        public SortInterviewCommand getSortInterviewCommand() {
            return this.sortInterviewCommand;
        }

        @Override
        public String toString() {
            return this.arg;
        }
    }

    public static final String COMMAND_WORD = "sort_interview";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + " " + ValidSortInterviewArgs.PAST
                    + ": Shows applicants who have interviews that have passed (sorted chronologically). \n"
                    + COMMAND_WORD + " " + ValidSortInterviewArgs.FUTURE
                    + ": Shows applicants who have upcoming interviews (sorted chronologically). ";

}
