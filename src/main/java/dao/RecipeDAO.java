package dao;

import model.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/cooking_recipes?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    private static final String INSERT_RECIPES_SQL = "INSERT INTO recipes (name, cooktime, ingredient, inscription, image) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_RECIPE_BY_ID = "SELECT id, name, cooktime, ingredient, inscription, image FROM recipes WHERE id = ?";
    private static final String SELECT_ALL_RECIPES = "SELECT * FROM recipes";
    private static final String DELETE_RECIPES_SQL = "DELETE FROM recipes WHERE id = ?";
    private static final String UPDATE_RECIPES_SQL = "UPDATE recipes SET name = ?, cooktime = ?, ingredient = ?, inscription = ?, image = ? WHERE id = ?";
    private static final String SELECT_ALL_RECIPES_BY_NAME = "SELECT * FROM recipes WHERE name LIKE ?";
    private static final String SELECT_ALL_RECIPES_BY_ID = "SELECT * FROM recipes WHERE id LIKE ?";
    private static final String SORT_ALL_RECIPES_BY_NAME = "SELECT * FROM recipes ORDER BY name";
    private static final String SORT_ALL_RECIPES_BY_ID = "SELECT * FROM recipes ORDER BY id";
    private static final String SELECT_ALL_RECIPES_BY_INGREDIENT = "SELECT * FROM recipes WHERE ingredient LIKE ?";

    public RecipeDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertRecipe(Recipe recipe) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RECIPES_SQL)) {
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getCooktime());
            preparedStatement.setString(3, recipe.getIngredient());
            preparedStatement.setString(4, recipe.getInscription());
            preparedStatement.setString(5, recipe.getImage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Recipe selectRecipe(int id) {
        Recipe recipe = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECIPE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String cooktime = rs.getString("cooktime");
                String ingredient = rs.getString("ingredient");
                String inscription = rs.getString("inscription");
                String image = rs.getString("image");
                recipe = new Recipe(id, name, cooktime, ingredient, inscription, image);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return recipe;
    }

    public List<Recipe> selectAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RECIPES)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String cooktime = rs.getString("cooktime");
                String ingredient = rs.getString("ingredient");
                String inscription = rs.getString("inscription");
                String image = rs.getString("image");
                recipes.add(new Recipe(id, name, cooktime, ingredient, inscription, image));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return recipes;
    }

    public boolean deleteRecipe(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPES_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateRecipe(Recipe recipe) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPES_SQL)) {
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getCooktime());
            statement.setString(3, recipe.getIngredient());
            statement.setString(4, recipe.getInscription());
            statement.setString(5, recipe.getImage());
            statement.setInt(6, recipe.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public List<Recipe> searchRecipesByName(String nameParam) throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RECIPES_BY_NAME)) {
            preparedStatement.setString(1, "%" + nameParam + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String cooktime = rs.getString("cooktime");
                String ingredient = rs.getString("ingredient");
                String inscription = rs.getString("inscription");
                String image = rs.getString("image");
                recipes.add(new Recipe(id, name, cooktime, ingredient, inscription, image));
            }
        }
        return recipes;
    }

    public List<Recipe> searchRecipesById(String idParam) throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RECIPES_BY_ID)) {
            preparedStatement.setString(1, idParam + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String cooktime = rs.getString("cooktime");
                String ingredient = rs.getString("ingredient");
                String inscription = rs.getString("inscription");
                String image = rs.getString("image");
                recipes.add(new Recipe(id, name, cooktime, ingredient, inscription, image));
            }
        }
        return recipes;
    }

    public List<Recipe> searchRecipeByIngredient(String ingredientParam) throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RECIPES_BY_INGREDIENT)) {
            preparedStatement.setString(1, "%" + ingredientParam + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String cooktime = rs.getString("cooktime");
                String ingredient = rs.getString("ingredient");
                String inscription = rs.getString("inscription");
                String image = rs.getString("image");
                recipes.add(new Recipe(id, name, cooktime, ingredient, inscription, image));
            }
        }
        return recipes;
    }

    public List<Recipe> sortRecipeByName(String order) throws SQLException {
        String sql = SORT_ALL_RECIPES_BY_NAME + (order.equalsIgnoreCase("desc") ? " DESC" : " ASC");
        List<Recipe> recipes = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String cooktime = rs.getString("cooktime");
                String ingredient = rs.getString("ingredient");
                String inscription = rs.getString("inscription");
                String image = rs.getString("image");
                recipes.add(new Recipe(id, name, cooktime, ingredient, inscription, image));
            }
        }
        return recipes;
    }

    public List<Recipe> sortRecipeById(String order) throws SQLException {
        String sql = SORT_ALL_RECIPES_BY_ID + (order.equalsIgnoreCase("desc") ? " DESC" : " ASC");
        List<Recipe> recipes = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String cooktime = rs.getString("cooktime");
                String ingredient = rs.getString("ingredient");
                String inscription = rs.getString("inscription");
                String image = rs.getString("image");
                recipes.add(new Recipe(id, name, cooktime, ingredient, inscription, image));
            }
        }
        return recipes;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}