package seedu.address.logic.commands;

/**
 * Filters applicants in the address book based on past or future interviews.
 */
public abstract class FilterInterviewCommand extends Command {

    /**
     * Contains information regarding the valid FilterInterviewCommand arguments, which are past and future.
     */
    public enum ValidFilterInterviewArgs {
        PAST("past", new FilterInterviewPastCommand()),
        FUTURE("future", new FilterInterviewFutureCommand());

        private final String arg;
        private final FilterInterviewCommand filterInterviewCommand;

        /**
         * Constructs a {@code ValidFilterInterviewArgs}.
         *
         * @param arg {@code String} of the valid argument input
         * @param filterInterviewCommand appropriate subclass of {@code FilterInterviewCommand} corresponding to the argument
         */
        ValidFilterInterviewArgs(String arg, FilterInterviewCommand filterInterviewCommand) {
            this.arg = arg;
            this.filterInterviewCommand = filterInterviewCommand;
        }

        /**
         * Gets the regex used to validate {@code FilterInterviewCommand} arguments.
         *
         * @return {@code String} used as regex to validate {@code FilterInterviewCommand} arguments.
         */
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
