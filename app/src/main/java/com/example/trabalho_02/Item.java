package com.example.trabalho_02;

//import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Item {

    private static int contador = 0;

    private int id;
    private String title;
    private String description;
    private String resume;

    public Item() {
        this.id = contador++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    @Override
    public String toString() {
        return id + ":" + title + '\n' + "resumo: " + resume;
    }

}
