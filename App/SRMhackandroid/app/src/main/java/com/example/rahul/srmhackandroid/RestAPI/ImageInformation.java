package com.example.rahul.srmhackandroid.RestAPI;

public class ImageInformation {
    String string;
    int fbool;
    String ext;

    public ImageInformation(String string, int fbool, String ext) {
        this.string = string;
        this.fbool = fbool;
        this.ext = ext;
    }

    public ImageInformation() {
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getFbool() {
        return fbool;
    }

    public void setFbool(int fbool) {
        this.fbool = fbool;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
