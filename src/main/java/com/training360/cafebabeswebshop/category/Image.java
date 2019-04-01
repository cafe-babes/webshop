package com.training360.cafebabeswebshop.category;

import java.io.InputStream;

public class Image {
    String filename;
    InputStream binary;

    public Image(String filename) {
        this(filename,null);
    }
    public Image(String filename, InputStream binary) {
        this.filename = filename;
        this.binary = binary;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public InputStream getBinary() {
        return binary;
    }

    public void setBinary(InputStream binary) {
        this.binary = binary;
    }
}
