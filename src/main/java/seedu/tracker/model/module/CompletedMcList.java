package seedu.tracker.model.module;

import static java.util.Objects.requireNonNull;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.tracker.model.calendar.AcademicCalendar;

public class CompletedMcList {

    public static final int GE_REQUIREMENT = 16;
    public static final int UE_REQUIREMENT = 40;
    public static final int FOUNDATION_REQUIREMENT = 36;
    public static final int BREADTH_DEPTH_REQUIREMENT = 40;
    public static final int PROFESSIONALISM_REQUIREMENT = 12;
    public static final int MATH_SCIENCE_REQUIREMENT = 16;

    public static final int[] MC_REQUIREMENTS_LIST = new int[] {GE_REQUIREMENT, UE_REQUIREMENT,
            FOUNDATION_REQUIREMENT, BREADTH_DEPTH_REQUIREMENT, PROFESSIONALISM_REQUIREMENT, MATH_SCIENCE_REQUIREMENT};

    public static final int GE_INDEX = 0;
    public static final int UE_INDEX = 1;
    public static final int FOUNDATION_INDEX = 2;
    public static final int BREADTH_DEPTH_INDEX = 3;
    public static final int PROFESSIONALISM_INDEX = 4;
    public static final int MATH_SCIENCE_INDEX = 5;

    public static final int[] TAG_INDEXES_LIST = new int[] {GE_INDEX, UE_INDEX,
            FOUNDATION_INDEX, BREADTH_DEPTH_INDEX, PROFESSIONALISM_INDEX, MATH_SCIENCE_INDEX};

    public static final String GE_TAG_TITLE = "GE";
    public static final String UE_TAG_TITLE = "UE";
    public static final String FOUNDATION_TAG_TITLE = "Foundation";
    public static final String BREADTH_DEPTH_TAG_TITLE = "BreadthDepth";
    public static final String PROFESSIONALISM_TAG_TITLE = "ITProfessionalism";
    public static final String MATH_SCIENCE_TAG_TITLE = "MathScience";

    public static final String[] TAGS_LIST = new String[] {GE_TAG_TITLE, UE_TAG_TITLE, FOUNDATION_TAG_TITLE,
            BREADTH_DEPTH_TAG_TITLE, PROFESSIONALISM_TAG_TITLE, MATH_SCIENCE_TAG_TITLE};

    private ObservableList<Mc> completedMcList;
    private AcademicCalendar currentSemester;

    public CompletedMcList(AcademicCalendar currentSemester) {
        requireNonNull(currentSemester);
        this.currentSemester = currentSemester;
        initialiseCompletedMcListWithDefault();
    }

    private void initialiseCompletedMcListWithDefault() {
        ObservableList<Mc> emptyMcList = FXCollections.observableArrayList();
        Mc zeroMc = new Mc(0);
        for (int index : TAG_INDEXES_LIST) {
            emptyMcList.add(index, zeroMc);
        }
        this.completedMcList = emptyMcList;
    }

    public void update(ObservableList<Module> modules, AcademicCalendar currentSemester) {
        this.currentSemester = currentSemester;
        ObservableList<Module> completedModules = getCompletedModulesList(modules);

        for (int index : TAG_INDEXES_LIST) {
            Mc mcByTag = getMcByTag(completedModules, TAGS_LIST[index]);
            completedMcList.set(index, mcByTag);
        }
    }

    private Mc getMcByTag(ObservableList<Module> modules, String tagName) {
        ModuleByTagNamePredicate modulesWithTag = new ModuleByTagNamePredicate(tagName);
        int numOfMc = modules.stream()
                .filter(modulesWithTag)
                .mapToInt(m -> m.getMc().value)
                .sum();
        return new Mc(numOfMc);
    }

    private ObservableList<Module> getCompletedModulesList(ObservableList<Module> modules) {
        return modules.stream()
                .filter(module -> module.hasAcademicCalendar() && module.getAcademicCalendar().isBefore(currentSemester))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Mc> getCompletedMcList() {
        return completedMcList;
    }

}
