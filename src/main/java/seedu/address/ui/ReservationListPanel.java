package seedu.address.ui;

import java.time.LocalDateTime;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.model.reservation.Reservation;

/**
 * Represents for Panel containing the list of reservations.
 */
public class ReservationListPanel extends UiPart<Region> {

    /**
     * Uses FXML to identify ReservationListPanel.
     */
    private static final String FXML = "ReservationListPanel.fxml";

    /**
     * Stands for components to be used in FXML.
     */
    @FXML
    private ListView<Reservation> reservationListView;

    /**
     * Creates a {@code ReservationListPanel} with the given {@code ObservableList}.
     */
    public ReservationListPanel(ObservableList<Reservation> reservationList) {
        super(FXML);
        reservationListView.setItems(reservationList.sorted((r1, r2) -> {
            LocalDateTime dateTimeR1 = DateTimeUtil.parseDateTime(r1.getDateTime().value);
            LocalDateTime dateTimeR2 = DateTimeUtil.parseDateTime(r2.getDateTime().value);
            if (dateTimeR1.isAfter(dateTimeR2)) {
                return -1;
            } else if (dateTimeR1.isEqual(dateTimeR2)) {
                return 0;
            } else {
                return 1;
            }
        }));
        reservationListView.setCellFactory(listView -> new ReservationListViewCell());
    }

    /**
     * Customs {@code ListCell} that displays the graphics of a {@code Reservation} using a {@code ReservationCard}.
     */
    class ReservationListViewCell extends ListCell<Reservation> {
        @Override
        protected void updateItem(Reservation reservation, boolean empty) {
            super.updateItem(reservation, empty);

            if (empty || reservation == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ReservationCard(reservation).getRoot());
            }
        }
    }

}
