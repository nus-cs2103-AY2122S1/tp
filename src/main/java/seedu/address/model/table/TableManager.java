package seedu.address.model.table;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DATETIME_PRINT_FORMAT;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.reservation.exception.ReservationException;

/**
 * Represents the main class that handles all table related operations and data
 */
public class TableManager {
    private static final String MESSAGE_RESTAURANT_FULL = "Restaurant is fully booked at %1$s";
    private static final String MESSAGE_TOO_MANY_PEOPLE = "No table can accommodate %1$d person(s) on ";
    private static final String MESSAGE_NO_TABLES_ADDED =
            "No tables exist. Set tables first before making reservations";

    private final TableList tables;

    /**
     * Constructs a new TableManager
     */
    public TableManager() {
        tables = new TableList();
    }

    /**
     * Constructs a new TableManager
     */
    public TableManager(TableList tables) {
        requireNonNull(tables);
        this.tables = tables;
    }

    public int getNumberOfTables() {
        return tables.getNumberOfTables();
    }

    /**
     * Returns the smallest-sized table that can fit specified number of people.
     *
     * @param numberOfPeople       number of people in the reservation
     * @param filteredReservations list of reservations on the same date time as the reservation being made
     * @return smallest table that fits the number of people
     * @throws ReservationException when no tables have been added, when no tables are free and when all free tables
     *                              are too small to accommodate the number of people.
     */
    public Table getAvailableTable(int numberOfPeople, List<Reservation> filteredReservations)
            throws ReservationException {
        // Check if tables have been added to table list
        checkIfTableListExist();

        // If the number of reservations is already more than number of tables, throw exception
        checkAnymoreTablesVacant(filteredReservations);

        // Filter away tables with reservations already
        List<Table> availableTables = filterTablesWithReservationsAlready(filteredReservations);

        // Filter away available tables that cannot fit the required number of
        availableTables = removeTablesThatAreTooSmall(availableTables, numberOfPeople);

        // If no table can accommodate required number of people, throw exception
        checkIfListOfTablesThatCanAccommodateIsEmpty(availableTables, numberOfPeople);

        // Return smallest table that can accommodate the required number of people
        return Collections.min(availableTables, Table::compareTableSize);
    }

    private void checkIfTableListExist() throws ReservationException {
        if (tables.isEmpty()) {
            throw new ReservationException(MESSAGE_NO_TABLES_ADDED);
        }
    }

    private void checkAnymoreTablesVacant(List<Reservation> filteredReservations) throws ReservationException {
        if (filteredReservations.size() >= getNumberOfTables()) {
            throw new ReservationException(MESSAGE_RESTAURANT_FULL);
        }
    }

    private void checkIfListOfTablesThatCanAccommodateIsEmpty(List<Table> availableTables, int numberOfPeople)
            throws ReservationException {
        if (availableTables.isEmpty()) {
            throw new ReservationException(String.format(MESSAGE_TOO_MANY_PEOPLE, numberOfPeople)
                    .concat(MESSAGE_DATETIME_PRINT_FORMAT));
        }
    }

    private List<Table> removeTablesThatAreTooSmall(List<Table> availableTables, int numberOfPeople) {
        availableTables.removeIf(table -> !table.canFit(numberOfPeople));
        return availableTables;
    }

    private List<Table> filterTablesWithReservationsAlready(List<Reservation> filteredReservations) {
        return tables
                .asUnmodifiableObservableList()
                .stream()
                .filter(table -> filteredReservations
                        .stream()
                        .noneMatch(reservation -> table.getTableId() == reservation.getTableId()))
                .collect(Collectors.toList());
    }

    /**
     * Replaces the tablelist with {@code tables}
     *
     * @param tables list of tables to replace old list with
     */
    public void setTables(List<Table> tables) {
        this.tables.setTables(tables);
    }

    /**
     * Replaces the table {@code target} in the list with {@code editedTable}
     */
    public void setTable(Table target, Table editedTable) {
        requireNonNull(editedTable);
        tables.setTable(target, editedTable);
    }

    /**
     * Checks if {@code table} exists in the database
     */
    public boolean hasTable(Table table) {
        requireNonNull(table);
        return tables.contains(table);
    }

    /**
     * Adds a new table to the list
     */
    public void addTable(Table table) {
        tables.add(table);
    }

    /**
     * Removes {@code key} from the database
     * {@code key} must exist in the list
     */
    public void removeTable(Table key) {
        tables.remove(key);
    }

    /**
     * Resets the table count to 0 when a settables command is called
     */
    public void resetTableCount() {
        Table.resetTableCount();
    }

    /**
     * Return the backing list as an unmodifiable {@code ObservableList}
     */
    public ObservableList<Table> getUnmodifiableObservableList() {
        return tables.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TableManager // instanceof handles nulls
                && tables.equals(((TableManager) other).tables));
    }
}
