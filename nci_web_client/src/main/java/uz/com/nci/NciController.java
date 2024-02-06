package uz.com.nci;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @className: NciController
 * @date: 12.01.2024
 * @author: Uralbaev Diyorbek
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/nci")
public class NciController {

    private final NciService nciService;

    @GetMapping
    public ResponseEntity<?> getExchangeCoursesWithWebClient(@RequestParam(value = "pinfl") String pinfl,
                                                             @RequestParam(value = "branch") String branch) {
        Mono<String> exchangeCourses = nciService.getExchangeCoursesWithWebClient(pinfl, branch);
        return ResponseEntity.ok(exchangeCourses);
    }

//    http://192.168.104.66:8088/back/v2/GetCustomerIdByPINFL/31311930262076/00980
    @GetMapping("/with-rest-template")
    public ResponseEntity<?> getExchangeCoursesWithRestTemplate(
                                                                @RequestParam(value = "pinfl") String pinfl,
                                                                @RequestParam(value = "branch") String branch) {
        String exchangeCourses = nciService.getExchangeCoursesWithRestTemplate(pinfl, branch);
        return ResponseEntity.ok(exchangeCourses);
    }
}
