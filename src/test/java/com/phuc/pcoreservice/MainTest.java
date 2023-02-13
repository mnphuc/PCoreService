package com.phuc.pcoreservice;

import com.phuc.pcoreservice.config.TelegramBot;

public class MainTest {
    public static void main(String[] args) {
        TelegramBot bot = new TelegramBot("bot_of_you", "5748050505:AAEZEtv9aSEHk5VWmWVr68jiYP9KlimddnE");
        TelegramBot telegramBot = new TelegramBot();
        telegramBot.sendMessage("xin chao");
    }
}
