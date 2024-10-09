package controller;

import model.Recipe;
import model.SuggestedRecipe;
import service.AdminService;
import service.RecipeService;
import service.ShowForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "RecipeServlet", urlPatterns = {"/recipes", "/suggestRecipe", "/approveSuggestedRecipes"})
public class RecipeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RecipeService recipeService;
    private static final int RECORDS_PER_PAGE = 5;

    public void init() {
        recipeService = new RecipeService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    recipeService.insertRecipe(request);
                    response.sendRedirect("recipes");
                    break;
                case "edit":
                    recipeService.updateRecipe(request);
                    response.sendRedirect("recipes");
                    break;
                case "suggestRecipe":
                    recipeService.suggestRecipe(request);
                    response.sendRedirect("recipes");
                    break;
                case "approveSuggestedRecipe":
                    recipeService.approveSuggestedRecipe(request);
                    response.sendRedirect("recipes?action=approveSuggestedRecipes");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                case "edit":
                case "delete":
                    if (!AdminService.isAdminLoggedIn(request)) {
                        response.sendRedirect("login.jsp");
                        return;
                    }
                    break;
            }

            switch (action) {
                case "create":
                    ShowForm.showNewForm(request, response);
                    break;
                case "edit":
                    ShowForm.showEditForm(request, response);
                    break;
                case "delete":
                    recipeService.deleteRecipe(Integer.parseInt(request.getParameter("id")));
                    response.sendRedirect("recipes");
                    break;
                case "details":
                    ShowForm.showRecipeDetails(request, response);
                    break;
                case "suggestRecipe":
                    ShowForm.showSuggestRecipeForm(request, response);
                    break;
                case "approveSuggestedRecipes":
                    if (!AdminService.isAdminLoggedIn(request)) {
                        response.sendRedirect("login.jsp");
                        return;
                    }
                    ShowForm.showApproveSuggestedRecipesForm(request, response);
                    break;
                default:
                    listRecipes(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listRecipes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        String typeSearch = request.getParameter("searchtype");
        String keyword = request.getParameter("keyword");
        String sortBy = request.getParameter("sortby");
        String order = request.getParameter("order");

        List<Recipe> listRecipe = recipeService.listRecipes(typeSearch, keyword, sortBy, order);

        int totalRecords = listRecipe.size();
        int totalPages = (int) Math.ceil((double) totalRecords / RECORDS_PER_PAGE);

        int start = (page - 1) * RECORDS_PER_PAGE;
        int end = Math.min(start + RECORDS_PER_PAGE, totalRecords);

        List<Recipe> pageRecipes = listRecipe.subList(start, end);

        request.setAttribute("listRecipe", pageRecipes);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("recordsPerPage", RECORDS_PER_PAGE);
        request.setAttribute("totalRecords", totalRecords);
        request.setAttribute("searchtype", typeSearch);
        request.setAttribute("keyword", keyword);
        request.setAttribute("sortby", sortBy);
        request.setAttribute("order", order);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/list.jsp");
        dispatcher.forward(request, response);
    }
}