package br.com.leocr.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerTest {

    private Consumer consumer;

    @Mock
    private KafkaConsumer<Integer, String> kafkaConsumerMock;

    @Before
    public void setUp() throws Exception {
        consumer = spy(new Consumer(kafkaConsumerMock));
    }

    @Test
    public void consume() {
        final String topic = "kafkaDemo";
        final long timeout = 100;
        final Map<TopicPartition, List<ConsumerRecord<Integer, String>>> records =
                new LinkedHashMap<TopicPartition, List<ConsumerRecord<Integer, String>>>();
        final int partition = 1;
        final TopicPartition key = new TopicPartition(topic, partition);
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        final List<ConsumerRecord<Integer, String>> value = new LinkedList<ConsumerRecord<Integer, String>>();
        records.put(key, value);
        final ConsumerRecords<Integer, String> consumerRecords = new ConsumerRecords<Integer, String>(records);
        when(kafkaConsumerMock.poll(timeout)).thenReturn(consumerRecords);

        final boolean result = consumer.consume();

        assertTrue(result);
        verify(kafkaConsumerMock, times(1)).poll(timeout);
    }
}