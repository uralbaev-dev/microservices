package com.octo.fraud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @className: FraudCheckController
 * @date: 15.08.2023
 * @author: Uralbaev Diyorbek
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fraud-check")
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable(value = "customerId") Integer customerId) {
        log.info(">> isFraudster ");
        return new FraudCheckResponse(fraudCheckService.isFraudulentCustomer(customerId));
    }
}
