package seedu.address.model.tuitionclass;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;

import seedu.address.model.person.Name;

/**
 * Represents a tuition TuitionClass in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class TuitionClass {

    private final ClassTiming classTiming;

    private final ClassName className;
    private final Location location;
    private final Rate rate;

    /**
     * ArrayList of {@code Name}
     * Rationale for choosing Name as identifier:
     * If the {@code Student} objects are stored, any changes to a student would cause a cascade of updates in classes
     * the student is in.
     */
    private final UniqueNameList uniqueNameList;

    /**
     * Represents a tuition class for Students to join. A {@code TuitionClass} can have multiple {@code Student}s.
     * A class is uniquely identified by its timing; a single timing can only have _one_ class.
     * <p>
     * A {@code Student} can have multiple {@code TuitionClass}es as well.
     *
     * @param className The name of the class to be created.
     * @param classTiming The timing of the class specified. This is the unique identifier (id) of the class.
     * @param location The location of the class.
     * @param rate How much it costs per hour to attend the class.
     */
    public TuitionClass(ClassName className, ClassTiming classTiming, Location location, Rate rate,
                        UniqueNameList uniqueNameList) {
        requireAllNonNull(className, classTiming, location, rate);
        this.className = className;
        this.classTiming = classTiming;
        this.location = location;
        this.rate = rate;
        this.uniqueNameList = uniqueNameList;
    }

    /**
     * Represents a way to create a TuitionClass with only it's classTiming.
     * <p>
     * A {@code Student} can have multiple {@code TuitionClass}es as well.
     *
     * @param classTiming The timing of the class specified. This is the unique identifier (id) of the class.
     */
    public TuitionClass(ClassName className, ClassTiming classTiming) {
        requireAllNonNull(className, classTiming);
        this.classTiming = classTiming;
        this.className = className;
        this.location = new Location("Placeholder");
        this.rate = new Rate("0");
        this.uniqueNameList = new UniqueNameList();
    }

    /**
     *  Represents a tuition class for Students to join. A {@code TuitionClass} can have multiple {@code Student}s.
     * A class is uniquely identified by its timing; a single timing can only have _one_ class. Without the
     * UniqueNameList.
     * <p>
     * A {@code Student} can have multiple {@code TuitionClass}es as well.
     *
     * @param className The name of the class to be created.
     * @param classTiming The timing of the class specified. This is the unique identifier (id) of the class.
     * @param location The location of the class.
     * @param rate How much it costs per hour to attend the class.
     */
    public TuitionClass(ClassName className, ClassTiming classTiming, Location location, Rate rate) {
        requireAllNonNull(className, classTiming, location, rate);
        this.className = className;
        this.classTiming = classTiming;
        this.location = location;
        this.rate = rate;
        this.uniqueNameList = new UniqueNameList();
    }

    public ClassName getClassName() {
        return className;
    }

    public String getClassNameString() {
        return className.toString();
    }

    public ClassTiming getClassTiming() {
        return classTiming;
    }


    public LocalTime getStartTiming() {
        return classTiming.getStartTime();
    }

    public LocalTime getEndTiming() {
        return classTiming.getEndTime();
    }

    public Location getLocation() {
        return location;
    }

    public Rate getRate() {
        return rate;
    }

    /**
     * Returns whether the tuition class contains the Student.
     *
     * @return whether the tuition class contains the Student or not.
     */
    public boolean containsStudent(Name name) {
        return uniqueNameList.contains(name);
    }

    public UniqueNameList getStudentList() {
        return uniqueNameList;
    }

    //todo fix this
    //public List<Name> getStudentList() {
    //        return Collections.unmodifiableList(uniqueStudentList);
    //}

    public void addStudent(Name name) {
        uniqueNameList.add(name);
    }

    /**
     * Removes student name from the name list.
     *
     * @param name to be removed.
     * @return this after name has been removed.
     */
    public TuitionClass removeStudent(Name name) {
        if (uniqueNameList.contains(name)) {
            uniqueNameList.remove(name);
        }
        return this;
    }

    public void addStudentList(UniqueNameList uniqueNameList) {
        this.uniqueNameList.addAll(uniqueNameList);
    }

    /**
     * Checks if the TuitionClass is at this timing.
     */
    public boolean isAtTiming(ClassTiming otherClassTiming) {
        return classTiming.equals(otherClassTiming);
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TuitionClass)) {
            return false;
        }

        TuitionClass o = (TuitionClass) other;
        /* A class is uniquely identified by its timing; a single timing can only have _one_ class */
        // TuitionClasses can now have null rates and location
        return o.classTiming.equals(getClassTiming());
    }

    /**
     * Returns true if the class timing of the class to be checked overlaps with this class.
     */
    public boolean isOverlapping(TuitionClass toCheck) {
        //return !(this.getClassTiming().isEarlier(toCheck.getClassTiming())
        //        || toCheck.getClassTiming().isEarlier(this.getClassTiming()));
        if (this.equals(toCheck)) {
            return true;
        } else if (this.getClassTiming().isSameDay(toCheck.getClassTiming()) //on the same day
                && ((toCheck.getStartTiming().compareTo(this.getEndTiming()) < 0
                && toCheck.getStartTiming().compareTo(this.getStartTiming()) >= 0) // && toCheck start time overlap
                // with this
                || (toCheck.getEndTiming().compareTo(this.getEndTiming()) <= 0
                && toCheck.getEndTiming().compareTo(this.getStartTiming()) > 0) //toCheck end time overlap with this
                || (toCheck.getStartTiming().compareTo(this.getStartTiming()) <= 0 //toCheck starts earlier than this
                && toCheck.getEndTiming().compareTo(this.getEndTiming()) >= 0))) { //toCheck ends later than this
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if 2 TuitionClass are the same using ClassTiming.
     *
     * @param otherClass to be checked with this.
     * @return boolean.
     */
    public boolean isSameClass(TuitionClass otherClass) {
        if (otherClass == this) {
            return true;
        }

        return otherClass != null
                && otherClass.getClassTiming().equals(getClassTiming());
    }

    public boolean isAfter(LocalTime time) {
        return this.classTiming.isAfter(time);
    }

    public LocalTime getStartTime() {
        return this.classTiming.getStartTime();
    }

    public LocalTime getEndTime() {
        return this.classTiming.getEndTime();
    }

    public int getDayToInt() {
        return this.classTiming.getDayToInt();
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Class Timing: ")
                .append(getClassTiming())
                .append(" ");
        if (!getClassTiming().equals(getClassName())) {
            builder.append(" Class Name: ")
                    .append(getClassName())
                    .append(" ");

        }
        builder.append("Location: ")
                .append(getLocation())
                .append(" ");
        builder.append("Rate: ")
                .append(getRate())
                .append(" ");

        return builder.toString();
    }
}
