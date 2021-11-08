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
public class MemberListPanel extends UiPart<Region> {

    /**
     * Uses FXML to identify MemberListPanel.
     */
    private static final String FXML = "MemberListPanel.fxml";

    /**
     * Stands for components to be used in FXML.
     */
    @FXML
    private ListView<Member> memberListView;

    /**
     * Creates a {@code MemberListPanel} with the given {@code ObservableList}.
     */
    public MemberListPanel(ObservableList<Member> memberList) {
        super(FXML);
        memberListView.setItems(memberList);
        memberListView.setCellFactory(listView -> new MemberListViewCell());
    }

    /**
     * Customs {@code ListCell} that displays the graphics of a {@code Member} using a {@code MemberCard}.
     */
    class MemberListViewCell extends ListCell<Member> {
        @Override
        protected void updateItem(Member member, boolean empty) {
            super.updateItem(member, empty);

            if (empty || member == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MemberCard(member, getIndex() + 1).getRoot());
            }
        }
    }

}
