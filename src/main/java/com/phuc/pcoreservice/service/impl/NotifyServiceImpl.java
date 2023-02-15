package com.phuc.pcoreservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.SendResponse;
import com.phuc.pcoreservice.dto.TelegramBotImageDTO;
import com.phuc.pcoreservice.service.INotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@Service
public class NotifyServiceImpl implements INotifyService {


    @Autowired
    RestTemplate rs;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.group.id}")
    private String chatId;

    @Override
    public ResponseEntity<?> sendMessageToTelegram(String message) {
        this.rs.getForEntity("https://api.telegram.org/bot" + botToken + "/sendMessage?chat_id=" + chatId + "&text=" + message, String.class, new Object[0]);
        return ResponseEntity.ok().body("send message success!");
    }


    @Override
    public ResponseEntity<?> sendPhotoTelegram(String base64Photo, String caption) {
        TelegramBot bot = new TelegramBot(botToken);
        Long nameFile = new Date().getTime();
        File outputFile = new File(nameFile+".png");
        byte[] imageBytes = Base64.getDecoder().decode(base64Photo);
        try {
            FileUtils.writeByteArrayToFile(outputFile, imageBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SendPhoto request = new SendPhoto(chatId, outputFile)
                .parseMode(ParseMode.HTML)
                .caption(caption)
                .disableNotification(true)
                .replyToMessageId(1)
                .replyMarkup(new ForceReply());
        SendResponse sendResponse = bot.execute(request);
        boolean ok = sendResponse.isOk();
        Message message = sendResponse.message();
        try {
            FileUtils.delete(outputFile);
        } catch (IOException e) {

        }
        return ResponseEntity.ok().body("send message success!");
    }
}
