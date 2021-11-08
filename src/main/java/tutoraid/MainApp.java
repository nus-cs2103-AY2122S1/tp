package tutoraid;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import tutoraid.commons.core.Config;
import tutoraid.commons.core.LogsCenter;
import tutoraid.commons.core.Messages;
import tutoraid.commons.core.Version;
import tutoraid.commons.exceptions.DataConversionException;
import tutoraid.commons.util.ConfigUtil;
import tutoraid.commons.util.StringUtil;
import tutoraid.logic.Logic;
import tutoraid.logic.LogicManager;
import tutoraid.model.LessonBook;
import tutoraid.model.Model;
import tutoraid.model.ModelManager;
import tutoraid.model.ReadOnlyLessonBook;
import tutoraid.model.ReadOnlyStudentBook;
import tutoraid.model.ReadOnlyUserPrefs;
import tutoraid.model.StudentBook;
import tutoraid.model.UserPrefs;
import tutoraid.model.util.SampleDataUtil;
import tutoraid.storage.JsonTutorAidLessonStorage;
import tutoraid.storage.JsonTutorAidStudentStorage;
import tutoraid.storage.JsonUserPrefsStorage;
import tutoraid.storage.Storage;
import tutoraid.storage.StorageManager;
import tutoraid.storage.TutorAidLessonStorage;
import tutoraid.storage.TutorAidStudentStorage;
import tutoraid.storage.UserPrefsStorage;
import tutoraid.ui.Ui;
import tutoraid.ui.UiManager;


/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    private String message = String.format("%s\n", Messages.WELCOME_MESSAGE);

    @Override
    public void init() throws Exception {
        logger.info("==================[ Initializing StudentBook and LessonBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        TutorAidLessonStorage tutorAidLessonStorage = new JsonTutorAidLessonStorage(userPrefs.getLessonBookFilePath());
        TutorAidStudentStorage tutorAidStudentStorage;
        try {
            tutorAidLessonStorage.readLessonBook(userPrefs.getLessonBookFilePath());
            tutorAidStudentStorage =
                    new JsonTutorAidStudentStorage(userPrefs.getStudentBookFilePath(),
                         tutorAidLessonStorage.readLessonBook().orElseGet(SampleDataUtil::getSampleLessonBook));
        } catch (DataConversionException e) {
            tutorAidStudentStorage = new JsonTutorAidStudentStorage(
                    userPrefs.getStudentBookFilePath(),
                    new LessonBook());
        }

        storage = new StorageManager(tutorAidStudentStorage, tutorAidLessonStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s student and lesson books
     * and {@code userPrefs}. <br>
     * The data from the sample student book will be used instead if {@code storage}'s student book is not found,
     * or an empty student book will be used instead if errors occur when reading {@code storage}'s student book.
     * This applies to the lesson book too.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyStudentBook> studentBookOptional;
        ReadOnlyStudentBook studentsInitialData;
        Optional<ReadOnlyLessonBook> lessonBookOptional;
        ReadOnlyLessonBook lessonsInitialData;
        try {
            lessonBookOptional = storage.readLessonBook();
            if (lessonBookOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample LessonBook");
                message += String.format("\n%s", Messages.MESSAGE_NO_LESSON_DATA);
            }
            lessonsInitialData = lessonBookOptional.orElseGet(SampleDataUtil::getSampleLessonBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty LessonBook");
            lessonsInitialData = new LessonBook();
            message += String.format("\n%s", Messages.MESSAGE_JSON_INTEGRITY_LESSON_ERROR);
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty LessonBook");
            lessonsInitialData = new LessonBook();
            message += String.format("\n%s", Messages.MESSAGE_JSON_INTEGRITY_LESSON_ERROR);
        }

        try {
            studentBookOptional = storage.readStudentBook(lessonsInitialData);
            if (studentBookOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample StudentBook");
                message += String.format("\n%s", Messages.MESSAGE_NO_STUDENT_DATA);
            }
            ReadOnlyLessonBook finalLessonsInitialData = lessonsInitialData;
            studentsInitialData = studentBookOptional.orElseGet(() -> SampleDataUtil.getSampleStudentBook(
                    finalLessonsInitialData));
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty StudentBook");
            message += String.format("\n%s", Messages.MESSAGE_JSON_INTEGRITY_STUDENT_ERROR);
            studentsInitialData = new StudentBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty StudentBook");
            message += String.format("\n%s", Messages.MESSAGE_JSON_INTEGRITY_STUDENT_ERROR);
            studentsInitialData = new StudentBook();
        }





        return new ModelManager(studentsInitialData, lessonsInitialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty StudentBook");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting StudentBook and LessonBook " + MainApp.VERSION);
        ui.start(primaryStage);
        UiManager.printMessage(message);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
