package com.example.fruitstore;

public class Fruit {
    private int imageId;
    private  String  txtValue;
    private  String price;

    public Fruit(int imageId, String txtValue, String price){
        this.imageId = imageId;
        this.txtValue = txtValue;
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTxtValue() {
        return txtValue;
    }

    public String getPrice() {
        return price;
    }
}
