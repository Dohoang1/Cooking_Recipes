package model;

public class Recipe {
    protected int id;
    protected String name;
    protected String cooktime;
    protected String ingredient;
    protected String inscription;
    protected String image;

    public Recipe() {}

    public Recipe(String name, String cooktime, String ingredient, String inscription, String image) {
        super();
        this.name = name;
        this.cooktime = cooktime;
        this.ingredient = ingredient;
        this.inscription = inscription;
        this.image = image;
    }

    public Recipe(int id, String name, String cooktime, String ingredient, String inscription, String image) {
        super();
        this.id = id;
        this.name = name;
        this.cooktime = cooktime;
        this.ingredient = ingredient;
        this.inscription = inscription;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCooktime() {
        return cooktime;
    }

    public void setCooktime(String cooktime) {
        this.cooktime = cooktime;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getInscription() {
        return inscription;
    }

    public void setInscription(String inscription) {
        this.inscription = inscription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}