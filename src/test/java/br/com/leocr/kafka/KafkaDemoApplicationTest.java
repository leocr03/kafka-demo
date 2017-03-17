package br.com.leocr.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
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
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KafkaDemoApplicationTest {

	private KafkaDemoApplication demo;

	@Mock
	private Future<RecordMetadata> futureMock;

	@Mock
	private KafkaProducer<Integer, String> kafkaProducerMock;

	@Mock
	private KafkaConsumer<Integer, String> kafkaConsumerMock;

	@Before
	public void setUp() throws Exception {
		demo = spy(new KafkaDemoApplication());
		demo.setKakfaProducer(kafkaProducerMock);
		demo.setKafkaConsumer(kafkaConsumerMock);
		when(futureMock.isDone()).thenReturn(true);
		//noinspection unchecked
		when(kafkaProducerMock.send(any(ProducerRecord.class))).thenReturn(futureMock);

		final String topic = "kafkaDemo";
		final long timeout = 1000;
		final Map<TopicPartition, List<ConsumerRecord<Integer, String>>> records =
				new LinkedHashMap<TopicPartition, List<ConsumerRecord<Integer, String>>>();
		final int partition = 1;
		final TopicPartition key = new TopicPartition(topic, partition);
		List<ConsumerRecord<Integer, String>> value = new LinkedList<ConsumerRecord<Integer, String>>();
		records.put(key, value);
		final ConsumerRecords<Integer, String> consumerRecords = new ConsumerRecords<Integer, String>(records);
		when(kafkaConsumerMock.poll(timeout)).thenReturn(consumerRecords);
	}

	@Test
	public void testKafkaExecuteSuccess() {
		final String text = "This is my producer to execute";
		final boolean result = demo.execute(text);
		assertEquals("The result is not true.", true, result);
		verify(demo, times(1)).produce(text);
		verify(demo, times(1)).consume();
	}

	@Test
	public void testKafkaProduceSuccess() {
		final String text = "This is my producer";
		final boolean result = demo.produce(text);
		assertTrue(result);
	}

	@Test
	public void testKafkaConsumeSuccess() {
		final boolean result = demo.consume();
		assertTrue(result);
	}
}
