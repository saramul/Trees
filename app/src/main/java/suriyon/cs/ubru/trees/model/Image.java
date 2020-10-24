package suriyon.cs.ubru.trees.model;

public class Image {
    private String treeName;
    private String imageName;

    public Image() {
    }

    public Image(String treeName, String imageUrlName) {
        this.treeName = treeName;
        this.imageName = imageUrlName;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
