package io.rcschat.messages.examples;

import io.rcschat.messages.core.ChatMessage;
import io.rcschat.messages.core.RcsMessage;
import io.rcschat.messages.core.contact.MessageContact;
import io.rcschat.messages.core.richcard.suggested.Suggestion;
import io.rcschat.messages.core.richcard.suggested.action.MapAction;
import io.rcschat.messages.core.richcard.suggested.action.RequestLocationPush;
import io.rcschat.messages.core.richcard.suggested.action.SuggestedAction;
import io.rcschat.messages.core.richcard.suggested.reply.SuggestedReply;
import io.rcschat.messages.core.richcard.suggested.chiplist.SuggestedChipList;

import java.util.Arrays;

public class TextMessage {
    public static final String SUGGESTED_REPLY_DISPLAY_TEXT = "Suggested Reply";
    public static final String REQUEST_LOCATION_DISPLAY_TEXT = "Current Location";

    public static ChatMessage generateTextMessage(final String userContact, final String text) {
        return ChatMessage.builder()
                .messageContact(MessageContact.builder()
                        .userContact(userContact)
                        .build()
                )
                .rcsMessage(RcsMessage.builder()
                        .textMessage(text)
                        .build()
                )
                .build();
    }

    public static ChatMessage generateTextMessageWithSuggestedReplyChiplist(final String userContact, final String text) {
        return ChatMessage.builder()
            .messageContact(MessageContact.builder()
                .userContact(userContact)
                .build()
            )
            .rcsMessage(RcsMessage.builder()
                .textMessage(text)
                .suggestedChipList(new SuggestedChipList(
                    Arrays.asList(
                        new Suggestion(new SuggestedReply(SUGGESTED_REPLY_DISPLAY_TEXT))
                    )
                ))
                .build()
            )
            .build();
    }

    public static ChatMessage generateTextMessageWithSuggestedRequestLocationChiplist(final String userContact, final String text) {
        return ChatMessage.builder()
            .messageContact(MessageContact.builder()
                .userContact(userContact)
                .build()
            )
            .rcsMessage(RcsMessage.builder()
                .textMessage(text)
                .suggestedChipList(new SuggestedChipList(
                    Arrays.asList(
                        new Suggestion(new SuggestedReply(SUGGESTED_REPLY_DISPLAY_TEXT)),
                        new Suggestion(SuggestedAction.builder()
                            .displayText(REQUEST_LOCATION_DISPLAY_TEXT)
                            .mapAction(new MapAction(new RequestLocationPush()))
                            .build())
                    )
                ))
                .build()
            )
            .build();
    }

    public static ChatMessage generateTextMessageSetter(final String userContact, final String text) {
        return new ChatMessage()
            .setMessageContact(new MessageContact(userContact))
            .setRcsMessage(new RcsMessage()
                .setTextMessage(text)
            );
    }
}
