import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.groupadministration.LeaveChat;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Bot extends TelegramLongPollingBot {

  @Override
  public void onUpdateReceived(Update update) {
    System.out.println(update);

    SendMessage sendMessage = new SendMessage();
    sendMessage.enableMarkdown(false);
    sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
    sendMessage.setText("Hello");
    sendMessage.setReplyMarkup(createInlineKeyboardWithTypeOfProgram());
    try {
      Message execute = execute(sendMessage);
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }

  public InlineKeyboardMarkup createInlineKeyboardWithTypeOfProgram() {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    List<InlineKeyboardButton> keyboardRow = null;
    keyboardRow = new ArrayList<>();
    InlineKeyboardButton button = new InlineKeyboardButton();
    button.setText("Press me");
    button.setCallbackData("callback_press_me");
    keyboardRow.add(button);
    rowList.add(keyboardRow);
    inlineKeyboardMarkup.setKeyboard(rowList);
    return inlineKeyboardMarkup;
  }

  public SendMediaGroup createMediaGroup(String caption, Long chatId) {
    InputMediaPhoto inputMedia = new InputMediaPhoto();
    inputMedia.setCaption(caption);
    inputMedia.setMedia(
        new File("/Users/sofiadiakonova/IdeaProjects/telegram_bot_test/src/main/resources/image_test.png"),
        "image_test.png");

    InputMediaPhoto inputMedia1 = new InputMediaPhoto();
    inputMedia1.setMedia(
        new File("/Users/sofiadiakonova/IdeaProjects/telegram_bot_test/src/main/resources/full-join.png"),
        "full-join.png");
    InputMediaPhoto inputMedia2 = new InputMediaPhoto();
    inputMedia2.setMedia(
        new File("/Users/sofiadiakonova/IdeaProjects/telegram_bot_test/src/main/resources/inner-join.png"),
        "inner-join.png");
    InputMediaPhoto inputMedia3 = new InputMediaPhoto();
    inputMedia3.setMedia(
        new File("/Users/sofiadiakonova/IdeaProjects/telegram_bot_test/src/main/resources/join-type.png"),
        "join-type.png");
    return SendMediaGroup.builder()
        .medias(List.of(inputMedia, inputMedia1, inputMedia2, inputMedia3))
        .chatId(String.valueOf(chatId))
        .replyToMessageId(2047)
        .build();
  }

  public synchronized Message sendMsg(String chatId, String s, ReplyKeyboard keyboard) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);
    sendMessage.setText(s);
    if (keyboard != null) {
      sendMessage.setReplyMarkup(keyboard);
    }
    try {
      Message execute = execute(sendMessage);
      return execute;
    } catch (TelegramApiException e) {
      if (e instanceof TelegramApiRequestException) {
        Integer statusCode = ((TelegramApiRequestException) e).getErrorCode();
        if (statusCode.equals(403)) {
          return null;
        }
      }
      throw new RuntimeException("Some problem", e);
    }
  }

  public synchronized void executeMsg(BotApiMethod message) {
    try {
      execute(message);
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getBotUsername() {
    return "MateTestBot";
  }

  @Override
  public String getBotToken() {
    return "1170690554:AAHKk3uZu3RI2HNQvWxbwsDQrswIeWucnSQ";
  }
}

//    Integer chatId = update.getMessage().getFrom().getId();
//    try {
//      UserProfilePhotos execute = execute(new GetUserProfilePhotos().setUserId(chatId).setLimit(1).setOffset(0));
////      execute(new GetUserProfilePhotos().setUserId(chatId).setLimit(1).setOffset(0)).getPhotos().get(0).get(0)
//      System.out.println(execute);
//    } catch (TelegramApiException e) {
//      System.out.println("exception");
//    }
//    if (a == 3) {
//      DeleteMessage deleteMessage = new DeleteMessage();
//      deleteMessage.setMessageId(messageId - 2);
//      deleteMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
//      try {
//        Boolean execute = execute(deleteMessage);
//        System.out.println("Message was removed: " + execute);
//      } catch (TelegramApiException e) {
//        e.printStackTrace();
//      }
//    }

// 531366747 - KSENIA id
