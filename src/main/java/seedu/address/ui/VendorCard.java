package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.vendor.Vendor;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class VendorCard extends UiPart<Region> {

    private static final String FXML = "VendorCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Vendor vendor;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label vendorId;
    @FXML
    private Label cost;
    @FXML
    private Label operatingHours;
    @FXML
    private Label serviceName;
    @FXML
    private ImageView addressIcon;
    @FXML
    private ImageView emailIcon;
    @FXML
    private ImageView phoneIcon;
    @FXML
    private ImageView vendorIdIcon;
    @FXML
    private ImageView serviceNameIcon;
    @FXML
    private ImageView costIcon;
    @FXML
    private ImageView operatingHoursIcon;
    /**
     * Creates a {@code VendorCard} with the given {@code Vendor} and index to display.
     */
    public VendorCard(Vendor vendor) {
        super(FXML);
        this.vendor = vendor;
        vendorId.setText("[" + vendor.getVendorId().value + "]");
        name.setText(vendor.getName().fullName);
        email.setText(" " + vendor.getEmail().value);
        address.setText(" " + vendor.getAddress().value);
        phone.setText(" " + vendor.getPhone().value);
        cost.setText(" " + vendor.getCost().value);
        serviceName.setText(" " + vendor.getServiceName().serviceName);
        operatingHours.setText(" " + vendor.getOperatingHours().toString());

        vendorIdIcon.setImage(new Image(getClass().getResourceAsStream("/images/vendor_id_icon.png")));
        emailIcon.setImage(new Image(getClass().getResourceAsStream("/images/email_icon.png")));
        addressIcon.setImage(new Image(getClass().getResourceAsStream("/images/address_icon.png")));
        phoneIcon.setImage(new Image(getClass().getResourceAsStream("/images/phone_icon.png")));
        serviceNameIcon.setImage(new Image(getClass().getResourceAsStream("/images/service_name_icon.png")));
        costIcon.setImage(new Image(getClass().getResourceAsStream("/images/cost_icon.png")));
        operatingHoursIcon.setImage(new Image(getClass().getResourceAsStream("/images/operating_hours_icon.png")));

        vendor.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VendorCard)) {
            return false;
        }

        // state check
        VendorCard card = (VendorCard) other;
        return vendorId.getText().equals(card.vendorId.getText())
                && vendor.equals(card.vendor);
    }
}
