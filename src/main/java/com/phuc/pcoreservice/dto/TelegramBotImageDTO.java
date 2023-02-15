package com.phuc.pcoreservice.dto;

import jdk.nashorn.internal.objects.annotations.Property;
import lombok.Data;

import java.io.File;

@Data
public class TelegramBotImageDTO {

    @Property(name = "chat_id")
    private String chat_id;

    @Property(name = "photo")
    private String photo;

    @Property(name = "caption")
    private String caption;
}
