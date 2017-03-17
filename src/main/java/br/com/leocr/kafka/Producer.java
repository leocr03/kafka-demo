package br.com.leocr.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.Future;

public class Producer {

    private String text;

    private KafkaProducer<Integer, String> kafkaProducer;

    public Producer(KafkaProducer<Integer, String> kakfaProducer) {
        this.kafkaProducer = kakfaProducer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean produce(String text) {
        final String topic = "kafkaDemo";
        final Integer key = 3;
        final ProducerRecord<Integer, String> record = new ProducerRecord<Integer, String>(topic, key, text);
        Future<RecordMetadata> result = kafkaProducer.send(record);
        return result.isDone();
    }
}
