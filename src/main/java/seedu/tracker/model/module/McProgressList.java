package seedu.tracker.model.module;

import static java.util.Objects.requireNonNull;

import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.tracker.model.UserInfo;
import seedu.tracker.model.calendar.AcademicCalendar;

/**
 * Represents the list of the user's progress for each requirement.
 */
public class McProgressList {

    public static final int GE_REQUIREMENT = 16;
    public static final int UE_REQUIREMENT = 40;
    public static final int FOUNDATION_REQUIREMENT = 36;
    public static final int BREADTH_DEPTH_REQUIREMENT = 40;
    public static final int PROFESSIONALISM_REQUIREMENT = 12;
    public static final int MATH_SCIENCE_REQUIREMENT = 16;

    public static final int TOTAL_INDEX = 0;
    public static final int GE_INDEX = 1;
    public static final int UE_INDEX = 2;
    public static final int FOUNDATION_INDEX = 3;
    public static final int BREADTH_DEPTH_INDEX = 4;
    public static final int PROFESSIONALISM_INDEX = 5;
    public static final int MATH_SCIENCE_INDEX = 6;

    public static final String GE_TAG_TITLE = "GE";
    public static final String UE_TAG_TITLE = "UE";
    public static final String FOUNDATION_TAG_TITLE = "Foundation";
    public static final String BREADTH_DEPTH_TAG_TITLE = "Breadth and Depth";
    public static final String PROFESSIONALISM_TAG_TITLE = "IT Professionalism";
    public static final String MATH_SCIENCE_TAG_TITLE = "Math and Science";

    private static final int[] TAG_INDEXES_LIST = new int[] {GE_INDEX, UE_INDEX,
        FOUNDATION_INDEX, BREADTH_DEPTH_INDEX, PROFESSIONALISM_INDEX, MATH_SCIENCE_INDEX};

    private static final int[] MC_REQUIREMENTS_LIST = new int[] {GE_REQUIREMENT, UE_REQUIREMENT,
        FOUNDATION_REQUIREMENT, BREADTH_DEPTH_REQUIREMENT, PROFESSIONALISM_REQUIREMENT, MATH_SCIENCE_REQUIREMENT};

    private static final String[] TAGS_LIST = new String[] {GE_TAG_TITLE, UE_TAG_TITLE, FOUNDATION_TAG_TITLE,
        BREADTH_DEPTH_TAG_TITLE, PROFESSIONALISM_TAG_TITLE, MATH_SCIENCE_TAG_TITLE};

    private ObservableList<McProgress> mcProgressList;
    private UserInfo userInfo;

    /**
     * Constructs a new McProgressList.
     * @param userInfo information used to calculate the needed mcs and get the required mc targets.
     */
    public McProgressList(UserInfo userInfo) {
        requireNonNull(userInfo);
        this.userInfo = userInfo;
        initialiseDefaultProgressList();
    }

    private void initialiseDefaultProgressList() {
        ObservableList<McProgress> defaultProgressList = FXCollections.observableArrayList();
        Mc zeroMc = new Mc();
        defaultProgressList.add(new McProgress(zeroMc, userInfo.getMcGoal(), "Total"));

        for (int index : TAG_INDEXES_LIST) {
            int reqInt = MC_REQUIREMENTS_LIST[index - 1];
            assert reqInt != 0 : "MC requirement will never be 0";
            Mc requirement = new Mc(reqInt);
            String tagName = TAGS_LIST[index - 1];

            McProgress newProgress = new McProgress(zeroMc, requirement, tagName);
            defaultProgressList.add(newProgress);
        }
        this.mcProgressList = defaultProgressList;
    }

    /**
     * Updates the list of McProgress.
     * @param modules module list to calculate completed mcs from.
     * @param userInfo user information used to calculate completed mcs and get mc targets.
     */
    public void update(ObservableList<Module> modules, UserInfo userInfo) {
        // update new user info
        this.userInfo = userInfo;

        // get completed modules based on new user info
        ObservableList<Module> completedModules = getCompletedModulesList(modules);

        // update the total progress with new total completed mcs and mc goal
        Mc totalCompletedMcs = getTotalCompletedMcs(completedModules);
        updateTotalProgress(totalCompletedMcs, userInfo.getMcGoal());

        //update the progress of other requirements with new completed mcs only (requirements are constant)
        for (int index : TAG_INDEXES_LIST) {
            Mc mcByTag = getMcByTag(completedModules, TAGS_LIST[index - 1]);
            updateProgress(index, mcByTag);
        }
    }

    private void updateTotalProgress(Mc totalCompletedMcs, Mc targetMcs) {
        McProgress totalProgress = mcProgressList.get(TOTAL_INDEX);
        McProgress newTotalProgress = new McProgress(totalProgress);
        newTotalProgress.setCompleted(totalCompletedMcs);
        newTotalProgress.setTarget(targetMcs);
        mcProgressList.set(TOTAL_INDEX, newTotalProgress);
    }

    private void updateProgress(int index, Mc completedMcs) {
        assert index != TOTAL_INDEX : "Updating of total completed MCs should not be done with this method";
        McProgress progress = mcProgressList.get(index);
        McProgress newProgress = new McProgress(progress);
        newProgress.setCompleted(completedMcs);
        mcProgressList.set(index, newProgress);
    }

    private Mc getMcByTag(ObservableList<Module> modules, String tagName) {
        ModuleByTagNamePredicate modulesWithTag = new ModuleByTagNamePredicate(tagName);
        int numOfMc = modules.stream()
                .filter(modulesWithTag)
                .mapToInt(m -> m.getMc().value)
                .sum();

        if (numOfMc == 0) {
            return new Mc();
        } else {
            return new Mc(numOfMc);
        }
    }

    private Mc getTotalCompletedMcs(ObservableList<Module> completedModules) {
        int totalNum = completedModules.stream()
                .mapToInt(module -> module.getMc().value)
                .sum();
        if (totalNum == 0) {
            return new Mc();
        } else {
            return new Mc(totalNum);
        }
    }

    private ObservableList<Module> getCompletedModulesList(ObservableList<Module> modules) {
        AcademicCalendar currentSemester = userInfo.getCurrentSemester();

        return modules.stream()
                .filter(module -> module.hasAcademicCalendar()
                        && module.getAcademicCalendar().isBefore(currentSemester))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<McProgress> getMcProgressList() {
        return mcProgressList;
    }

}
