package com.company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

class StationTest {
    private Station station=new Station(5,100, "Neptun");
    private SpaceShip ss1;
    private SpaceShip ss2;
    private SpaceShip ss3;
    @Before
    private void SetUp() throws Exception{
        ss1=new SpaceShip("qwerty", Races.Earthlings,10);
        ss2=new SpaceShip("qwerty1", Races.Khodakeytsi,10);
        ss3=new SpaceShip("qwerty2", Races.Turiantsi,10);

        station.StationManager(ss1);
        station.StationManager(ss2);
        station.StationManager(ss3);
    }
    @Test
    private void StationManager(){
        ArrayList<SpaceShip> _ships1 = new ArrayList<SpaceShip>();
        _ships1.add(ss1);
        _ships1.add(ss2);
        Assert.assertEquals(station._ships, _ships1);
        station.StationManager(ss2);
        Assert.assertEquals(station._ships, _ships1);
    }
    @Test
    private void StarShipInfo(){

    }
}