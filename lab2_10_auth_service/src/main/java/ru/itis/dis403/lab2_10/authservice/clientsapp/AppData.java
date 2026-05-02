package ru.itis.dis403.lab2_10.authservice.clientsapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor
public class AppData {
    private String appId;
    private String appKey;
    private String appRedirectURL;
}
