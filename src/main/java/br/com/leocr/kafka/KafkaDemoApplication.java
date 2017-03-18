package br.com.leocr.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

public class KafkaDemoApplication {

    private KafkaProducer<Integer, String> kakfaProducer;

    private KafkaConsumer<Integer, String> kafkaConsumer;

    public KafkaDemoApplication() {
        Properties producerProperties = new Properties();
        producerProperties.put("bootstrap.servers", "localhost:9092");
        producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        producerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kakfaProducer = new KafkaProducer<Integer, String>(producerProperties);

        Properties consumerProperties = new Properties();
        consumerProperties.put("bootstrap.servers", "localhost:9092");
        consumerProperties.put("group.id", "kafkaDemo");
        consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        consumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaConsumer = new KafkaConsumer<Integer, String>(consumerProperties);
    }

    public boolean execute(String text) {
        final boolean result;
        produce(text);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result = consume();
        return result;
    }

    boolean produce(String text) {
        final Producer producer = new Producer(kakfaProducer);
        return producer.produce(text);
    }

    boolean consume() {
        final Consumer consumer = new Consumer(kafkaConsumer);
        return consumer.consume();
    }

    void setKakfaProducer(KafkaProducer<Integer, String> kakfaProducer) {
        this.kakfaProducer = kakfaProducer;
    }

    void setKafkaConsumer(KafkaConsumer<Integer, String> kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }
}
