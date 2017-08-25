
package entity;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class FacadeImpl implements FacadeInterface{
    
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_Exam-Preparation_JOQL_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

    @Override
    public List<Student> findAllStudents() {
        Query q = em.createNamedQuery("Student.findAll");
        List<Student> studentList = q.getResultList();
        return studentList;
        
    }

    @Override
    public List<Student> findAllStudentsWithFirstName(String studentFirstname) {
        Query q = em.createNamedQuery("Student.findByFirstname");
        q.setParameter("firstname", studentFirstname);
        List<Student> studentList = q.getResultList();
        return studentList;
    }

    @Override
    public void createStudent(Student s) {
       em.getTransaction().begin();
       em.persist(s);
       em.getTransaction().commit();
    }

    @Override
    public void assignStudentToSemester(Student s, Semester sm) {
       em.getTransaction().begin();
       s.setSemester(sm);
       Collection<Student> coll = sm.getStudentCollection();
       coll.add(s);
       sm.setStudentCollection(coll);
       em.getTransaction().commit();
    }

    @Override
    public List<Student> fintAllStudentsWithLastName(String studentLastname) {
        Query q = em.createNamedQuery("Student.findByLastname");
        q.setParameter("lastname", studentLastname);
        List<Student> studentList = q.getResultList();
        return studentList;
    }

    @Override
    public int findStudentCount() {
       
        Query q = em.createNamedQuery("Student.findAll");
        int count = q.getResultList().size();
        return count;
        
    }

    @Override
    public int findStudentCountFromSemester(Semester sm) {
       
        Query q = em.createNamedQuery("Student.bySemester");
        q.setParameter("semesterId", sm.getId());
        int count = q.getResultList().size();
        return count;
        
        
    }

    @Override
    public Teacher teacherWithMostSemesters() {
        
        Query q = em.createQuery("SELECT s.teacher, COUNT(s.teacher) as scount FROM Semester s GROUP BY s ORDER BY scount desc");
        q.setMaxResults(1);
        List<Object[]> result = q.getResultList();
        for(Object[] e : result){
           Teacher t = (Teacher) e[0]; 
           return t;
        }
        return null;
        
        
        
        
    }
        
        
        
        
    
}
