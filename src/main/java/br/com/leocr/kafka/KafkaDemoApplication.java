package br.com.leocr.kafka;

public class KafkaDemoApplication {

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
        final Producer producer = new Producer();
        producer.setText(text);
        return producer.produce();
    }

    public boolean consume() {
        final Consumer consumer = new Consumer();
        return consumer.consume();
    }
}
