package io.prostiforlaif.telegram.iapula;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.logging.BotLogger;

/**
 * Created by DaneasaV on 14.06.2016.
 */
public class IaPulaHandler extends TelegramLongPollingBot {

  private static final String CHANNEL_MESSAGE_TEXT = "IAPULA";
  private static final String ERROR_MESSAGE_TEXT = "There was an error sending the traditional IAPULA to channel *%s*, the error was: ```%s```";

  @Override public void onUpdateReceived(Update update) {
    Message message = update.getMessage();

    SendMessage sendMessage = new SendMessage();
    sendMessage.enableMarkdown(true);
    sendMessage.setChatId(message.getChatId().toString());


    sendMessage.setText(CHANNEL_MESSAGE_TEXT);
    sendMessage.enableMarkdown(true);

    try {
      sendMessage(sendMessage);
    } catch (TelegramApiException e) {
      sendErrorMessage(message, e.getMessage());
    }
  }


  private void sendErrorMessage(Message message, String errorText) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.enableMarkdown(true);
    sendMessage.setChatId(message.getChatId().toString());
    sendMessage.setReplayToMessageId(message.getMessageId());

    sendMessage.setText(String.format(ERROR_MESSAGE_TEXT, message.getText().trim(), errorText.replace("\"", "\\\"")));
    sendMessage.enableMarkdown(true);

    try {
      sendMessage(sendMessage);
    } catch (TelegramApiException e) {
      BotLogger.error("IAPULA", e);
    }
  }


  @Override public String getBotUsername() {
    return "iapula_bot";
  }

  @Override public String getBotToken() {
    return "233917762:AAFeNM7JyiyT-IaTSXhE2wFOR7UAHHEkR20";
  }
}
