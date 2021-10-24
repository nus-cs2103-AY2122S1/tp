package seedu.address.ui;

import java.util.Comparator;

import com.gluonhq.charm.glisten.control.Icon;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.client.Client;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays the important information of a {@code Client}.
 */
public class ClientCard extends UiPart<Region> {

    private static final String FXML = "ClientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Client client;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label riskAppetite;
    @FXML
    private Label disposableIncome;
    @FXML
    private Label lastMet;
    @FXML
    private Label currentPlan;
    @FXML
    private Label nextMeeting;
    @FXML
    private FlowPane tags;

    // TODO: do later
    // @FXML
    // private BorderPane phoneIconPane;
    // @FXML
    // private BorderPane addressIconPane;
    // @FXML
    // private BorderPane emailIconPane;
    // @FXML
    // private BorderPane riskIconPane;
    // @FXML
    // private BorderPane incomeIconPane;
    // @FXML
    // private BorderPane planIconPane;
    // @FXML
    // private BorderPane lastMetIconPane;

    // TODO: do later
    // private static final Text PHONE_ICON = GlyphsDude.createIcon(FontAwesomeIcons.PHONE, "10px");
    // private static final Text HOME_ICON = GlyphsDude.createIcon(FontAwesomeIcons.HOME, "10px");
    // private static final Text MAIL_FORWARD_ICON = GlyphsDude.createIcon(FontAwesomeIcons.MAIL_FORWARD, "10px");
    // private static final Text LINE_CHART_ICON = GlyphsDude.createIcon(FontAwesomeIcons.LINE_CHART, "10px");
    // private static final Text MONEY_ICON = GlyphsDude.createIcon(FontAwesomeIcons.MONEY, "10px");
    // private static final Text DOLLAR_ICON = GlyphsDude.createIcon(FontAwesomeIcons.DOLLAR, "10px");
    // private static final Text HAND_ALT_DOWN_ICON = GlyphsDude.createIcon(FontAwesomeIcons.HAND_ALT_DOWN, "10px");


    /**
     * Creates a {@code ClientCode}.
     */
    public ClientCard(Client client) {
        super(FXML);

        this.client = client;
        id.setText(client.getClientId().value + ". ");
        name.setText(client.getName().fullName);
        email.setText(client.getEmail().value);
        phone.setText(client.getPhone().value);
        address.setText(client.getAddress().value);
        riskAppetite.setText(client.getRiskAppetite().value);
        disposableIncome.setText(client.getDisposableIncome().value);
        currentPlan.setText(client.getCurrentPlan().toString());
        lastMet.setText(client.getLastMet().toString());
        nextMeeting.setText(client.getNextMeeting().toString());
        client.getTags().stream()
            .sorted(Comparator.comparing(Tag::getName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.getName())));

        // TODO: do later
        // phoneIconPane.setCenter(PHONE_ICON);
        // addressIconPane.setCenter(HOME_ICON);
        // emailIconPane.setCenter(MAIL_FORWARD_ICON);
        // riskIconPane.setCenter(LINE_CHART_ICON);
        // incomeIconPane.setCenter(MONEY_ICON);
        // planIconPane.setCenter(DOLLAR_ICON);
        // lastMetIconPane.setCenter(HAND_ALT_DOWN_ICON);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientCard)) {
            return false;
        }

        // state check
        ClientCard card = (ClientCard) other;
        return id.getText().equals(card.id.getText())
            && client.equals(card.client);
    }
}
