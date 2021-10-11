package donnafin.ui;

import java.util.Objects;
import java.util.function.BiConsumer;

import donnafin.logic.PersonAdapter;
import donnafin.model.person.Attribute;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class AttributePanel extends UiPart<Region> implements Attribute {

    private static final String FXML = "AttributePanel.fxml";

    private final String packagedExtraField = "donnafin.model.person.";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private Label label;

    @FXML
    private TextField textField;

    private Attribute attribute;

    private BiConsumer<PersonAdapter.PersonField, String> editor;

    /**
     * Constructor for Attribute panel
     * @param attribute
     */
    public AttributePanel(Attribute attribute, BiConsumer<PersonAdapter.PersonField, String> editor) {
        super(FXML);
        this.attribute = attribute;
        this.editor = editor;
        String attributeName = attribute.getClass().getName().replace(packagedExtraField, "");
        label.setText(attributeName);
        textField.setText(attribute.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AttributePanel attributePanel = (AttributePanel) o;
        return Objects.equals(attributePanel, attributePanel)
                && Objects.equals(label, attributePanel.label)
                && Objects.equals(textField, attributePanel.textField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute, label, textField);
    }

    public void setLabel(String text) {
        label.setText(text);
    }

    public String getLabel() {
        return this.label.getText();
    }

    public void setValue(String text) {
        textField.setText(text);
    }

    public String getValue() {
        return this.textField.getText();
    }

    /**
     * Handles the Enter button pressed event.
     * Updates client info after user edit.
     */
    @FXML
    private void handleCommandEntered() {
        String newTextField = textField.getText();
        System.out.println(getPersonField());
        editor.accept(getPersonField(), newTextField);
    }

    /**
     * Gets the PersonField enum type of attribute from label
     * @return Enum PersonField value of attribute
     */
    private PersonAdapter.PersonField getPersonField() {
        switch(label.getText()) {
        case "Name":
            return PersonAdapter.PersonField.NAME;
        case "Address":
            return PersonAdapter.PersonField.ADDRESS;
        case "Phone":
            return PersonAdapter.PersonField.PHONE;
        case "Email":
            return PersonAdapter.PersonField.EMAIL;
        default:
            return null;
        }
    }
}
