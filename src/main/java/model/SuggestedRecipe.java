package model;

public class SuggestedRecipe extends Recipe{
    private String suggestedBy;
    private String status;

    public SuggestedRecipe() {
        super();
        this.status = "pending";
    }

    public SuggestedRecipe(String name, String cooktime, String ingredient, String inscription, String image, String suggestedBy) {
        super(name, cooktime, ingredient, inscription, image);
        this.suggestedBy = suggestedBy;
        this.status = "pending";
    }

    public SuggestedRecipe(int id, String name, String cooktime, String ingredient, String inscription, String image, String suggestedBy) {
        super(id, name, cooktime, ingredient, inscription, image);
        this.suggestedBy = suggestedBy;
        this.status = "pending";
    }


    public String getSuggestedBy() {
        return suggestedBy;
    }

    public void setSuggestedBy(String suggestedBy) {
        this.suggestedBy = suggestedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
