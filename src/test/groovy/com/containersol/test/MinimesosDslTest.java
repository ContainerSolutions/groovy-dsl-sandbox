package com.containersol.test;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class MinimesosDslTest {

    @Test
    public void testClusterName() {
        File configFile = new File("src/test/resources/clustername/minimesos.conf");

        MinimesosConfigParser parser = new MinimesosConfigParser();
        MinimesosDsl minimesosDsl = parser.parse(configFile);

        assertEquals("testcluster", minimesosDsl.getClusterName());
    }

}
