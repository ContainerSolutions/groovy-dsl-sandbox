package com.containersol.test;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class MinimesosConfigParserTest {

    @Test
    public void testClusterName() {
        String config =
        """
        minimesos {

            clusterName = "testcluster"

        }
        """

        MinimesosConfigParser parser = new MinimesosConfigParser()
        MinimesosDsl minimesosDsl = parser.parse(config)

        assertEquals("testcluster", minimesosDsl.getClusterName())
    }

}
