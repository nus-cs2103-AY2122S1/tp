package tutoraid.testutil;

import tutoraid.model.student.ParentName;
import tutoraid.model.student.PaymentStatus;
import tutoraid.model.student.Phone;
import tutoraid.model.student.Progress;
import tutoraid.model.student.Student;
import tutoraid.model.student.StudentName;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {
    public static final String DEFAULT_STUDENT_NAME = "Amy Bee";
    public static final String DEFAULT_STUDENT_PHONE = "96355255";
    public static final String DEFAULT_PARENT_NAME = "Mr Bee";
    public static final String DEFAULT_PARENT_PHONE = "85355255";
    public static final String DEFAULT_PROGRESS = "No Progress";
    public static final boolean DEFAULT_PAYMENT_STATUS = false;

    private StudentName studentName;
    private Phone studentPhone;
    private ParentName parentName;
    private Phone parentPhone;
    private Progress progress;
    private PaymentStatus paymentStatus;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        studentName = new StudentName(DEFAULT_STUDENT_NAME);
        studentPhone = new Phone(DEFAULT_STUDENT_PHONE);
        parentName = new ParentName(DEFAULT_PARENT_NAME);
        parentPhone = new Phone(DEFAULT_PARENT_PHONE);
        progress = new Progress(DEFAULT_PROGRESS);
        paymentStatus = new PaymentStatus(DEFAULT_PAYMENT_STATUS);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        studentName = studentToCopy.getStudentName();
        studentPhone = studentToCopy.getStudentPhone();
        parentName = studentToCopy.getParentName();
        parentPhone = studentToCopy.getParentPhone();
        progress = studentToCopy.getProgress();
        paymentStatus = studentToCopy.getPaymentStatus();
    }

    /**
     * Sets the {@code StudentName} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudentName(String name) {
        this.studentName = new StudentName(name);
        return this;
    }

    /**
     * Sets the {@code ParentName} of the {@code Student} that we are building.
     */
    public StudentBuilder withParentName(String name) {
        this.parentName = new ParentName(name);
        return this;
    }

    /**
     * Sets the {@code StudentPhone} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudentPhone(String phone) {
        this.studentPhone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code ParentPhone} of the {@code Student} that we are building.
     */
    public StudentBuilder withParentPhone(String phone) {
        this.parentPhone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Progress} of the {@code Student} that we are building.
     */
    public StudentBuilder withProgress(String progress) {
        this.progress = new Progress(progress);
        return this;
    }

    /**
     * Sets the {@code Payment Statis} of the {@code Student} that we are building.
     */
    public StudentBuilder withPaymentStatus(boolean hasPaid) {
        this.paymentStatus = new PaymentStatus(hasPaid);
        return this;
    }

    public Student build() {
        return new Student(studentName, studentPhone, parentName, parentPhone, progress, paymentStatus);
    }
}
