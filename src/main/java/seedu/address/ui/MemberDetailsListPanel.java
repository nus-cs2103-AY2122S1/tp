package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.member.Member;

/**
 * Represents for Panel containing the list of members.
 */
public class MemberDetailsListPanel extends UiPart<Region> {

    /**
     * Uses FXML to identify MemberDetailsPanel.
     */
    private static final String FXML = "MemberDetailsListPanel.fxml";

    /**
     * Stands for components to be used in FXML.
     */
    @FXML
    private ListView<Member> memberDetailsListView;

    /**
     * Creates a {@code MemberListPanel} with the given {@code ObservableList}.
     */
    public MemberDetailsListPanel(ObservableList<Member> memberList) {
        super(FXML);
        memberDetailsListView.setItems(memberList);
        memberDetailsListView.setCellFactory(listView -> new MemberDetailsListViewCell());
    }

    /**
     * Customs {@code ListCell} that displays the graphics of a {@code Member} using a {@code MemberDetailsCard}.
     */
    class MemberDetailsListViewCell extends ListCell<Member> {
        @Override
        protected void updateItem(Member member, boolean empty) {
            super.updateItem(member, empty);

            if (empty || member == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MemberDetailsCard(member).getRoot());
            }
        }
    }

}
