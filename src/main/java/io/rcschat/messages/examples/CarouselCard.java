package io.rcschat.messages.examples;

import io.rcschat.messages.core.ChatMessage;
import io.rcschat.messages.core.RcsMessage;
import io.rcschat.messages.core.contact.MessageContact;
import io.rcschat.messages.core.richcard.Message;
import io.rcschat.messages.core.richcard.GeneralPurposeCardCarousel;
import io.rcschat.messages.core.richcard.RichcardMessage;
import io.rcschat.messages.core.richcard.layout.CarouselLayout;
import io.rcschat.messages.core.richcard.layout.CardWidth;
import io.rcschat.messages.core.richcard.Content;
import io.rcschat.messages.core.richcard.suggested.Postback;
import io.rcschat.messages.core.richcard.suggested.reply.SuggestedReply;
import io.rcschat.messages.core.richcard.suggested.Suggestion;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CarouselCard {
    public static final String URL_ACTION_DISPLAY_TEXT = "Click to Browse";
    public static final String URL_TO_OPEN = "https://rcschat.io";
    public static final String REQUEST_LOCATION_DISPLAY_TEXT = "Current Location";

    public static ChatMessage generateCarouselCard(final String userContact, final int numOfCards) {
        return ChatMessage.builder()
            .messageContact(MessageContact.builder()
                .userContact(userContact)
                .build()
            )
            .rcsMessage(RcsMessage.builder()
                .richcardMessage(generateRichcardMessage(numOfCards))
                .build()
            )
            .build();
    }

    public static RichcardMessage generateRichcardMessage(final int numOfCards) {
        return RichcardMessage.builder()
            .message(new Message(generateGeneralPurposeCardCarousel(numOfCards)))
            .build();
    }

    public static GeneralPurposeCardCarousel generateGeneralPurposeCardCarousel(final int numOfCards) {
        final GeneralPurposeCardCarousel carousel =
                GeneralPurposeCardCarousel.builder()
                        .layout(CarouselLayout.builder()
                                .cardWidth(CardWidth.MEDIUM_WIDTH)
                                .build()
                        )
                        .build();

        for (int i = 0; i < numOfCards; ++i) {
            carousel.addContent(generateContent(i));
        }

        return carousel;
    }

    public static Content generateContent(final int idx) {
        final Content content =
                Content.builder()
                        .title("card title" + idx)
                        .description("card description" + idx)
                        .media(Media.generateMedia())
                        .suggestions(new ArrayList<Suggestion>())
                        .build();

        content.getSuggestions()
                .add(Suggestion.builder()
                        .reply(SuggestedReply.builder()
                                .displayText("Reply card" + idx)
                                .postback(new Postback(
                                    new String(
                                        Base64.encodeBase64("data".getBytes(StandardCharsets.UTF_8)),
                                        StandardCharsets.UTF_8)
                                    )
                                )
                                .build()
                        )
                        .build()
                );

        content.addSuggestedUrlAction(URL_ACTION_DISPLAY_TEXT, URL_TO_OPEN)
                .addSuggestedRequestLocationAction(REQUEST_LOCATION_DISPLAY_TEXT);

        return content;
    }
}
