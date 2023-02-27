package com.phuc.pcoreservice.controller;

import com.phuc.pcoreservice.payload.request.PhotoRequest;
import com.phuc.pcoreservice.service.INotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bot")
public class TelegramBotController {

    @Autowired
    private INotifyService notifyService;



    @PostMapping("send-log-photo")
    public ResponseEntity<?> sendPhotoToBot(@RequestBody PhotoRequest photoRequest){
        return notifyService.sendPhotoTelegram(photoRequest.getPhoto(), photoRequest.getCaption());
    }
}
