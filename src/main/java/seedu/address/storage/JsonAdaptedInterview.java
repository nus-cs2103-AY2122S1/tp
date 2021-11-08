package seedu.address.storage;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.Interview.InterviewStatus;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

public class JsonAdaptedInterview {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Interview's %s field is missing!";

    private final String position;

    private final Set<String> candidateIDs = new HashSet<>();

    private final String startTime;

    private final String date;

    private final String duration;

    private final InterviewStatus status;

    /**
     * Constructs a {@code JsonAdaptedInterview} with the given {@code positionName}.
     */
    @JsonCreator
    public JsonAdaptedInterview(@JsonProperty("position") String position,
                                @JsonProperty("candidateIDs") Set<String> candidateIDs,
                                @JsonProperty("date") String date,
                                @JsonProperty("startTime") String startTime,
                                @JsonProperty("duration") String duration,
                                @JsonProperty("status") InterviewStatus status) {
        this.position = position;
        if (candidateIDs != null) {
            this.candidateIDs.addAll(candidateIDs);
        }
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.status = status;
    }


    /**
     * Converts a given {@code Position} into this class for Jackson use.
     */
    public JsonAdaptedInterview(Interview source) {
        position = source.getPositionTitle().fullTitle;
        candidateIDs.addAll(source.getCandidates().stream()
                .map(s -> String.valueOf(s.hashCode()))
                .collect(Collectors.toList()));
        date = source.getDateInFormattedString();
        startTime = source.getTimeInFormattedString();
        duration = source.getDurationInFormattedString();
        status = source.getStatus();
    }

    /**
     * Parses {@code String date} into a {@code LocalDate}.
     * @param date Input String.
     * @return LocalDate of an Interview.
     * @throws ParseException If the given {@code date} is invalid
     */
    public static LocalDate parseDate(String date) throws ParseException {
        String dateFormat = "^[0-9]{1,2}[\\\\/][0-9]{1,2}[\\\\/][0-9]{4}$";
        Pattern p = Pattern.compile(dateFormat);
        Matcher m = p.matcher(date);
        if (m.find()) {
            String[] foundDate = m.group().split("/");
            int year = Integer.parseInt(foundDate[2]);
            int month = Integer.parseInt(foundDate[1]);
            int day = Integer.parseInt(foundDate[0]);
            try {
                return LocalDate.of(year, month, day);
            } catch (DateTimeException e) {
                throw new ParseException(Interview.MESSAGE_DATE_CONSTRAINTS);
            }
        }
        throw new ParseException(Interview.MESSAGE_DATE_CONSTRAINTS);
    }

    /**
     * Parses {@code String time} into a {@code LocalTime}.
     * @param time Input String.
     * @return LocalTime of an Interview.
     * @throws ParseException If the given {@code time} is invalid
     */
    public static LocalTime parseTime(String time) throws ParseException {
        String timeFormat = "^[0-9]{4}$";
        Pattern p = Pattern.compile(timeFormat);
        Matcher m = p.matcher(time);
        if (m.find()) {
            int hour = Integer.parseInt(time.substring(0, 2));
            int min = Integer.parseInt(time.substring(2));
            try {
                return LocalTime.of(hour, min);
            } catch (DateTimeException e) {
                throw new ParseException(Interview.MESSAGE_TIME_CONSTRAINTS);
            }
        }
        throw new ParseException(Interview.MESSAGE_TIME_CONSTRAINTS);
    }

    /**
     * Parses {@code String duration} into a {@code Duration}.
     * @param duration Input String.
     * @return Duration of an Interview.
     * @throws ParseException If the given {@code duration} is invalid
     */
    public static Duration parseDuration(String duration) throws ParseException {
        try {
            Long actualDuration = Long.parseLong(duration);
            //capped at strictly less than 24 hours or 1440 minutes
            if (actualDuration <= 0 || actualDuration >= 1440) {
                throw new ParseException(Interview.MESSAGE_DURATION_CONSTRAINTS_INVALID_NUMBER);
            }
            return Duration.ofMinutes(actualDuration);
        } catch (NumberFormatException e) {
            throw new ParseException(Interview.MESSAGE_DURATION_CONSTRAINTS_NOT_A_NUMBER);
        }
    }



    /**
     * Converts this Jackson-friendly adapted position object into the model's {@code Position} object.
     * @return An Interview loaded from Json file and yet to have actual candidates added.
     * @throws IllegalValueException if there were any data constraints violated in the adapted position.
     */
    public Interview toModelType() throws IllegalValueException {
        if (position == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Position.class.getSimpleName()));
        }
        if (!Title.isValidTitle(position)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }

        final Set<Integer> personHashCodes = new HashSet<>();
        for (String id : candidateIDs) {
            personHashCodes.add(Integer.parseInt(id));
        }

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "date"));
        }
        LocalDate localDate;
        try {
            localDate = parseDate(date);
        } catch (ParseException e) {
            throw new IllegalValueException(Interview.MESSAGE_DATE_CONSTRAINTS);
        }
        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "time"));
        }
        LocalTime localTime;
        try {
            localTime = parseTime(startTime);
        } catch (ParseException e) {
            throw new IllegalValueException(Interview.MESSAGE_TIME_CONSTRAINTS);
        }
        if (duration == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "duration"));
        }
        Duration duration;
        try {
            duration = parseDuration(this.duration);
        } catch (ParseException e) {
            //getMessage() is used because parseDuration can throw an exception with 2 different messages
            throw new IllegalValueException(e.getMessage());
        }
        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    InterviewStatus.class.getSimpleName()));
        }
        Interview interview = new Interview(new Position(new Title(position),
                Position.PositionStatus.OPEN), new HashSet<>(), localDate, localTime, duration, status);
        interview.setCandidateIDs(personHashCodes);
        return interview;
    }
}
