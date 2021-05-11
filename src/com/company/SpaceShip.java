package com.company;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SpaceShip {
    public String name;
    public float mass;
    public int raceID;
    public Date incomingDate;
    static String[] races = {"Земляни", "Турианци", "Ходакейци"};

    public SpaceShip(){}

    public SpaceShip(String name, int raceID, float mass){
        this.name=name;
        this.raceID=raceID;
        this.mass=mass;
        Date date = new Date();
        incomingDate=date;
    }

    public void SpaceShipImport(Station station, SpaceShip ship){
        station._ships.add(ship);
        station.curShips+=1;
        station.curMass+=ship.mass;
    }

    public void SpaceShipExport(Station station){
        System.out.println("Укажите номер корабля который должен покинуть станцию");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        if(index <0 || index > station._ships.size()){
            System.out.println("На станции нет корабля с таким номером!");
        }else{
            station.curShips-=1;
            station.curMass-=station._ships.get(index).mass;
            station._ships.remove(index);
            System.out.println("Корабль успешно покинул станцию!");
        }
    }

    public void Info(){
        System.out.println("- " + name + " | " + races[raceID] + " | "+ mass + " тонн" + " | "+" Дата прибытия: "+ incomingDate);
    }
}