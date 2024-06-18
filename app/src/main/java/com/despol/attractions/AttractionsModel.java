package com.despol.attractions;

public class AttractionsModel {
    String attractionName;
    String attractionCountry;
    String attractionCity;
    String attractionCrDate;
    String attractionCategory;
    String description;
    String coverImage;

    public AttractionsModel(String attractionName, String attractionCountry, String attractionCity,
                            String attractionCrDate, String attractionCategory, String description,
                            String coverImage) {
        this.attractionName = attractionName;
        this.attractionCountry = attractionCountry;
        this.attractionCity = attractionCity;
        this.attractionCrDate = attractionCrDate;
        this.attractionCategory = attractionCategory;
        this.description = description;
        this.coverImage = coverImage;
    }

    // getters
    public String getAttractionName() {
        return attractionName;
    }

    public String getAttractionCountry() {
        return attractionCountry;
    }

    public String getAttractionCity() {
        return attractionCity;
    }

    public String getAttractionCrDate() {
        return attractionCrDate;
    }

    public String getAttractionCategory() {
        return attractionCategory;
    }

    public String getDescription() {
        return description;
    }

    public String getCoverImage() {
        return coverImage;
    }
}
