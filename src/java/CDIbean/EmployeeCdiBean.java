/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIbean;

import EJB.EmployeeSessionBeanLocal;
import Entity.Applicationsleave;
import Entity.Attendance;
import Entity.HrmsTraining;
import Entity.Hrmsemployees;
import Entity.Resignation;
import Entity.TaskSheet;
//import RestClient.EmployeeRestClient;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author pratham
 */
@Named(value = "employeeCdiBean")
@SessionScoped
public class EmployeeCdiBean implements Serializable {

//    EmployeeRestClient employeerest=new EmployeeRestClient();
    @EJB
    private EmployeeSessionBeanLocal employeeSessionBean;

    Date requestdate, fromdate, todate;
    String leavetype, leaveduration, reason, managername;
    Integer userid;
//    HttpSession session;

    public Date getRequestdate() {
        return requestdate;
    }

    public void setRequestdate(Date requestdate) {
        this.requestdate = requestdate;
    }

    public Date getFromdate() {
        return fromdate;
    }

    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    public Date getTodate() {
        return todate;
    }

    public void setTodate(Date todate) {
        this.todate = todate;
    }

    public String getLeavetype() {
        return leavetype;
    }

    public void setLeavetype(String leavetype) {
        this.leavetype = leavetype;
    }

    public String getLeaveduration() {
        return leaveduration;
    }

