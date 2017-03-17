package br.com.leocr.kafka;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class KafkaDemoApplicationTest {

	private KafkaDemoApplication demo;

	@Before
	public void setUp() throws Exception {
		demo = spy(new KafkaDemoApplication());
	}

	@Test
	public void testKafkaExecuteSuccess() {
		final String text = "This is my producer to execute";
		final boolean result = demo.execute(text);
		assertEquals("The result is not true.", true, result);
		verify(demo, times(1)).produce(text);
	}

	@Test
	public void testKafkaProduceSuccess() {
		final String text = "This is my producer";
		final boolean result = demo.produce(text);
		assertTrue(result);

		// TODO check Kafka using to create the proper asserts
	}

	@Test
	public void testKafkaConsumeSuccess() {
		final boolean result = demo.consume();
		assertTrue(result);
	}
}
