package com.flats.talkingflatsapp.order;

public class Order {

    String timeView, typeCar, typeOfWorkView, timeOfWork;

    public Order(String timeView, String typeCar, String typeOfWorkView, String timeOfWork) {
        this.timeView = timeView;
        this.typeCar = typeCar;
        this.typeOfWorkView = typeOfWorkView;
        this.timeOfWork = timeOfWork;
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

    public String getTypeOfWorkView() {
        return typeOfWorkView;
    }

    public void setTypeOfWorkView(String typeOfWorkView) {
        this.typeOfWorkView = typeOfWorkView;
    }

    public String getTimeOfWork() {
        return timeOfWork;
    }

    public void setTimeOfWork(String timeOfWork) {
        this.timeOfWork = timeOfWork;
    }
}
