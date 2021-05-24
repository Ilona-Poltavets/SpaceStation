package com.company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class StationTest {
    private Station station;
    private SpaceShip ss1;
    private SpaceShip ss2;
    ArrayList<SpaceShip>_ships1 = new ArrayList<SpaceShip>();
    @Before
    public void SetUp() throws Exception {
        station = new Station(10,50,"Saturn");
        ss1 = new SpaceShip("qwerty",Races.Earthlings,10);
        ss2 = new SpaceShip("qwerty1",Races.Khodakeytsi,100);
    }

    @Test
    public void stationManager() {
        boolean actual = station.StationManager(ss1);
        Assert.assertEquals(true, actual);
        boolean actual2 = station.StationManager(ss2);
        Assert.assertEquals(false, actual2);
    }
}