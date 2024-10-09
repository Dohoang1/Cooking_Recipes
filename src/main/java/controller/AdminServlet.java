package controller;

import service.UserService;
import service.ShowForm;
import dao.AdminDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminServlet", urlPatterns = {"/login", "/logout", "/register"})
public class AdminServlet extends HttpServlet {
    private AdminDAO adminDAO;
    private UserService userService;

    public void init() {
        adminDAO = new AdminDAO();
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();

        switch (action) {
            case "/register":
                userService.registerUser(request, response);
                break;
            case "/login":
                userService.loginUser(request, response);
                break;
            default:
                response.sendRedirect("recipes");
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/logout":
                userService.logoutUser(request, response);
                break;
            case "/register":
                ShowForm.showRegisterForm(request, response);
                break;
            case "/login":
                ShowForm.showLoginForm(request, response);
                break;
            default:
                response.sendRedirect("recipes");
                break;
        }
    }
}