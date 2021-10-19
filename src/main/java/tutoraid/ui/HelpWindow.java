package tutoraid.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import tutoraid.commons.core.LogsCenter;

import java.util.logging.Logger;

import static tutoraid.commons.core.HelpGuide.*;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2122s1-cs2103t-w16-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Label userGuideTitle;

    @FXML
    private Label overview;

    @FXML
    private Label fullUserGuide;

    @FXML
    private Label quickStartTitle, quickStartStep1, quickStartStep2, quickStartStep3;

    @FXML
    private Label featuresTitle, featuresPoint1, featuresPoint2, featuresPoint3, featuresPoint4, featuresPoint5;

    @FXML
    private Label format, format1, format2, format3, format4, format5, format6, format7, format8, format9,
            format10, format11;

    @FXML
    private Label example, example1, example2, example3, example4, example5, example6, example7, example8, example9,
            example10;

    @FXML
    private Label helpTitle, helpCommand, helpDesc;

    @FXML
    private Label addSTitle, addSCommand, addSDesc, addSExample;

    @FXML
    private Label listTitle, listDesc, listGeneralCommand, listExample, listExampleDesc, listAExample, listAExampleDesc;

    @FXML
    private Label delSTitle, delSCommand, delSDesc, delSExample, delSExampleDesc;

    @FXML
    private Label editSTitle, editSCommand, editSDesc, editSExample, editSExampleDesc;

    @FXML
    private Label viewSTitle, viewSCommand, viewSDesc, viewSExample, viewSExampleDesc;

    @FXML
    private Label addPTitle, addPCommand, addPDesc, addPExample, addPExampleDesc;

    @FXML
    private Label delPTitle, delPCommand, delPDesc, delPExample, delPExampleDesc;

    @FXML
    private Label paidTitle, paidCommand, paidDesc, paidExample, paidExampleDesc;

    @FXML
    private Label unpaidTitle, unpaidCommand, unpaidDesc, unpaidExample, unpaidExampleDesc;

    @FXML
    private Label clearTitle, clearCommand, clearDesc;

    @FXML
    private Label exitTitle, exitCommand, exitDesc;

    @FXML
    private Label savingDataTitle, savingDataDesc;

    @FXML
    private Label editDataTitle, editDataDesc1, editDataDesc2, editFilePath, editCaution;

    @FXML
    private Label faqTitle, faqQ, faqA;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        userGuide();
        overview();
        quickStart();
        featureNotes();
        helpFeature();
        addStudentFeature();
        deleteStudentFeature();
        listFeatures();
        editStudentFeature();
        viewStudentFeature();
        addProgressFeature();
        deleteProgressFeature();
        paidFeature();
        unpaidFeature();
        clearFeature();
        exitFeature();
        savingData();
        editData();
        faq();
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }

    /**
     * Creates the user guide section.
     */
    public void userGuide() {
        userGuideTitle.setText(USER_GUIDE_TITLE);
    }

    /**
     * Creates the overview section.
     */
    public void overview() {
        overview.setText(OVERVIEW);
        fullUserGuide.setText(FULL_USER_GUIDE);
    }

    /**
     * Creates the 'quick start' section.
     */
    public void quickStart() {
        quickStartTitle.setText(QUICK_START_TITLE);
        quickStartStep1.setText(QUICK_START_STEP1);
        quickStartStep2.setText(QUICK_START_STEP2);
        quickStartStep3.setText(QUICK_START_STEP3);
    }

    /**
     * Creates the 'feature notes' section.
     */
    public void featureNotes() {
        featuresTitle.setText(FEATURES_TITLE);
        featuresPoint1.setText(FEATURES_POINT1);
        featuresPoint2.setText(FEATURES_POINT2);
        featuresPoint3.setText(FEATURES_POINT3);
        featuresPoint4.setText(FEATURES_POINT4);
        featuresPoint5.setText(FEATURES_POINT5);
    }

    /**
     * Creates the 'help' section.
     */
    public void helpFeature() {
        format.setText(FORMAT);

        helpTitle.setText(HELP_TITLE);
        helpCommand.setText(HELP_COMMAND);
        helpDesc.setText(HELP_DESC);
    }

    /**
     * Creates the 'add student feature' section.
     */
    public void addStudentFeature() {
        format1.setText(FORMAT);
        example.setText(EXAMPLE);

        addSTitle.setText(ADD_S_TITLE);
        addSDesc.setText(ADD_S_DESC);
        addSCommand.setText(ADD_S_COMMAND);
        addSExample.setText(ADD_S_EXAMPLE);
    }

    /**
     * Creates the 'delete student feature' section.
     */
    public void deleteStudentFeature() {
        format3.setText(FORMAT);
        example2.setText(EXAMPLE);

        delSTitle.setText(DEL_S_TITLE);
        delSDesc.setText(DEL_S_DESC);
        delSCommand.setText(DEL_S_COMMAND);
        delSExample.setText(DEL_S_EXAMPLE);
        delSExampleDesc.setText(DEL_S_EXAMPLE_DESC);
    }

    /**
     * Creates the 'edit student feature' section.
     */
    public void editStudentFeature() {
        format4.setText(FORMAT);
        example3.setText(EXAMPLE);

        editSTitle.setText(EDIT_S_TITLE);
        editSDesc.setText(EDIT_S_DESC);
        editSCommand.setText(EDIT_S_COMMAND);
        editSExample.setText(EDIT_S_EXAMPLE);
        editSExampleDesc.setText(EDIT_S_EXAMPLE_DESC);
    }

    /**
     * Creates the 'view student feature' section.
     */
    public void viewStudentFeature() {
        format5.setText(FORMAT);
        example4.setText(EXAMPLE);

        viewSTitle.setText(VIEW_S_TITLE);
        viewSDesc.setText(VIEW_S_DESC);
        viewSCommand.setText(VIEW_S_COMMAND);
        viewSExample.setText(VIEW_S_EXAMPLE);
        viewSExampleDesc.setText(VIEW_S_EXAMPLE_DESC);
    }

    /**
     * Creates the 'list feature' section.
     */
    public void listFeatures() {
        format2.setText(FORMAT);
        example1.setText(EXAMPLE);

        listTitle.setText(LIST_TITLE);
        listDesc.setText(LIST_DESC);
        listGeneralCommand.setText(LIST_GENERAL_COMMAND);
        listExample.setText(LIST_EXAMPLE);
        listExampleDesc.setText(LIST_EXAMPLE_DESC);
        listAExample.setText(LIST_A_EXAMPLE);
        listAExampleDesc.setText(LIST_A_EXAMPLE_DESC);
    }

    /**
     * Creates the 'add progress feature' section.
     */
    public void addProgressFeature() {
        format6.setText(FORMAT);
        example5.setText(EXAMPLE);

        addPTitle.setText(ADD_P_TITLE);
        addPDesc.setText(ADD_P_DESC);
        addPCommand.setText(ADD_P_COMMAND);
        addPExample.setText(ADD_P_EXAMPLE);
        addPExampleDesc.setText(ADD_P_EXAMPLE_DESC);
    }

    /**
     * Creates the 'delete progress feature' section.
     */
    public void deleteProgressFeature() {
        format7.setText(FORMAT);
        example6.setText(EXAMPLE);

        delPTitle.setText(DEL_P_TITLE);
        delPDesc.setText(DEL_P_DESC);
        delPCommand.setText(DEL_P_COMMAND);
        delPExample.setText(DEL_P_EXAMPLE);
        delPExampleDesc.setText(DEL_P_EXAMPLE_DESC);
    }

    /**
     * Creates the 'paid feature' section.
     */
    public void paidFeature() {
        format8.setText(FORMAT);
        example7.setText(EXAMPLE);

        paidTitle.setText(PAID_TITLE);
        paidDesc.setText(PAID_DESC);
        paidCommand.setText(PAID_COMMAND);
        paidExample.setText(PAID_EXAMPLE);
        paidExampleDesc.setText(PAID_EXAMPLE_DESC);
    }

    /**
     * Creates the 'unpaid feature' section.
     */
    public void unpaidFeature() {
        format9.setText(FORMAT);
        example8.setText(EXAMPLE);

        unpaidTitle.setText(UNPAID_TITLE);
        unpaidDesc.setText(UNPAID_DESC);
        unpaidCommand.setText(UNPAID_COMMAND);
        unpaidExample.setText(UNPAID_EXAMPLE);
        unpaidExampleDesc.setText(UNPAID_EXAMPLE_DESC);
    }

    /**
     * Creates the 'clear feature' section.
     */
    public void clearFeature() {
        format10.setText(FORMAT);

        clearTitle.setText(CLEAR_TITLE);
        clearDesc.setText(CLEAR_DESC);
        clearCommand.setText(CLEAR_COMMAND);
    }

    /**
     * Creates the 'exit feature' section.
     */
    public void exitFeature() {
        format11.setText(FORMAT);

        exitTitle.setText(EXIT_TITLE);
        exitDesc.setText(EXIT_DESC);
        exitCommand.setText(EXIT_COMMAND);
    }

    /**
     * Creates the 'saving data' section.
     */
    public void savingData() {
        savingDataTitle.setText(SAVING_DATA_TITLE);
        savingDataDesc.setText(SAVING_DATA_DESC);
    }

    /**
     * Creates the 'edit data' section.
     */
    public void editData() {
        editDataTitle.setText(EDIT_DATA_TITLE);
        editDataDesc1.setText(EDIT_DATA_DESC1);
        editDataDesc2.setText(EDIT_DATA_DESC2);
        editFilePath.setText(EDIT_FILEPATH);
        editCaution.setText(EDIT_CAUTION);
    }

    /**
     * Creates the FAQ section.
     */
    public void faq() {
        faqTitle.setText(FAQ_TITLE);
        faqQ.setText(FAQ_Q);
        faqA.setText(FAQ_A);
    }
}
