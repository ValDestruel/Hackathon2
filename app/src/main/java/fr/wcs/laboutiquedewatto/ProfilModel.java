package fr.wcs.laboutiquedewatto;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfilModel implements Parcelable{

    private String name;
    private String gender;
    private String species;
    private String homeworld;
    private String image;
    private String hairColor;
    private String eyeColor;
    private String skinColor;
    private String price;

    public ProfilModel() {
    }

    public ProfilModel(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public ProfilModel(String name, String gender, String species, String homeworld, String image, String hairColor, String eyeColor, String skinColor, String price) {
        this.name = name;

        this.gender = gender;
        this.species = species;
        this.homeworld = homeworld;
        this.image = image;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.skinColor = skinColor;
        this.price = price;
    }

    protected ProfilModel(Parcel in) {
        name = in.readString();
        gender = in.readString();
        species = in.readString();
        homeworld = in.readString();
        image = in.readString();
        hairColor = in.readString();
        eyeColor = in.readString();
        skinColor = in.readString();
        price = in.readString();
    }

    public static final Creator<ProfilModel> CREATOR = new Creator<ProfilModel>() {
        @Override
        public ProfilModel createFromParcel(Parcel in) {
            return new ProfilModel(in);
        }

        @Override
        public ProfilModel[] newArray(int size) {
            return new ProfilModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(species);
        dest.writeString(homeworld);
        dest.writeString(image);
        dest.writeString(hairColor);
        dest.writeString(eyeColor);
        dest.writeString(skinColor);
        dest.writeString(price);
    }
}
