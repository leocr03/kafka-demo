package br.com.leocr.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;

public class Consumer {

    private KafkaConsumer<Integer, String> kafkaConsumer;

    public Consumer(KafkaConsumer<Integer, String> kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    public boolean consume() {
        final String topic = "kafkaDemo";
        kafkaConsumer.subscribe(Collections.singletonList(topic));
        ConsumerRecords<Integer, String> consumerRecords = kafkaConsumer.poll(1000);
        return !consumerRecords.isEmpty();
    }
}
