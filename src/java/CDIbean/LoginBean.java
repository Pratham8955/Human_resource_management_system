/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIbean;

//import Client.UserClient;
//import Entity.Users;
import EJB.AdminSessionBeanLocal;
import Entity.Hrmsemployees;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import secretkey.JwtUtil;

/**
 *
 * @author Lenovo
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static final Logger logger = Logger.getLogger(LoginBean.class.getName());

    @EJB
    private AdminSessionBeanLocal adminSessionBean;

    private String firstname;
    private String password;
    private String jwtToken;
    private String msg;
    private SecretKey secretKey = JwtUtil.getSecretKey();

    public LoginBean() {

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public String login() {
        try {
            Hrmsemployees user = adminSessionBean.validateProvider(firstname, password);

            if (user != null) {
                jwtToken = generateJwtToken(user);

                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                session = (HttpSession) facesContext.getExternalContext().getSession(true);

                session.setAttribute("jwtToken", jwtToken);
                session.setAttribute("role", user.getGroupId().getRole());
                session.setAttribute("userId", user);
//                System.out.println(user);
//                System.out.println("cdi secret key:::" + secretKey);
                if ("Employee".equals(user.getGroupId().getRole())) {
                    return "/Secure/Employee/newTemplate1.xhtml?faces-redirect=true";
                } else if ("Admin".equals(user.getGroupId().getRole())) {
                    return "/Secure/Admin/newTemplate.xhtml?faces-redirect=true";
                } else if ("Manager".equals(user.getGroupId().getRole())) {
                    return "/Secure/Manager/newTemplate2.xhtml?faces-redirect=true";
                } else {
                    msg = "Invalid role.";
                    return "login.xhtml";
                }
            } else {
                msg = "Invalid email or password.";
                return "login.xhtml";
            }
        } catch (Exception e) {
            msg = "An error occurred during login.";
            return "error.xhtml";
        }
    }

    public String logout() {
        try {
            // Log the logout activity
            Logger.getLogger(LoginBean.class.getName()).info("User logged out: " + firstname);

            // Invalidate the session
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().invalidateSession();

            // Construct the correct redirect URL
            String contextPath = facesContext.getExternalContext().getRequestContextPath();
            facesContext.getExternalContext().redirect(contextPath + "/faces/login.xhtml");
        } catch (IOException e) {
            Logger.getLogger(LoginBean.class.getName()).severe("Logout redirection failed: " + e.getMessage());
            return null; // Stay on the same page if redirection fails
        }

        return null; // No need for JSF navigation when using externalContext.redirect
    }

    private String generateJwtToken(Hrmsemployees user) {
        return Jwts.builder()
                .setSubject(user.getEmpFirstname())
                .claim("role", user.getGroupId().getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24-hour validity
                .signWith(secretKey)
                .compact();

    }

}
