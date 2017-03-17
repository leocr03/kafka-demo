package br.com.leocr.kafka;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerTest {

    private Consumer consumer;

    @Before
    public void setUp() throws Exception {
        consumer = spy(new Consumer());
    }

    @Test
    public void consume() {
        final boolean result = consumer.consume();
        assertTrue(result);
    }
}