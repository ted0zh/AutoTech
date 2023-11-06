package com.autoTech.autoTech.services;

import org.springframework.stereotype.Component;

@Component
public class MessageService {
    public String getMessage(String initialMsg) {
        return initialMsg + " (approved by Spring!)";
    }
}
