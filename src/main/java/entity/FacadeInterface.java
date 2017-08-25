/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;

/**
 *
 * @author KnaldeKalle
 */
public interface FacadeInterface {
    
    public List<Student> findAllStudents();
    
    public List<Student> findAllStudentsWithFirstName(String studentFirstname);
    
    public void createStudent(Student s);
    
    public void assignStudentToSemester(Student s, Semester sm);
   
    public List<Student> fintAllStudentsWithLastName(String studentLasttname);
     
    public int findStudentCount();
    
    public int findStudentCountFromSemester(Semester sm);
    
    public Teacher teacherWithMostSemesters();
   
    
    
}
