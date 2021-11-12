package seedu.address.ui;

import static com.calendarfx.view.DayViewBase.EarlyLateHoursStrategy.SHOW_COMPRESSED;
import static com.calendarfx.view.DayViewBase.HoursLayoutStrategy.FIXED_HOUR_COUNT;
import static com.calendarfx.view.page.DayPage.DayPageLayout.STANDARD;

import java.time.LocalDate;
import java.time.LocalTime;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.lesson.TimeRange;

public class SchedulePanel extends UiPart<Region> {

    private static final String FXML = "SchedulePanel.fxml";

    private final CalendarView calendarView;

    @FXML
    private StackPane scheduleView;

    /**
     * Creates a {@code SchedulePanel} with the given {@code Calendar}.
     */
    public SchedulePanel(Calendar calendar) {
        super(FXML);
        calendarView = new CalendarView();

        initialiseCalendarView(calendar);
        initialiseDayPage();
        initialiseWeekPage();
        initialiseYearPage();
        initialiseScheduleView();

        createTimeThread();
    }

    private void initialiseScheduleView() {
        scheduleView.getChildren().setAll(calendarView);
    }

    /**
     * Sets up CalendarFX.CalendarView.
     * Adapted from CalendarFX developer manual.
     * http://dlsc.com/wp-content/html/calendarfx/manual.html#_quick_start
     */
    private void initialiseCalendarView(Calendar calendar) {
        calendar.setReadOnly(true); // !!! Disables editing entries in calendar interface

        CalendarSource calendarSource = new CalendarSource();
        calendarSource.getCalendars().addAll(calendar);
        calendarView.getCalendarSources().addAll(calendarSource);

        // Disable callbacks
        calendarView.setEntryFactory(param -> null); // !!! Important! Disables creating new entry on double click
        calendarView.setEntryDetailsCallback(param -> null);
        calendarView.setEntryContextMenuCallback(null);
        calendarView.setContextMenuCallback(null);

        // Custom display settings
        calendarView.setStartTime(TimeRange.DAY_START);
        calendarView.setEndTime(TimeRange.DAY_END);
        calendarView.setShowPrintButton(false);
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowSourceTrayButton(false);
        calendarView.setShowPageToolBarControls(false);
        calendarView.setShowSearchField(false);

        // Start at week page
        calendarView.showWeekPage();
    }

    /**
     * Sets up the calendarView's DayPage.
     */
    private void initialiseDayPage() {
        calendarView.getDayPage().setDayPageLayout(STANDARD);

        calendarView.getDayPage().getYearMonthView().setShowWeekNumbers(false);

        calendarView.getDayPage().getDetailedDayView().setVisibleHours(15);
        calendarView.getDayPage().getDetailedDayView().setEarlyLateHoursStrategy(SHOW_COMPRESSED);
        calendarView.getDayPage().getDetailedDayView().setHoursLayoutStrategy(FIXED_HOUR_COUNT);
    }

    /**
     * Sets up the calendarView's WeekPage.
     */
    private void initialiseWeekPage() {
        calendarView.getWeekPage().getDetailedWeekView().setVisibleHours(15);
        calendarView.getWeekPage().getDetailedWeekView().setHoursLayoutStrategy(FIXED_HOUR_COUNT);
        calendarView.getWeekPage().getDetailedWeekView().setEarlyLateHoursStrategy(SHOW_COMPRESSED);
    }

    /**
     * Sets up the calendarView's YearPage.
     */
    private void initialiseYearPage() {
        calendarView.getYearPage().getMonthSheetView().setShowWeekNumber(false); // This doesn't seem to work
    }

    //@@author Chesterwongz-reused
    /**
     * Adapted from CalendarFX developer manual.
     * http://dlsc.com/wp-content/html/calendarfx/manual.html#_quick_start
     */
    private void createTimeThread() {
        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });
                    try {
                        // update every 60 seconds
                        sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
    }

    //@@author Chesterwongz
    public void goNext() {
        calendarView.getSelectedPage().goForward();
    }

    public void goToday() {
        calendarView.getSelectedPage().goToday();
    }

    public void goToDate(LocalDate date) {
        calendarView.setDate(date);
    }

    public void goBack() {
        calendarView.getSelectedPage().goBack();
    }

    public void showDay() {
        calendarView.showDayPage();
    }

    public void showWeek() {
        calendarView.showWeekPage();
    }

    public void showMonth() {
        calendarView.showMonthPage();
    }

    public void showYear() {
        calendarView.showYearPage();
    }
}
