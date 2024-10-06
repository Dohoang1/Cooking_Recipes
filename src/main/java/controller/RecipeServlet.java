package controller;

import dao.RecipeDAO;
import model.Recipe;

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

@WebServlet(name = "RecipeServlet", urlPatterns = "/recipes")
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
}