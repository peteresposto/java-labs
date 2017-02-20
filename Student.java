package ca.uwo.eng.se2205b.lab2.model;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Peter on 2/2/2017.
 */
public interface Student {

    /**
     * Set the student's first name
     * @param firstName the student's first name
     */
    public void setFirstName(@Nonnull String firstName);

    /**
     * Get the student's first name
     * @return the student's first name
     */
    public String getFirstName();

    /**
     * Set the student's last name
     * @param lastName the student's last name
     */
    public void setLastName(@Nonnull String lastName);

    /**
     * Get the student's last name
     * @return the student's last name
     */
    public String getLastName();

    /**
     * Enroll the student in a course
     * @param courseName the name of the course to enroll in
     */
    public void enrollInCourse(@Nonnull Course courseName);

    /**
     * Remove a student from a course
     * @param courseName The name of the course to drop
     * @return the dropped course
     */
    public String dropACourse(@Nonnull Course courseName);

    /**
     * @param department
     */
    public void setDepartment(@Nullable Department department);

    /**
     * @return
     */
    public Department getDepartment();

    /**
     * @param id
     */
    public void setID(@Nonnegative long id);

    /**
     * @return
     */
    public long getID();

    /**
     * @return
     */
    public List<Course> getCourses();

    /**
     * @return
     */
    public boolean equals(Object o);
}

