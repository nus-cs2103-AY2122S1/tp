package seedu.address.ui;

import java.text.DecimalFormat;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.member.Member;
import seedu.address.model.transaction.Transaction;

/**
 * A UI component that displays information of a summary.
 */
public class SummaryBox extends UiPart<Region> {

    /**
     * Uses FXML to identify SummaryBox.
     */
    private static final String FXML = "SummaryBox.fxml";

    /**
     * Stands for pattern used to format decimals.
     */
    private static final String PATTERN = "#.##";

    /**
     * Stands for current displaying memberList.
     */
    public final ObservableList<Member> memberList;

    /**
     * Stands for components to be used in FXML.
     *
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    @FXML
    private Label totalNumberOfMembers;
    @FXML
    private Label totalNumberOfTransactions;
    @FXML
    private Label totalAmountOfTransactions;
    @FXML
    private Label totalNumberOfTransactionsInPastMonth;
    @FXML
    private Label totalAmountOfTransactionsInPastMonth;
    @FXML
    private Label totalNumberOfTransactionsInPast3Months;
    @FXML
    private Label totalAmountOfTransactionsInPast3Months;
    @FXML
    private Label totalNumberOfTransactionsInPast6Months;
    @FXML
    private Label totalAmountOfTransactionsInPast6Months;

    /**
     * Creates a {@code MemberCode} with the given {@code Member} and index to display.
     */
    public SummaryBox(ObservableList<Member> memberList) {
        super(FXML);
        this.memberList = memberList;
        DecimalFormat df = new DecimalFormat(PATTERN);
        totalNumberOfMembers.textProperty().bind(
                Bindings.createStringBinding(() -> String.valueOf(memberList.size()), memberList));
        totalNumberOfTransactions.textProperty().bind(
                Bindings.createStringBinding(() -> String.valueOf(memberList.stream()
                        .mapToInt(member -> member.getTransactions().size()).sum()), memberList));
        totalAmountOfTransactions.textProperty().bind(
                Bindings.createStringBinding(() -> df.format(memberList.stream()
                        .mapToDouble(member -> member.getTransactions().stream()
                                .mapToDouble(transaction -> transaction.getBilling()
                                        .getDoubleValue()).sum()).sum()), memberList));
        totalNumberOfTransactionsInPastMonth.textProperty().bind(
                Bindings.createStringBinding(() -> String.valueOf(memberList.stream()
                        .mapToLong(member -> member.getTransactions().stream().filter(transaction -> Transaction
                                        .isWithinPastMonths(transaction.getTimestamp().getLongValue(), 1))
                                .count()).sum()), memberList));
        totalAmountOfTransactionsInPastMonth.textProperty().bind(
                Bindings.createStringBinding(() -> df.format(memberList.stream()
                        .mapToDouble(member -> member.getTransactions().stream().filter(transaction -> Transaction
                                        .isWithinPastMonths(transaction.getTimestamp().getLongValue(), 1))
                                .mapToDouble(transaction -> transaction.getBilling()
                                        .getDoubleValue()).sum()).sum()), memberList));
        totalNumberOfTransactionsInPast3Months.textProperty().bind(
                Bindings.createStringBinding(() -> String.valueOf(memberList.stream()
                        .mapToLong(member -> member.getTransactions().stream().filter(transaction -> Transaction
                                        .isWithinPastMonths(transaction.getTimestamp().getLongValue(), 3))
                                .count()).sum()), memberList));
        totalAmountOfTransactionsInPast3Months.textProperty().bind(
                Bindings.createStringBinding(() -> df.format(memberList.stream()
                        .mapToDouble(member -> member.getTransactions().stream().filter(transaction -> Transaction
                                        .isWithinPastMonths(transaction.getTimestamp().getLongValue(), 3))
                                .mapToDouble(transaction -> transaction.getBilling()
                                        .getDoubleValue()).sum()).sum()), memberList));
        totalNumberOfTransactionsInPast6Months.textProperty().bind(
                Bindings.createStringBinding(() -> String.valueOf(memberList.stream()
                        .mapToLong(member -> member.getTransactions().stream().filter(transaction -> Transaction
                                        .isWithinPastMonths(transaction.getTimestamp().getLongValue(), 6))
                                .count()).sum()), memberList));
        totalAmountOfTransactionsInPast6Months.textProperty().bind(
                Bindings.createStringBinding(() -> df.format(memberList.stream()
                        .mapToDouble(member -> member.getTransactions().stream().filter(transaction -> Transaction
                                        .isWithinPastMonths(transaction.getTimestamp().getLongValue(), 6))
                                .mapToDouble(transaction -> transaction.getBilling()
                                        .getDoubleValue()).sum()).sum()), memberList));
    }
}
