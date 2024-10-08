package controller;

import dao.RecipeDAO;
import model.Recipe;
import model.SuggestedRecipe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "RecipeServlet", urlPatterns = {"/recipes", "/suggestRecipe", "/approveSuggestedRecipes"})
public class RecipeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RecipeDAO recipeDAO;
    private static final int RECORDS_PER_PAGE = 5;

    public void init() {
        recipeDAO = new RecipeDAO();
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
                    insertRecipe(request, response);
                    break;
                case "edit":
                    updateRecipe(request, response);
                    break;
                case "suggestRecipe":
                    suggestRecipe(request, response);
                    break;
                case "approveSuggestedRecipe":
                    approveSuggestedRecipe(request, response);
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
                    if (!isAdminLoggedIn(request)) {
                        response.sendRedirect("login.jsp");
                        return;
                    }
                    break;
            }

            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteRecipe(request, response);
                    break;
                case "details":
                    showRecipeDetails(request, response);
                    break;
                case "suggestRecipe":
                    showSuggestRecipeForm(request, response);
                    break;
                case "approveSuggestedRecipes":
                    if (!isAdminLoggedIn(request)) {
                        response.sendRedirect("login.jsp");
                        return;
                    }
                    showApproveSuggestedRecipesForm(request, response);
                    break;
                default:
                    listRecipes(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private boolean isAdminLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("admin") != null;
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

        List<Recipe> listRecipe;
        int totalRecords;

        if (typeSearch != null && keyword != null && !keyword.isEmpty()) {
            listRecipe = searchRecipes(typeSearch, keyword);
        } else if (sortBy != null && order != null) {
            listRecipe = sortRecipes(sortBy, order);
        } else {
            listRecipe = recipeDAO.selectAllRecipes();
        }

        totalRecords = listRecipe.size();
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

    private List<Recipe> searchRecipes(String typeSearch, String keyword) throws SQLException {
        switch (typeSearch) {
            case "name":
                return recipeDAO.searchRecipesByName(keyword);
            case "id":
                return recipeDAO.searchRecipesById(keyword);
            case "ingredient":
                return recipeDAO.searchRecipeByIngredient(keyword);
            default:
                return recipeDAO.selectAllRecipes();
        }
    }

    private List<Recipe> sortRecipes(String sortBy, String order) throws SQLException {
        switch (sortBy) {
            case "ID":
                return recipeDAO.sortRecipeById(order);
            case "Name":
                return recipeDAO.sortRecipeByName(order);
            default:
                return recipeDAO.selectAllRecipes();
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Recipe existingRecipe = recipeDAO.selectRecipe(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/edit.jsp");
        request.setAttribute("recipe", existingRecipe);
        dispatcher.forward(request, response);
    }

    private void insertRecipe(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String name = request.getParameter("name");
        String cooktime = request.getParameter("cooktime");
        String ingredient = request.getParameter("ingredient");
        String inscription = request.getParameter("inscription");
        String image = request.getParameter("image");
        Recipe newRecipe = new Recipe(name, cooktime, ingredient, inscription, image);
        recipeDAO.insertRecipe(newRecipe);
        response.sendRedirect("recipes");
    }

    private void updateRecipe(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String cooktime = request.getParameter("cooktime");
        String ingredient = request.getParameter("ingredient");
        String inscription = request.getParameter("inscription");
        String image = request.getParameter("image");
        Recipe updatedRecipe = new Recipe(id, name, cooktime, ingredient, inscription, image);
        recipeDAO.updateRecipe(updatedRecipe);
        response.sendRedirect("recipes");
    }

    private void deleteRecipe(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        recipeDAO.deleteRecipe(id);
        response.sendRedirect("recipes");
    }

    private void showSuggestRecipeForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/suggestRecipe.jsp");
        dispatcher.forward(request, response);
    }

    private void suggestRecipe(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String cooktime = request.getParameter("cooktime");
        String ingredient = request.getParameter("ingredient");
        String inscription = request.getParameter("inscription");
        String image = request.getParameter("image");
        String suggestedBy = (String) request.getSession().getAttribute("user");

        SuggestedRecipe newRecipe = new SuggestedRecipe(name, cooktime, ingredient, inscription, image, suggestedBy);
        recipeDAO.insertSuggestedRecipe(newRecipe);
        response.sendRedirect("recipes");
    }

    private void showApproveSuggestedRecipesForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<SuggestedRecipe> suggestedRecipes = recipeDAO.selectAllSuggestedRecipes();
        request.setAttribute("suggestedRecipes", suggestedRecipes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/approveSuggestedRecipes.jsp");
        dispatcher.forward(request, response);
    }

    private void showRecipeDetails(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Recipe recipe = recipeDAO.selectRecipe(id);
        request.setAttribute("recipe", recipe);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/recipeDetails.jsp");
        dispatcher.forward(request, response);
    }

    private void approveSuggestedRecipe(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");

        SuggestedRecipe suggestedRecipe = recipeDAO.selectSuggestedRecipe(id);
        if (suggestedRecipe != null) {
            if ("approved".equals(status)) {
                Recipe newRecipe = new Recipe(suggestedRecipe.getName(), suggestedRecipe.getCooktime(),
                        suggestedRecipe.getIngredient(), suggestedRecipe.getInscription(), suggestedRecipe.getImage());
                recipeDAO.insertRecipe(newRecipe);
            }

            recipeDAO.updateSuggestedRecipeStatus(id, status);
        }
        response.sendRedirect("recipes?action=approveSuggestedRecipes");
    }
}