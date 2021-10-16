package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;

public class RemarkEditor {

    @FXML
    private TextArea textArea;

    @FXML
    private Label name;

    public void setRemark(String name, String remark) {
        this.name.setText(name);
        textArea.setText(remark);
    }

    public Remark getNewRemark() {
        return new Remark(textArea.getText());
    }
}
