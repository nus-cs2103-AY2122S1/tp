package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ModelInfo extends UiPart<Region> {
    private static final String FXML = "ModelInfo.fxml";

    @FXML
    private VBox mainContainer;

    /**
     * Creates a ModelInfo Object.
     * @param val is an integer value to be displayed in bold
     * @param info is the description of the integer val
     */
    public ModelInfo(int val, String info) {
        super(FXML);
        mainContainer.setBackground(new Background(
                new BackgroundFill(Color.web("#383838"), CornerRadii.EMPTY, Insets.EMPTY)));
        mainContainer.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0.5))));

        Text value = new Text(Integer.toString(val));
        value.setFont(Font.font(40));
        value.setFill(Color.color(0.3, 1, 0.6));
        value.setStyle("-fx-font-weight: bold");
        value.setTextAlignment(TextAlignment.CENTER);

        Text infoText = new Text(info);
        infoText.setFont(Font.font(15));
        infoText.setFill(Color.WHITE);
        infoText.setTextAlignment(TextAlignment.CENTER);

        if (val == 0) {
            value.setText("-");
        }

        mainContainer.getChildren().addAll(value, infoText);
    }
}
