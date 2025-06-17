package com.ios7.wallify;

public class Developer {
    private String name;
    private String imageUrl;
    private String devUrl;

    // Constructor to initialize the Developer object
    public Developer(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.devUrl = devUrl;
    }

    // Getter for the name
    public String getName() {
        return name;
    }

    // Getter for the image URL
    public String getImageUrl() {
        return imageUrl;
    }
    public String getDevUrl() {
        return devUrl;
    }
}

