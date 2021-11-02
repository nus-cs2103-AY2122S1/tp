package seedu.address.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import seedu.address.commons.util.TimeUtil;

public class DefaultShiftTimingsStorage {

    public static final String FILEPATH = "data/DefaultShiftTimings.txt";
    private static File file = new File(FILEPATH);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    // Morning shift start time, Morning shift end time, Afternoon shift start time, Afternoon shift end time
    private static final String[] DEFAULT_TIMINGS = new String[]{"10:00", "16:00", "16:00", "22:00"};
    private static String[] stringTimings = new String[]{"10:00", "16:00", "16:00", "22:00"};
    private static LocalTime[] timings = new LocalTime[4];

    /**
     * Loads the existing file for the list of requirements if it exists.
     *
     * @return a LocalTime[] of requirements.
     * @throws IOException If there are errors processing the file.
     */
    public static LocalTime[] load() throws IOException {

        if (!file.exists()) {
            // Create the data folder if it does not exist.
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            file.createNewFile(); // Create the DefaultShiftTimings.txt file.
            update();
            return timings;
        }

        if (file.length() == 0) {
            update();
            return timings;
        }

        return readFileModifyRequirements();
    }

    /**
     * Reads the saved file and adds tasks to the tasklist.
     *
     * @return A LocalTime[] of tasks read from the save file.
     * @throws FileNotFoundException If saved file cannot be found.
     */
    private static LocalTime[] readFileModifyRequirements() throws FileNotFoundException {
        Scanner fileSc = new Scanner(file);
        int i = 0;

        while (fileSc.hasNext() && i < 4) {
            String nextLine = fileSc.nextLine();
            stringTimings[i] = nextLine;
            timings[i] = LocalTime.parse(nextLine, TIME_FORMATTER);
            i++;
        }

        fileSc.close();
        return timings;
    }

    /**
     * Updates the file and {@code:timings} based on the {@code:stringTimings}.
     *
     * @throws FileNotFoundException If the file is not found.
     */
    private static void update() throws FileNotFoundException {
        StringBuilder txt = new StringBuilder();
        int i = 0;
        for (String s: stringTimings) {
            txt.append(s).append("\n");
            timings[i] = LocalTime.parse(s, TIME_FORMATTER); // timings array is also updated for load()
            i++;
        }

        TimeUtil.updateTimings(timings);

        PrintWriter pw = new PrintWriter(file);
        pw.append(txt.toString());
        pw.flush();
    }

    /**
     * Updates the save file with the new timings given.
     *
     * @param mornStartTime The start time of the morning shift.
     * @param mornEndTime The end time of the morning shift.
     * @param aftStartTime The start time of the afternoon shift.
     * @param aftEndTime The end time of the afternoon shift.
     * @throws FileNotFoundException Thrown when file is not found.
     */
    public static void update(LocalTime mornStartTime, LocalTime mornEndTime, LocalTime aftStartTime,
                              LocalTime aftEndTime) throws FileNotFoundException {
        stringTimings[0] = mornStartTime.toString();
        stringTimings[1] = mornEndTime.toString();
        stringTimings[2] = aftStartTime.toString();
        stringTimings[3] = aftEndTime.toString();
        update();
    }

    /**
     * Updates the save file with the new timings given.
     *
     * @param newTimings a LocalTime[] of new timings.
     * @throws FileNotFoundException Thrown when file is not found.
     */
    public static void update(LocalTime[] newTimings) throws FileNotFoundException {
        stringTimings[0] = newTimings[0].toString();
        stringTimings[1] = newTimings[1].toString();
        stringTimings[2] = newTimings[2].toString();
        stringTimings[3] = newTimings[3].toString();
        update();
    }

    /**
     * Returns the current default timings.
     *
     * @return The current default timings.
     */
    public static String getTimings() {
        return String.format("Morning Shift: %s-%s\nAfternoon Shift: %s-%s",
                stringTimings[0], stringTimings[1], stringTimings[2], stringTimings[3]);
    }

    /**
     * Resets the timings to the default timings.
     *
     * @throws FileNotFoundException If the file cannot be found.
     */
    public static void reset() throws FileNotFoundException {
        for (int i = 0; i < 4; i++) {
            stringTimings[i] = DEFAULT_TIMINGS[i];
        }
        update();
    }
}
