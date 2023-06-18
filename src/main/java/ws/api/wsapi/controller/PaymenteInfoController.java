package ws.api.wsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.api.wsapi.dto.PaymentProcessDto;
import ws.api.wsapi.service.PaymentInfoService;

@RequestMapping("/payment")
@RestController
public class PaymenteInfoController {
    @Autowired
    private PaymentInfoService paymentInfoService;
    @PostMapping("/process")
    public ResponseEntity<Boolean> processPayment(@Validated @RequestBody PaymentProcessDto processDto){
        return ResponseEntity.ok().body(paymentInfoService.process(processDto));
    }
}
