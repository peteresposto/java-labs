package ca.uwo.eng.se2205b.lab2.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Peter on 2/5/2017.
 */
public class RealDepartment implements Department{

    private String name;
    private ArrayList<Student> studentList;
    private ArrayList<Course> courseList;

    /**
     * Constructor for the RealDepartment class
     * @param name the name of the department
     */
    RealDepartment(String name){
        setName(name);
        studentList = new ArrayList<Student>();
        courseList = new ArrayList<Course>();
    }

    /**
     * Get the name of the student
     * @return the department's name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @param name Non-{@code null} name
     *
     */
    @Override
    public void setName(@Nonnull String name) {
        this.name = name;
    }

    /**
     *
     * @param student Non-{@code null} student to add.
     */
    @Override
    public void enrollStudent(@Nonnull Student student) {
        if (!studentList.contains(student)) {
            studentList.add(student);
            student.setDepartment(this);
        }
    }

    /**
     *
     * @param student Removed student
     * @return the student that was removed
     */
    @Override
    public Student removeStudent(@Nonnull Student student) {
        if (studentList.contains(student)) {
            studentList.remove(student);
            student.setDepartment(null);
        }
        return student;
    }

    /**
     *
     * @return a list of students enrolled in the department
     */
    @Override
    public List<Student> getEnrolledStudents() {
        return studentList;
    }

    /**
     *
     * @param course Course to add
     */
    @Override
    public void addCourse(@Nonnull Course course) {
        if (!courseList.contains(course)){
            courseList.add(course);
            course.setDepartment(this);
        }
    }

    /**
     *
     * @param course Course to remove
     * @return the course being removed
     */
    @Override
    public Course removeCourse(@Nonnull Course course) {
        if (courseList.contains(course)) {
            courseList.remove(course);
            course.setDepartment(null);
        }
        return course;
    }

    /**
     *
     * @return a list of courses in the department
     */
    @Override
    public List<Course> getCourses() {
        return courseList;
    }

    /**
     *
     * @param o the object being compared with for equality
     * @return whether or not the object is equal to o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealDepartment that = (RealDepartment) o;
        return Objects.equal(name, that.name);
    }

    /**
     *
     * @return a string containing information about the department
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("studentList", studentList.stream().map(Student::getID).collect(Collectors.toList()))
                .add("courseList", courseList.stream().map(Course::getCourseCode).collect(Collectors.toList()))
                .toString();
    }
}
