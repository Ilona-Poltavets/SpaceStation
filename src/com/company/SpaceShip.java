package com.company;

import java.util.Date;
import java.util.List;

public class SpaceShip {
    public String name;
    public float mass;
    public int raceID;
    public Date incomingDate;
    static String[] races = {"Земляни", "Турианци", "Ходакейци"};

    public SpaceShip(String name, int raceID, float mass){
        this.name=name;
        this.raceID=raceID;
        this.mass=mass;
        Date date = new Date();
        incomingDate=date;
    }

    public void Info(){
        System.out.println("- " + name + " | " + races[raceID] + " | "+ mass + " тонн" + " | "+" Дата прибытия: "+ incomingDate);
    }
}
