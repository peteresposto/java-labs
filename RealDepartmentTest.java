package ca.uwo.eng.se2205b.lab2.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Test the {@link Department} implementation.
 */
public class RealDepartmentTest {

    @Test
    public void testSetName() {
        RealDepartment department1 = new RealDepartment("Engineering");
        department1.setName("Science");
        assertEquals("Science", department1.getName());
    }

    @Test
    public void testSetNullName() {
        RealDepartment department1 = new RealDepartment("Engineering");

        try {
            department1.setName(null);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Argument for @Nonnull parameter 'name' of ca/uwo/eng/se2205b/lab2/model/RealDepartment.setName must not be null");
        }
    }

    @Test
    public void testEnrollStudent() {
        RealDepartment department1 = new RealDepartment("Engineering");
        RealStudent student2 = new RealStudent("Jeff", "Skilling", null, 251);

        department1.enrollStudent(student2);
        assertTrue(department1.getEnrolledStudents().contains(student2));
        assertTrue(student2.getDepartment().equals(department1));
    }

    @Test
    public void testEnrollNullStudent() {
        RealDepartment department1 = new RealDepartment("Engineering");

        try {
            department1.enrollStudent(null);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Argument for @Nonnull parameter 'student' of ca/uwo/eng/se2205b/lab2/model/RealDepartment.enrollStudent must not be null");
        }
    }

    @Test
    public void testRemoveStudent() {
        RealDepartment department1 = new RealDepartment("Engineering");
        RealStudent student2 = new RealStudent("Jeff", "Skilling", null, 251);

        department1.enrollStudent(student2);
        department1.removeStudent(student2);

        assertFalse(department1.getEnrolledStudents().contains(student2));
        assertNull(student2.getDepartment());
    }

    @Test
    public void testRemoveNullStudent() {
        RealDepartment department1 = new RealDepartment("Engineering");

        try {
            department1.removeStudent(null);
        } catch (IllegalArgumentException e){
            assertEquals(e.getMessage(), "Argument for @Nonnull parameter 'student' of ca/uwo/eng/se2205b/lab2/model/RealDepartment.removeStudent must not be null");
        }
    }

    @Test
    public void testRemoveStudentFromEmptyList() {
        RealDepartment department1 = new RealDepartment("Engineering");
        RealStudent student2 = new RealStudent("Jeff", "Skilling", null, 251);

        department1.removeStudent(student2);
    }

    @Test
    public void testAddCourse() {
        RealDepartment department1 = new RealDepartment("Engineering");
        RealCourse course1 = new RealCourse("ES 1050", "1050", null, 5);

        department1.addCourse(course1);
        assertTrue(department1.getCourses().contains(course1));
        assertTrue(course1.getDepartment().equals(department1));
    }

    @Test
    public void testAddNullCourse() {
        RealDepartment department1 = new RealDepartment("Engineering");

        try {
            department1.addCourse(null);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Argument for @Nonnull parameter 'course' of ca/uwo/eng/se2205b/lab2/model/RealDepartment.addCourse must not be null");
        }
    }

    @Test
    public void testRemoveCourse() {
        RealDepartment department1 = new RealDepartment("Engineering");
        RealCourse course1 = new RealCourse("ES 1050", "1050", null, 5);

        department1.addCourse(course1);
        department1.removeCourse(course1);

        assertFalse(department1.getCourses().contains(course1));
        assertNull(course1.getDepartment());
    }

    @Test
    public void testRemoveNullCourse() {
        RealDepartment department1 = new RealDepartment("Engineering");
        try {
            department1.removeCourse(null);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Argument for @Nonnull parameter 'course' of ca/uwo/eng/se2205b/lab2/model/RealDepartment.removeCourse must not be null");
        }
    }

    @Test
    public void testRemoveCourseFromEmptyList() {
        RealDepartment department1 = new RealDepartment("Engineering");
        RealCourse course1 = new RealCourse("ES 1050", "1050", null, 5);

        department1.removeCourse(course1);
    }

    @Test
    public void testEqualityOnSelf() {
        RealDepartment department1 = new RealDepartment("Engineering");

        assertTrue(department1.equals(department1));
    }

    @Test
    public void testEqualityOnSomethingElse() {
        RealDepartment department1 = new RealDepartment("Engineering");
        RealDepartment department2 = new RealDepartment("Social Science");

        assertFalse(department1.equals(department2));
    }

    @Test
    public void testEqualityOnNull() {
        RealDepartment department1 = new RealDepartment("Engineering");

        assertFalse(department1.equals(null));
    }

    @Test
    public void testDepartmentToString() {
        RealDepartment department1 = new RealDepartment("Engineering");

        assertEquals("RealDepartment{name=Engineering, studentList=[], courseList=[]}", department1.toString());
    }
}