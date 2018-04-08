package com.akurus.telegram.bot;

import ai.api.model.AIResponse;
import com.akurus.telegram.AI.DataFlow;
import com.akurus.telegram.AI.DataFlowPool;
import com.google.gson.JsonElement;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.Map;

public class TelegramBot extends TelegramLongPollingBot {
    public static final String BOT_NAME = "RussianEdition_bot";
    public static final String BOT_TOKEN = "418492503:AAHvWxBdzolBRbSwqjHSCk9IBobK9OiYzq0";

    public TelegramBot() {
    }

    public TelegramBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotUsername() {
//        return "WhatIsItYouWant_bot";
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
//        return "402968864:AAHAp07vDK8k5R44gFchyzGX59kdUxZOZtI";
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            DataFlow dataFlow = DataFlowPool.getConnection();
            AIResponse response = dataFlow.request(update.getMessage().getText());
            String displayText;
            if (response.getStatus().getCode() == 200) {
                System.out.println(response.getResult());
                Map<String, JsonElement> param = response.getResult().getParameters(); //JSON из API AI

                if (param.isEmpty()) {
                    displayText = response.getResult().getFulfillment().getSpeech();
                } else {
                    switch (update.getMessage().getText()) {
                        case "Трубы горят":
                            displayText = "https://vk.com/topic-76281904_30722945";
                            break;
                        case "Замена счетчика":
                            displayText = "https://www.avito.ru/kazan/predlozheniya_uslug/santehnik_1212023278";
                            break;
                        default:
                            displayText = "https://www.avito.ru/kazan/predlozheniya_uslug/santehnik_868369315";
                            break;
                    }
                }
                SendMessage sendMessage = new SendMessage()
                        .setParseMode(ParseMode.HTML)
                        .setText(displayText)
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