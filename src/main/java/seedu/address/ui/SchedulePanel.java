package seedu.address.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.DayViewBase;
import com.calendarfx.view.DetailedWeekView;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.lesson.TimeRange;

public class SchedulePanel extends UiPart<Region> {

    private static final String FXML = "SchedulePanel.fxml";

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    private final DetailedWeekView calendarView;

    @FXML
    private StackPane scheduleView;

    /**
     * Creates a {@code SchedulePanel} with the given {@code Calendar}.
     */
    public SchedulePanel(Calendar calendar) {
        super(FXML);
        calendarView = new DetailedWeekView();
        initialiseCalendar(calendar);
        createTimeThread();
    }

    /**
     * Sets up CalendarFX.
     * Adapted from CalendarFX developer manual.
     * http://dlsc.com/wp-content/html/calendarfx/manual.html#_quick_start
     */
    public void initialiseCalendar(Calendar calendar) {
        CalendarSource calendarSource = new CalendarSource();
        calendarSource.getCalendars().addAll(calendar);
        calendarView.getCalendarSources().addAll(calendarSource);
        calendarView.setStartTime(TimeRange.DAY_START);
        calendarView.setEndTime(TimeRange.DAY_END);
        calendarView.setVisibleHours(15);
        calendarView.setAdjustToFirstDayOfWeek(false);
        calendarView.setShowScrollBar(false);
        calendarView.setHoursLayoutStrategy(DayViewBase.HoursLayoutStrategy.FIXED_HOUR_COUNT);
        calendarView.setEarlyLateHoursStrategy(DayViewBase.EarlyLateHoursStrategy.HIDE);
        scheduleView.getChildren().setAll(calendarView);
        scheduleView.setDisable(true);
    }

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
}
