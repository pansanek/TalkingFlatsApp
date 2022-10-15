package com.flats.talkingflatsapp.order;

public class Order {

    String timeView, typeCar,  timeOfWork,address;

    public Order(String timeView, String typeCar, String timeOfWork,String address) {
        this.timeView = timeView;
        this.typeCar = typeCar;
        this.timeOfWork = timeOfWork;
        this.address  = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimeView() {
        return timeView;
    }

    public void setTimeView(String timeView) {
        this.timeView = timeView;
    }

    public String getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(String typeCar) {
        this.typeCar = typeCar;
    }


    public String getTimeOfWork() {
        return timeOfWork;
    }

    public void setTimeOfWork(String timeOfWork) {
        this.timeOfWork = timeOfWork;
    }
}
