package seedu.times.testutil;

import seedu.times.model.tuitionclass.ClassName;
import seedu.times.model.tuitionclass.ClassTiming;
import seedu.times.model.tuitionclass.Location;
import seedu.times.model.tuitionclass.Rate;
import seedu.times.model.tuitionclass.StudentNameList;
import seedu.times.model.tuitionclass.TuitionClass;

public class TuitionClassBuilder {

    public static final String DEFAULT_CLASS_NAME = "O Levels English";
    public static final String DEFAULT_RATE = "51";
    public static final String DEFAULT_LOCATION = "Tuas Blk 111";
    public static final String DEFAULT_CLASS_TIMING = "sun 16:00-17:00";
    public static final String[] DEFAULT_NAME_LIST = {"Alice", "Benson", "Carl"};

    private ClassName className;
    private ClassTiming classTiming;
    private Rate rate;
    private Location location;
    private StudentNameList studentNameList;

    /**
     * Constructs a new TuitionClassBuilder with the default information.
     */
    public TuitionClassBuilder() {
        className = new ClassName(DEFAULT_CLASS_NAME);
        classTiming = new ClassTiming(DEFAULT_CLASS_TIMING);
        rate = new Rate(DEFAULT_RATE);
        location = new Location(DEFAULT_LOCATION);
        studentNameList = new StudentNameList(DEFAULT_NAME_LIST);
    }

    /**
     * Constructs a new TuitionClassBuilder with the same information as the classToCopy.
     *
     * @param classToCopy TuitionClass to copy the information from.
     */
    public TuitionClassBuilder(TuitionClass classToCopy) {
        className = classToCopy.getClassName();
        classTiming = classToCopy.getClassTiming();
        rate = classToCopy.getRate();
        location = classToCopy.getLocation();
        studentNameList = classToCopy.getStudentList();
    }

    /**
     * Changes the class name of the TuitionClassBuilder to the given name.
     *
     * @param className Name to be changed to.
     * @return TuitionClassBuilder with the new name.
     */
    public TuitionClassBuilder withClassName(String className) {
        this.className = new ClassName(className);
        return this;
    }

    /**
     * Changes the class timing of the TuitionClassBuilder to the given class timing.
     *
     * @param classTiming Class timing to be changed to.
     * @return TuitionClassBuilder with the new class timing.
     */
    public TuitionClassBuilder withClassTiming(String classTiming) {
        this.classTiming = new ClassTiming(classTiming);
        return this;
    }

    /**
     * Changes the rate of the TuitionClassBuilder to the given rate.
     *
     * @param rate Rate to be changed to.
     * @return TuitionClassBuilder with the new rate.
     */
    public TuitionClassBuilder withRate(String rate) {
        this.rate = new Rate(rate);
        return this;
    }

    /**
     * Changes the location of the TuitionClassBuilder to the given location.
     *
     * @param location Location to be changed to.
     * @return TuitionClassBuilder with the new location.
     */
    public TuitionClassBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Changes the student list of the TuitionClassBuilder to the given student list.
     *
     * @param studentList Student list to be changed to.
     * @return TuitionClassBuilder with the new student list.
     */
    public TuitionClassBuilder withStudentList(String... studentList) {
        this.studentNameList = new StudentNameList(studentList);
        return this;
    }

    /**
     * Builds a TuitionClass with the information of the TuitionClassBuilder object.
     *
     * @return TuitionClass with the information of the TuitionClassBuilder.
     */
    public TuitionClass build() {
        return new TuitionClass(className, classTiming, location, rate, studentNameList);
    }
}
