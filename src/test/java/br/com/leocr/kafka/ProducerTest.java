package br.com.leocr.kafka;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProducerTest {

    private Producer producer;

    @Before
    public void setUp() throws Exception {
        producer = new Producer();
    }

    @Test
    public void produce() throws Exception {
        final boolean result = producer.produce();
        assertTrue(result);
    }
}