package seedu.address.model.reservation;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Phone;
import seedu.address.model.table.Table;
import seedu.address.model.tag.Tag;

/**
 * Represents a reservation
 */
public class Reservation {
    private static final DateTimeFormatter DATE_TIME_PRINTING_FORMAT =
            DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a");

    private final Phone phone;
    private final int numberOfPeople;
    private final LocalDateTime dateTime;
    private final Table table;
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Creates a reservation with remark and tags
     */
    public Reservation(
            Phone phone, int numberOfPeople, LocalDateTime dateTime,
            Table table, Remark remark, Set<Tag> tags
    ) {
        requireAllNonNull(phone, numberOfPeople, dateTime, tags, remark);
        this.phone = phone;
        this.numberOfPeople = numberOfPeople;
        this.dateTime = dateTime;
        this.table = table;
        this.remark = remark;
        this.tags.addAll(tags);
    }

    public Phone getPhone() {
        return phone;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getTableId() {
        return table.getTableId();
    }

    public Table getTable() {
        return table;
    }

    public Remark getRemark() {
        return remark;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Reservation)) {
            return false;
        }

        Reservation that = (Reservation) o;
        return numberOfPeople == that.numberOfPeople
                && phone.equals(that.phone)
                && dateTime.equals(that.dateTime)
                && table.equals(that.table)
                && tags.equals(that.tags);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(phone, numberOfPeople, dateTime, table, tags);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Phone: ");
        builder.append(phone)
                .append("; Number Of People: ")
                .append(numberOfPeople)
                .append("; Time: ")
                .append(dateTime.format(DATE_TIME_PRINTING_FORMAT))
                .append("; Table: ")
                .append(table);

        if (remark != null) {
            builder.append("; Remark: ")
                    .append(remark);
        }
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }
}
