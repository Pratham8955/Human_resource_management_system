/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package EJB;

import Entity.Applicationsleave;
import Entity.Attendance;
import Entity.HrmsTraining;
import Entity.Hrmsemployees;
import Entity.Resignation;
import Entity.TaskSheet;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pratham
 */
@Local
public interface EmployeeSessionBeanLocal {

    public String AddImmediateLeave(Date requestDate, String leaveType, String leaveDuration, Date fromDate, Date toDate, String manager, String reason, Integer userId);

    public String AddPreplannedLeave(Date requestDate, String leaveType, String leaveDuration, Date fromDate, Date toDate, String manager, String reason, Integer userId);

    public List<Applicationsleave> leaveList();

    public List<Applicationsleave> leaveListpreplanned();

    public String Resignation(String empFirstname, String empLastname, Date dateOfResign, String resignReason, String feedback, String companyPropertyReturn, String manager, Integer userId);

    public String AddTask(TaskSheet Task);

    public String addAttendance(Integer userId, String depName, String timeIn, Date punchDate);

    public Hrmsemployees getUserId(Integer userId);

    public List<Attendance> showAttendance();

    public List<HrmsTraining> SpecificTrainingForEmployee();

    public List<Hrmsemployees> getSpecificManagers();

    public Hrmsemployees getProfileFromSession();

//    public List<Attendance> ShowAttendance();

    public void TimeOut(Attendance Timeout);

    public List<Resignation> showResignation();

}
