package ca.uwo.eng.se2205b.lab2.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Peter on 2/4/2017.
 */
public class RealCourse implements Course{
    String name;
    String id;
    Department department;
    int maxStudents;
    ArrayList<Student> listOfStudents;

    public RealCourse(String name, String id, Department department, int maxStudents) {
        setName(name);
        setCourseID(id);
        setDepartment(department);
        this.maxStudents = maxStudents;

        listOfStudents = new ArrayList<Student>();
    }

    /**
     *
     * @return
     */
    @Override
    public String getCourseCode() {
        return id;
    }

    @Override
    public void setCourseID(String id) {
        this.id = id;
    }

    /**
     *
     * @param name Name of the course
     *
     */
    @Override
    public void setName(@Nonnull String name) {
        this.name = name;
    }

    /**
     *
     * @return the name of the course
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @param newDepartment New department a course resides in.
     */
    @Override
    public void setDepartment(Department newDepartment) {
        if (newDepartment == null){
            if (!(department == null))
                department.removeCourse(this);
            department = null;
            return;
        }
        else {
            department = newDepartment;
            newDepartment.addCourse(this);
        }
    }

    /**
     *
     * @return the department
     */
    @Override
    public Department getDepartment() {
        return department;
    }

    /**
     *
     * @return the maximum occupancy of the course
     */
    @Override
    public int getMaximumOccupancy() {
        return maxStudents;
    }

    /**
     *
     * @param student Non-{@code null} student to add.
     *
     */
    @Override
    public void enrollStudent(@Nonnull Student student) {

        if (!listOfStudents.contains(student)) {
            if (listOfStudents.size() == maxStudents)
                throw new CourseMaxCapacityStoreException(this, student);

            listOfStudents.add(student);
            student.enrollInCourse(this);
        }

        else if (listOfStudents.contains(student))
            return;
    }

    /**
     *
     * @param student Removed student
     * @return the removed student
     */
    @Override
    public Student removeStudent(@Nonnull Student student) {
        if (!listOfStudents.contains(student))
            return student;

        listOfStudents.remove(student);
        student.dropACourse(this);

        return student;
    }

    /**
     *
     * @return a list of students enrolled in the course
     */
    @Override
    public List<Student> getEnrolledStudents() {
        return Collections.unmodifiableList(listOfStudents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealCourse that = (RealCourse) o;
        return Objects.equal(id, that.id);

    }

    /**
     *
     * @return a string containing information about the course
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("id", id)
                .add("department", (Objects.equal(department, null)) ? null: department.getName())
                .add("maxStudents", maxStudents)
                .add("listOfStudents", listOfStudents.stream().map(Student::getID).collect(Collectors.toList()))
                .toString();
    }
}
