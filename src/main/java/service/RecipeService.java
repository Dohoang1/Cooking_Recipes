package service;

import dao.RecipeDAO;
import model.Recipe;
import model.SuggestedRecipe;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class RecipeService {
    private RecipeDAO recipeDAO;

    public RecipeService() {
        this.recipeDAO = new RecipeDAO();
    }

    public List<Recipe> listRecipes(String typeSearch, String keyword, String sortBy, String order) throws SQLException {
        if (typeSearch != null && keyword != null && !keyword.isEmpty()) {
            return searchRecipes(typeSearch, keyword);
        } else if (sortBy != null && order != null) {
            return sortRecipes(sortBy, order);
        } else {
            return recipeDAO.selectAllRecipes();
        }
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

    public void insertRecipe(HttpServletRequest request) throws SQLException {
        String name = request.getParameter("name");
        String cooktime = request.getParameter("cooktime");
        String ingredient = request.getParameter("ingredient");
        String inscription = request.getParameter("inscription");
        String image = request.getParameter("image");
        Recipe newRecipe = new Recipe(name, cooktime, ingredient, inscription, image);
        recipeDAO.insertRecipe(newRecipe);
    }

    public void updateRecipe(HttpServletRequest request) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String cooktime = request.getParameter("cooktime");
        String ingredient = request.getParameter("ingredient");
        String inscription = request.getParameter("inscription");
        String image = request.getParameter("image");
        Recipe updatedRecipe = new Recipe(id, name, cooktime, ingredient, inscription, image);
        recipeDAO.updateRecipe(updatedRecipe);
    }

    public void deleteRecipe(int id) throws SQLException {
        recipeDAO.deleteRecipe(id);
    }

    public void suggestRecipe(HttpServletRequest request) throws SQLException {
        String name = request.getParameter("name");
        String cooktime = request.getParameter("cooktime");
        String ingredient = request.getParameter("ingredient");
        String inscription = request.getParameter("inscription");
        String image = request.getParameter("image");
        String suggestedBy = (String) request.getSession().getAttribute("user");

        SuggestedRecipe newRecipe = new SuggestedRecipe();
        newRecipe.setName(name);
        newRecipe.setCooktime(cooktime);
        newRecipe.setIngredient(ingredient);
        newRecipe.setInscription(inscription);
        newRecipe.setImage(image);
        newRecipe.setSuggestedBy(suggestedBy);
        newRecipe.setStatus("pending");

        recipeDAO.insertSuggestedRecipe(newRecipe);
    }

    public void approveSuggestedRecipe(HttpServletRequest request) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");
        SuggestedRecipe suggestedRecipe = recipeDAO.selectSuggestedRecipe(id);
        if (suggestedRecipe != null) {
            if ("approved".equals(status)) {
                Recipe newRecipe = new Recipe(suggestedRecipe.getName(), suggestedRecipe.getCooktime(),
                        suggestedRecipe.getIngredient(), suggestedRecipe.getInscription(), suggestedRecipe.getImage());
                recipeDAO.insertRecipe(newRecipe);
            }
            recipeDAO.deleteSuggestedRecipe(id);
        }
    }
}