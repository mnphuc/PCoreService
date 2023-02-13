package com.phuc.pcoreservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends org.telegram.telegrambots.bots.TelegramLongPollingBot {
    @Value("${telegram.bot.username}")
    private String botUsername;
    @Value("${telegram.bot.token}")
    private String botToken;

    public TelegramBot(String botUsername, String botToken) {
        this.botUsername = botUsername;
        this.botToken = botToken;
    }
    public TelegramBot() {

    }

    @Override
    public void onUpdateReceived(Update update) {
        // Xử lý tin nhắn được nhận
        Long chatId = update.getMessage().getChatId();
        // Gửi tin nhắn đến người dùng
        SendMessage message = new SendMessage("-892127097", "Xin chào!");
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage(String message){
        SendMessage sendMessage = new SendMessage("-892127097", message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
