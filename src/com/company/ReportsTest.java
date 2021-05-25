package com.company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReportsTest {
    private SpaceShip ss1;
    private SpaceShip ss2;
    private Reports report1=new Reports();
    @Before
    public void SetUp() throws Exception {
        ss1 = new SpaceShip("qwerty",Races.Earthlings,10);
        ss2 = new SpaceShip("qwerty1",Races.Khodakeytsi,100);
    }

    @Test
    void addReport() {
        boolean actual1=report1.addReport(Type.Ship_heavy,ss2);
        assertTrue(actual1);
        boolean actual2=report1.addReport(Type.Racial_conflicts,ss1);
        assertTrue(actual2);
    }
}