package seedu.address.ui;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Person;

public class BirthdayReminderCard extends UiPart<Region> {
    private static final String FXML = "BirthdayReminderListCard.fxml";
    private static final String MONTH_DAY_STRING_FORMAT = "dd MMM";
    private static final String BIRTHDAY_MESSAGE_DEFAULT = "Turns %d on %s!";
    private static final String BIRTHDAY_MESSAGE_THIS_WEEK = "Turns %d in %d days on %s!";
    private static final String BIRTHDAY_MESSAGE_TODAY = "Turns %d TODAY!";
    private static final String WELL_WISHES_PROMPT_MESSAGE = "Send your well wishes to %s.";
    private static final String STYLE_CARD_BIRTHDAY_TODAY = "-fx-background-color: #6F9C51; "
            + "-fx-border-width: 0.5px; "
            + "-fx-border-color: #1d1d1d";
    private static final String STYLE_CARD_BIRTHDAY_THIS_WEEK = "-fx-background-color: #345597; "
            + "-fx-border-width: 0.5px; "
            + "-fx-border-color: #1d1d1d";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label reminderMessage;
    @FXML
    private Label wellWishesPrompt;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public BirthdayReminderCard(Person person) {
        super(FXML);
        this.person = person;
        name.setText(person.getFullName());
        Optional<Birthday> possibleBirthday = person.getBirthday();
        assert possibleBirthday.isPresent();
        Birthday birthday = possibleBirthday.orElse(null);
        String phoneNumber = person.getPhoneNumber();
        reminderMessage.setText(generateBirthdayReminderMessage(birthday));
        wellWishesPrompt.setText(generateSendWellWishesPrompt(phoneNumber));
    }

    private String generateBirthdayReminderMessage(Birthday birthday) {
        assert birthday != null;
        LocalDate birthdate = birthday.birthdate;
        MonthDay birthMonthDay = accountForLeapDay(MonthDay.from(birthdate));
        String birthMonthDayAsString = birthMonthDay.format(DateTimeFormatter.ofPattern(MONTH_DAY_STRING_FORMAT));
        Year birthYear = Year.from(birthdate);
        LocalDate birthdateInCurrentYear = birthMonthDay.atYear(YearMonth.now().getYear());

        long age = ChronoUnit.YEARS.between(birthYear, Year.now());
        long daysToBirthday = ChronoUnit.DAYS.between(LocalDate.now(), birthdateInCurrentYear);

        if (daysToBirthday == 0) {
            cardPane.setStyle(STYLE_CARD_BIRTHDAY_TODAY);
            return String.format(BIRTHDAY_MESSAGE_TODAY, age);
        }
        if (daysToBirthday > 0 && daysToBirthday <= 7) {
            cardPane.setStyle(STYLE_CARD_BIRTHDAY_THIS_WEEK);
            return String.format(BIRTHDAY_MESSAGE_THIS_WEEK, age, daysToBirthday, birthMonthDayAsString);
        }
        if (daysToBirthday < 0) {
            // birthday has passed increment age for displaying next year's age
            return String.format(BIRTHDAY_MESSAGE_DEFAULT, age + 1, birthMonthDayAsString);
        } else {
            return String.format(BIRTHDAY_MESSAGE_DEFAULT, age, birthMonthDayAsString);
        }
    }

    private MonthDay accountForLeapDay(MonthDay birthMonthDay) {
        boolean isLeapDay = birthMonthDay.equals(MonthDay.of(2, 29));
        boolean hasPassedThisYear = birthMonthDay.isAfter(MonthDay.now());
        int thisYear = Year.now().getValue();
        if (!hasPassedThisYear && isLeapDay && !Year.now().isLeap()) {
            // Leap day birthday yet to pass and currently leap year
            return birthMonthDay.withDayOfMonth(28);
        } else if (hasPassedThisYear && isLeapDay && !Year.of(thisYear + 1).isLeap()) {
            // Leap day birthday passed and next year is leap year
            return birthMonthDay.withDayOfMonth(28);
        } else {
            return birthMonthDay;
        }
    }

    private String generateSendWellWishesPrompt(String phoneNumber) {
        return String.format(WELL_WISHES_PROMPT_MESSAGE, phoneNumber);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return person.equals(card.person);
    }
}
