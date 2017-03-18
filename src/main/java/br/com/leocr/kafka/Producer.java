package br.com.leocr.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.Future;

class Producer {

    private KafkaProducer<Integer, String> kafkaProducer;

    Producer(KafkaProducer<Integer, String> kakfaProducer) {
        this.kafkaProducer = kakfaProducer;
    }

    boolean produce(String text) {
        final String topic = "kafkaDemo";
        final Integer key = 3;
        final ProducerRecord<Integer, String> record = new ProducerRecord<Integer, String>(topic, key, text);
        final Future<RecordMetadata> result = kafkaProducer.send(record);
        kafkaProducer.close();
        return result.isDone();
    }
}
