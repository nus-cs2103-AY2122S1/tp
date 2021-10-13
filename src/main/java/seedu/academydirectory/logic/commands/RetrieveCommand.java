package seedu.academydirectory.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.academydirectory.commons.core.Messages;
import seedu.academydirectory.logic.parser.Prefix;
import seedu.academydirectory.model.Model;
import seedu.academydirectory.model.student.Information;
import seedu.academydirectory.model.student.InformationWantedFunction;

/**
 * Finds and lists all persons in academy directory whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class RetrieveCommand extends Command {
    public static final List<Prefix> SUPPORTED_PREFIX = List.of(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM);

    public static final String COMMAND_WORD = "retrieve";

    public static final String HELP_MESSAGE = "### Retrieving additional information of students': `retrieve`\n"
            + "\n"
            + "Retrieves additional information of students if present. Currently supported information includes: \n"
            + "- Studio Participation\n"
            + "- Telegram Handle\n"
            + "- Contact Number, if any\n"
            + "\n"
            + "Format: `retrieve INFORMATION [of STUDENT_NAME]`";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Retrieve personal details of students "
            + "and displays them as a list\n"
            + "Parameters: " + PREFIX_EMAIL + " | "
            + PREFIX_TELEGRAM + " | " + PREFIX_PHONE + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PHONE;

    private final List<InformationWantedFunction> filterList;

    public RetrieveCommand(List<InformationWantedFunction> filterList) {
        this.filterList = filterList;
    }

    public RetrieveCommand(InformationWantedFunction filter) {
        this(List.of(filter));
    }

    private String executeFilter(Model model, InformationWantedFunction filter) {
        ObservableList<Information> view = model.getFilteredStudentListView(filter);
        return view.size() == 0
                ? String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, 0)
                : view.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(filterList.stream().parallel().map(x -> executeFilter(model, x))
                .collect(Collectors.joining("\n")));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof RetrieveCommand) {
            List<InformationWantedFunction> otherList = ((RetrieveCommand) other).filterList;
            List<InformationWantedFunction> thisList = this.filterList;

            return thisList.stream().map(otherList::contains).reduce(true, (x, y) -> x && y)
                    &&
                    otherList.stream().map(thisList::contains).reduce(true, (x, y) -> x && y);
        }
        return false;
    }
}
