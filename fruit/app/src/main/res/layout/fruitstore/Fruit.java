package com.example.fruitstore;

public class Fruit {
    private int imageId;
    private  String  txtValue;

    public Fruit(int imageId, String txtValue){
        this.imageId = imageId;
        this.txtValue = txtValue;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTxtValue() {
        return txtValue;
    }


}
