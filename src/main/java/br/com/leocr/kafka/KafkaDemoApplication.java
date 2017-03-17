package br.com.leocr.kafka;

public class KafkaDemoApplication {
    public boolean execute(String text) {
        final boolean result = true;
        return result;
    }

    public Producer produce(String text) {
        final Producer producer = new Producer();
        producer.setText(text);
        return producer;
    }

    public Consumer consume() {
        final Consumer consumer = new Consumer();
        return consumer;
    }
}
