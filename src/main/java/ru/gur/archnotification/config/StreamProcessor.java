package ru.gur.archnotification.config;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StreamProcessor {

    @Autowired
    void buildPipeline(StreamsBuilder builder) {
        KStream<String, Double> keyValueTopic = builder.stream("KeyValueTopic", Consumed.with(Serdes.String(), Serdes.Double()));

        KTable<String, Double> accountBalance = keyValueTopic
                .groupBy((key, word) -> key)
                .reduce(Double::sum, Materialized.as("counts"));

        accountBalance.toStream().to("AccountsWithBalance", Produced.with(Serdes.String(), Serdes.Double()));
    }
}
