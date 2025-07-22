/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package EJB;

import Entity.HrmsDepartment;
import Entity.Hrmsemployees;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pratham
 */
@Local
public interface AdminSessionBeanLocal {
//Department

    public String addDepartment(HrmsDepartment dept);

    public List<HrmsDepartment> ViewDepartment();

    public String deleteDepartment(int departmentid);

    public String editDepartment(HrmsDepartment dep);

    public HrmsDepartment SearchDepartment(int departmentid);

    public List<Hrmsemployees> getManager();

    public List<Hrmsemployees> viewHR();

    public List<Hrmsemployees> viewIT();

    public List<Hrmsemployees> viewMarketing();

    public List<Hrmsemployees> viewFinance();

    //Employee
    public String addEmployee(Hrmsemployees emp);

    public List<Hrmsemployees> showEmployee();

    public String editEmployee(Hrmsemployees emp);

    public Hrmsemployees searchEmployee(int userid);

    public String deleteEmployee(int userid);

    public List<String> getAllManagers();

    public List<String> getAllEmployee();

    public Hrmsemployees validateProvider(String firstname, String password);

    public Integer getEmpCount();

   

}
