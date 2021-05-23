package com.company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class StationTest {
    private Station station;
    private SpaceShip ss1;
    private SpaceShip ss2;
    private SpaceShip ss3;
    ArrayList<SpaceShip>_ships1 = new ArrayList<SpaceShip>();
    @Before
    public void SetUp() throws Exception {
        station = new Station(10,50,"Saturn");
        ss1 = new SpaceShip("qwerty",Races.Earthlings,10);
        ss2 = new SpaceShip("qwerty1",Races.Khodakeytsi,10);
        ss3 = new SpaceShip("qwerty2", Races.Turiantsi,10);
    }

    @Test
    public void stationManager() {
        ss1.SpaceShipImport(station);
        boolean actual = station.StationManager(ss2);
        Assert.assertEquals(true, actual);
    }
}