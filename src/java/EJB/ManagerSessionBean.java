/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJB;

import Entity.Applicationsleave;
import Entity.Attendance;
import Entity.HrmsTraingType;
import Entity.HrmsTraining;
import Entity.Hrmsemployees;
import Entity.Resignation;
import Entity.TaskSheet;
import static java.lang.System.out;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pratham
 */
@Stateless
public class ManagerSessionBean implements ManagerSessionBeanLocal {

    @PersistenceContext(unitName = "Hrms_PayaraPU")
    private EntityManager em;

    @Override
    public List<Applicationsleave> ShowLeaveForManager() {
        try {
            // Retrieve the logged-in user's session
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            Hrmsemployees sessionUserId = (Hrmsemployees) session.getAttribute("userId");
            out.println(sessionUserId);
            if (sessionUserId != null && sessionUserId.getEmpFirstname() != null) {
                // Fetch leave applications where the manager's name matches
                return em.createQuery("SELECT al FROM Applicationsleave al WHERE al.managerName = :managerName", Applicationsleave.class)
                        .setParameter("managerName", sessionUserId.getEmpFirstname())
                        .getResultList();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public void approveLeave(Applicationsleave leave) {
        try {

            em.merge(leave);  // Persist the updated leave entity

        } catch (Exception e) {
            e.getMessage();  // Log or handle the exception as needed
        }
    }

    @Override
    public List<Resignation> ShowResignationForManager() {
        try {
            // Retrieve the logged-in user's session
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            Hrmsemployees sessionUserId = (Hrmsemployees) session.getAttribute("userId");

            if (sessionUserId != null && sessionUserId.getEmpFirstname() != null) {
                // Fetch leave applications where the manager's name matches
                return em.createQuery("SELECT r FROM Resignation r WHERE r.manager = :managerName", Resignation.class)
                        .setParameter("managerName", sessionUserId.getEmpFirstname()).getResultList();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public void approveResignation(Resignation resignation) {
        try {
            em.merge(resignation);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public List<TaskSheet> ShowTaskForManager() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            Hrmsemployees sessionUserId = (Hrmsemployees) session.getAttribute("userId");
            String query = "SELECT t FROM TaskSheet t "
                    + "LEFT JOIN t.userId e "
                    + "WHERE e.managerName = :managerNamesession";
            TypedQuery<TaskSheet> typedQuery = em.createQuery(query, TaskSheet.class);
            typedQuery.setParameter("managerNamesession", sessionUserId.getEmpFirstname());
            return typedQuery.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public void approveTask(TaskSheet task) {
        try {
            em.merge(task);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void AddTraining(HrmsTraingType train) {
        try {
            out.println("hi bro");
            em.persist(train);
            out.println("hi");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public List<HrmsTraingType> showTrainingType() {
        try {
            List<HrmsTraingType> list = em.createNamedQuery("HrmsTraingType.findAll", HrmsTraingType.class).getResultList();
            return list;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public HrmsTraingType SearchTrainingType(int id) {
        try {
            HrmsTraingType training = em.find(HrmsTraingType.class, id);
            if (training != null) {
                return training;
            } else {
                throw new Exception("The Training Type Does not exist!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String deleteTrainingType(int tid) {
        System.out.println("Deleting training type with ID: " + tid);
        try {
            HrmsTraingType train = em.find(HrmsTraingType.class, tid);
            if (train == null) {
                System.out.println("Entity not found");
                return "Entity not found";
            }
            em.remove(train);
            System.out.println("Entity removed successfully");
            return "TrainingView.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String editTrainingType(HrmsTraingType train) {
        try {
            HrmsTraingType training = em.find(HrmsTraingType.class, train.getTId());
            training = train;
            em.merge(training);
            return "Edit Training Type Succesfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public void AddEmpTraining(HrmsTraining training) {
        try {
            em.persist(training);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public List<Hrmsemployees> getAllTrainingsEmployee() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            Hrmsemployees sessionUserId = (Hrmsemployees) session.getAttribute("userId");
            if (sessionUserId != null && sessionUserId.getEmpFirstname() != null) {
                return em.createQuery("SELECT h FROM Hrmsemployees h WHERE h.managerName = :managerName", Hrmsemployees.class)
                        .setParameter("managerName", sessionUserId.getEmpFirstname()).getResultList();
            }

        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<HrmsTraining> getTraingsSheduled() {
        try {
            List<HrmsTraining> list = em.createNamedQuery("HrmsTraining.findAll", HrmsTraining.class).getResultList();
            return list;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }

}
