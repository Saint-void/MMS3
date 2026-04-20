package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class QuoteController {

    private List<String> quotes = new ArrayList<>();
    private Random random = new Random();

    // Constructor → preload quotes
    public QuoteController() {
        for (int i = 1; i <= 100; i++) {
            quotes.add("Quote " + i + ": Stay consistent, success is coming.");
        }
    }

    @GetMapping("/quote")
    public Map<String, String> getRandomQuote() {
        Map<String, String> response = new HashMap<>();

        int index = random.nextInt(quotes.size());
        String quote = quotes.get(index);

        response.put("quote", quote);

        return response;
    }
}