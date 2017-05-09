package data.neo4j;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.driver.v1.Session;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Uffe on 06-05-2017.
 */
public class Neo4jConnectionTEST {
    protected static GraphDatabaseService graphDb;

    @BeforeClass
    public static void setup(){
        graphDb = new TestGraphDatabaseFactory().newImpermanentDatabase();
    }

    @AfterClass
    public static void tearDown()
    {
        graphDb.shutdown();
    }

    @Test
    public void testValidNeo4jConnection() {
        Neo4jConnection con = new Neo4jConnection();
        Session session = con.getConnection("bolt://hobby-ilgiikaijildgbkefmcbhhpl.dbs.graphenedb.com:24786", "test", "b.nyjHUt6ivza6.yumM1PXk3jBRCLV4");
        assertThat(session, is(notNullValue()));
        session.close();
    }

    @Test(expected = NullPointerException.class)
    public void testInvalidNeo4jConnection() {
        Neo4jConnection con = new Neo4jConnection();
        Session session = con.getConnection("urlwithoutdb.com", "neo4j", "class");
        assertThat(session == null, is(true));
        session.close();
    }
}
