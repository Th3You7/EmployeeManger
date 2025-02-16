package com.employeemanager.controller;

import com.employeemanager.doa.EmployerDao;
import com.employeemanager.model.Employer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/")
public class EmployeeServlet extends HttpServlet {
    EmployerDao employerDao = new EmployerDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // handle all actions
        String action = req.getServletPath();
        String path = req.getContextPath();

        switch (action) {
            case "/new":
                newEmployerForm(req, resp);
                break;
            case "/edit-form":
                editEmployerForm(req, resp);
                break;
            case "/create":
                createEmployee(req, resp);
                break;
            case "/list":
                listEmployees(req, resp);
                break;
            case "/edit":
                editEmployee(req, resp);
                break;
            case "/delete":
                deleteEmployee(req, resp);
                break;
            default:
                resp.sendRedirect(path + "/list");
        }
    }
    private void createEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");

        Employer employer =  new Employer(name, gender, address, phone, email);
        HttpSession session = req.getSession();

        try {
            employerDao.insertEmployee(employer);
            session.setAttribute("message", "Employee added successfully");
            session.setAttribute("messageType", "success");
        }catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("list");
    }
    private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int employerId = Integer.parseInt(id);
        HttpSession session = req.getSession();
        try {
        employerDao.deleteEmployee(employerId);
            session.setAttribute("message", "User deleted successfully");
            session.setAttribute("messageType", "success");
        }catch (Exception e) {
            session.setAttribute("message", e.getMessage());
            session.setAttribute("messageType", "danger");

        }
        resp.sendRedirect("list");
    }
    private void listEmployees(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    ArrayList<Employer> employers = employerDao.getAllEmployers();
    req.setAttribute("employers", employers);
    req.getRequestDispatcher("list.jsp").forward(req, res);

}
    private void newEmployerForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("form.jsp").forward(req, resp);
    }
    private void editEmployerForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int employerId = Integer.parseInt(id);
        Employer employer = employerDao.getEmployer(employerId);
        System.out.println(employer);
        req.setAttribute("employer", employer);
        req.getRequestDispatcher("form.jsp").forward(req, resp);
    }
    private void editEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // get the id
        int employerId = Integer.parseInt(req.getParameter("id"));
        // get the params from the url;
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        HttpSession session = req.getSession();

        try {
            Employer employer = employerDao.getEmployer(employerId);
            employer.setName(name);
            employer.setGender(gender);
            employer.setAddress(address);
            employer.setPhone(phone);
            employer.setEmail(email);
            employerDao.updateEmployee(employer);
            session.setAttribute("message", "Employee edited successfully");
            session.setAttribute("messageType", "success");

        }catch (Exception e) {
            session.setAttribute("message", e.getMessage());
            session.setAttribute("messageType", "danger");

        }

        resp.sendRedirect("list");
    }
}
