/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIbean;

import EJB.AdminSessionBeanLocal;
import Entity.GroupMaster;
import Entity.HrmsDepartment;
import Entity.Hrmsemployees;
import RestClient.AdminRestClient;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import static java.lang.System.out;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author pratham
 */
@Named(value = "adminCdiBean")
@SessionScoped
public class AdminCdiBean implements Serializable {

    @PostConstruct
    public void init() {
        getAllManager();
    }

    @EJB
    private AdminSessionBeanLocal adminSessionBean;
    AdminRestClient adminrest = new AdminRestClient();

    //Department
    Integer depId;

    String depName, managerName, msg;

    //Employee
    Integer userid, salary, emp_depid, roleid;
    String firstname, lastname, email, phone, address, gender, password, emp_managername;
    Date hiredate;

    private List<String> managerNames, employeename;

    public String getEmp_managername() {
        return emp_managername;
    }

    public void setEmp_managername(String emp_managername) {
        this.emp_managername = emp_managername;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getEmp_depid() {
        return emp_depid;
    }

    public void setEmp_depid(Integer emp_depid) {
        this.emp_depid = emp_depid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public List<String> getManagerNames() {
        return managerNames;
    }

    public List<String> getEmployeename() {
        return employeename;
    }

    public String addDepartment() {
        HrmsDepartment dep = new HrmsDepartment();
        dep.setDepName(depName);
        dep.setManagerName(managerName);
        adminrest.addDepartment(depName, managerName);
        this.depName = null;
        this.managerName = null;
        return "ShowDepartment.xhtml?faces-redirect=true";
    }

    public List<HrmsDepartment> showDepartment() {
        Response rs = adminrest.ViewDepartment(Response.class);
        ArrayList<HrmsDepartment> categoryList = new ArrayList<HrmsDepartment>();
        GenericType<Collection<HrmsDepartment>> gcCategory = new GenericType<Collection<HrmsDepartment>>() {
        };
        categoryList = (ArrayList<HrmsDepartment>) rs.readEntity(gcCategory);
        return categoryList;
//GenericType<List<HrmsDepartment>> list1 = new GenericType<List<HrmsDepartment>>() {
//        };

//        List<HrmsDepartment> list = adminrest.ViewDepartment(list1);
//        return list;
    }

    public String deleteDepartment(Integer deptId) {
        adminrest.deleteDepartment(deptId);
        return "ShowDepartment.xhtml?faces-redirect=true";
    }

    public String SearchDepartment(Integer deptId) {
        HrmsDepartment dep = adminrest.SearchDepartment(HrmsDepartment.class, deptId);
        depId = dep.getDepId();
        depName = dep.getDepName();
        managerName = dep.getManagerName();
        return "editDepartment.xhtml?faces-redirect=true";
    }

    public String editDepartment() {
        HrmsDepartment dep = new HrmsDepartment();
        dep.setDepId(depId);
        dep.setDepName(depName);
        dep.setManagerName(managerName);
        adminrest.editDepartment(dep);
        depId = null;
        depName = "";
        managerName = "";
        return "ShowDepartment.xhtml?faces-redirect=true";

    }

    public List<Hrmsemployees> getManager() {
        List<Hrmsemployees> showListEmp = adminSessionBean.getManager();
        return showListEmp;
    }

    //employee
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

    public String addEmployee() {
        Hrmsemployees emp = new Hrmsemployees();
        emp.setEmpFirstname(firstname);
        emp.setEmpLastname(lastname);
        emp.setEmpEmail(email);
        emp.setEmpPhone(phone);
        emp.setEmpAddress(address);
        emp.setEmpGender(gender);
        emp.setEmpPassword(hashPassword(password));
        emp.setHireDate(hiredate);
        emp.setSalary(salary);
        HrmsDepartment dep = new HrmsDepartment();
        dep.setDepId(emp_depid);
        emp.setDepId(dep);
        emp.setManagerName(emp_managername);
        GroupMaster group = new GroupMaster();
        group.setGroupId(roleid);
        emp.setGroupId(group);

        msg = adminSessionBean.addEmployee(emp);

//        adminrest.addEmployee(firstname, lastname, email, phone, address, gender, password, hiredate, salary, managerName, depId, roleid);
        this.firstname = "";
        this.lastname = "";
        this.email = "";
        this.phone = "";
        this.address = "";
        this.gender = "";
        this.password = "";
        this.hiredate = null;
        this.salary = null;
        this.emp_depid = null;
        this.emp_managername = "";
        this.roleid = null;
        return "ShowEmployee.xhtml?faces-redirect=true";

    }

    public List<Hrmsemployees> showEmployee() {
//         Response rs = adminrest.showEmployee(Response.class);
//        ArrayList<Hrmsemployees> categoryList = new ArrayList<Hrmsemployees>();
//        GenericType<Collection<Hrmsemployees>> gcCategory = new GenericType<Collection<Hrmsemployees>>() {
//        };
//        categoryList = (ArrayList<Hrmsemployees>) rs.readEntity(gcCategory);
//        return categoryList;
        List<Hrmsemployees> showListEmp = adminSessionBean.showEmployee();
        return showListEmp;
    }

    public String deleteEmployee(Integer userid) {
        adminSessionBean.deleteEmployee(userid);
        return "ShowEmployee.xhtml?faces-redirect=true";
    }

    public String searchEmployee(Integer userid) {

        Hrmsemployees emp = adminSessionBean.searchEmployee(userid);
        if (emp == null) {
            msg = "employee not found!";
            return null;
        }
        this.userid = userid;
        this.firstname = emp.getEmpFirstname();
        this.lastname = emp.getEmpLastname();
        this.email = emp.getEmpEmail();
        this.phone = emp.getEmpPhone();
        this.address = emp.getEmpAddress();
        this.gender = emp.getEmpGender();
        this.hiredate = emp.getHireDate();
        this.salary = emp.getSalary();

        HrmsDepartment dep = emp.getDepId();

        this.emp_depid = dep.getDepId();
        out.println(emp_depid);
        this.emp_managername = emp.getManagerName();
        return "EditEmployee.xhtml?faces-redirect=true";
    }

    public String editEmployee() {

        Hrmsemployees existingEmp = new Hrmsemployees();
        existingEmp.setUserId(this.userid);
        existingEmp.setEmpFirstname(this.firstname);
        existingEmp.setEmpLastname(this.lastname);
        existingEmp.setEmpEmail(this.email);
        existingEmp.setEmpPhone(this.phone);
        existingEmp.setEmpAddress(this.address);
        existingEmp.setEmpGender(this.gender);
        existingEmp.setHireDate(this.hiredate);
        existingEmp.setSalary(this.salary);
        existingEmp.setManagerName(this.emp_managername);

//        out.println("userid" + userid);
//        out.println("userid" + firstname);
        HrmsDepartment dep = adminSessionBean.SearchDepartment(emp_depid);
//        out.println("depId" + dep);
        if (dep != null) {
            existingEmp.setDepId(dep);
            adminSessionBean.editEmployee(existingEmp);
            this.userid = null;
            this.firstname = "";
            this.lastname = "";
            this.email = "";
            this.phone = "";
            this.address = "";
            this.gender = "";
            this.hiredate = null;
            this.salary = null;
            this.emp_depid = null;
            this.emp_managername = "";
            return "ShowEmployee.xhtml?faces-redirect=true";
        } else {
            msg = "Error: ";
            return null;
        }

    }

    public List<Hrmsemployees> viewHR() {
//        Response rs = adminrest.viewHR(Response.class);
//
//        GenericType<Collection<Hrmsemployees>> gcCategory = new GenericType<Collection<Hrmsemployees>>() {
//        };
//
//        Collection<Hrmsemployees> categoryList = rs.readEntity(gcCategory);
//
//        ArrayList<Hrmsemployees> employeeList = new ArrayList<>(categoryList);
//
//        return employeeList;

        List<Hrmsemployees> hrlist = adminSessionBean.viewHR();
        return hrlist;
    }

    public List<Hrmsemployees> viewIT() {
//          Response rs = adminrest.viewIT(Response.class);
//
//        GenericType<Collection<Hrmsemployees>> gcCategory = new GenericType<Collection<Hrmsemployees>>() {
//        };
//
//        Collection<Hrmsemployees> categoryList = rs.readEntity(gcCategory);
//
//        ArrayList<Hrmsemployees> employeeList = new ArrayList<>(categoryList);
//
//        return employeeList;
        List<Hrmsemployees> ITlist = adminSessionBean.viewIT();
        return ITlist;
    }

    public List<Hrmsemployees> viewFinance() {
//        Response rs = adminrest.viewFinance(Response.class);
//        if (rs.getStatus() == Response.Status.OK.getStatusCode()) {
//            GenericType<Collection<Hrmsemployees>> gcCategory = new GenericType<Collection<Hrmsemployees>>() {
//            };
//            Collection<Hrmsemployees> categoryList = rs.readEntity(gcCategory);
//            ArrayList<Hrmsemployees> employeeList = new ArrayList<>(categoryList);
//            return employeeList;
//        } else {
//            // Handle error response
//            throw new RuntimeException("Failed to fetch HR employees: " + rs.getStatusInfo());
//        }
        List<Hrmsemployees> Financelist = adminSessionBean.viewFinance();
        return Financelist;
    }

    public List<Hrmsemployees> viewMarketing() {
//        Response rs = adminrest.viewMarketing(Response.class);
//if (rs.getStatus() == Response.Status.OK.getStatusCode()) {
//    GenericType<Collection<Hrmsemployees>> gcCategory = new GenericType<Collection<Hrmsemployees>>() {};
//    Collection<Hrmsemployees> categoryList = rs.readEntity(gcCategory);
//    ArrayList<Hrmsemployees> employeeList = new ArrayList<>(categoryList);
//    return employeeList;
//} else {
//    // Handle error response
//    throw new RuntimeException("Failed to fetch HR employees: " + rs.getStatusInfo());
//}
        List<Hrmsemployees> MarketingList = adminSessionBean.viewMarketing();
        return MarketingList;
    }

    public void getAllManager() {
        managerNames = adminSessionBean.getAllManagers();
        out.println("Manager Names in CDI Bean: " + managerNames);
    }

    public void getAllEmployee() {
        employeename = adminSessionBean.getAllEmployee();  // Fetch only names
    }

    Integer CounterEmp, TotalManagers;

    public Integer getCounterEmp() {
        return CounterEmp;
    }

    public void setCounterEmp(Integer CounterEmp) {
        this.CounterEmp = CounterEmp;
    }

    public Integer getEmpCount() {
        return this.CounterEmp = adminSessionBean.getEmpCount();

    }

    public AdminCdiBean() {
    }

}
