package io.rcschat.messages.examples;

import io.rcschat.messages.core.richcard.media.MediaHeight;

public class Media {
    public static final MediaHeight MEDIA_HEIGHT = MediaHeight.MEDIUM_HEIGHT;
    public static final String MEDIA_URL = "https://rcschat.io/hatch.png";

    public static io.rcschat.messages.core.richcard.media.Media generateMedia() {
        return io.rcschat.messages.core.richcard.media.Media.builder()
                .height(MEDIA_HEIGHT)
                .mediaUrl(MEDIA_URL)
                .mediaContentType("image/jpeg")
                .mediaFileSize(17632L)
                .build();
    }
}
