/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJB;

import Entity.Applicationsleave;
import Entity.Attendance;
import Entity.HrmsTraining;
import Entity.Hrmsemployees;
import Entity.Resignation;
import Entity.TaskSheet;
import static java.lang.System.out;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.faces.context.FacesContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author pratham
 */
@Stateless
public class EmployeeSessionBean implements EmployeeSessionBeanLocal {

    @PersistenceContext(unitName = "Hrms_PayaraPU")
    private EntityManager em;

    @Override
    public String AddImmediateLeave(Date requestDate, String leaveType, String leaveDuration, Date fromDate, Date toDate, String manager, String reason, Integer userId) {
        try {
            // Retrieve the logged-in user's employee object from the session
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);

            Hrmsemployees sessionUser = (Hrmsemployees) session.getAttribute("userId");
            if (sessionUser == null) {
                throw new Exception("User not logged in or session has expired.");
            }

            // Validate the session user's details
            if (!sessionUser.getUserId().equals(userId)) {
                throw new Exception("Session user details do not match the provided details.");
            }

            // Check for existing pending Immediate leave requests
            if ("Immediate".equalsIgnoreCase(leaveType)) {
                TypedQuery<Applicationsleave> query = em.createQuery(
                        "SELECT l FROM Applicationsleave l WHERE l.hrmsemployees.userId = :userId AND l.leaveType = :leaveType AND l.status IS NULL",
                        Applicationsleave.class);
                query.setParameter("userId", userId);
                query.setParameter("leaveType", "Immediate");

                List<Applicationsleave> pendingImmediateLeaves = query.getResultList();

                if (!pendingImmediateLeaves.isEmpty()) {
                    // There are pending Immediate leave requests
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "You already have a pending Immediate leave request."));
                    return null; // Stay on the same page
                }
            }

            // Proceed to persist the new Immediate leave request
            Hrmsemployees employee = em.find(Hrmsemployees.class, userId);
            if (employee == null) {
                throw new Exception("Employee with userId " + userId + " not found");
            }

            Applicationsleave leave = new Applicationsleave();
            leave.setRequestDate(requestDate);
            leave.setLeaveType(leaveType);
            leave.setLeaveDuration(leaveDuration);
            leave.setFromDate(fromDate);
            leave.setToDate(toDate);
            leave.setManagerName(manager);
            leave.setReason(reason);
            leave.setHrmsemployees(employee);

            em.persist(leave);

            return "insertleave";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return null;
        }
    }

    public String AddPreplannedLeave(Date requestDate, String leaveType, String leaveDuration, Date fromDate, Date toDate, String manager, String reason, Integer userId) {
        try {
            // Retrieve the logged-in user's employee object from the session
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);

            Hrmsemployees sessionUser = (Hrmsemployees) session.getAttribute("userId");
            if (sessionUser == null) {
                throw new Exception("User not logged in or session has expired.");
            }

            // Validate the session user's details
            if (!sessionUser.getUserId().equals(userId)) {
                throw new Exception("Session user details do not match the provided details.");
            }

            // Check for existing pending Preplanned leave requests
            if ("Preplanned".equalsIgnoreCase(leaveType)) {
                TypedQuery<Applicationsleave> query = em.createQuery(
                        "SELECT l FROM Applicationsleave l WHERE l.hrmsemployees.userId = :userId AND l.leaveType = :leaveType AND l.status IS NULL",
                        Applicationsleave.class);
                query.setParameter("userId", userId);
                query.setParameter("leaveType", "Preplanned");

                List<Applicationsleave> pendingPreplannedLeaves = query.getResultList();

                if (!pendingPreplannedLeaves.isEmpty()) {
                    // There are pending Preplanned leave requests
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "You already have a pending Preplanned leave request."));
                    return null; // Stay on the same page
                }
            }

            // Proceed to persist the new leave request
            Hrmsemployees employee = em.find(Hrmsemployees.class, userId);
            if (employee == null) {
                throw new Exception("Employee with userId " + userId + " not found.");
            }

            // Create a new Applicationsleave entity
            Applicationsleave leave = new Applicationsleave();
            leave.setRequestDate(requestDate);
            leave.setLeaveType(leaveType);
            leave.setLeaveDuration(leaveDuration);
            leave.setFromDate(fromDate);
            leave.setToDate(toDate);
            leave.setManagerName(manager);
            leave.setReason(reason);
            leave.setHrmsemployees(employee);

            em.persist(leave);

            return "ShowPreplannedLeave.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return null;
        }
    }

    public List<Applicationsleave> leaveList() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            Hrmsemployees sessionUserId = (Hrmsemployees) session.getAttribute("userId");
            if (sessionUserId != null) {
                String query = "SELECT a FROM Applicationsleave a WHERE a.leaveType = 'Immediate' AND a.hrmsemployees.userId = :employeeId";
                List<Applicationsleave> showList = em.createQuery(query, Applicationsleave.class
                ).setParameter("employeeId", sessionUserId.getUserId()).getResultList();
                return showList;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public List<Applicationsleave> leaveListpreplanned() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            Hrmsemployees sessionUserId = (Hrmsemployees) session.getAttribute("userId");
            if (sessionUserId != null) {
                String query = "SELECT a FROM Applicationsleave a WHERE a.leaveType = 'Preplanned' AND a.hrmsemployees.userId = :employeeId";
                List<Applicationsleave> showList = em.createQuery(query, Applicationsleave.class
                ).setParameter("employeeId", sessionUserId.getUserId()).getResultList();
                return showList;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public String Resignation(String empFirstname, String empLastname, Date dateOfResign, String resignReason,
            String feedback, String companyPropertyReturn, String manager, Integer userId) {
        try {
            // Retrieve the logged-in user's employee object from the session
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);

            Hrmsemployees sessionUser = (Hrmsemployees) session.getAttribute("userId");
            if (sessionUser == null) {
                throw new Exception("User not logged in or session has expired.");
            }

            // Validate the session user's details
            if (!sessionUser.getEmpFirstname().equals(empFirstname)
                    || !sessionUser.getEmpLastname().equals(empLastname)
                    || !sessionUser.getUserId().equals(userId)) {
                throw new Exception("Session user details do not match the provided details.");
            }

            // Fetch the employee from the database
            Hrmsemployees employee = em.find(Hrmsemployees.class, userId);
            if (employee == null) {
                throw new Exception("Employee with userId " + userId + " not found.");
            }

            // Check if the user has already applied for resignation (any status: Pending, Approved, or Rejected)
            TypedQuery<Resignation> query = em.createQuery(
                    "SELECT r FROM Resignation r WHERE r.hrmsemployees.userId = :userId",
                    Resignation.class);
            query.setParameter("userId", userId);

            List<Resignation> existingResignations = query.getResultList();

            if (!existingResignations.isEmpty()) {
                // User has already submitted a resignation
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "You have already submitted a resignation."));
                return null; // Stay on the same page
            }

            // Proceed to persist the resignation request
            Resignation resignation = new Resignation();
            resignation.setEmpFirstname(empFirstname);
            resignation.setEmpLastname(empLastname);
            resignation.setDateOfResign(dateOfResign);
            resignation.setReason(resignReason);
            resignation.setFeedback(feedback);
            resignation.setCompanyPropertyReturn(companyPropertyReturn);
            resignation.setManager(manager);
            resignation.setHrmsemployees(employee);
            resignation.setStatus("Pending"); // Set status to Pending initially

            em.persist(resignation);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Resignation request submitted successfully."));

            return "ResignationStatus.xhtml?faces-redirect=true"; // Redirect to resignation status page
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return null;
        }
    }

    @Override
    public String AddTask(TaskSheet Task) {
        try {
            em.persist(Task);
            return "task added";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Hrmsemployees
            getUserId(Integer userId) {
        try {
            em.find(Hrmsemployees.class,
                    userId);

        } catch (Exception e) {
            e.getCause();
        }
        return null;
    }

    public String addAttendance(Integer userId, String depName, String timeIn, Date punchDate) {
        try {
            // Get session user information
            Hrmsemployees sessionUser = (Hrmsemployees) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("userId");

            if (sessionUser == null) {
                throw new Exception("Session user is not found.");
            }

            // Validate session user's details
            if (!sessionUser.getDepId().getDepName().equals(depName)
                    || !sessionUser.getUserId().equals(userId)) {
                throw new Exception("Session user details do not match the provided details.");
            }

            // Find the employee from the database
            Hrmsemployees employee = em.find(Hrmsemployees.class, userId);
            if (employee == null) {
                throw new Exception("Employee not found in the database.");
            }

            // Check if attendance already exists for the given day
            TypedQuery<Attendance> query = em.createQuery(
                    "SELECT a FROM Attendance a WHERE a.hrmsemployees.userId = :userId AND a.punchDate = :punchDate",
                    Attendance.class);
            query.setParameter("userId", userId);
            query.setParameter("punchDate", punchDate);

            List<Attendance> existingAttendance = query.getResultList();

            if (!existingAttendance.isEmpty()) {
                // Attendance already entered for the specified day
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Attendance already entered for today."));
                return null; // Prevent further processing
            }

            // Create and persist the attendance record
            Attendance attendance = new Attendance();
            attendance.setDeptName(depName);

            // Adjust timeIn for TemporalType.TIME
            attendance.setTimeIn(timeIn);

            // Set the punch date (no change needed)
            attendance.setPunchDate(punchDate);

            // Set the employee
            attendance.setHrmsemployees(employee);

            // Persist the attendance record
            em.persist(attendance);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Attendance added successfully."));

            return "Attendance added successfully!";
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return "Error: " + e.getMessage();
        }
    }

    public List<Resignation> showResignation() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            Hrmsemployees sessionUserId = (Hrmsemployees) session.getAttribute("userId");
            if (sessionUserId != null) {
                // Fetch attendance records where userid matches
                return em.createQuery("SELECT r FROM Resignation r WHERE r.hrmsemployees.userId = :employeeId", Resignation.class)
                        .setParameter("employeeId", sessionUserId.getUserId())
                        .getResultList();

            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<Attendance> showAttendance() {
        try {
            // Retrieve the logged-in user's session
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            Hrmsemployees sessionUserId = (Hrmsemployees) session.getAttribute("userId");
            out.println(sessionUserId);
            // Ensure session user is not null

            if (sessionUserId != null) {
                // Fetch attendance records where userid matches
                return em.createNamedQuery("Attendance.findByUserId", Attendance.class)
                        .setParameter("userId", sessionUserId.getUserId())
                        .getResultList();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if an exception occurs
    }

//    public List<Attendance> ShowAttendance() {
//        try {
//            
//            List<Attendance> list = em.createNamedQuery("Attendance.findAll", Attendance.class).getResultList();
//            return list;
//        } catch (Exception e) {
//            e.getMessage();
//            return null;
//        }
//    }
    public List<HrmsTraining> SpecificTrainingForEmployee() {
        try {
            // Get the current HTTP session
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);

            // Retrieve the logged-in user's employee object from the session
            Hrmsemployees sessionUser = (Hrmsemployees) session.getAttribute("userId");
            if (sessionUser == null || sessionUser.getEmpFirstname() == null) {
                System.out.println("Session user or first name is null.");
                return null;

            }

            // Execute query to fetch training records for the employee
            return em.createQuery("SELECT h FROM HrmsTraining h WHERE h.userId.empFirstname = :employeename", HrmsTraining.class
            )
                    .setParameter("employeename", sessionUser.getEmpFirstname())
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Hrmsemployees> getSpecificManagers() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);

            // Retrieve the logged-in user's employee object from the session
            Hrmsemployees sessionUser = (Hrmsemployees) session.getAttribute("userId");

            return em.createQuery("SELECT h FROM Hrmsemployees h WHERE h.empFirstname = :employeename", Hrmsemployees.class
            )
                    .setParameter("employeename", sessionUser.getEmpFirstname())
                    .getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public Hrmsemployees getProfileFromSession() {
        try {
            // Get the current session
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session == null) {
                return null; // Session doesn't exist
            }

            // Get the userId stored in the session
            String userId = (String) session.getAttribute("userId");
            if (userId == null) {
                return null; // userId doesn't exist in session
            }

            // Query the database to fetch the employee using the userId
            Query query = em.createQuery("SELECT e FROM Hrmsemployees e WHERE e.userId = :userId");
            query.setParameter("userId", userId);

            System.out.println(userId);
            // Return the single result, assuming userId is unique
            return (Hrmsemployees) query.getSingleResult();

        } catch (NoResultException e) {
            // Handle case when no employee is found with the given userId
            System.err.println("No employee found for userId: " + e.getMessage());
            return null;
        } catch (NonUniqueResultException e) {
            // Handle case when multiple employees are found with the same userId
            System.err.println("Multiple employees found for userId: " + e.getMessage());
            return null;
        } catch (Exception e) {
            // Catch any other exceptions
            e.printStackTrace();
            return null;
        }
    }

    public void TimeOut(Attendance Timeout) {
        try {
            em.merge(Timeout);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }

}
