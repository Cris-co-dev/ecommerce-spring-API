package com.crisdev.api.storeapi.controller;

import com.crisdev.api.storeapi.dto.request.UserPaymentMethodRequest;
import com.crisdev.api.storeapi.dto.response.UserPaymentMethodResponse;
import com.crisdev.api.storeapi.service.UserPaymentMethodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/payment-methods")
public class UserPaymentMethodController {


    private final UserPaymentMethodService userPaymentMethodService;

    public UserPaymentMethodController(UserPaymentMethodService userPaymentMethodService) {
        this.userPaymentMethodService = userPaymentMethodService;
    }

    @GetMapping("/{paymentMethodId}")
    public ResponseEntity<UserPaymentMethodResponse> readPaymentMethodById(@PathVariable Long paymentMethodId) {
        return ResponseEntity.ok(userPaymentMethodService.readById(paymentMethodId));
    }

    @PostMapping
    public ResponseEntity<UserPaymentMethodResponse> addPaymentMethod(@RequestBody UserPaymentMethodRequest pmRequest) {

        UserPaymentMethodResponse response = userPaymentMethodService.addPaymentMethod(pmRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{paymentMethodId}")
    public ResponseEntity<UserPaymentMethodResponse> updatePaymentMethod(@PathVariable Long paymentMethodId,
                                                                         @RequestBody UserPaymentMethodRequest pmRequest) {

        UserPaymentMethodResponse response = userPaymentMethodService
                .updatePaymentMethod(paymentMethodId, pmRequest);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{userId}/{paymentMethodId}")
    public ResponseEntity<UserPaymentMethodResponse> updateDefaultPaymentMethod(@PathVariable Long userId,
                                                                                @PathVariable Long paymentMethodId) {
        UserPaymentMethodResponse response = userPaymentMethodService.updateDefaultPaymentMethod(userId, paymentMethodId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{paymentMethodId}")
    public ResponseEntity<Map<String,String>> deletePaymentMethod(@PathVariable Long paymentMethodId) {
        userPaymentMethodService.deletePaymentMethod(paymentMethodId);
        return ResponseEntity.ok(Collections.singletonMap("message", "PaymentMethod deleted successfully"));
    }

}
