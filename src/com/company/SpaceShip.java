package com.company;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

enum Races {Earthlings, Turiantsi, Khodakeytsi}

public class SpaceShip {
    public int idStation;
    public int id;
    public String name;
    public float mass;
    public Date incomingDate;
    Races race;

    public SpaceShip() {
    }

    public SpaceShip(int id, String name, Date incomingDate, String race, float mass, int idStation) {
        this.id = id;
        this.name = name;
        this.race = Races.valueOf(race);
        this.mass = mass;
        this.incomingDate = incomingDate;
        this.idStation = idStation;
    }

    public SpaceShip(String name, Races race, float mass) {
        this.name = name;
        this.race = race;
        this.mass = mass;
        Date date = new Date();
        incomingDate = date;
        this.idStation = 1;
    }

    public void SpaceShipExport(Station station) {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            List<SpaceShip>_ships=dbHandler.getAllSpaceShips();
            System.out.println("Укажите номер корабля который должен покинуть станцию");
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();
            if (index < 0 || index > _ships.size()) {
                System.out.println("На станции нет корабля с таким номером!");
            } else {
                Main._station01.curShips-=1;
                Main._station01.curMass-=_ships.get(index-1).mass;
                dbHandler.deleteSpaceShip(_ships.get(index-1).id);
                System.out.println("Корабль успешно покинул станцию!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void Info(int index) {
        System.out.println("- "+ index + " | " + name + " | " + race + " | " + mass + " тонн" + " | " + " Дата прибытия: " + incomingDate);
    }
}