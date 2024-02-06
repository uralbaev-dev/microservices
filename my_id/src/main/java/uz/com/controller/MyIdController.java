package uz.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.com.service.MyIDService;

/**
 * @className: controller
 * @date: 23.01.2024
 * @author: Uralbaev Diyorbek
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/myId")
public class MyIdController {

    private final MyIDService myIDService;

    @GetMapping("/accessToken")
    public ResponseEntity<?> getAccessToken(@RequestParam("code") String code) {
        return ResponseEntity.ok(myIDService.getAccessToken(code));
    }
}
