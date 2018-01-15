package com.example.jonathanlarsen.pensionconsultmainpage.Logic;

public class SmtpConfiguration {

    private final String mail = "pensiontest1234";
    private final String password = "SnakeEyes123";
    private final String port = "465";
    private final String host = "smtp.gmail.com";


    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }
}
