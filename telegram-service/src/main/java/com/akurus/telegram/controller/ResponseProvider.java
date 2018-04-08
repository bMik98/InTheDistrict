package com.akurus.telegram.controller;

import com.akurus.telegram.entity.Advert;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResponseProvider {
    private List<Advert> cache = new ArrayList<>();

    public ResponseProvider() {
    }

    List<Advert> find(String city) {
        if(cache.size() == 0) {
            init();
        }
        List<Advert> result = new ArrayList<>();
        return result;
    }

    private void init() {
        try {
            System.out.println(getClass().getResource("ffff").getFile().toString());
            Files.lines(Paths.get(getClass().getResource("Advert1.txt").getFile()), StandardCharsets.UTF_8).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
