package ca.uwo.eng.se2205b.lab2.model;

/**
 * Created by Peter on 2/4/2017.
 */
public class CourseMaxCapacityStoreException extends RuntimeException{
    private Student student;
    private Course course;

    /**
     * Returns the student
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Returns the course
     * @return the course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * An exception thrown when a course is at full capacity
     * @param course the course that is at full capacity
     * @param student the student trying to be added
     */
    public CourseMaxCapacityStoreException(Course course, Student student) {
        super(course.getName() + " can not store " + student.getFirstName() + " " + student.getLastName() + ", maximum capacity reached.");
        this.course = course;
        this.student = student;
    }
}
