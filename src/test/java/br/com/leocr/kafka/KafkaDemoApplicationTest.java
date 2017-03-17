package br.com.leocr.kafka;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class KafkaDemoApplicationTest {

	private KafkaDemoApplication demo;

	@Before
	public void setUp() throws Exception {
		demo = new KafkaDemoApplication();
	}

	@Test
	public void testKafkaExecuteSuccess() {
		final String text = "This is my consumer";
		final boolean result = demo.execute(text);
		assertEquals("The result is not true.", true, result);
	}

	@Test
	public void testKafkaProduceSuccess() {
		final String text = "This is my consumer";
		final Producer result = demo.produce(text);
		assertNotNull(result);
		assertEquals("The text is different.", text, result.getText());
	}

	@Test
	public void testKafkaConsumeSuccess() {
		final Consumer result = demo.consume();
		assertNotNull(result);
	}
}
