
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJB;

import Entity.GroupMaster;
import Entity.HrmsDepartment;
import Entity.Hrmsemployees;
import static java.lang.System.out;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author pratham
 */
@Stateless
public class AdminSessionBean implements AdminSessionBeanLocal {

    @PersistenceContext(unitName = "Hrms_PayaraPU")
    private EntityManager em;

    @Override
    public String addDepartment(HrmsDepartment dept) {
        try {
            em.persist(dept);
            return "Insert";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<HrmsDepartment> ViewDepartment() {
        try {
            List<HrmsDepartment> list = em.createNamedQuery("HrmsDepartment.findAll", HrmsDepartment.class).getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String deleteDepartment(int departmentid) {
        try {
            HrmsDepartment dep = em.find(HrmsDepartment.class, departmentid);
            em.remove(dep);
            return "deleted department";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public HrmsDepartment SearchDepartment(int departmentid) {
        try {
            HrmsDepartment dep = em.find(HrmsDepartment.class, departmentid);
            if (dep != null) {
                return dep;
            } else {
                throw new Exception("the department is not available");
            }
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public String editDepartment(HrmsDepartment dep) {
        try {
            HrmsDepartment Hrmsdep = em.find(HrmsDepartment.class, dep.getDepId());
            Hrmsdep = dep;
            em.merge(Hrmsdep);
            return "Updated department";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<Hrmsemployees> getManager() {
        try {
            List<Hrmsemployees> showListEmp = em.createNamedQuery("Hrmsemployees.findAll").getResultList();
            return showListEmp;
        } catch (Exception e) {
            return (List<Hrmsemployees>) e.getCause();
        }
    }

    @Override
    public List<Hrmsemployees> viewHR() {
        try {
            String query = "SELECT h FROM Hrmsemployees h WHERE h.depId.depId = :depId";
            List<Hrmsemployees> hrlist = em.createQuery(query, Hrmsemployees.class)
                    .setParameter("depId", 97) // Replace 46 with the department ID you're filtering
                    .getResultList();
            return hrlist;
        } catch (Exception e) {
            System.err.println("Error while fetching HR list:");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Hrmsemployees> viewIT() {
        try {
            String query = "SELECT h FROM Hrmsemployees h WHERE h.depId.depId = :depId";
            List<Hrmsemployees> hrlist = em.createQuery(query, Hrmsemployees.class)
                    .setParameter("depId", 96) // Replace 46 with the department ID you're filtering
                    .getResultList();
            return hrlist;
        } catch (Exception e) {
            System.err.println("Error while fetching HR list:");
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Hrmsemployees> viewMarketing() {
        try {
            String query = "SELECT h FROM Hrmsemployees h WHERE h.depId.depId = :depId";
            List<Hrmsemployees> hrlist = em.createQuery(query, Hrmsemployees.class)
                    .setParameter("depId", 101) // Replace 46 with the department ID you're filtering
                    .getResultList();
            return hrlist;
        } catch (Exception e) {
            System.err.println("Error while fetching HR list:");
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Hrmsemployees> viewFinance() {
        try {
            String query = "SELECT h FROM Hrmsemployees h WHERE h.depId.depId = :depId";
            List<Hrmsemployees> hrlist = em.createQuery(query, Hrmsemployees.class)
                    .setParameter("depId", 98) // Replace 46 with the department ID you're filtering
                    .getResultList();
            return hrlist;
        } catch (Exception e) {
            System.err.println("Error while fetching HR list:");
            e.printStackTrace();
            return null;
        }
    }

    //Employee
    @Override
    public String addEmployee(Hrmsemployees emp) {
        try {
            out.println("hua kya    ");
            em.persist(emp);
            out.println("ho gaga");
            return "Eployee registered";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<Hrmsemployees> showEmployee() {
        try {
            List<Hrmsemployees> showListEmp = em.createNamedQuery("Hrmsemployees.findAll").getResultList();
            return showListEmp;
        } catch (Exception e) {
            return (List<Hrmsemployees>) e.getCause();
        }
    }

    @Override
    public String deleteEmployee(int userid) {
        try {
            Hrmsemployees emp = em.find(Hrmsemployees.class, userid);
            em.remove(emp);
            return "Deleted Employee";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Hrmsemployees searchEmployee(int userid) {
        try {
            Hrmsemployees emp = em.find(Hrmsemployees.class, userid);
            if (emp != null) {
                return emp;
            } else {
                throw new Exception("The Employee Does not exist!");
            }
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public String editEmployee(Hrmsemployees emp) {
        try {
            Hrmsemployees existingEmp = em.find(Hrmsemployees.class, emp.getUserId());
            if (existingEmp != null) {
                // Update fields selectively to preserve unchanged data
                existingEmp.setEmpFirstname(emp.getEmpFirstname());
                existingEmp.setEmpLastname(emp.getEmpLastname());
                existingEmp.setEmpEmail(emp.getEmpEmail());
                existingEmp.setEmpPhone(emp.getEmpPhone());
                existingEmp.setEmpAddress(emp.getEmpAddress());
                existingEmp.setEmpGender(emp.getEmpGender());
                existingEmp.setHireDate(emp.getHireDate());
                existingEmp.setSalary(emp.getSalary());
                existingEmp.setManagerName(emp.getManagerName());
                existingEmp.setDepId(emp.getDepId());

                em.merge(existingEmp);
                return "edited";
            }
        } catch (Exception e) {
           return e.getMessage();
        }
            return null;

    }

    @Override
    public List<String> getAllManagers() {
        String jpql = "SELECT h.empFirstname FROM Hrmsemployees h WHERE h.managerName IS NULL";
        TypedQuery<String> query = em.createQuery(jpql, String.class);
        return query.getResultList();
    }

    public List<String> getAllEmployee() {
        String jpql = "SELECT h.empFirstname FROM Hrmsemployees h";
        TypedQuery<String> query = em.createQuery(jpql, String.class);
        return query.getResultList();
    }

    @Override
    public Hrmsemployees validateProvider(String firstname, String password) {
        try {
            Hrmsemployees user = em.createQuery("SELECT h FROM Hrmsemployees h WHERE h.empFirstname = :empFirstname", Hrmsemployees.class)
                    .setParameter("empFirstname", firstname)
                    .getSingleResult();

            // Validate the password (assuming it's stored as a hashed value in DB)
            if (user != null && user.getEmpPassword().equals(hashPassword(password))) {
                return user;
            }
        } catch (Exception e) {
            return null; // Invalid credentials or user not found
        }
        return null;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hashString = new StringBuilder();
            for (byte b : hashBytes) {
                hashString.append(String.format("%02x", b));
            }
            return hashString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    @Override
    public Integer getEmpCount() {
        try {
            Integer emp = em.createNamedQuery("Hrmsemployees.findAll", Hrmsemployees.class).getResultList().size();
            return emp;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    

    public void persist(Object object) {
        em.persist(object);
    }
}
