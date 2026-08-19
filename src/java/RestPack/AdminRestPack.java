/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package RestPack;

import EJB.AdminSessionBeanLocal;
import Entity.GroupMaster;
import Entity.HrmsDepartment;
import Entity.Hrmsemployees;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author pratham
 */
@Path("generic")
public class AdminRestPack {

    AdminSessionBeanLocal adminSessionBean = lookupAdminSessionBeanLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AdminRestPack
     */
    public AdminRestPack() {
    }

    /**
     * Retrieves representation of an instance of RestPack.AdminRestPack
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AdminRestPack
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    private AdminSessionBeanLocal lookupAdminSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (AdminSessionBeanLocal) c.lookup("java:global/Hrms_Payara/AdminSessionBean!EJB.AdminSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    @POST
    @Path("addDepartment/{departmentName}/{ManagerName}")
    public String addDepartment(@PathParam("departmentName") String departmentName, @PathParam("ManagerName") String ManagerName) {
        HrmsDepartment dep = new HrmsDepartment();
        dep.setDepName(departmentName);
        dep.setManagerName(ManagerName);
        return adminSessionBean.addDepartment(dep);

    }

    @GET
    @Path("ViewDepartment")
    @Produces(MediaType.APPLICATION_XML)
    public List<HrmsDepartment> ViewDepartment() {
        return adminSessionBean.ViewDepartment();
    }

    @DELETE
    @Path("deleteDepartment/{departmentid}")
    public String deleteDepartment(@PathParam("departmentid") int departmentid) {
        return adminSessionBean.deleteDepartment(departmentid);
    }

    @GET
    @Path("searchDepartment/{departmentid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response SearchDepartment(@PathParam("departmentid") int departmentid) {
        HrmsDepartment dep = adminSessionBean.SearchDepartment(departmentid);
        if (dep != null) {
            return Response.ok(dep).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Employee not found").build();
        }
    }

    @PUT
    @Path("editDepartment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editDepartment(HrmsDepartment dep) {
        String result = adminSessionBean.editDepartment(dep);
        if ("updated".equals(result)) {
            return Response.ok("updated SuccesFully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Employee not found").build();
        }
    }

    @POST
    @Path("addEmployee")
    public String addEmployee(
            @PathParam("empFirstname") String empFirstname,
            @PathParam("empLastname") String empLastname,
            @PathParam("empEmail") String empEmail,
            @PathParam("empPhone") String empPhone,
            @PathParam("empAddress") String empAddress,
            @PathParam("empGender") String empGender,
            @PathParam("empPassword") String empPassword,
            @PathParam("hireDate") Date hireDate, // Parse into Date
            @PathParam("salary") Integer salary,
            @PathParam("managerName") String managerName,
            @PathParam("depId") Integer depId,
            @PathParam("groupId") Integer groupId // New parameter for Group ID
    ) {
        try {
            // Parse hireDate
//            Date parsedHireDate = new SimpleDateFormat("yyyy-MM-dd").parse(hireDate);

            // Create and populate the Hrmsemployees object
            Hrmsemployees emp = new Hrmsemployees();
            emp.setEmpFirstname(empFirstname);
            emp.setEmpLastname(empLastname);
            emp.setEmpEmail(empEmail);
            emp.setEmpPhone(empPhone);
            emp.setEmpAddress(empAddress);
            emp.setEmpGender(empGender);
            emp.setEmpPassword(hashPassword(empPassword));
            emp.setHireDate(hireDate);
            emp.setSalary(salary);

            // Set the HrmsDepartment
            HrmsDepartment dep = new HrmsDepartment();
            dep.setDepId(depId);
            emp.setDepId(dep);

            // Set the GroupMaster
            GroupMaster group = new GroupMaster();
            group.setGroupId(groupId);
            emp.setGroupId(group);

            emp.setManagerName(managerName);

            // Call the session bean to add the employee
            return adminSessionBean.addEmployee(emp);

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception while adding employee", e);
            return "Error adding employee: " + e.getMessage();
        }
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
    
     @GET
    @Path("showAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hrmsemployees> showEmployee() {
        return adminSessionBean.showEmployee();
    }

    @DELETE
    @Path("delete/{userid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteEmployee(@PathParam("userid") Integer userid) {
        try {
            adminSessionBean.deleteEmployee(userid);
            return Response.ok("Employee deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error deleting employee: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("search/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchEmployee(@PathParam("userid") Integer userid) {
        Hrmsemployees emp = adminSessionBean.searchEmployee(userid);
        if (emp != null) {
            return Response.ok(emp).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Employee not found").build();
        }
    }

    @PUT
    @Path("edit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response editEmployee(Hrmsemployees emp) {
        try {
            String result = adminSessionBean.editEmployee(emp);
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error editing employee: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("viewHR")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hrmsemployees> viewHR() {
        return adminSessionBean.viewHR();
    }

    @GET
    @Path("viewIT")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hrmsemployees> viewIT() {
        return adminSessionBean.viewIT();
    }

    @GET
    @Path("viewFinance")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hrmsemployees> viewFinance() {
        return adminSessionBean.viewFinance();
    }

    @GET
    @Path("viewMarketing")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hrmsemployees> viewMarketing() {
        return adminSessionBean.viewMarketing();
    }

}
