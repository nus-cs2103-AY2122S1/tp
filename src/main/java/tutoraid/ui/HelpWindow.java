package tutoraid.ui;

import static tutoraid.commons.core.HelpGuide.ADD_P_COMMAND;
import static tutoraid.commons.core.HelpGuide.ADD_P_DESC;
import static tutoraid.commons.core.HelpGuide.ADD_P_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.ADD_P_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.ADD_P_TITLE;
import static tutoraid.commons.core.HelpGuide.ADD_S_COMMAND;
import static tutoraid.commons.core.HelpGuide.ADD_S_DESC;
import static tutoraid.commons.core.HelpGuide.ADD_S_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.ADD_S_TITLE;
import static tutoraid.commons.core.HelpGuide.CLEAR_COMMAND;
import static tutoraid.commons.core.HelpGuide.CLEAR_DESC;
import static tutoraid.commons.core.HelpGuide.CLEAR_TITLE;
import static tutoraid.commons.core.HelpGuide.DEL_P_COMMAND;
import static tutoraid.commons.core.HelpGuide.DEL_P_DESC;
import static tutoraid.commons.core.HelpGuide.DEL_P_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.DEL_P_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.DEL_P_TITLE;
import static tutoraid.commons.core.HelpGuide.DEL_S_COMMAND;
import static tutoraid.commons.core.HelpGuide.DEL_S_DESC;
import static tutoraid.commons.core.HelpGuide.DEL_S_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.DEL_S_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.DEL_S_TITLE;
import static tutoraid.commons.core.HelpGuide.EDIT_CAUTION;
import static tutoraid.commons.core.HelpGuide.EDIT_DATA_DESC1;
import static tutoraid.commons.core.HelpGuide.EDIT_DATA_DESC2;
import static tutoraid.commons.core.HelpGuide.EDIT_DATA_TITLE;
import static tutoraid.commons.core.HelpGuide.EDIT_FILEPATH;
import static tutoraid.commons.core.HelpGuide.EDIT_S_COMMAND;
import static tutoraid.commons.core.HelpGuide.EDIT_S_DESC;
import static tutoraid.commons.core.HelpGuide.EDIT_S_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.EDIT_S_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.EDIT_S_TITLE;
import static tutoraid.commons.core.HelpGuide.EXAMPLE;
import static tutoraid.commons.core.HelpGuide.EXIT_COMMAND;
import static tutoraid.commons.core.HelpGuide.EXIT_DESC;
import static tutoraid.commons.core.HelpGuide.EXIT_TITLE;
import static tutoraid.commons.core.HelpGuide.FAQ_A;
import static tutoraid.commons.core.HelpGuide.FAQ_Q;
import static tutoraid.commons.core.HelpGuide.FAQ_TITLE;
import static tutoraid.commons.core.HelpGuide.FEATURES_POINT1;
import static tutoraid.commons.core.HelpGuide.FEATURES_POINT2;
import static tutoraid.commons.core.HelpGuide.FEATURES_POINT3;
import static tutoraid.commons.core.HelpGuide.FEATURES_POINT4;
import static tutoraid.commons.core.HelpGuide.FEATURES_POINT5;
import static tutoraid.commons.core.HelpGuide.FEATURES_TITLE;
import static tutoraid.commons.core.HelpGuide.FORMAT;
import static tutoraid.commons.core.HelpGuide.FULL_USER_GUIDE;
import static tutoraid.commons.core.HelpGuide.HELP_COMMAND;
import static tutoraid.commons.core.HelpGuide.HELP_DESC;
import static tutoraid.commons.core.HelpGuide.HELP_TITLE;
import static tutoraid.commons.core.HelpGuide.LIST_A_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.LIST_A_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.LIST_DESC;
import static tutoraid.commons.core.HelpGuide.LIST_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.LIST_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.LIST_GENERAL_COMMAND;
import static tutoraid.commons.core.HelpGuide.LIST_TITLE;
import static tutoraid.commons.core.HelpGuide.OVERVIEW;
import static tutoraid.commons.core.HelpGuide.PAID_COMMAND;
import static tutoraid.commons.core.HelpGuide.PAID_DESC;
import static tutoraid.commons.core.HelpGuide.PAID_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.PAID_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.PAID_TITLE;
import static tutoraid.commons.core.HelpGuide.QUICK_START_STEP1;
import static tutoraid.commons.core.HelpGuide.QUICK_START_STEP2;
import static tutoraid.commons.core.HelpGuide.QUICK_START_STEP3;
import static tutoraid.commons.core.HelpGuide.QUICK_START_TITLE;
import static tutoraid.commons.core.HelpGuide.SAVING_DATA_DESC;
import static tutoraid.commons.core.HelpGuide.SAVING_DATA_TITLE;
import static tutoraid.commons.core.HelpGuide.UNPAID_COMMAND;
import static tutoraid.commons.core.HelpGuide.UNPAID_DESC;
import static tutoraid.commons.core.HelpGuide.UNPAID_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.UNPAID_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.UNPAID_TITLE;
import static tutoraid.commons.core.HelpGuide.USER_GUIDE_TITLE;
import static tutoraid.commons.core.HelpGuide.VIEW_S_COMMAND;
import static tutoraid.commons.core.HelpGuide.VIEW_S_DESC;
import static tutoraid.commons.core.HelpGuide.VIEW_S_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.VIEW_S_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.VIEW_S_TITLE;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tutoraid.commons.core.LogsCenter;
/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2122s1-cs2103t-w16-3.github.io/tp/UserGuide.html";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Label userGuideTitle;

    @FXML
    private Label overview;

    @FXML
    private Label fullUserGuide;

    @FXML
    private Label quickStartTitle;
    @FXML
    private Label quickStartStep1;
    @FXML
    private Label quickStartStep2;
    @FXML
    private Label quickStartStep3;

    @FXML
    private Label featuresTitle;
    @FXML
    private Label featuresPoint1;
    @FXML
    private Label featuresPoint2;
    @FXML
    private Label featuresPoint3;
    @FXML
    private Label featuresPoint4;
    @FXML
    private Label featuresPoint5;

    @FXML
    private Label format;
    @FXML
    private Label format1;
    @FXML
    private Label format2;
    @FXML
    private Label format3;
    @FXML
    private Label format4;
    @FXML
    private Label format5;
    @FXML
    private Label format6;
    @FXML
    private Label format7;
    @FXML
    private Label format8;
    @FXML
    private Label format9;
    @FXML
    private Label format10;
    @FXML
    private Label format11;

    @FXML
    private Label example;
    @FXML
    private Label example1;
    @FXML
    private Label example2;
    @FXML
    private Label example3;
    @FXML
    private Label example4;
    @FXML
    private Label example5;
    @FXML
    private Label example6;
    @FXML
    private Label example7;
    @FXML
    private Label example8;
    @FXML
    private Label example9;
    @FXML
    private Label example10;

    @FXML
    private Label helpTitle;
    @FXML
    private Label helpCommand;
    @FXML
    private Label helpDesc;

    @FXML
    private Label addSTitle;
    @FXML
    private Label addSCommand;
    @FXML
    private Label addSDesc;
    @FXML
    private Label addSExample;

    @FXML
    private Label listTitle;
    @FXML
    private Label listDesc;
    @FXML
    private Label listGeneralCommand;
    @FXML
    private Label listExample;
    @FXML
    private Label listExampleDesc;
    @FXML
    private Label listAExample;
    @FXML
    private Label listAExampleDesc;


    @FXML
    private Label delSTitle;
    @FXML
    private Label delSCommand;
    @FXML
    private Label delSDesc;
    @FXML
    private Label delSExample;
    @FXML
    private Label delSExampleDesc;


    @FXML
    private Label editSTitle;
    @FXML
    private Label editSCommand;
    @FXML
    private Label editSDesc;
    @FXML
    private Label editSExample;
    @FXML
    private Label editSExampleDesc;

    @FXML
    private Label viewSTitle;
    @FXML
    private Label viewSCommand;
    @FXML
    private Label viewSDesc;
    @FXML
    private Label viewSExample;
    @FXML
    private Label viewSExampleDesc;

    @FXML
    private Label addPTitle;
    @FXML
    private Label addPCommand;
    @FXML
    private Label addPDesc;
    @FXML
    private Label addPExample;
    @FXML
    private Label addPExampleDesc;

    @FXML
    private Label delPTitle;
    @FXML
    private Label delPCommand;
    @FXML
    private Label delPDesc;
    @FXML
    private Label delPExample;
    @FXML
    private Label delPExampleDesc;

    @FXML
    private Label paidTitle;
    @FXML
    private Label paidCommand;
    @FXML
    private Label paidDesc;
    @FXML
    private Label paidExample;
    @FXML
    private Label paidExampleDesc;

    @FXML
    private Label unpaidTitle;
    @FXML
    private Label unpaidCommand;
    @FXML
    private Label unpaidDesc;
    @FXML
    private Label unpaidExample;
    @FXML
    private Label unpaidExampleDesc;


    @FXML
    private Label clearTitle;
    @FXML
    private Label clearCommand;
    @FXML
    private Label clearDesc;

    @FXML
    private Label exitTitle;
    @FXML
    private Label exitCommand;
    @FXML
    private Label exitDesc;

    @FXML
    private Label savingDataTitle;
    @FXML
    private Label savingDataDesc;

    @FXML
    private Label editDataTitle;
    @FXML
    private Label editDataDesc1;
    @FXML
    private Label editDataDesc2;
    @FXML
    private Label editFilePath;
    @FXML
    private Label editCaution;

    @FXML
    private Label faqTitle;
    @FXML
    private Label faqQ;
    @FXML
    private Label faqA;

    @FXML
    private VBox helpWindow;

    /**
     * Creates a new HelpWindow by calling on individual helper functions to set up the different sections of the
     * user guide in the help window.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);

        //Setting up the start of the user guide
        userGuide();
        overview();
        quickStart();

        featureNotes();

        //Setting a section for each of the features that have been implemented thus far
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

        //Setting up the end of the user guide
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
     * Creates the user guide title.
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
     * Creates the 'features' section.
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
     * Creates the 'help feature' section.
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
