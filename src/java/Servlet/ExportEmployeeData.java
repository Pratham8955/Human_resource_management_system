/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author pratham
 */
@WebServlet(name = "ExportEmployeeData", urlPatterns = {"/exportemp.fin"})
public class ExportEmployeeData extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=EmployeeData.xlsx");
        String url = "jdbc:mysql://localhost:3306/hrms?useSSL=false";
        String username = "root";
        String password = "palak8955";
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            PreparedStatement stmt = conn.prepareStatement(" select * from hrmsemployees");

            ResultSet rs = stmt.executeQuery();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("EmployeeData");

//             for the header
            Row hr = sheet.createRow(0);

            Cell c1 = hr.createCell(0);
            c1.setCellValue("First Name");
            Cell c2 = hr.createCell(1);
            c2.setCellValue("Last Name");
            Cell c3 = hr.createCell(2);
            c3.setCellValue("Email");
            Cell c4 = hr.createCell(3);
            c4.setCellValue("Contact Number");
            Cell c5 = hr.createCell(4);
            c5.setCellValue("Address");
            Cell c6 = hr.createCell(5);
            c6.setCellValue("Gender");
            Cell c7 = hr.createCell(6);
            c7.setCellValue("Password");
            Cell c8 = hr.createCell(7);
            c8.setCellValue("Hire Date");
            Cell c9 = hr.createCell(8);
            c9.setCellValue("Salary");
            Cell c10 = hr.createCell(9);
            c10.setCellValue("Manager Name");
            Cell c11 = hr.createCell(10);
            c11.setCellValue("Department Name");

            int rowNumber = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowNumber++);
                Cell fnameCell = row.createCell(0);
                fnameCell.setCellValue(rs.getString("emp_firstname"));
                Cell lnameCell = row.createCell(1);
                lnameCell.setCellValue(rs.getString("emp_lastname"));
                Cell emailCell = row.createCell(2);
                emailCell.setCellValue(rs.getString("emp_email"));
                Cell phoneCell = row.createCell(3);
                phoneCell.setCellValue(rs.getString("emp_phone"));
                Cell addressCell = row.createCell(4);
                addressCell.setCellValue(rs.getString("emp_address"));
                Cell genderCell = row.createCell(5);
                genderCell.setCellValue(rs.getString("emp_gender"));
                Cell passwordcell = row.createCell(6);
                passwordcell.setCellValue(rs.getString("emp_password"));
                Cell hiredateCell = row.createCell(7);
                hiredateCell.setCellValue(rs.getString("hire_date"));
                Cell salaryCell = row.createCell(8);
                salaryCell.setCellValue(rs.getString("salary"));
                Cell managerCell = row.createCell(9);
                managerCell.setCellValue(rs.getString("manager_name"));

                if (managerCell.toString() == "") {
                    managerCell.setCellValue("-");
                }

                Cell deptCell = row.createCell(10);
                deptCell.setCellValue(rs.getString("dept_id"));
            }
            try {
                OutputStream outputStream = response.getOutputStream();
                workbook.write(outputStream);
                if (outputStream != null) {
                    System.out.println("File is exported");
                } else {
                    System.out.println("File does not exported");
                }
            } catch (Exception e) {
                e.getMessage();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getMessage();
//            response.getWriter().write("Error exoprting data to Excel");
        } finally {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
