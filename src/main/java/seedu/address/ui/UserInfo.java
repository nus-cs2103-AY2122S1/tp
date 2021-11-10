package seedu.address.ui;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import seedu.address.logic.Logic;

public class UserInfo extends UiPart<Region> {
    private static final String FXML = "UserInfo.fxml";

    @FXML
    private HBox mainContainer;

    @FXML
    private VBox leftHeader;

    @FXML
    private VBox totalContactsContainer;

    @FXML
    private VBox daysTillWeddingContainer;

    /**
     * Creates an UserInfo object
     */
    public UserInfo(Logic logic) {
        super(FXML);

        Text userName = new Text(" Welcome to WedFast! ");
        userName.setStyle("-fx-font-weight: bold");
        userName.setFont(Font.font(30));
        userName.setFill(Color.color(1, 1, 1));

        String date = "  ".concat(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Text dateText = new Text(date);
        dateText.setFont(Font.font(15));
        dateText.setFill(Color.color(0.3, 1, 0.6));

        leftHeader.setBackground(new Background(
                new BackgroundFill(Color.web("#383838"), CornerRadii.EMPTY, Insets.EMPTY)));

        leftHeader.getChildren().addAll(userName, dateText);
        leftHeader.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0.5))));

        // Assume dummy variables
        int contacts = logic.size();
        int noOfdates = Integer.MIN_VALUE;
        try {
            noOfdates = (int) LocalDate.now().until(logic.getCountdown().getDate(), ChronoUnit.DAYS);
        } catch (NullPointerException nu) {
            nu.printStackTrace();
        }

        ModelInfo totalContacts = new ModelInfo(contacts, "People in your contact");
        totalContactsContainer.getChildren().add(totalContacts.getRoot());

        ModelInfo datesToWedding;
        if (noOfdates == Integer.MIN_VALUE) {
            datesToWedding = new ModelInfo(0, "JSON file has been corrupted!");
        } else if (noOfdates < 0) {
            datesToWedding = new ModelInfo(0, "Oh no! Your Wedding day has passed!");
        } else {
            datesToWedding = new ModelInfo(noOfdates, "Days before Wedding day");
        }
        daysTillWeddingContainer.getChildren().add(datesToWedding.getRoot());
    }

}
