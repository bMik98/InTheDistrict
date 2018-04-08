package com.akurus.telegram.bot;



import ai.api.model.AIResponse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import com.akurus.telegram.AI.DataFlow;
import com.akurus.telegram.AI.DataFlowPool;
import java.util.HashMap;


public class TelegramBot extends TelegramLongPollingBot {


    public TelegramBot() {
    }

    public TelegramBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotUsername() {
        return "WhatIsItYouWant_bot";
    }

    @Override
    public String getBotToken() {
        return "402968864:AAHAp07vDK8k5R44gFchyzGX59kdUxZOZtI";
    }

    @Override
    public void onUpdateReceived(Update update) {
        try{
            DataFlow dataFlow = DataFlowPool.getConnection();
            AIResponse response = dataFlow.request(update.getMessage().getText());

            if (response.getStatus().getCode() == 200 && response != null) {
                System.out.println(response.getResult());
                HashMap<String, JsonElement> param =  response.getResult().getParameters();//JSON из API AI

                    SendMessage sendMessage = new SendMessage()
                            .setParseMode(ParseMode.HTML)
                            .setText("сылка")
                            .setChatId(update.getMessage().getChatId().toString());
                    execute(sendMessage);
            } else {
                SendMessage sendMessage = new SendMessage()
                        .setText(response.getStatus().getErrorDetails())
                        .setParseMode(ParseMode.HTML);
                execute(sendMessage);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}