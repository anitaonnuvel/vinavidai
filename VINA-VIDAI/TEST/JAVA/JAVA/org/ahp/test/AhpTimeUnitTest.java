package org.ahp.test;

import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.junit.Test;

public class AhpTimeUnitTest {
    
    @Test
    public void testMillisToHMS(){
        long lHours = TimeUnit.HOURS.convert( 12783000, TimeUnit.MILLISECONDS );
        TestCase.assertEquals( 3, lHours );
        long lMinutes = TimeUnit.MINUTES.convert( 12783000 - TimeUnit.MILLISECONDS.convert( lHours, TimeUnit.HOURS ), TimeUnit.MILLISECONDS );
        TestCase.assertEquals( 33, lMinutes );
        long lSeconds = TimeUnit.SECONDS.convert( 12783000 - ( TimeUnit.MILLISECONDS.convert( lHours, TimeUnit.HOURS ) + TimeUnit.MILLISECONDS.convert( lMinutes, TimeUnit.MINUTES ) ), TimeUnit.MILLISECONDS );
        TestCase.assertEquals( 3, lSeconds );
    }

}
