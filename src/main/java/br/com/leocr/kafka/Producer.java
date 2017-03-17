package br.com.leocr.kafka;

public class Producer {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean produce() {
        final boolean result = true;
        return result;
    }
}
