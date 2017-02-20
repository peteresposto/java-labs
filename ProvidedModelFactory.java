package ca.uwo.eng.se2205b.lab2.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Test fixture used to create a test model for test cases.
 */
public class ProvidedModelFactory {

    /**
     * Creates the model shown in
     * <a href="https://uwoece-se2205b-2017.github.io/labs/02-oop-serialization#question-0">Q1 Deliverable</a>.
     *
     * @return List of Department values
     */

    public static List<Department> createModel() {
        // see Deliverable for Q1

        RealDepartment CEE = new RealDepartment("CEE");
        RealDepartment ECE = new RealDepartment("ECE");
        RealDepartment AM = new RealDepartment("AM");

        RealCourse AM1413 = new RealCourse("Calculus I" , "AM 1413", AM, 10);
        RealCourse ES1022 = new RealCourse("Statics" , "ES 1022Y", CEE, 10);
        RealCourse ES1036 = new RealCourse("Programming I" , "ES 1036", ECE, 10);
        RealCourse SE2205 = new RealCourse("Algorithms and Data Structures" , "SE 2205", ECE, 10);

        RealStudent johnSmith = new RealStudent("John", "Smith", CEE, 1111);
        RealStudent sarahMcLachlan = new RealStudent("Sarah", "McLachlan", ECE, 2222);
        RealStudent geneWilder = new RealStudent("Gene", "Wilder", ECE, 3333);
        RealStudent ronWeasley = new RealStudent("Ron", "Weasley", ECE, 4444);
        RealStudent minhPham = new RealStudent("Minh", "Pham", ECE, 5555);
        RealStudent georgeTakei = new RealStudent("George", "Takei", AM, 6666);
        RealStudent ralphNader = new RealStudent("Ralph", "Nader", AM, 7777);
        RealStudent janeTarzan = new RealStudent("Jane", "Tarzan", ECE, 8888);

        johnSmith.enrollInCourse(AM1413);
        johnSmith.enrollInCourse(ES1022);

        sarahMcLachlan.enrollInCourse(AM1413);
        sarahMcLachlan.enrollInCourse(ES1036);
        sarahMcLachlan.enrollInCourse(SE2205);

        geneWilder.enrollInCourse(AM1413);
        geneWilder.enrollInCourse(SE2205);

        ronWeasley.enrollInCourse(ES1022);
        ronWeasley.enrollInCourse(SE2205);

        minhPham.enrollInCourse(AM1413);
        minhPham.enrollInCourse(ES1022);

        georgeTakei.enrollInCourse(AM1413);
        georgeTakei.enrollInCourse(SE2205);

        ralphNader.enrollInCourse(AM1413);
        ralphNader.enrollInCourse(ES1022);
        ralphNader.enrollInCourse(ES1036);
        ralphNader.enrollInCourse(SE2205);

        ArrayList<Department> departmentList = new ArrayList<Department>();
        departmentList.add(CEE);
        departmentList.add(ECE);
        departmentList.add(AM);

        return departmentList;
    }

    @Test
    public void testDepartmentPresence() {
        List<Department> modelList = createModel();
        RealDepartment ECE = new RealDepartment("ECE");

        assertTrue(modelList.contains(ECE));
    }

    @Test
    public void testCoursePresence() {
        List<Department> modelList = createModel();
        RealDepartment AM = new RealDepartment("AM");
        RealCourse AM1413 = new RealCourse("Calculus I" , "AM 1413", AM, 10);

        assertEquals("Calculus I", modelList.get(2).getCourses().get(0).getName());
    }

    @Test
    public void testStudentPresence() {
        List<Department> modelList = createModel();

        assertEquals("John", modelList.get(0).getEnrolledStudents().get(0).getFirstName());
    }

    @Test
    public void testRemovingACourse() {
        List<Department> modelList = createModel();

        modelList.get(0).getEnrolledStudents().get(0).dropACourse(modelList.get(0).getCourses().get(0));
        assertFalse(modelList.get(0).getEnrolledStudents().get(0).getCourses().contains(createModel().get(0).getCourses().get(0)));

        modelList.get(0).getEnrolledStudents().get(0).enrollInCourse(modelList.get(0).getCourses().get(0));
        assertTrue(modelList.get(0).getEnrolledStudents().get(0).getCourses().contains(createModel().get(0).getCourses().get(0)));
    }

    @Test
    public void testRemovingAStudent() {
        List<Department> modelList = createModel();

        modelList.get(0).getCourses().get(0).removeStudent(modelList.get(0).getEnrolledStudents().get(0));
        assertFalse(modelList.get(0).getCourses().get(0).getEnrolledStudents().contains(createModel().get(0).getEnrolledStudents().get(0)));

        modelList.get(0).getCourses().get(0).enrollStudent(modelList.get(0).getEnrolledStudents().get(0));
        assertTrue(modelList.get(0).getCourses().get(0).getEnrolledStudents().contains(createModel().get(0).getEnrolledStudents().get(0)));
    }

    @Test
    public void testCourseMaxCapacity() {
        RealCourse SE2250 = new RealCourse("Software Construction", "2250", null, 0);
        RealStudent student2 = new RealStudent("Jeff", "Skilling", null, 251);

        try {
            student2.enrollInCourse(SE2250);
        } catch (CourseMaxCapacityStoreException e) {
            assertEquals(e.getMessage(), "Software Construction can not store Jeff Skilling, maximum capacity reached.");
        }
    }

    @Test
    public void testRemovingAStudentFromADepartment(){
        List<Department> model = createModel();

        model.get(0).removeStudent(model.get(0).getEnrolledStudents().get(0));
        assertFalse(model.get(0).getEnrolledStudents().contains(createModel().get(0).getEnrolledStudents().get(0)));
    }
}