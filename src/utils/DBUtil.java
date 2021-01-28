package utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.Employee;
import models.Report;

public class DBUtil {
    private static final String PERSISTENCE_UNIT_NAME = "daily_report_system";
    private static EntityManagerFactory emf;

    public static EntityManager createEntityManager() {




        return getEntityManagerFactory().createEntityManager();
    }

    private static EntityManagerFactory getEntityManagerFactory() {
        if(emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }

        return emf;
    }

    public static List<Employee> getEmployeeList(){
        EntityManager em = DBUtil.createEntityManager();
        return em.createQuery(" SELECT e FROM Employee e ",Employee.class).getResultList();
    }

    public static List<Report> getReportList(){
        EntityManager em = DBUtil.createEntityManager();
        return em.createQuery(" SELECT r FROM Report r ",Report.class).getResultList();
    }
}