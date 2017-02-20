package ca.uwo.eng.se2205b.lab2.model;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.List;

/**
 * Created by Peter on 2/4/2017.
 */

//Make default constructor

public class RealStudentTest {

    @Test
    public void testSetFirstName() {
        RealStudent theStudent = new RealStudent("Jeff", "Skilling", null, 250845021);

        theStudent.setFirstName("Kenneth");
        assertEquals("Kenneth", theStudent.getFirstName());
    }

    @Test
    public void testSetFirstNameNull() {
        RealStudent theStudent = new RealStudent("Jeff", "Skilling", null, 250845021);

        try {
            theStudent.setFirstName(null);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Argument for @Nonnull parameter 'firstName' of ca/uwo/eng/se2205b/lab2/model/RealStudent.setFirstName must not be null");
        }
    }

    @Test
    public void testSetLastName() {
        RealStudent theStudent = new RealStudent("Jeff", "Skilling", null, 250845021);

        theStudent.setLastName("Lay");
        assertEquals("Lay", theStudent.getLastName());
    }

    @Test
    public void testSetLastNameNull() {
        RealStudent theStudent = new RealStudent("Jeff", "Skilling", null, 250845021);

        try {
            theStudent.setLastName(null);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Argument for @Nonnull parameter 'lastName' of ca/uwo/eng/se2205b/lab2/model/RealStudent.setLastName must not be null");
        }
    }

    @Test
    public void testSetDepartment() {
        RealStudent theStudent = new RealStudent("Jeff", "Skilling", null, 250845021);
        RealDepartment Engineering = new RealDepartment("Engineering");

        theStudent.setDepartment(Engineering);
        assertEquals(Engineering, theStudent.getDepartment());
    }

    @Test
    public void testSetNullDepartment() {
        RealStudent theStudent = new RealStudent("Jeff", "Skilling", null, 250845021);

        theStudent.setDepartment(null);
        assertEquals(null, theStudent.getDepartment());
    }

    @Test
    public void testSetID() {
        RealStudent theStudent = new RealStudent("Jeff", "Skilling", null, 250845021);
        theStudent.setID(850);

        assertEquals(850, theStudent.getID());
    }

    @Test
    public void testSetIDNotGreaterThanZero() {
        RealStudent theStudent = new RealStudent("Jeff", "Skilling", null, 250845021);

        try {
            theStudent.setID(0);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Argument for @Nonnegative parameter 'id' of ca/uwo/eng/se2205b/lab2/model/RealStudent.setID must not be negative");
        }
    }

    @Test
    public void testEnrollInCourse() {
        RealStudent student1 = new RealStudent("Peter", "Esposto", null, 250);
        RealCourse ES1050 = new RealCourse("ES 1050", "1050", null, 2);

        student1.enrollInCourse(ES1050);

        assertTrue(student1.getCourses().contains(ES1050));
        assertTrue(ES1050.getEnrolledStudents().contains(student1));
    }

    @Test
    public void testEnrollInNullCourse() {
        RealStudent student1 = new RealStudent("Peter", "Esposto", null, 250);

        try {
            student1.enrollInCourse(null);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Argument for @Nonnull parameter 'courseName' of ca/uwo/eng/se2205b/lab2/model/RealStudent.enrollInCourse must not be null");
        }
    }

    @Test
    public void testMaxCapacity() {
        RealCourse ES1050 = new RealCourse("ES 1050", "1050", null, 2);

        RealStudent student1 = new RealStudent("Peter", "Esposto", null, 250);
        RealStudent student2 = new RealStudent("Jeff", "Skilling", null, 251);
        RealStudent student3 = new RealStudent("Kenneth", "Lay", null, 252);

        student1.enrollInCourse(ES1050);
        student2.enrollInCourse(ES1050);

        try {
            student3.enrollInCourse(ES1050);
        } catch (Exception e){
            assertEquals(e.getMessage(), "ES 1050 can not store Kenneth Lay, maximum capacity reached.");
        }
    }

    @Test
    public void testRemoveCourse(){
        RealStudent student1 = new RealStudent("Peter", "Esposto", null, 250);
        RealCourse ES1050 = new RealCourse("ES 1050", "1050", null, 2);

        student1.enrollInCourse(ES1050);
        student1.dropACourse(ES1050);
        assertFalse(student1.getCourses().contains(ES1050));
        assertFalse(ES1050.getEnrolledStudents().contains(student1));
    }

    @Test
    public void testRemoveANullCourse() {
        RealStudent student1 = new RealStudent("Peter", "Esposto", null, 250);

        try {
            student1.dropACourse(null);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Argument for @Nonnull parameter 'courseName' of ca/uwo/eng/se2205b/lab2/model/RealStudent.dropACourse must not be null");
        }
    }

    @Test
    public void testRemoveNonexistentCourse() {
        RealCourse ES1050 = new RealCourse("ES 1050", "1050", null, 2);
        RealStudent student1 = new RealStudent("Peter", "Esposto", null, 250);
        student1.dropACourse(ES1050);
    }

    @Test
    public void testRemoveDuplicateCourse() {
        RealStudent student1 = new RealStudent("Peter", "Esposto", null, 250);
        RealCourse ES1050 = new RealCourse("ES 1050", "1050", null, 2);

        student1.enrollInCourse(ES1050);
        student1.dropACourse(ES1050);
        student1.dropACourse(ES1050);

        assertFalse(student1.getCourses().contains(ES1050));
        assertFalse(ES1050.getEnrolledStudents().contains(student1));
    }

    @Test
    public void testEqualityOnSelf() {
        RealStudent student1 = new RealStudent("Peter", "Esposto", null, 250);

        assertTrue(student1.equals(student1));
    }

    @Test
    public void testEqualityOnSomethingElse() {
        RealStudent student1 = new RealStudent("Peter", "Esposto", null, 250);
        RealStudent student2 = new RealStudent("Jeff", "Skilling", null, 251);

        assertFalse(student1.equals(student2));
    }

    @Test
    public void testEqualityOnNull() {
        RealStudent student2 = new RealStudent("Jeff", "Skilling", null, 251);

        assertFalse(student2.equals(null));
    }

    @Test
    public void testStudentToString() {
        RealStudent student2 = new RealStudent("Jeff", "Skilling", null, 251);

        assertEquals("RealStudent{firstName=Jeff, lastName=Skilling, department=null, id=251, courses=[]}", student2.toString());
    }
}
