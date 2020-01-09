package io.rcschat.messages.examples;

import io.rcschat.messages.json.JsonConverter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleGenerator {
    private static final String USER_CONTACT_NUMBER = "+11234567890";
    private static final String HELLO_MESSAGE = "Hello! Welcome to RCSChat.io";
    private static final int NUM_OF_RICHCARD_IN_CAROUSEL = 3;

    public static void main(final String[] args) {
        log.info("TextMessage with Builder:\n{}\n",
            JsonConverter.serialize(TextMessage.generateTextMessage(USER_CONTACT_NUMBER, HELLO_MESSAGE)));
        log.info("TextMessage with Setter:\n{}\n",
            JsonConverter.serialize(TextMessage.generateTextMessageSetter(USER_CONTACT_NUMBER, HELLO_MESSAGE)));
        log.info("TextMessage with Wither:\n{}\n",
            JsonConverter.serialize(TextMessage.generateTextMessageWither(USER_CONTACT_NUMBER, HELLO_MESSAGE)));
        log.info("TextMessage with SuggestedReply ChipList:\n{}\n",
            JsonConverter.serialize(TextMessage.generateTextMessageWithSuggestedReplyChiplist(USER_CONTACT_NUMBER, HELLO_MESSAGE)));
        log.info("TextMessage with RequestLocation ChipList:\n{}\n",
            JsonConverter.serialize(TextMessage.generateTextMessageWithSuggestedRequestLocationChiplist(USER_CONTACT_NUMBER, HELLO_MESSAGE)));

        log.info("Standalone RichCard with SuggestedReply and Suggested DialerAction:\n{}\n",
            JsonConverter.serialize(StandaloneCard.generateStandaloneCard(USER_CONTACT_NUMBER)));

        log.info("RichCard Carousel with SuggestedReply, Suggested UrlAction, and Suggested RequestLocation Action:\n{}\n",
            JsonConverter.serialize(CarouselCard.generateCarouselCard(USER_CONTACT_NUMBER, NUM_OF_RICHCARD_IN_CAROUSEL)));
    }
}
