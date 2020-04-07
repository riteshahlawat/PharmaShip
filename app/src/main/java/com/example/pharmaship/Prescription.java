package com.example.pharmaship;

import android.graphics.Bitmap;

public class Prescription {
    private String name;
    private String status;
    private String dateOfArrival;
    private Bitmap photo;

    public Prescription() {

    }
    public Prescription(String name, String status, String dateOfArrival, Bitmap photo) {
        this.name = name;
        this.status = status;
        this.dateOfArrival = dateOfArrival;
        this.photo = photo;
    }
    // Getter

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getDateOfArrival() {
        return dateOfArrival;
    }

    public Bitmap getPhoto() {
        return photo;
    }
    // Setter

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDateOfArrival(String dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
