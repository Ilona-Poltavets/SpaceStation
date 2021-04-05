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
    ArrayList<Reports> _refusedRequestsReports = new ArrayList<Reports>();

    public Station (int maxShips, float maxMass, String name){
        this.name=name;
        this.maxMass=maxMass;
        this.maxShips=maxShips;
        curShips=0;
        curMass=0;
    }

    public void SpaceShipImport(SpaceShip ship){
        _ships.add(ship);
        curShips+=1;
        curMass+=ship.mass;
    }

    public void SpaceShipExport(){
        System.out.println("Укажите номер корабля который должен покинуть станцию");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        if(index <0 || index > _ships.size()){
            System.out.println("На станции нет корабля с таким номером!");
       }else{
            curShips-=1;
            curMass-=_ships.get(index).mass;
            _ships.remove(index);
            System.out.println("Корабль успешно покинул станцию!");
        }
    }

    public boolean StationManager(SpaceShip ship){
        int problemID=0;
        boolean isFool=false;
        boolean massIsLimited=false;
        boolean racesIsAccess=false;
        boolean isAccept=false;
        if(curShips>=maxShips){
            isFool=true;
            System.out.println("Количество кораблей на станции достигло максимума!");
            problemID=2;
            Reports report = new Reports(problemID ,ship);
            _refusedRequestsReports.add(report);
            return isAccept;
        }
        if(curMass>=maxMass){
            massIsLimited=true;
            System.out.println("Сумарная масса кораблей находящихся на станции достигла своего предела!");
            problemID=0;
            Reports report = new Reports(problemID ,ship);
            _refusedRequestsReports.add(report);
            return isAccept;
        }
        if(ship.mass>maxMass-curMass){
            massIsLimited=true;
            System.out.println("Этот корабль слишком тяжелый!");
            problemID=1;
            Reports report = new Reports(problemID ,ship);
            _refusedRequestsReports.add(report);
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
                    racesIsAccess=true;
                    System.out.println("В доступе отказано из за рассовых разногласий!");
                    problemID=3;
                    Reports report = new Reports(problemID ,ship);
                    _refusedRequestsReports.add(report);
                    return isAccept;
                }
            }else if(ship.raceID==1){
                if(count2<count3){
                    racesIsAccess=true;
                    System.out.println("В доступе отказано из за рассовых разногласий!");
                    problemID=3;
                    Reports report = new Reports(problemID ,ship);
                    _refusedRequestsReports.add(report);
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

    public void StationPeriodInfo() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        Scanner scanner00 = new Scanner(System.in);
        Scanner scanner01 = new Scanner(System.in);
        format.applyPattern("dd.MM.yyyy");
        boolean dateIsInput=false;
        while(dateIsInput!=true) {
            System.out.print("Введите дату от которой хотите начать поиск (Формат: День.Месяц.Год): ");
            String frstDate = scanner00.nextLine();
            Date firstDate = format.parse(frstDate);
            System.out.print("Введите дату на которой хотите закончить поиск (Формат: День.Месяц.Год): ");
            String lastDate = scanner00.nextLine();
            Date secondDate = format.parse(lastDate);
            if(firstDate.compareTo(secondDate)==-1){
                for(int i=0; i<_refusedRequestsReports.size(); i++){
                    if(_refusedRequestsReports.get(i).reportDate.compareTo(firstDate)==1){
                        if(_refusedRequestsReports.get(i).reportDate.compareTo(secondDate)==-1){
                            _refusedRequestsReports.get(i).Info();
                        }
                    }
                }
                dateIsInput=true;
            }else{
                System.out.println("Первая дата должна быть раньше второй!");
            }
        }

    }
}
