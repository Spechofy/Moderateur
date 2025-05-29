package com.example.Moderateur.kafka.config;

import com.example.Moderateur.kafka.event.CommentKafkaEvent;
import com.example.Moderateur.kafka.event.ModerateurKafkaEvent;
import com.example.Moderateur.kafka.event.SignaleKafkaEvent;
import com.example.Moderateur.kafka.event.UserKafkaEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Kafka consumer config.
 */
@Configuration
public class KafkaConsumerConfig {

    private final String bootstrapAddress = "localhost:29092"; // adapte si besoin
    private final String groupId = "moderateur-group";

    // Utilisé pour factoriser la création des factories
    private <T> ConsumerFactory<String, T> createFactory(Class<T> clazz) {
        JsonDeserializer<T> deserializer = new JsonDeserializer<>(clazz);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*"); // à restreindre en production
        deserializer.setUseTypeMapperForKey(false);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    /**
     * Comment kafka listener factory concurrent kafka listener container factory.
     *
     * @return the concurrent kafka listener container factory
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CommentKafkaEvent> commentKafkaListenerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, CommentKafkaEvent>();
        factory.setConsumerFactory(createFactory(CommentKafkaEvent.class));
        return factory;
    }

    /**
     * Moderateur kafka listener factory concurrent kafka listener container factory.
     *
     * @return the concurrent kafka listener container factory
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ModerateurKafkaEvent> moderateurKafkaListenerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, ModerateurKafkaEvent>();
        factory.setConsumerFactory(createFactory(ModerateurKafkaEvent.class));
        return factory;
    }

    /**
     * Signale kafka listener factory concurrent kafka listener container factory.
     *
     * @return the concurrent kafka listener container factory
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SignaleKafkaEvent> signaleKafkaListenerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, SignaleKafkaEvent>();
        factory.setConsumerFactory(createFactory(SignaleKafkaEvent.class));
        return factory;
    }

    /**
     * User kafka listener factory concurrent kafka listener container factory.
     *
     * @return the concurrent kafka listener container factory
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserKafkaEvent> userKafkaListenerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, UserKafkaEvent>();
        factory.setConsumerFactory(createFactory(UserKafkaEvent.class));
        return factory;
    }
}
