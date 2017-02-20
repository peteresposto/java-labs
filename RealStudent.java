package ca.uwo.eng.se2205b.lab2.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Peter on 2/2/2017.
 */
public class RealStudent implements Student {

    private String firstName;
    private String lastName;
    private Department department;
    private long id;

    private ArrayList<Course> courses;

    /**
     *
     * @param firstName the student's first name
     * @param lastName the student's last name
     * @param department the department the student belongs to
     * @param id the id number of the student
     */
    public RealStudent(String firstName, String lastName, Department department, long id) {
        setFirstName(firstName);
        setLastName(lastName);
        setDepartment(department);
        setID(id);

        courses = new ArrayList<Course>();
    }

    /**
     * Set the student's first name
     * @param firstName the student's first name
     */
    @Override
    public void setFirstName(@Nonnull String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the student's first name
     * @return the student's first name
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the student's last name
     * @param lastName the student's last name
     */
    @Override
    public void setLastName(@Nonnull String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the student's last name
     * @return the student's last name
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * Enroll the student in a course
     * @param courseName the course the student is enrolling in
     */
    @Override
    public void enrollInCourse(@Nonnull Course courseName) {
        if (!courses.contains(courseName)){
            courses.add(courseName);
            courseName.enrollStudent(this);
        }
        else if (courses.contains(courseName))
            return;
    }

    /**
     * Drop one of the student's courses (remove it from a course)
     * @param courseName the course being dropped
     * @return the course being dropped
     */
    @Override
    public String dropACourse(@Nonnull Course courseName) {
        if (!courses.contains(courseName))
            return courseName.getName();

        courses.remove(courseName);
        courseName.removeStudent(this);

        return courseName.getName();
    }

    /**
     * Set which department the student belongs to
     * @param department the department being set to
     */
    @Override
    public void setDepartment(@Nullable Department department) {
        if (department == null)
            this.department = null;
        else {
            this.department = department;
            department.enrollStudent(this);
        }
    }

    /**
     * Get the student's department
     * @return the student's department
     */
    @Override
    public Department getDepartment() {
        return department;
    }

    /**
     * Set the student's ID number
     * @param id the ID number being set to
     */
    @Override
    public void setID(@Nonnegative long id) {
        this.id = id;
    }

    /**
     * Get the student's ID number
     * @return the student's ID number
     */
    @Override
    public long getID() {
        return id;
    }

    /**
     * Get a list of the courses the student is enrolled in
     * @return a list of courses the student is enrolled in
     */
    @Override
    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    /**
     *
     * @param o the object being compared to for equality
     * @return whether or not the equality is true
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealStudent that = (RealStudent) o;
        return id == that.id;
    }

    /**
     * Convert some of the student's information into a string
     * @return a string containing the student's information
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("firstName", this.getFirstName())
                .add("lastName", this.getLastName())
                .add("department", (Objects.equal(department, null)) ? null : department.getName())
                .add("id", id)
                .add("courses", courses.stream().map(Course::getCourseCode).collect(Collectors.toList()))
                .toString();
    }
}