    public void setLeaveduration(String leaveduration) {
        this.leaveduration = leaveduration;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getManagername() {
        return managername;
    }

    public void setManagername(String managername) {
        this.managername = managername;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String AddImmediateLeave() {
//        employeerest.addImmediateLeave(requestdate, leavetype, leavetype, leaveduration, feedback, task, manager, reason, reason);
        employeeSessionBean.AddImmediateLeave(requestdate, leavetype, leaveduration, fromdate, todate, managername, reason, userid);
        this.requestdate = null;
        this.leavetype = "";
        this.leaveduration = "";
        this.fromdate = null;
        this.todate = null;
        this.managername = "";
        this.reason = "";
        this.userid = null;
        return "ShowImmediateLeave.xhtml?faces-redirect=true";

    }

    public String AddPreplannedLeave() {
//        employeerest.addPreplannedLeave(requestdate, leavetype, leavetype, leaveduration, feedback, task, manager, reason, reason);
        employeeSessionBean.AddPreplannedLeave(requestdate, leavetype, leaveduration, fromdate, todate, managername, reason, userid);
        this.requestdate = null;
        this.leavetype = "";
        this.leaveduration = "";
        this.fromdate = null;
        this.todate = null;
        this.managername = "";
        this.reason = "";
        this.userid = null;
        return "ShowPreplannedLeave.xhtml?faces-redirect=true";

    }

    public List<Applicationsleave> leaveList() {
        List<Applicationsleave> showList = employeeSessionBean.leaveList();
        return showList;
    }

    public List<Applicationsleave> leaveListpreplanned() {
        List<Applicationsleave> showList = employeeSessionBean.leaveListpreplanned();
        return showList;
    }

    private String empFirstname;
    private String empLastname;
    private Date dateOfResign;
    private String resignReason;
    private String feedback;
    private String companyPropertyReturn;
    private String manager;
    private Integer resigId;

    // Getter and Setter for empFirstname
    public String getEmpFirstname() {
        return empFirstname;
    }

    public void setEmpFirstname(String empFirstname) {
        this.empFirstname = empFirstname;
    }

    // Getter and Setter for empLastname
    public String getEmpLastname() {
        return empLastname;
    }

    public void setEmpLastname(String empLastname) {
        this.empLastname = empLastname;
    }

    // Getter and Setter for dateOfResign
    public Date getDateOfResign() {
        return dateOfResign;
    }

    public void setDateOfResign(Date dateOfResign) {
        this.dateOfResign = dateOfResign;
    }

    // Getter and Setter for resignReason (formerly 'reason')
    public String getResignReason() {
        return resignReason;
    }

    public void setResignReason(String resignReason) {
        this.resignReason = resignReason;
    }

    // Getter and Setter for feedback
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    // Getter and Setter for companyPropertyReturn
    public String getCompanyPropertyReturn() {
        return companyPropertyReturn;
    }

    public void setCompanyPropertyReturn(String companyPropertyReturn) {
        this.companyPropertyReturn = companyPropertyReturn;
    }

    // Getter and Setter for manager
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    // Getter and Setter for resigId
    public Integer getResigId() {
        return resigId;

    }

    public void setResigId(Integer resigId) {
        this.resigId = resigId;
    }

    public String Resignation() {
//        employeerest.resignation(requestdate, empFirstname, empLastname, leaveduration, resignReason, feedback, companyPropertyReturn, manager, reason);
        employeeSessionBean.Resignation(empFirstname, empLastname, dateOfResign, resignReason, feedback, companyPropertyReturn, manager, userid);
        this.empFirstname = "";
        this.empLastname = "";
        this.dateOfResign = null;
        this.resignReason = "";
        this.feedback = "";
        this.companyPropertyReturn = null;
        this.manager = "";
        this.userid = null;
        return "ShowResignation.xhtml?faces-redirect=true";
    }

    public List<Resignation> showResignation() {
        List<Resignation> list = employeeSessionBean.showResignation();
        return list;
    }

    Date entrydate;
    String task;
    String actiontime;

    public Date getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(Date entrydate) {
        this.entrydate = entrydate;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getActiontime() {
        return actiontime;
    }

    public void setActiontime(String actiontime) {
        this.actiontime = actiontime;
    }

    public String AddTask() {
        TaskSheet Task = new TaskSheet();
        Hrmsemployees emp = new Hrmsemployees();
        emp.setUserId(userid);

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);

        Hrmsemployees sessionuserid = (Hrmsemployees) session.getAttribute("userId");

        if (sessionuserid != null && sessionuserid.getUserId().equals(emp.getUserId()) && sessionuserid.getEmpFirstname().equals(empFirstname)) {
            // Proceed if the IDs match
            Task.setUserId(emp);

            Task.setEmpName(empFirstname);

            Task.setEntryDate(entrydate);
            Task.setTask(task);
            Task.setActionTime(actiontime);

            employeeSessionBean.AddTask(Task);

            this.userid = null;
            this.empFirstname = "";
            this.entrydate = null;
            this.task = "";
            this.actiontime = "";

            return null;
        } else {

            System.out.println("Session user ID does not match the entered user ID.");
            return null;
        }

    }
    Integer Atten_userid;
    String Atten_dep_name, Atten_timeIn;
    Date Atten_punch_date;
    Time Atten_TimeOut;

    public Integer getAtten_userid() {
        return Atten_userid;
    }

    public void setAtten_userid(Integer Atten_userid) {
        this.Atten_userid = Atten_userid;
    }

    public String getAtten_dep_name() {
        return Atten_dep_name;
    }

    public void setAtten_dep_name(String Atten_dep_name) {
        this.Atten_dep_name = Atten_dep_name;
    }

    public Date getAtten_punch_date() {
        return Atten_punch_date;
    }

    public void setAtten_punch_date(Date Atten_punch_date) {
        this.Atten_punch_date = Atten_punch_date;
    }

    public String getAtten_timeIn() {
        return Atten_timeIn;
    }

    public void setAtten_timeIn(String Atten_timeIn) {
        this.Atten_timeIn = Atten_timeIn;
    }

    public Time getAtten_TimeOut() {
        return Atten_TimeOut;
    }

    public void setAtten_TimeOut(Time Atten_TimeOut) {
        this.Atten_TimeOut = Atten_TimeOut;
    }

    public String AddAttendance() {
        employeeSessionBean.addAttendance(Atten_userid, Atten_dep_name, Atten_timeIn, Atten_punch_date);

        // Reset form fields
        this.Atten_userid = null;
        this.Atten_dep_name = null;
        this.Atten_timeIn = null;
        this.Atten_punch_date = null;

        // Redirect to attendance report
        return "attendanceReport.xhtml?faces-redirect=true";

    }

    public List<Attendance> showAttendance() {
//        GenericType<List<Attendance>> list1 = new GenericType<List<Attendance>>() {
//        };
//
//        List<Attendance> list = employeerest.showAttendance(list1);
//        return list;
        List<Attendance> list = employeeSessionBean.showAttendance();
        return list;
    }

    public List<HrmsTraining> SpecificTrainingForEmployee() {
//        GenericType<List<HrmsTraining>> list1 = new GenericType<List<HrmsTraining>>() {
//        };
//
//        List<HrmsTraining> list = employeerest.getSpecificTrainingForEmployee(list1);
//                return list;
        List<HrmsTraining> list = employeeSessionBean.SpecificTrainingForEmployee();
        return list;
    }

    public List<Hrmsemployees> getSpecificManagers() {
//        GenericType<List<Hrmsemployees>> list1 = new GenericType<List<Hrmsemployees>>() {
//        };
////
//        List<Hrmsemployees> list = employeerest.getSpecificManagers(list1);
//                return list;
        List<Hrmsemployees> list = employeeSessionBean.getSpecificManagers();
        return list;
    }

    public void TimeOut(Attendance attendance) {
        try {
            // Get the current date and time in UTC
            ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneOffset.UTC);

            // Convert the ZonedDateTime to a Timestamp for storage
            Timestamp timeOut = Timestamp.from(zonedDateTime.toInstant());

            // Set the time out on the attendance
            attendance.setTimeOut(timeOut);

            // Call your session bean to save the attendance
            employeeSessionBean.TimeOut(attendance);

            // Optionally, log or return some information
            System.out.println("Time Out recorded: " + timeOut);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    public EmployeeCdiBean() {
    }

}
