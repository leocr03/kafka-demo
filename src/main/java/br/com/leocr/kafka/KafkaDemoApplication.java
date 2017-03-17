package br.com.leocr.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

class KafkaDemoApplication {

    private KafkaProducer<Integer, String> kakfaProducer;

    private KafkaConsumer<Integer, String> kafkaConsumer;

    boolean execute(String text) {
        final boolean result;
        result = produce(text) && consume();
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
