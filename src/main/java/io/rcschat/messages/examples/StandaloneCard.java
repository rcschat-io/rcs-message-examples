package io.rcschat.messages.examples;

import io.rcschat.messages.core.ChatMessage;
import io.rcschat.messages.core.RcsMessage;
import io.rcschat.messages.core.contact.MessageContact;
import io.rcschat.messages.core.richcard.Message;
import io.rcschat.messages.core.richcard.Content;
import io.rcschat.messages.core.richcard.GeneralPurposeCard;
import io.rcschat.messages.core.richcard.RichcardMessage;
import io.rcschat.messages.core.richcard.layout.CardLayout;
import io.rcschat.messages.core.richcard.layout.CardOrientation;
import io.rcschat.messages.core.richcard.suggested.Suggestion;
import io.rcschat.messages.core.richcard.suggested.action.SuggestedAction;
import io.rcschat.messages.core.richcard.suggested.reply.SuggestedReply;
import io.rcschat.messages.core.richcard.suggested.action.DialerAction;

import java.util.Arrays;

public class StandaloneCard {
    public static final String SUGGESTED_REPLY_DISPLAY_TEXT = "Suggested Reply";
    public static final String DIALER_ACTION_DISPLAY_TEXT = "Call";
    public static final String SERVICE_NUMBER_TO_CALL = "+11234567890";

    public static ChatMessage generateStandaloneCard(final String userContact) {
        return ChatMessage.builder()
            .messageContact(MessageContact.builder()
                .userContact(userContact)
                .build()
            )
            .rcsMessage(RcsMessage.builder()
                .richcardMessage(generateRichcardMessage())
                .build()
            )
            .build();
    }

    public static RichcardMessage generateRichcardMessage() {
        return RichcardMessage.builder()
            .message(new Message(generateGeneralPurposeCard()))
            .build();
    }

    public static GeneralPurposeCard generateGeneralPurposeCard() {
        return GeneralPurposeCard.builder()
                .layout(new CardLayout(CardOrientation.VERTICAL))
                .content(generateContent())
                .build();
    }

    public static Content generateContent() {
        return Content.builder()
                        .title("card title")
                        .description("card description")
                        .media(Media.generateMedia())
                        .suggestions(Arrays.asList(
                                new Suggestion(new SuggestedReply(SUGGESTED_REPLY_DISPLAY_TEXT)),
                                new Suggestion(SuggestedAction.builder()
                                        .displayText(DIALER_ACTION_DISPLAY_TEXT)
                                        .dialerAction(new DialerAction(new DialerAction.DialPhoneNumber(SERVICE_NUMBER_TO_CALL)))
                                        .build()
                                ))
                        )
                        .build();
    }
}
