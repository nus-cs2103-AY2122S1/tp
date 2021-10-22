package seedu.fast.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A HBox that contains a label text and the tag image.
 */
public class ItemComponent extends HBox {
    @FXML
    private Label component;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for ItemComponent
     * @param text Information to be displayed.
     * @param img Image of the icon.
     */
    public ItemComponent(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/PersonComponent.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        component.setText(text);
        displayPicture.setImage(img);
    }
}
