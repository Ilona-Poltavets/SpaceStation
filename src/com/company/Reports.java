package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

enum Type {Station_overloaded, Ship_heavy, At_station_no_place, Racial_conflicts};

public class Reports {
    public Date reportDate;
    public Type reportName;
    public String shipName;
    public Float shipMass;
    static ArrayList<Reports> _refusedRequestsReports = new ArrayList<Reports>();

    public Reports() {
    }

    public Reports(Type type, SpaceShip ship) {
        Date date = new Date();
        reportDate = date;
        reportName = type;
        this.shipName = ship.name;
        this.shipMass = ship.mass;
    }

    public void StationPeriodInfo() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        Scanner scanner00 = new Scanner(System.in);
        Scanner scanner01 = new Scanner(System.in);
        format.applyPattern("dd.MM.yyyy");
        boolean dateIsInput = false;
        while (dateIsInput != true) {
            System.out.print("Введите дату от которой хотите начать поиск (Формат: День.Месяц.Год): ");
            String frstDate = scanner00.nextLine();
            Date firstDate = format.parse(frstDate);
            System.out.print("Введите дату на которой хотите закончить поиск (Формат: День.Месяц.Год): ");
            String lastDate = scanner01.nextLine();
            Date secondDate = format.parse(lastDate);
            if (firstDate.compareTo(secondDate) == -1) {
                System.out.println(_refusedRequestsReports.size());
                for (int i = 0; i < _refusedRequestsReports.size(); i++) {
                    if (_refusedRequestsReports.get(i).reportDate.compareTo(firstDate) == 1) {
                        if (_refusedRequestsReports.get(i).reportDate.compareTo(secondDate) == -1) {
                            _refusedRequestsReports.get(i).Info();
                        }
                    }
                }
                dateIsInput = true;
            } else {
                System.out.println("Первая дата должна быть раньше второй!");
            }
        }
    }

    public boolean addReport(Type type, SpaceShip ship) {
        if (type == Type.Station_overloaded) {
            System.out.println("Количество кораблей на станции достигло максимума!");
            Reports report = new Reports(type, ship);
            _refusedRequestsReports.add(report);
            return true;
        } else if (type == Type.Ship_heavy) {
            System.out.println("Сумарная масса кораблей находящихся на станции достигла своего предела!");
            Reports report = new Reports(type, ship);
            _refusedRequestsReports.add(report);
            return true;
        } else if (type == Type.At_station_no_place) {
            System.out.println("Этот корабль слишком тяжелый!");
            Reports report = new Reports(type, ship);
            _refusedRequestsReports.add(report);
            return true;
        } else if (type == Type.Racial_conflicts) {
            System.out.println("В доступе отказано из за рассовых разногласий!");
            Reports report = new Reports(type, ship);
            _refusedRequestsReports.add(report);
            return true;
        }
        else return false;

    }

    public void Info() {
        System.out.println("-Отчет: " + reportDate + "| " + "Причина: " + reportName + "   Корабль: " + shipName + "  Масса: " + shipMass + " тонн ;");
    }
}
