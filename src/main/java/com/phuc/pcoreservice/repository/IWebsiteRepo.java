package com.phuc.pcoreservice.repository;

public interface IWebsiteRepo {
    String getRandomUrl();

    String getRandomWebsite(String ipAddress);
}
