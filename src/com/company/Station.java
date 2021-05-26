package com.company;

import java.sql.SQLException;
import java.util.List;

public class Station {
    public int id;
    public String name;
    public int maxShips;
    public int curShips;
    public float maxMass;
    public float curMass;

    public Station(int id, String name, int maxShips, int curShips, float maxMass, float curMass) {
        this.id = id;
        this.name = name;
        this.maxMass = maxMass;
        this.curMass = curMass;
        this.maxShips = maxShips;
        this.curShips = curShips;
    }

    public Station(int maxShips, float maxMass, String name) {
        id = 1;
        this.name = name;
        this.maxMass = maxMass;
        this.maxShips = maxShips;
        curShips = 0;
        curMass = 0;
    }

    public boolean StationManager(SpaceShip ship) {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            List<SpaceShip> _ships = dbHandler.getAllSpaceShips();
            Type type;
            boolean isFool = false;
            boolean massIsLimited = false;
            boolean racesIsAccess = false;
            boolean isAccept = false;
            Reports rp = new Reports();
            if (curShips >= maxShips) {
                System.out.println("Количество кораблей на станции достигло максимума!");
                dbHandler.addReport(new Reports(Type.Station_overloaded, ship));
                return isAccept;
            }
            if (curMass >= maxMass) {
                System.out.println("Сумарная масса кораблей находящихся на станции достигла своего предела!");
                dbHandler.addReport(new Reports(Type.Ship_heavy, ship));
                return isAccept;
            }
            if (ship.mass > maxMass - curMass) {
                System.out.println("Этот корабль слишком тяжелый!");
                dbHandler.addReport(new Reports(Type.At_station_no_place, ship));
                return isAccept;
            }
            if (curShips >= 2) {
                int count0 = 0;
                int count1 = 0;
                int count2 = 0;
                for (int i = 0; i < _ships.size(); i++) {
                    if (_ships.get(i).race == Races.Earthlings) {
                        count0 += 1;
                    } else if (_ships.get(i).race == Races.Turiantsi) {
                        count1 += 1;
                    } else {
                        count2 += 1;
                    }
                }
                if (ship.race == Races.Earthlings) {
                    if (count0 < count1) {
                        System.out.println("В доступе отказано из за рассовых разногласий!");
                        dbHandler.addReport(new Reports(Type.Racial_conflicts, ship));
                        return isAccept;
                    }
                } else if (ship.race == Races.Turiantsi) {
                    if (count1 < count2) {
                        System.out.println("В доступе отказано из за рассовых разногласий!");
                        dbHandler.addReport(new Reports(Type.Racial_conflicts, ship));
                        return isAccept;
                    }
                }
            }
            if (!isFool && !massIsLimited && !racesIsAccess) {
                isAccept = true;
                System.out.println("Корабль успешно прошел проверку!");
            }
            return isAccept;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void StationShipsInfo() {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            List<SpaceShip> spaceShips = dbHandler.getAllSpaceShips();
            int i=1;
            for (SpaceShip spaceShip : spaceShips) {
                spaceShip.Info(i);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}