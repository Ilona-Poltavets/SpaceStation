package com.company;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> races = new ArrayList<String>();
    static Station _station01 = new Station(10, 100, "Пилигрим");
    static Reports _report=new Reports();

    public static void main(String[] args) throws ParseException {
        boolean stop=false;
        while (!stop) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("--------СТАНЦИЯ ПИЛИГРИМ---------");
            System.out.println("Кол-Во Кораблей: "+ _station01._ships.size()+"    Вес: "+_station01.curMass+" тонн /" + _station01.maxMass+" тонн");
            System.out.println("- Отправить запрос кораблем [0]");
            System.out.println("- Получить информацию о кораблях что находяться на станции[1]");
            System.out.println("- Отправить корабль на вылет[2]");
            System.out.println("- Получить информацию об отказах в посадке[3]");
            System.out.println("- Закончить и выйти[4]");
            System.out.print("Ввод: ");
            int choice = scanner.nextInt();
            switch (choice){
                case 0:
                    SendRequestToStation();
                    break;
                case 1:
                    _station01.StationShipsInfo();
                    break;
                case 2:
                    SpaceShip ship=new SpaceShip();
                    ship.SpaceShipExport(_station01);
                    break;
                case 3:
                    _report.StationPeriodInfo();
                    break;
                case 4:
                    stop=true;
                    break;
            }
        }

    }

    public static void SendRequestToStation(){
        System.out.println("Что бы отправить запрос заполните анкету с данными вашего корабля!");
        Scanner scanner00 = new Scanner(System.in);
        Scanner scanner01 = new Scanner(System.in);
        Scanner scanner03 = new Scanner(System.in);
        String name="";
        int raceID=0;
        float mass=0;
        boolean complete=false;
        while(!complete){
            System.out.print("Укажите имя вашего корабля: ");
            name=scanner00.nextLine();
            System.out.print("Укажите рассу от имени которой ваш корабль будет прибывать на станции(Земляни[0], Турианци[1], Ходакейци[2]): ");
            String[] types = {"Земляни", "Турианци", "Ходакейци"};
            raceID =scanner01.nextInt();
            if(raceID>=0 && raceID<=2){
                System.out.println("SCAN: "+ types[raceID]);
                System.out.print("Укажите массу вашего корабля: ");
                mass = scanner03.nextFloat();
                complete=true;
            }else{
                System.out.println("Указаной вами расы нет в списках. В доступе отказано!");
            }
        }
        SpaceShip ship = new SpaceShip(name, raceID, mass);
        System.out.println("Ваш запрос принят на обработку!");
        if(_station01.StationManager(ship)){
            ship.SpaceShipImport(_station01,ship);
        }
    }
}