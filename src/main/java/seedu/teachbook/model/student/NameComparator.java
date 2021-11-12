package seedu.teachbook.model.student;

import java.util.Comparator;

public class NameComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        String name1 = s1.getName().toString();
        String name2 = s2.getName().toString();
        return name1.compareTo(name2);
    }

}
