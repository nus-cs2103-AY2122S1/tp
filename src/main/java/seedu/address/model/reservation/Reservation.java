package seedu.address.model.reservation;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import java.time.LocalDateTime;

public class Reservation {
    private int numberOfPeople;
    private LocalDateTime time;

    public Reservation(int numberOfPeople, LocalDateTime time) {
        requireAllNonNull(numberOfPeople, time);
        this.numberOfPeople = numberOfPeople;
        this.time = time;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
