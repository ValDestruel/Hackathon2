package fr.wcs.laboutiquedewatto;

public class ProfilModel {

    private String name;
    private double height;
    private int mass;
    private String gender;
    private String species;
    private String homeworld;
    private String image;
    private String hairColor;
    private String eyeColor;

    public ProfilModel() {}

    public ProfilModel(String name, double height, int mass, String gender, String species, String homeworld, String image, String hairColor, String eyeColor) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.gender = gender;
        this.species = species;
        this.homeworld = homeworld;
        this.image = image;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }
}
