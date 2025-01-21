package com.ios7.wallify;

public class Developer {
    private String name;
    private String imageUrl;

    // Constructor to initialize the Developer object
    public Developer(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    // Getter for the name
    public String getName() {
        return name;
    }

    // Getter for the image URL
    public String getImageUrl() {
        return imageUrl;
    }
}

