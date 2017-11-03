package com.Inspira.odo.model;

/**
 * Created by shirya on 03/11/17.
 */

public class SellerHomeData {

    private int Image;
    private String NameRequest;
    private String NameCar;
    private String TypeCare;
    private String YearCar ;
    private String ModleCare ;
    private String Colorcar ;
    private String TimePost ;
    private boolean Favorite ;

    public SellerHomeData(int image, String nameRequest, String nameCar,
                          String typeCare, String yearCar, String modleCare,
                          String colorcar, String timePost, boolean favorite) {
        Image = image;
        NameRequest = nameRequest;
        NameCar = nameCar;
        TypeCare = typeCare;
        YearCar = yearCar;
        ModleCare = modleCare;
        Colorcar = colorcar;
        TimePost = timePost;
        Favorite = favorite;
    }


    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getNameRequest() {
        return NameRequest;
    }

    public void setNameRequest(String nameRequest) {
        NameRequest = nameRequest;
    }

    public String getNameCar() {
        return NameCar;
    }

    public void setNameCar(String nameCar) {
        NameCar = nameCar;
    }

    public String getTypeCare() {
        return TypeCare;
    }

    public void setTypeCare(String typeCare) {
        TypeCare = typeCare;
    }

    public String getYearCar() {
        return YearCar;
    }

    public void setYearCar(String yearCar) {
        YearCar = yearCar;
    }

    public String getModleCare() {
        return ModleCare;
    }

    public void setModleCare(String modleCare) {
        ModleCare = modleCare;
    }

    public String getColorcar() {
        return Colorcar;
    }

    public void setColorcar(String colorcar) {
        Colorcar = colorcar;
    }

    public String getTimePost() {
        return TimePost;
    }

    public void setTimePost(String timePost) {
        TimePost = timePost;
    }

    public boolean isFavorite() {
        return Favorite;
    }

    public void setFavorite(boolean favorite) {
        Favorite = favorite;
    }
}
