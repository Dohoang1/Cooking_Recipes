package service;

import dao.RecipeDAO;
import model.Recipe;
import model.SuggestedRecipe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowForm {

    public static void showRegisterForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
    }

    public static void showLoginForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String successMessage = (String) session.getAttribute("successMessage");
        if (successMessage != null) {
            request.setAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    public static void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create.jsp");
        dispatcher.forward(request, response);
    }

    public static void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        RecipeDAO recipeDAO = new RecipeDAO();
        Recipe existingRecipe = recipeDAO.selectRecipe(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/edit.jsp");
        request.setAttribute("recipe", existingRecipe);
        dispatcher.forward(request, response);
    }

    public static void showRecipeDetails(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String type = request.getParameter("type");
        RecipeDAO recipeDAO = new RecipeDAO();
        if ("suggested".equals(type)) {
            SuggestedRecipe recipe = recipeDAO.selectSuggestedRecipe(id);
            request.setAttribute("recipe", recipe);
            request.setAttribute("isSuggested", true);
        } else {
            Recipe recipe = recipeDAO.selectRecipe(id);
            request.setAttribute("recipe", recipe);
            request.setAttribute("isSuggested", false);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/recipeDetails.jsp");
        dispatcher.forward(request, response);
    }

    public static void showSuggestRecipeForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/suggestRecipe.jsp");
        dispatcher.forward(request, response);
    }

    public static void showApproveSuggestedRecipesForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        RecipeDAO recipeDAO = new RecipeDAO();
        List<SuggestedRecipe> suggestedRecipes = recipeDAO.selectAllSuggestedRecipes();
        request.setAttribute("suggestedRecipes", suggestedRecipes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/approveSuggestedRecipes.jsp");
        dispatcher.forward(request, response);
    }
}