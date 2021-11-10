package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReportElement;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.ui.ReportWindow;

public class ReportCommand extends Command {

    public static final String COMMAND_WORD = "report";
    public static final String SHOWING_REPORT_MESSAGE = "Opened report window.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows status report for contacts.\n"
            + "Example: " + COMMAND_WORD;
    private static int totalConfirmedCount;
    private static int totalPendingCount;
    private static int totalDeclinedCount;

    /**
     * Creates new {@code ReportCommand}
     */
    public ReportCommand() {

    }

    /**
     * Checks for tag in the existing report arraylist. If it is present, the corresponding report element is
     * updated with incremented status count and true is returned. Else, false is returned.
     * @param reportArray is the arraylist with report elements
     * @param tag is the tag associated with element
     * @param status is the status associated with element
     * @return true if tag exists and so status can be updated
     */
    public static boolean canUpdateExistingElement(ArrayList<ReportElement> reportArray, String tag, String status) {
        for (ReportElement e : reportArray) {
            if (e.hasSameTag(tag)) {
                incrementStatusCount(e, status);
                return true;
            }
        }
        return false;
    }

    /**
     * Updates status count according to type of status detected.
     * @param element is the report element in which the count is updated
     * @param currStatus is the status being used to keep count
     */
    public static void incrementStatusCount(ReportElement element, String currStatus) {
        switch (currStatus) {
        case "Confirmed":
            element.incrementConfirmed();
            break;
        case "Pending":
            element.incrementPending();
            break;
        case "Declined":
            element.incrementDeclined();
            break;
        default:
            break;
        }
    }

    /**
     * Creates a report arraylist that keeps track of all the tags and the corresponding
     * status count for each tag.
     * @param model is the source from which the list of contacts can be obtained
     * @return report created
     */
    public static String createReport(Model model) throws CommandException {
        totalConfirmedCount = 0;
        totalPendingCount = 0;
        totalDeclinedCount = 0;
        ArrayList<ReportElement> reportArray = new ArrayList<ReportElement>();
        ObservableList<Person> listOfPeople = model.getFilteredPersonList();

        for (Person currPerson : listOfPeople) {
            Set<Tag> currPersonTags = currPerson.getTags();
            String statusString = currPerson.getStatus().toString();
            switch (statusString) {
            case "Confirmed":
                totalConfirmedCount++;
                break;
            case "Pending":
                totalPendingCount++;
                break;
            case "Declined":
                totalDeclinedCount++;
                break;
            default:
                break;
            }
            for (Tag tagUsed : currPersonTags) {
                String tagString = tagUsed.toString();
                if (!canUpdateExistingElement(reportArray, tagString, statusString)) {
                    createNewElement(reportArray, tagString, statusString);
                }
            }
        }

        String priceOverallSum = new PriceCommand().execute(model).getFeedbackToUser();
        StringBuilder priceSumByTag = new StringBuilder();
        for (String tagString : getListOfTag(listOfPeople)) {
            priceSumByTag.append(new PriceCommand(tagString).execute(model).getFeedbackToUser())
                    .append("\n");
        }

        String priceSumReport = String.format("\n%s\n%s", priceOverallSum, priceSumByTag);
        return provideTextReport(reportArray) + priceSumReport;
    }

    /**
     * Creates and adds a new report element to report array.
     * @param reportArray is the arraylist storing report elements
     * @param currTag is the tag being added
     * @param currStatus is the status being used to keep count
     */
    public static void createNewElement(ArrayList<ReportElement> reportArray, String currTag, String currStatus) {
        switch (currStatus) {
        case "Confirmed":
            reportArray.add(new ReportElement(currTag, 1, 0, 0));
            break;
        case "Pending":
            reportArray.add(new ReportElement(currTag, 0, 1, 0));
            break;
        case "Declined":
            reportArray.add(new ReportElement(currTag, 0, 0, 1));
            break;
        default:
            break;
        }
    }

    /**
     * Creates a report in an organised format.
     * @param fullReportArray is the arraylist with all report elements
     * @return final report as a string
     */
    public static String provideTextReport(ArrayList<ReportElement> fullReportArray) {
        String summary = "Current status for contacts: "
                + "\n" + totalConfirmedCount + " confirmed, "
                + totalPendingCount + " pending, "
                + totalDeclinedCount + " declined" + "\n" + "\n";
        String reportAsString = summary + "Current status for tags: " + "\n";
        for (ReportElement currElement : fullReportArray) {
            reportAsString = reportAsString + currElement + "\n";
        }
        return reportAsString;
    }

    /** Get a list of unique existing tags */
    private static List<String> getListOfTag(ObservableList<Person> listOfPeople) {
        Set<String> setOfTagString = new HashSet<>();
        for (Person person : listOfPeople) {
            Set<Tag> tagsOfCurrPerson = person.getTags();
            addTagsToSet(tagsOfCurrPerson, setOfTagString);
        }
        return new ArrayList<>(setOfTagString);
    }

    /** Add each tag from {@code tags} to {@code setOfTagString} */
    private static void addTagsToSet(Set<Tag> tags, Set<String> setOfTagString) {
        for (Tag tag : tags) {
            setOfTagString.add(tag.tagName);
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ReportWindow.setReportMessage(createReport(model));
        ReportWindow window = new ReportWindow();
        window.show();
        return new CommandResult(SHOWING_REPORT_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReportCommand);
    }
}
