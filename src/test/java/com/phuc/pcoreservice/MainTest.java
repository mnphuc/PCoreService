package com.phuc.pcoreservice;

public class MainTest {
    public static void main(String[] args) {
        String url = "https://fingerprints.bablosoft.com/prepare?version=5&tags=%s&key=%s&returnpc=true";
        String format = String.format(url, "ww", "pp");
        System.out.println(format);
    }
}
