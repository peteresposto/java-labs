package ca.uwo.eng.se2205b.lab2.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the {@link Course} implementation
 */
public class RealCourseTest {

    @Test
    public void testSetName(){
        RealCourse course1 = new RealCourse("ES 1050", "1050", null, 5);
        course1.setName("ES 1022Y");

        assertEquals("ES 1022Y", course1.getName());
    }

    @Test
    public void testSetNullName() {
        RealCourse course1 = new RealCourse("ES 1050", "1050", null, 5);
        try{
            course1.setName(null);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Argument for @Nonnull parameter 'name' of ca/uwo/eng/se2205b/lab2/model/RealCourse.setName must not be null");
        }
    }

    @Test
    public void testEnrollStudent() {
        RealCourse course1 = new RealCourse("ES 1050", "1050", null, 5);
        RealStudent student1 = new RealStudent("Jeff", "Skilling", null, 250);

        course1.enrollStudent(student1);
        assertTrue(course1.getEnrolledStudents().contains(student1));
        assertTrue(student1.getCourses().contains(course1));
    }

    @Test
    public void testEnrollStudentInFullCourse() {
        RealCourse ES1050 = new RealCourse("ES 1050", "1050", null, 2);

        RealStudent student1 = new RealStudent("Peter", "Esposto", null, 250);
        RealStudent student2 = new RealStudent("Jeff", "Skilling", null, 251);
        RealStudent student3 = new RealStudent("Kenneth", "Lay", null, 252);

        ES1050.enrollStudent(student1);
        ES1050.enrollStudent(student2);
        try {
            ES1050.enrollStudent(student3);
        } catch (CourseMaxCapacityStoreException e){
            assertEquals(e.getMessage(), "ES 1050 can not store Kenneth Lay, maximum capacity reached.");
        }
    }

    @Test
    public void testEnrollNullStudent() {
        RealCourse ES1050 = new RealCourse("ES 1050", "1050", null, 2);

        try {
            ES1050.enrollStudent(null);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Argument for @Nonnull parameter 'student' of ca/uwo/eng/se2205b/lab2/model/RealCourse.enrollStudent must not be null");
        }
    }

    @Test
    public void removeStudent() {
        RealCourse ES1050 = new RealCourse("ES 1050", "1050", null, 2);
        RealStudent student1 = new RealStudent("Peter", "Esposto", null, 250);

        ES1050.enrollStudent(student1);
        ES1050.removeStudent(student1);

        assertFalse(ES1050.getEnrolledStudents().contains(student1));
        assertFalse(student1.getCourses().contains(ES1050));
    }

    @Test
    public void removeNullStudent() {
        RealCourse ES1050 = new RealCourse("ES 1050", "1050", null, 2);

        try {
            ES1050.removeStudent(null);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Argument for @Nonnull parameter 'student' of ca/uwo/eng/se2205b/lab2/model/RealCourse.removeStudent must not be null");
        }
    }

    @Test
    public void removeStudentInEmptyCourse() {
        RealStudent student1 = new RealStudent("Peter", "Esposto", null, 250);
        RealCourse ES1050 = new RealCourse("ES 1050", "1050", null, 2);

        ES1050.removeStudent(student1);
        assertFalse(ES1050.getEnrolledStudents().contains(student1));
    }

    @Test
    public void testDepartmentContains() {
        RealDepartment Engineering = new RealDepartment("Engineering");
        RealCourse course1 = new RealCourse("ES 1050", "1050", Engineering, 5);

        Engineering.addCourse(course1);
        assertTrue(Engineering.getCourses().contains(course1));
        assertTrue(course1.getDepartment().equals(Engineering));
    }

    @Test
    public void testSetDepartment() {
        RealDepartment Engineering = new RealDepartment("Engineering");
        RealCourse course1 = new RealCourse("ES 1050", "1050", null, 5);
        course1.setDepartment(Engineering);

        assertTrue(Engineering.getCourses().contains(course1));
    }

    @Test
    public void testSetDepartmentToNull() {
        RealDepartment Engineering = new RealDepartment("Engineering");
        RealCourse course1 = new RealCourse("ES 1050", "1050", Engineering, 5);

        course1.setDepartment(null);

        assertFalse(Engineering.getCourses().contains(course1));
        assertNull(course1.getDepartment());
    }

    @Test
    public void testSetCourseID() {
        RealCourse course1 = new RealCourse("ES 1050", "1050", null, 5);

        course1.setCourseID("22");
        assertEquals("22", course1.getCourseCode());
    }

    @Test
    public void testEqualityOnSelf() {
        RealCourse course1 = new RealCourse("ES 1050", "1050", null, 5);

        assertTrue(course1.equals(course1));
    }

    @Test
    public void testEqualityOnSomethingElse() {
        RealCourse course1 = new RealCourse("ES 1050", "1050", null, 5);
        RealCourse course2 = new RealCourse("SE 2203", "2203", null, 10);

        assertFalse(course1.equals(course2));
    }

    @Test
    public void testEqualityOnNull() {
        RealCourse course1 = new RealCourse("ES 1050", "1050", null, 5);

        assertFalse(course1.equals(null));
    }

    @Test
    public void testCourseToString() {
        RealCourse course1 = new RealCourse("ES 1050", "1050", null, 5);

        assertEquals("RealCourse{name=ES 1050, id=1050, department=null, maxStudents=5, listOfStudents=[]}", course1.toString());
    }
}