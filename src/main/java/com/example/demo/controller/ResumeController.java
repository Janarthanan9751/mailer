
package com.example.demo.controller;

import com.example.demo.service.GmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ResumeController {

    @Autowired
    private GmailService gmailService;

    @PostMapping("/send-resume")
    public ResponseEntity<String> sendResume(@RequestBody Map<String, String> payload) {
        try {
            gmailService.sendResumeEmail(payload.get("email"));
            return ResponseEntity.ok("Resume sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Failed to send resume");
        }
    }
}
