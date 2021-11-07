package tutoraid.ui;

import static tutoraid.commons.core.HelpGuide.ADD_L_COMMAND;
import static tutoraid.commons.core.HelpGuide.ADD_L_DESC;
import static tutoraid.commons.core.HelpGuide.ADD_L_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.ADD_L_TITLE;
import static tutoraid.commons.core.HelpGuide.ADD_P_COMMAND;
import static tutoraid.commons.core.HelpGuide.ADD_P_DESC;
import static tutoraid.commons.core.HelpGuide.ADD_P_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.ADD_P_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.ADD_P_TITLE;
import static tutoraid.commons.core.HelpGuide.ADD_SL_COMMAND;
import static tutoraid.commons.core.HelpGuide.ADD_SL_DESC;
import static tutoraid.commons.core.HelpGuide.ADD_SL_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.ADD_SL_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.ADD_SL_TITLE;
import static tutoraid.commons.core.HelpGuide.ADD_S_COMMAND;
import static tutoraid.commons.core.HelpGuide.ADD_S_DESC;
import static tutoraid.commons.core.HelpGuide.ADD_S_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.ADD_S_TITLE;
import static tutoraid.commons.core.HelpGuide.CLEAR_COMMAND;
import static tutoraid.commons.core.HelpGuide.CLEAR_DESC;
import static tutoraid.commons.core.HelpGuide.CLEAR_TITLE;
import static tutoraid.commons.core.HelpGuide.DEL_L_COMMAND;
import static tutoraid.commons.core.HelpGuide.DEL_L_DESC;
import static tutoraid.commons.core.HelpGuide.DEL_L_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.DEL_L_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.DEL_L_TITLE;
import static tutoraid.commons.core.HelpGuide.DEL_P_COMMAND;
import static tutoraid.commons.core.HelpGuide.DEL_P_DESC;
import static tutoraid.commons.core.HelpGuide.DEL_P_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.DEL_P_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.DEL_P_TITLE;
import static tutoraid.commons.core.HelpGuide.DEL_SL_COMMAND;
import static tutoraid.commons.core.HelpGuide.DEL_SL_DESC;
import static tutoraid.commons.core.HelpGuide.DEL_SL_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.DEL_SL_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.DEL_SL_TITLE;
import static tutoraid.commons.core.HelpGuide.DEL_S_COMMAND;
import static tutoraid.commons.core.HelpGuide.DEL_S_DESC;
import static tutoraid.commons.core.HelpGuide.DEL_S_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.DEL_S_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.DEL_S_TITLE;
import static tutoraid.commons.core.HelpGuide.EDIT_CAUTION;
import static tutoraid.commons.core.HelpGuide.EDIT_DATA_DESC1;
import static tutoraid.commons.core.HelpGuide.EDIT_DATA_DESC2;
import static tutoraid.commons.core.HelpGuide.EDIT_DATA_DESC3;
import static tutoraid.commons.core.HelpGuide.EDIT_DATA_TITLE;
import static tutoraid.commons.core.HelpGuide.EDIT_LESSONS_FILEPATH;
import static tutoraid.commons.core.HelpGuide.EDIT_L_COMMAND;
import static tutoraid.commons.core.HelpGuide.EDIT_L_DESC;
import static tutoraid.commons.core.HelpGuide.EDIT_L_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.EDIT_L_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.EDIT_L_TITLE;
import static tutoraid.commons.core.HelpGuide.EDIT_STUDENTS_FILEPATH;
import static tutoraid.commons.core.HelpGuide.EDIT_S_COMMAND;
import static tutoraid.commons.core.HelpGuide.EDIT_S_DESC;
import static tutoraid.commons.core.HelpGuide.EDIT_S_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.EDIT_S_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.EDIT_S_TITLE;
import static tutoraid.commons.core.HelpGuide.EXAMPLE;
import static tutoraid.commons.core.HelpGuide.EXIT_COMMAND;
import static tutoraid.commons.core.HelpGuide.EXIT_DESC;
import static tutoraid.commons.core.HelpGuide.EXIT_TITLE;
import static tutoraid.commons.core.HelpGuide.FAQ;
import static tutoraid.commons.core.HelpGuide.FAQ_TITLE;
import static tutoraid.commons.core.HelpGuide.FEATURES_NOTES;
import static tutoraid.commons.core.HelpGuide.FEATURES_TITLE;
import static tutoraid.commons.core.HelpGuide.FIND_COMMAND;
import static tutoraid.commons.core.HelpGuide.FIND_DESC1;
import static tutoraid.commons.core.HelpGuide.FIND_DESC2;
import static tutoraid.commons.core.HelpGuide.FIND_DESC3;
import static tutoraid.commons.core.HelpGuide.FIND_L_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.FIND_L_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.FIND_S_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.FIND_S_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.FIND_TITLE;
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
import static tutoraid.commons.core.HelpGuide.L_FLAG;
import static tutoraid.commons.core.HelpGuide.OVERVIEW;
import static tutoraid.commons.core.HelpGuide.QUICK_START;
import static tutoraid.commons.core.HelpGuide.QUICK_START_TITLE;
import static tutoraid.commons.core.HelpGuide.SAVING_DATA_DESC;
import static tutoraid.commons.core.HelpGuide.SAVING_DATA_TITLE;
import static tutoraid.commons.core.HelpGuide.S_FLAG;
import static tutoraid.commons.core.HelpGuide.USER_GUIDE_TITLE;
import static tutoraid.commons.core.HelpGuide.VIEW_L_COMMAND;
import static tutoraid.commons.core.HelpGuide.VIEW_L_DESC;
import static tutoraid.commons.core.HelpGuide.VIEW_L_EXAMPLE;
import static tutoraid.commons.core.HelpGuide.VIEW_L_EXAMPLE_DESC;
import static tutoraid.commons.core.HelpGuide.VIEW_L_TITLE;
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
    private Label quickStart;

    @FXML
    private Label featuresTitle;
    @FXML
    private Label featuresNotes;

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
    private Label format12;
    @FXML
    private Label format13;
    @FXML
    private Label format14;
    @FXML
    private Label format15;
    @FXML
    private Label format16;
    @FXML
    private Label format17;
    @FXML
    private Label format18;

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
    private Label example11;
    @FXML
    private Label example12;
    @FXML
    private Label example13;
    @FXML
    private Label example14;
    @FXML
    private Label example15;


    @FXML
    private Label sFlag;
    @FXML
    private Label lFlag;

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
    private Label findTitle;
    @FXML
    private Label findCommand;
    @FXML
    private Label findDesc1;
    @FXML
    private Label findDesc2;
    @FXML
    private Label findDesc3;
    @FXML
    private Label findSExample;
    @FXML
    private Label findSExampleDesc;
    @FXML
    private Label findLExample;
    @FXML
    private Label findLExampleDesc;

    @FXML
    private Label addLTitle;
    @FXML
    private Label addLCommand;
    @FXML
    private Label addLDesc;
    @FXML
    private Label addLExample;

    @FXML
    private Label delLTitle;
    @FXML
    private Label delLCommand;
    @FXML
    private Label delLDesc;
    @FXML
    private Label delLExample;
    @FXML
    private Label delLExampleDesc;

    @FXML
    private Label editLTitle;
    @FXML
    private Label editLCommand;
    @FXML
    private Label editLDesc;
    @FXML
    private Label editLExample;
    @FXML
    private Label editLExampleDesc;

    @FXML
    private Label viewLTitle;
    @FXML
    private Label viewLCommand;
    @FXML
    private Label viewLDesc;
    @FXML
    private Label viewLExample;
    @FXML
    private Label viewLExampleDesc;

    @FXML
    private Label addSlTitle;
    @FXML
    private Label addSlCommand;
    @FXML
    private Label addSlDesc;
    @FXML
    private Label addSlExample;
    @FXML
    private Label addSlExampleDesc;

    @FXML
    private Label delSlTitle;
    @FXML
    private Label delSlCommand;
    @FXML
    private Label delSlDesc;
    @FXML
    private Label delSlExample;
    @FXML
    private Label delSlExampleDesc;

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
    private Label editDataDesc3;
    @FXML
    private Label editStudentsFilePath;
    @FXML
    private Label editLessonsFilePath;
    @FXML
    private Label editCaution;

    @FXML
    private Label faqTitle;
    @FXML
    private Label faq;

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
        addLessonFeature();
        deleteLessonFeature();
        editLessonFeature();
        viewLessonFeature();
        addStudentsToLessonsFeature();
        deleteStudentsFromLessonsFeature();
        clearFeature();
        exitFeature();
        findFeature();

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
        quickStart.setText(QUICK_START);
        quickStart.setLineSpacing(2.0);
    }

    /**
     * Creates the 'features' section.
     */
    public void featureNotes() {
        featuresTitle.setText(FEATURES_TITLE);
        featuresNotes.setText(FEATURES_NOTES);
        featuresNotes.setLineSpacing(2.0);
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
     * Creates the 'find feature' section.
     */
    public void findFeature() {
        format12.setText(FORMAT);
        example9.setText(EXAMPLE);
        sFlag.setText(S_FLAG);
        lFlag.setText(L_FLAG);

        findTitle.setText(FIND_TITLE);
        findDesc1.setText(FIND_DESC1);
        findDesc2.setText(FIND_DESC2);
        findDesc3.setText(FIND_DESC3);
        findCommand.setText(FIND_COMMAND);
        findSExample.setText(FIND_S_EXAMPLE);
        findSExampleDesc.setText(FIND_S_EXAMPLE_DESC);
        findLExample.setText(FIND_L_EXAMPLE);
        findLExampleDesc.setText(FIND_L_EXAMPLE_DESC);
    }

    /**
     * Creates the 'add lesson feature' section.
     */
    public void addLessonFeature() {
        format13.setText(FORMAT);
        example10.setText(EXAMPLE);

        addLTitle.setText(ADD_L_TITLE);
        addLDesc.setText(ADD_L_DESC);
        addLCommand.setText(ADD_L_COMMAND);
        addLExample.setText(ADD_L_EXAMPLE);
    }

    /**
     * Creates the 'delete lesson feature' section.
     */
    public void deleteLessonFeature() {
        format14.setText(FORMAT);
        example11.setText(EXAMPLE);

        delLTitle.setText(DEL_L_TITLE);
        delLDesc.setText(DEL_L_DESC);
        delLCommand.setText(DEL_L_COMMAND);
        delLExample.setText(DEL_L_EXAMPLE);
        delLExampleDesc.setText(DEL_L_EXAMPLE_DESC);
    }

    /**
     * Creates the 'edit lesson feature' section.
     */
    public void editLessonFeature() {
        format15.setText(FORMAT);
        example12.setText(EXAMPLE);

        editLTitle.setText(EDIT_L_TITLE);
        editLDesc.setText(EDIT_L_DESC);
        editLCommand.setText(EDIT_L_COMMAND);
        editLExample.setText(EDIT_L_EXAMPLE);
        editLExampleDesc.setText(EDIT_L_EXAMPLE_DESC);
    }

    /**
     * Creates the 'view lesson feature' section.
     */
    public void viewLessonFeature() {
        format16.setText(FORMAT);
        example13.setText(EXAMPLE);

        viewLTitle.setText(VIEW_L_TITLE);
        viewLDesc.setText(VIEW_L_DESC);
        viewLCommand.setText(VIEW_L_COMMAND);
        viewLExample.setText(VIEW_L_EXAMPLE);
        viewLExampleDesc.setText(VIEW_L_EXAMPLE_DESC);
    }

    /**
     * Create the 'add student(s) to lesson(s) feature' section.
     */
    public void addStudentsToLessonsFeature() {
        format17.setText(FORMAT);
        example14.setText(EXAMPLE);

        addSlTitle.setText(ADD_SL_TITLE);
        addSlDesc.setText(ADD_SL_DESC);
        addSlCommand.setText(ADD_SL_COMMAND);
        addSlExample.setText(ADD_SL_EXAMPLE);
        addSlExampleDesc.setText(ADD_SL_EXAMPLE_DESC);
    }

    /**
     * Create the 'delete student(s) from lesson(s) feature' section.
     */
    public void deleteStudentsFromLessonsFeature() {
        format18.setText(FORMAT);
        example15.setText(EXAMPLE);

        delSlTitle.setText(DEL_SL_TITLE);
        delSlDesc.setText(DEL_SL_DESC);
        delSlCommand.setText(DEL_SL_COMMAND);
        delSlExample.setText(DEL_SL_EXAMPLE);
        delSlExampleDesc.setText(DEL_SL_EXAMPLE_DESC);
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
        editDataDesc3.setText(EDIT_DATA_DESC3);
        editStudentsFilePath.setText(EDIT_STUDENTS_FILEPATH);
        editLessonsFilePath.setText(EDIT_LESSONS_FILEPATH);
        editCaution.setText(EDIT_CAUTION);
    }

    /**
     * Creates the FAQ section.
     */
    public void faq() {
        faqTitle.setText(FAQ_TITLE);
        faq.setText(FAQ);
        faq.setLineSpacing(2.0);
    }
}
