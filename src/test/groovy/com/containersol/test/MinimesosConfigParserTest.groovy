package com.containersol.test

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinimesosConfigParserTest {

    MinimesosConfigParser parser

    @Before
    public void before() {
        parser = new MinimesosConfigParser()
    }

    @Test
    public void testClusterName() {
        String config =
        """
        minimesos {

            clusterName = "testcluster"

        }
        """

        assertEquals("testcluster", parser.parse(config).getClusterName())
    }

    @Test
    public void testTimeout() {
        String config =
        """
        minimesos {

            timeout = 500

        }
        """

        assertEquals(500, parser.parse(config).getTimeout())
    }

    @Test(expected = MissingPropertyException.class)
    public void testUnsupportedProperty() {
        String config =
                """
        minimesos {

            unsupportedProperty = "foo"

        }
        """

        parser.parse(config)
    }


    @Test(expected = MissingPropertyException.class)
    public void testUnsupportedBlock() {
        String config =
                """
        minimesos {

            unsupportedBlock {

            }

        }
        """

        parser.parse(config)
    }


}
