package com.company;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

enum Races{Earthlings, Turiantsi, Khodakeytsi}

public class SpaceShip {
    public String name;
    public float mass;
    public Date incomingDate;
    Races race;

    public SpaceShip(){}

    public SpaceShip(String name, Races race, float mass){
        this.name=name;
        this.race=race;
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
        System.out.println("- " + name + " | " + race + " | "+ mass + " тонн" + " | "+" Дата прибытия: "+ incomingDate);
    }
}