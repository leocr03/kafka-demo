package br.com.leocr.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;
import java.util.Collections;

class Consumer {

    private KafkaConsumer<Integer, String> kafkaConsumer;

    Consumer(KafkaConsumer<Integer, String> kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    boolean consume() {
        final String topic = "kafkaDemo";
        final int partition = 0;
        final TopicPartition topicPartition = new TopicPartition(topic, partition);
        final Collection<TopicPartition> partitions = Collections.singletonList(topicPartition);
        kafkaConsumer.assign(partitions);
        final ConsumerRecords<Integer, String> consumerRecords = kafkaConsumer.poll(100);
        final boolean isEmpty = consumerRecords.isEmpty();

        for (ConsumerRecord<Integer, String> record : consumerRecords) {
            System.out.printf("**** offset = %d, key = %s, value = %s\n", record.offset(), record.key(),
                    record.value());
        }

        return !isEmpty;
    }
}
