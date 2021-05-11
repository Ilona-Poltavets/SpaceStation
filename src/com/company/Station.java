package com.company;

import java.awt.image.AreaAveragingScaleFilter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Station {
    public String name;
    public int maxShips;
    public int curShips;
    public float maxMass;
    public float curMass;
    ArrayList<SpaceShip> _ships = new ArrayList<SpaceShip>();

    public Station (int maxShips, float maxMass, String name){
        this.name=name;
        this.maxMass=maxMass;
        this.maxShips=maxShips;
        curShips=0;
        curMass=0;
    }

    public boolean StationManager(SpaceShip ship){
        Type type;
        boolean isFool=false;
        boolean massIsLimited=false;
        boolean racesIsAccess=false;
        boolean isAccept=false;
        Reports rp=new Reports();
        if(curShips>=maxShips){
            type=Type.Station_overloaded;
            isFool=true;
            rp.addReport(type,ship);
            return isAccept;
        }
        if(curMass>=maxMass){
            type=Type.Ship_heavy;
            massIsLimited=true;
            rp.addReport(type,ship);
            return isAccept;
        }
        if(ship.mass>maxMass-curMass){
            type=Type.At_station_no_place;
            massIsLimited=true;
            rp.addReport(type,ship);
            return isAccept;
        }
        if(curShips>=2){
            int count1=0;
            int count2=0;
            int count3=0;
            for(int i=0; i<_ships.size(); i++){
                if(_ships.get(i).raceID==0){
                    count1+=1;
                }else if(_ships.get(i).raceID==1){
                    count2+=1;
                }else{
                    count3+=1;
                }
            }
            if(ship.raceID==0){
                if(count1 < count2){
                    type=Type.Racial_conflicts;
                    racesIsAccess=true;
                    rp.addReport(type,ship);
                    return isAccept;
                }
            }else if(ship.raceID==1){
                if(count2<count3){
                    type=Type.Racial_conflicts;
                    racesIsAccess=true;
                    rp.addReport(type,ship);
                    return isAccept;
                }
            }
        }
        if(!isFool && !massIsLimited && !racesIsAccess){
            isAccept=true;
            System.out.println("Корабль успешно прошел проверку!");
        }
        return isAccept;
    }

    public void StationShipsInfo(){
        for (int i=0; i<_ships.size(); i++){
            System.out.print(i);
            _ships.get(i).Info();
        }
    }
}