package com.phuc.pcoreservice.service;

import org.springframework.http.ResponseEntity;

public interface INotifyService {
    ResponseEntity<?> sendMessageToTelegram(String message);

    ResponseEntity<?> sendPhotoTelegram(String base64Photo, String caption);
}
