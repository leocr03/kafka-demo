package br.com.leocr.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.concurrent.Future;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProducerTest {

    private Producer producer;

    @Mock
    private KafkaProducer<Integer, String> kafkaProducerMock;

    @Mock
    private Future<RecordMetadata> futureMock;

    @Before
    public void setUp() throws Exception {
        producer = spy(new Producer(kafkaProducerMock));
    }

    @SuppressWarnings({"UnnecessaryLocalVariable", "unchecked"})
    @Test
    public void produce() throws Exception {
        final String text = "This is a producer test";
        final String topic = "kafkaDemo";
        final Integer key = 3;
        final String value = text;
        final ProducerRecord<Integer, String> record = new ProducerRecord<Integer, String>(topic, key, value);

        when(futureMock.isDone()).thenReturn(true);
        when(kafkaProducerMock.send(any(ProducerRecord.class))).thenReturn(futureMock);

        final boolean result = producer.produce(text);

        assertTrue(result);
        verify(kafkaProducerMock, times(1)).send(record);
    }
}