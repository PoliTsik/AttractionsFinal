package com.despol.attractions;

public class AttractionsModel {
    private int id;
    private String attractionName;
    private String attractionCountry;
    private String attractionCity;
    private String attractionCrDate;
    private String attractionCategory;
    private String description;
    private String coverImage;

    public AttractionsModel() {}

    public AttractionsModel(int id, String attractionName, String attractionCountry, String attractionCity,
                            String attractionCrDate, String attractionCategory, String description,
                            String coverImage) {
        this.id = id;
        this.attractionName = attractionName;
        this.attractionCountry = attractionCountry;
        this.attractionCity = attractionCity;
        this.attractionCrDate = attractionCrDate;
        this.attractionCategory = attractionCategory;
        this.description = description;
        this.coverImage = coverImage;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    public String getAttractionCountry() {
        return attractionCountry;
    }

    public void setAttractionCountry(String attractionCountry) {
        this.attractionCountry = attractionCountry;
    }

    public String getAttractionCity() {
        return attractionCity;
    }

    public void setAttractionCity(String attractionCity) {
        this.attractionCity = attractionCity;
    }

    public String getAttractionCrDate() {
        return attractionCrDate;
    }

    public void setAttractionCrDate(String attractionCrDate) {
        this.attractionCrDate = attractionCrDate;
    }

    public String getAttractionCategory() {
        return attractionCategory;
    }

    public void setAttractionCategory(String attractionCategory) {
        this.attractionCategory = attractionCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
