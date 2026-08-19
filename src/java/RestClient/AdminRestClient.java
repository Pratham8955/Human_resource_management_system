/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package RestClient;

import java.util.Date;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:AdminRestPack [generic]<br>
 * USAGE:
 * <pre>
 *        AdminRestClient client = new AdminRestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author pratham
 */
public class AdminRestClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/Hrms_Payara/webresources";

    public AdminRestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("generic");
    }

    public <T> T SearchDepartment(Class<T> responseType, Integer departmentid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("searchDepartment/{0}", new Object[]{departmentid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T viewIT(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("viewIT");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T viewFinance(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("viewFinance");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T viewMarketing(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("viewMarketing");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void putJson(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public Response editDepartment(Object requestEntity) throws ClientErrorException {
        return webTarget.path("editDepartment").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public <T> T showEmployee(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("showAll");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String addDepartment(String departmentName, String ManagerName) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addDepartment/{0}/{1}", new Object[]{departmentName, ManagerName})).request().post(null, String.class);
    }

    public <T> T ViewDepartment(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("ViewDepartment");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public Response editEmployee(Object requestEntity) throws ClientErrorException {
        return webTarget.path("edit").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public String deleteDepartment(Integer departmentid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("deleteDepartment/{0}", new Object[]{departmentid})).request().delete(String.class);
    }

    public <T> T viewHR(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget.path("viewHR");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public Response deleteEmployee(Integer userid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("delete/{0}", new Object[]{userid})).request().delete(Response.class);
    }

    public <T> T searchEmployee(Class<T> responseType, Integer userid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("search/{0}", new Object[]{userid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String getJson() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String addEmployee(String empFirstname, String empLastname, String empEmail, String empPhone, String empAddress, String empGender, String empPassword, Date hireDate, Integer salary, String managerName, Integer depId, Integer groupId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addEmployee/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}/{10}/{11}", new Object[]{empFirstname, empLastname, empEmail, empPhone, empAddress, empGender, empPassword, hireDate, salary, managerName, depId, groupId})).request().post(null, String.class);
    }

    public void close() {
        client.close();
    }

}
