package com.company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ReportsTest {
    private SpaceShip ss=new SpaceShip("qwerty",Races.Khodakeytsi,23);
    Reports report=new Reports();
    private Reports r1;
    private Reports r2;
    private Reports r3;
    @Before
    private void SetUp() throws Exception{
        r1=new Reports(Type.Station_overloaded,ss);
        r2=new Reports(Type.Ship_heavy,ss);
        r3=new Reports(Type.Ship_heavy,ss);
        report.addReport(Type.Station_overloaded,ss);
        report.addReport(Type.Ship_heavy,ss);
        report.addReport(Type.Ship_heavy,ss);
    }
    @Test
    void stationPeriodInfo() {

    }

    @Test
    void addReport() {
        ArrayList<Reports> _Reports1 = new ArrayList<Reports>();
        _Reports1.add(r1);
        _Reports1.add(r2);
        _Reports1.add(r3);
        Assert.assertEquals(_Reports1,report._refusedRequestsReports);
    }

    @Test
    void info() {
    }
}