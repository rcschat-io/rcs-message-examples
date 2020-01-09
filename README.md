# RCS Message Examples

This repository contains examples to show how to construct and parse Rich Communication Services (RCS) messages with the [rcs-message-1.0.0-all.jar](https://rcschat.io/sdk/rcs-message/rcs-message-1.0.0-all.jar) library.

## Demo
[RCS Message Demo](https://rcschat.io/demo/rcs)

## Running the examples
### Gradle users
Running at the command line
```
./gradlew run
```

## Construct messages
[rcs-message](https://apidoc.rcschat.io/apidoc/rcsmessage/index.html?overview-summary.html) implements RCS messages that are standardized by GSMA RCS technical specifications ["Rich Communication Suite 7.0 Advanced Communications Services and Client Specification v8.0 Section 3.6.10"](https://www.gsma.com/futurenetworks/wp-content/uploads/2017/06/RCS_7_UNI.zip).
A message can be constructed in the following ways:
* Message Builder (**recommended**)
* NoArgsConstructor with Setter
* NoArgsConstructor with Wither
* AllArgsConstructor

SuggestedReply and SuggestedAction in Richcard and SuggestedChipList can also be constructed with helper functions:
* Adder in ChatMessage and RcsMessage adds a Suggestion in the ChipList.
* Adder in Content adds a Suggestion in the Richcard.

Additional helper functions can be requested through issues.

### Text Message
1. Message Builder
    ```
    final ChatMessage chatMessage = ChatMessage.builder()
        .messageContact(
            MessageContact.builder()
                .userContact("+11234567890")
                .build()
        )
        .rcsMessage(
            RcsMessage.builder()
                .textMessage("Hello! Welcome to RCSChat.io")
                .build()
        )
        .build();
    ```
2. NoArgsConstructor with Setter
    ```
    final RcsMessage rcsMessage = new RcsMessage();
    rcsMessage.setTextMessage("Hello! Welcome to RCSChat.io");

    final ChatMessage chatMessage = new ChatMessage();
    chatMessage.setMessageContact(new MessageContact("+11234567890"));
    chatMessage.setRcsMessage(rcsMessage);
    ```
3. NoArgsConstructor with Wither
    ```
    final ChatMessage chatMessage = new ChatMessage()
        .withMessageContact(new MessageContact("+11234567890"))
        .withRcsMessage(new RcsMessage()
            .withTextMessage("Hello! Welcome to RCSChat.io")
        );
    ```
    The corresponding Json string representation:
    ```aidl
    {
      "messageContact": {
        "userContact": "+11234567890"
      },
      "rcsMessage": {
        "textMessage": "Hello! Welcome to RCSChat.io"
      }
    }
    ```
    [Demo](https://rcschat.io/demo/rcs)
### ChipList
1. Message Builder
    ```aidl
    final ChatMessage chatMessage = ChatMessage.builder()
        .messageContact(MessageContact.builder()
            .userContact(userContact)
            .build()
        )
        .rcsMessage(RcsMessage.builder()
            .textMessage(text)
            .suggestedChipList(new SuggestedChipList(
                Arrays.asList(
                    new Suggestion(new SuggestedReply("Suggested Reply")),
                    new Suggestion(SuggestedAction.builder()
                        .displayText("Current Location")
                        .mapAction(new MapAction(new RequestLocationPush()))
                        .build())
                )
            ))
            .build()
        )
        .build();
    ```

2. Adder
    ```aidl
    final ChatMessage chatMessage = new ChatMessage()
        .withMessageContact(new MessageContact("+11234567890"))
        .withRcsMessage(new RcsMessage()
            .withTextMessage("Hello! Welcome to RCSChat.io")
        );

    chatMessage.addSuggestedRequestLocationAction("Current Location");
    ```
    The corresponding Json string representation:
    ```aidl
    {
      "messageContact": {
        "userContact": "+11234567890"
      },
      "rcsMessage": {
        "textMessage": "Hello! Welcome to RCSChat.io",
        "suggestedChipList": {
          "suggestions": [
            {
              "action": {
                "mapAction": {
                  "requestLocationPush": {
                  }
                },
                "displayText": "Current Location"
              }
            }
          ]
        }
      }
    }
    ```
    [Demo](https://rcschat.io/demo/rcs)
### Standalone Richcard Message
1. Message Builder
    ```
    final GeneralPurposeCard generalPurposeCard = GeneralPurposeCard.builder()
        .layout(new CardLayout(CardOrientation.VERTICAL))
        .content(
            Content.builder()
                .title("card title")
                .description("card description")
                .media(
                    Media.builder()
                        .height(MEDIA_HEIGHT)
                        .mediaUrl("https://rcschat.io/hatch.png")
                        .mediaContentType("image/jpeg")
                        .mediaFileSize(17632L)
                        .build();
                )
                .suggestions(
                    Arrays.asList(
                        new Suggestion(new SuggestedReply("Suggested Reply"))
                    )
                )
                .build();
        )
        .build();

    final ChatMessage chatMessage = ChatMessage.builder()
        .messageContact(
            MessageContact.builder()
                .userContact("+11234567890")
                .build()
        )
        .rcsMessage(
            RcsMessage.builder()
                .richcardMessage(
                    RichcardMessage.builder()
                        .message(new Message(generalPurposeCard))
                        .build()
                )
                .build()
        )
        .build();
    ```
    The corresponding Json string representation:
    ```aidl
    {
      "messageContact": {
        "userContact": "+11234567890"
      },
      "rcsMessage": {
        "richcardMessage": {
          "message": {
            "generalPurposeCard": {
              "layout": {
                "cardOrientation": "VERTICAL"
              },
              "content": {
                "title": "card title",
                "description": "card description",
                "media": {
                  "mediaUrl": "https://rcschat.io/hatch.png",
                  "mediaContentType": "image/jpeg",
                  "mediaFileSize": 17632,
                  "height": "MEDIUM_HEIGHT"
                },
                "suggestions": [
                  {
                    "reply": {
                      "displayText": "Suggested Reply"
                    }
                  }
                ]
              }
            }
          }
        }
      }
    }
    ```
    [Demo](https://rcschat.io/demo/rcs)

### Richcard Carousel Message
1. Message Builder
    ```
    final GeneralPurposeCardCarousel generalPurposeCardCarousel = GeneralPurposeCardCarousel.builder()
        .layout(new CarouselLayout(CardWidth.MEDIUM_WIDTH))
        .build();
    for (int i = 0; i < MAX_CARD_NUM; ++i) {
        generalPurposeCardCarousel.addContent(
            Content.builder()
                .title("card title" + i)
                .description("card description" + i)
                .media(
                    Media.builder()
                        .height(MEDIA_HEIGHT)
                        .mediaUrl("https://rcschat.io/hatch.png")
                        .mediaContentType("image/jpeg")
                        .mediaFileSize(17632L)
                        .build();
                )
                .suggestions(
                    Arrays.asList(
                        new Suggestion(new SuggestedReply("Reply card" + i))
                    )
                )
                .build();
        );
    }

    final ChatMessage chatMessage = ChatMessage.builder()
        .messageContact(
            MessageContact.builder()
                .userContact("+11234567890")
                .build()
        )
        .rcsMessage(
            RcsMessage.builder()
                .richcardMessage(
                    RichcardMessage.builder()
                        .message(new Message(generalPurposeCardCarousel))
                        .build()
                )
                .build()
        )
        .build();
    ```
    The corresponding Json string representation:
    ```aidl
    {
      "messageContact": {
        "userContact": "+11234567890"
      },
      "rcsMessage": {
        "richcardMessage": {
          "message": {
            "generalPurposeCardCarousel": {
              "layout": {
                "cardWidth": "MEDIUM_WIDTH"
              },
              "content": [
                {
                  "title": "card title0",
                  "description": "card description0",
                  "media": {
                    "mediaUrl": "https://rcschat.io/hatch.png",
                    "mediaContentType": "image/jpeg",
                    "mediaFileSize": 17632,
                    "height": "MEDIUM_HEIGHT"
                  },
                  "suggestions": [
                    {
                      "reply": {
                        "displayText": "Reply card0"
                      }
                    }
                  ]
                },
                {
                  "title": "card title1",
                  "description": "card description1",
                  "media": {
                    "mediaUrl": "https://rcschat.io/hatch.png",
                    "mediaContentType": "image/jpeg",
                    "mediaFileSize": 17632,
                    "height": "MEDIUM_HEIGHT"
                  },
                  "suggestions": [
                    {
                      "reply": {
                        "displayText": "Reply card1"
                      }
                    }
                  ]
                },
                {
                  "title": "card title2",
                  "description": "card description2",
                  "media": {
                    "mediaUrl": "https://rcschat.io/hatch.png",
                    "mediaContentType": "image/jpeg",
                    "mediaFileSize": 17632,
                    "height": "MEDIUM_HEIGHT"
                  },
                  "suggestions": [
                    {
                      "reply": {
                        "displayText": "Reply card2"
                      }
                    }
                  ]
                }
              ]
            }
          }
        }
      }
    }
    ```
    [Demo](https://rcschat.io/demo/rcs)

## Vert.x service proxy package
To use in Vert.x service proxies, convert to types that satisfy the restrictions for service interfaces. The proxy package implements service proxy compatible version of the core package.

* Construct from core package
    ```
    final io.rcschat.messages.core.FileMessage fileMessage = new io.rcschat.messages.core.FileMessage();

    final io.rcschat.messages.proxy.FileMessage proxyFileMessage = new io.rcschat.messages.proxy.FileMessage(fileMessage);
    ```
* Construct new
    ```aidl
    final io.rcschat.messages.proxy.FileMessage proxyFileMessage = new io.rcschat.messages.proxy.FileMessage();
    proxyFileMessage.setFileUrl("https://rcschat.io/hatch.png");
    ```