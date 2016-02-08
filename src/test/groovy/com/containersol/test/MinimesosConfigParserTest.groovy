package com.containersol.test

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull;

public class MinimesosConfigParserTest {

    private MinimesosConfigParser parser

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


    @Test
    public void testLoadSingleAgent() {

        String config = """
                minimesos {
                    agent {
                        loggingLevel = "WARN"
                    }
                }
        """

        MinimesosDsl dsl = parser.parse(config)
        assertEquals( 1, dsl.agents.size() )

        Agent agent = dsl.agents.get(0)
        assertEquals( "WARN", agent.loggingLevel )

    }

    @Test
    public void testLoadTwoAgents() {

        String config = """
                minimesos {
                    agent {
                    }
                    agent {
                    }
                }
        """

        MinimesosDsl dsl = parser.parse(config)
        assertEquals( 2, dsl.agents.size() )

    }

    @Test
    public void testLoadMaster() {

        String config = """
                minimesos {
                    master {
                        imageName  = "another/master"
                    }
                }
        """

        MinimesosDsl dsl = parser.parse(config)
        assertNotNull( dsl.master )
        assertEquals( "another/master", dsl.master.imageName )

    }

}
