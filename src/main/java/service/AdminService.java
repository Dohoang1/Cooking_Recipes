package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminService {
    public static boolean isAdminLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("admin") != null;
    }
}
