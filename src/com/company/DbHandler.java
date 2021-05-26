package com.company;

import org.sqlite.JDBC;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DbHandler {
    public static final String DATE_FORMAT_NOW = "dd-MM-yyyy HH:mm:ss";
    private static final String CON_STR = "jdbc:sqlite:D:/sqlite/OOP_DB.db";
    private static DbHandler instance = null;

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    private Connection connection;

    private DbHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(CON_STR);
    }

    //Table Stations
    public List<Station> getAllStations() {
        try (Statement statement = this.connection.createStatement()) {
            List<Station> stations = new ArrayList<Station>();
            ResultSet resultSet = statement.executeQuery("SELECT id,name,maxShips,curShips,maxMass,curMass from Stations");
            while (resultSet.next()) {
                stations.add(new Station(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("maxShips"), resultSet.getInt("curShips"), resultSet.getFloat("maxMass"), resultSet.getFloat("curMass")));
            }
            return stations;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void updateStation(Station station) {
        try (Statement statement = this.connection.createStatement()) {
            statement.executeUpdate("UPDATE `Stations` SET `curShips`="+station.curShips+",`curMass`="+station.curMass+" WHERE id=" + station.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Station getStation(int id) {
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id,name,maxShips,curShips,maxMass,curMass from Stations WHERE id=" + id);
            Station station = new Station(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("maxShips"), resultSet.getInt("curShips"), resultSet.getFloat("maxMass"), resultSet.getFloat("curMass"));
            return station;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Table Reports
    public List<Reports> getAllReports() {
        try (Statement statement = this.connection.createStatement()) {
            List<Reports> reports = new ArrayList<Reports>();
            ResultSet resultSet = statement.executeQuery("SELECT id, reportDate, reportName, shipName, shipMass from Reports");
            while (resultSet.next()) {
                Date date = new SimpleDateFormat(DATE_FORMAT_NOW).parse(resultSet.getString("reportDate"));
                reports.add(new Reports(resultSet.getInt("id"), date, resultSet.getString("reportName"), resultSet.getString("shipName"), resultSet.getFloat("shipMass")));
            }
            return reports;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void addReport(Reports report) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO Reports(`reportDate`,`reportName`,`shipName`,`shipMass`) " + "VALUES(?,?,?,?)")) {
            String date = sdf.format(report.reportDate);
            statement.setObject(1, date);
            statement.setObject(2, report.reportName);
            statement.setObject(3, report.shipName);
            statement.setObject(4, report.shipMass);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Table SpaceShips
    public List<SpaceShip> getAllSpaceShips() {
        try (Statement statement = this.connection.createStatement()) {
            List<SpaceShip> spaceShips = new ArrayList<SpaceShip>();
            ResultSet resultSet = statement.executeQuery("SELECT id,name,mass,incomingDate,race,id_station from SpaceShips");
            while (resultSet.next()) {
                Date date = new SimpleDateFormat(DATE_FORMAT_NOW).parse(resultSet.getString("incomingDate"));
                spaceShips.add(new SpaceShip(resultSet.getInt("id"), resultSet.getString("name"), date, resultSet.getString("race"), resultSet.getFloat("mass"), resultSet.getInt("id_station")));
            }
            return spaceShips;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void addSpaceShip(SpaceShip spaceShip) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO SpaceShips(`name`,`mass`,`incomingDate`,`race`,`id_station`) " + "VALUES(?,?,?,?,?)")) {
            statement.setObject(1, spaceShip.name);
            statement.setObject(2, spaceShip.mass);
            String date = sdf.format(spaceShip.incomingDate);
            statement.setObject(3, date);
            statement.setObject(4, spaceShip.race);
            statement.setObject(5, spaceShip.idStation);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSpaceShip(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement("DELETE FROM SpaceShips WHERE id=?")) {
            statement.setObject(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}