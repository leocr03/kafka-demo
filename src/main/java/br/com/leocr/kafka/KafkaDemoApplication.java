package br.com.leocr.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

public class KafkaDemoApplication {

    private KafkaProducer<Integer, String> kakfaProducer;

    private KafkaConsumer<Integer, String> kafkaConsumer;

    public boolean execute(String text) {
        final boolean result;

        if(produce(text)) {
            result = consume();
        } else {
            result = false;
        }

        return result;
    }

    public boolean produce(String text) {
        final Producer producer = new Producer(kakfaProducer);
        producer.setText(text);
        return producer.produce(text);
    }

    public boolean consume() {
        final Consumer consumer = new Consumer(kafkaConsumer);
        return consumer.consume();
    }

    public void setKakfaProducer(KafkaProducer<Integer, String> kakfaProducer) {
        this.kakfaProducer = kakfaProducer;
    }

    public void setKafkaConsumer(KafkaConsumer<Integer, String> kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }
}
