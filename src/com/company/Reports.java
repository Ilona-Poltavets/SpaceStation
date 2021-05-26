package com.company;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.List;

enum Type {Station_overloaded, Ship_heavy, At_station_no_place, Racial_conflicts};

public class Reports {
    public int id;
    public Date reportDate;
    public Type reportName;
    public String shipName;
    public Float shipMass;

    public Reports() {
    }

    public Reports(int id, Date reportDate, String type, String shipName, Float shipMass) {
        this.id = id;
        this.reportDate = reportDate;
        this.reportName = Type.valueOf(type);
        this.shipName = shipName;
        this.shipMass = shipMass;
    }

    public Reports(Type type, SpaceShip ship) {
        Date date = new Date();
        reportDate = date;
        reportName = type;
        this.shipName = ship.name;
        this.shipMass = ship.mass;
    }

    public void StationPeriodInfo() throws ParseException {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            List<Reports> _refusedRequestsReports = dbHandler.getAllReports();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Info() {
        System.out.println("-Отчет: " + id + " | " + reportDate + "| " + "Причина: " + reportName + "   Корабль: " + shipName + "  Масса: " + shipMass + " тонн ;");
    }
}
