package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Region;

/**
 * A UI component that displays information of commands.
 */
public class HelpBox extends UiPart<Region> {

    /**
     * Stands for the official URL to our tP repository on GitHub.
     */
    public static final String OFFICIAL_URL =
            "https://ay2122s1-cs2103t-f12-4.github.io/tp/";

    /**
     * Uses FXML to identify HelpBox.
     */
    private static final String FXML = "HelpBox.fxml";

    /**
     * Stands for components to be used in FXML.
     *
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private Label login;
    @FXML
    private Label setPassword;
    @FXML
    private Label addNewMember;
    @FXML
    private Label deleteMemberByMemberId;
    @FXML
    private Label deleteMemberByIndexNumber;
    @FXML
    private Label findMemberByMemberId;
    @FXML
    private Label findMembersByName;
    @FXML
    private Label findMembersByPhone;
    @FXML
    private Label findMembersByEmail;
    @FXML
    private Label findMembersByRegistrationDate;
    @FXML
    private Label viewMemberProfile;
    @FXML
    private Label editMemberByMemberId;
    @FXML
    private Label editMemberByIndexNumber;
    @FXML
    private Label addTransactionByMemberId;
    @FXML
    private Label editTransactionByMemberIdTransactionId;
    @FXML
    private Label deleteTransactionByMemberIdTransactionId;
    @FXML
    private Label addReservationByMemberId;
    @FXML
    private Label editReservationByMemberIdTransactionId;
    @FXML
    private Label deleteReservationByMemberIdTransactionId;
    @FXML
    private Label sortMembersByCreditInAsc;
    @FXML
    private Label sortMembersByCreditInDesc;
    @FXML
    private Label redeemPointByMemberId;
    @FXML
    private Label redeemPointIndexNumber;
    @FXML
    private Label url;
    @FXML
    private Button copyButton;

    /**
     * Creates a help box to show the commands.
     */
    public HelpBox() {
        super(FXML);
        login.setText("login <PASSWORD>");
        setPassword.setText("set -pass/<PASSWORD>");
        addNewMember.setText("add -mem/ -n/<NAME> -p/<PHONE> -e/<EMAIL> -a/<ADDRESS>");
        deleteMemberByMemberId.setText("del -mem/ -id/<MEMBER_ID>");
        deleteMemberByIndexNumber.setText("del -mem/ -i/<MEMBER_INDEX>");
        findMemberByMemberId.setText("find -mem/ -id/<MEMBER_ID>");
        findMembersByName.setText("find -mem/ -n/<NAME>");
        findMembersByPhone.setText("find -mem/ -p/<PHONE>");
        findMembersByEmail.setText("find -mem/ -e/<EMAIL>");
        findMembersByRegistrationDate.setText("find -mem/ -d/<REGISTRATION_DATE yyyy-MM-dd>");
        viewMemberProfile.setText("show -mem/ -id/<MEMBER_ID>");
        editMemberByMemberId.setText("edit -mem/ -id/<MEMBER_ID> [-n/<NAME>] [-p/<PHONE>] [-e/<EMAIL>] [-a/<ADDRESS>]");
        editMemberByIndexNumber.setText("edit -mem/ -i/<INDEX> [-n/<NAME>] [-p/<PHONE>] [-e/<EMAIL>] [-a/<ADDRESS>]");
        addTransactionByMemberId.setText("add -txn/ -b/<BILLING_AMOUNT> -id/<MEMBER_ID>");
        editTransactionByMemberIdTransactionId.setText(
                "edit -txn/ -id/<MEMBER_ID + TRANSACTION_ID> -b/<BILLING_AMOUNT>");
        deleteTransactionByMemberIdTransactionId.setText("del -txn/ -id/<MEMBER_ID + TRANSACTION_ID>");
        addReservationByMemberId.setText("add -rs/ -dt/<DATE_TIME yyyy-MM-dd HH:mm> -rm/<REMARK> -id/<MEMBER_ID>");
        editReservationByMemberIdTransactionId.setText(
                "edit -rs/ -id/<MEMBER_ID + RESERVATION_ID> [-dt/<DATE_TIME yyyy-MM-dd HH:mm>] [-rm/<REMARK>]");
        deleteReservationByMemberIdTransactionId.setText("del -rs/ -id/<MEMBER_ID + RESERVATION_ID>");
        sortMembersByCreditInAsc.setText("sort -mem/ -c/ -a/");
        sortMembersByCreditInDesc.setText("sort -mem/ -c/ -d/");
        redeemPointByMemberId.setText("redeem -rd/<POINTS> -id/<MEMBER_ID>");
        redeemPointIndexNumber.setText("redeem -rd/<POINTS> -i/<INDEX>");
        url.setText(OFFICIAL_URL);
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(OFFICIAL_URL);
        clipboard.setContent(url);
    }
}
