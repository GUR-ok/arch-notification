package ru.gur.archnotification.config;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaStreamConfig {

    @Value("${kafka.bootstrapAddress}")
    private String SERVER;

    @Bean
    public StreamsConfig streamsConfig() {
        Properties settings = new Properties();
        // Set a few key parameters
        settings.put(StreamsConfig.APPLICATION_ID_CONFIG, "arch-notification");
        // Kafka bootstrap server (broker to talk to); ubuntu is the host name for my VM running Kafka, port 9092 is where the (single) broker listens
        settings.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER);
        // default serdes for serialzing and deserializing key and value from and to streams in case no specific Serde is specified
        settings.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        settings.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Double().getClass().getName());
        settings.put(StreamsConfig.STATE_DIR_CONFIG, "C:\\temp");
        // to work around exception Exception in thread "StreamThread-1" java.lang.IllegalArgumentException: Invalid timestamp -1
        // at org.apache.kafka.clients.producer.ProducerRecord.<init>(ProducerRecord.java:60)
        // see: https://groups.google.com/forum/#!topic/confluent-platform/5oT0GRztPBo
//        settings.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class);

        return new StreamsConfig(settings);
    }

    @Bean
    public KafkaStreams kafkaStreams(StreamsConfig streamsConfig) {
        System.out.println("Kafka streams Started");

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Double> keyValueTopic = builder.stream("KeyValueTopic", Consumed.with(Serdes.String(), Serdes.Double()));

        KTable<String, Double> accountBalance = keyValueTopic
                .groupBy((key, word) -> key)
                .reduce(Double::sum);

        System.out.println("Kafka streams accountBalance");
        accountBalance.toStream().to("AccountsWithBalance", Produced.with(Serdes.String(), Serdes.Double()));

        KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfig);

        streams.start();

        return streams;
    }
}
