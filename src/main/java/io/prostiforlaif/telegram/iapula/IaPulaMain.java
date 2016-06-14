package io.prostiforlaif.telegram.iapula;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

/**
 * Created by DaneasaV on 14.06.2016.
 */
public class IaPulaMain {

  public static void main(String[] args) {
    TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
    try {
      telegramBotsApi.registerBot(new IaPulaHandler());
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }

}
