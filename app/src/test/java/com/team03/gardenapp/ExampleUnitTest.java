package com.team03.gardenapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

    }

    @Test
    public void practiceTest() {

        BasePlant plant = new BasePlant("Daisy", id, lastWatered, sunlight, type, waterAmount, waterFrequency);

        assertEquals(plant.getName(), "Daisy");
//        assertEquals(plant.getName(), "Dais");

        assertEquals(4, (2 + 2));
//        assertEquals(4, (3 + 2));

    }
    public void gotName(){
//        assertNotNull(User.name);
    }
//    public void gotName(){
//        assertNotNull(BasePlant.name);
//    }
}