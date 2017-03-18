package br.com.leocr.kafka.integration;

import br.com.leocr.kafka.KafkaDemoApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

/**
 *
 *
 *
 */

@RunWith(JUnit4.class)
public class KafkaDemoApplicationIT {

    private KafkaDemoApplication demo;

    @Before
    public void setUp() throws Exception {
        demo = new KafkaDemoApplication();
    }

    @Test
    public void execute() throws Exception {
        final String text = "This is a integration test";
        final boolean result = demo.execute(text);
        assertTrue(result);
    }
}
