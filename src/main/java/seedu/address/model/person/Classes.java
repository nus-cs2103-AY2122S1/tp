package seedu.address.model.person;

import seedu.address.model.Tuition.TuitionClass;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

public class Classes {

    private ArrayList<TuitionClass> classes;

    public Classes(ArrayList<TuitionClass> classes) {
        requireNonNull(classes);
        this.classes = classes;
    }

    public int getNumofClass() {
        return classes.size();
    }

    public void updateClasses(ArrayList<TuitionClass> classes) {
        this.classes = classes;
    }

    public ArrayList<TuitionClass> getClasses() {
        return this.classes;
    }

    public ArrayList<TuitionClass> removeClasses(TuitionClass tuitionClass) {
        this.classes.remove(tuitionClass);
        return this.classes;
    }

    @Override
    public String toString() {
        return classes.toString();
    }

    @Override
    public int hashCode() {
        return classes.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Classes // instanceof handles nulls
                && equalClasses(((Classes) other).classes)); // state check
    }

    public boolean equalClasses(ArrayList<TuitionClass> cmpClasses) {
        if (cmpClasses.size() != this.classes.size())
            return false;
        for (int i = 0; i < classes.size(); i++) {
            if (!cmpClasses.get(i).equals(this.classes.get(i))) {
                return false;
            }
        }
        return true;
    }
}
