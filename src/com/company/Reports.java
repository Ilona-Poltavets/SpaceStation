package com.company;

import java.util.Date;

public class Reports {
    public Date reportDate;
    public int reportID;
    public String[] reports ={"Станция перегружена", "Корабль тяжелый", "На станции нет места", "Рассовые конфликты"};
    public String shipName;
    public Float shipMass;

    public Reports(int reportID, SpaceShip ship){
        Date date = new Date();
        reportDate=date;
        this.reportID=reportID;
        this.shipName=ship.name;
        this.shipMass=ship.mass;
    }

    public void Info(){
        System.out.println("-Отчет: " + reportDate + "| " +"Причина: " + reports[reportID]+ "   Корабль: " + shipName+ "  Масса: "+shipMass + " тонн ;");
    }
}
