package sft.integration.use.sut;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(sft.SimpleFunctionalTest.class)
public class ErrorOccursWhenTerminatingAnUseCaseContext {

    @BeforeClass
    public static void setup(){
        doNothing();
    }

    @Test
    public void scenario(){
        doNothing();
    }

    @AfterClass
    public static void teardown(){
        anErrorOccurs();
    }

    private static void anErrorOccurs() {
        throw new RuntimeException("Boom");
    }

    private static void doNothing() {
        Assert.assertTrue(true);
    }
}
