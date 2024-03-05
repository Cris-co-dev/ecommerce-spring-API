package com.crisdev.api.storeapi.controller;

import com.crisdev.api.storeapi.dto.request.PaymentTypeRequest;
import com.crisdev.api.storeapi.persistence.entity.PaymentType;
import com.crisdev.api.storeapi.service.PaymentTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment-types")
public class PaymentTypeController {

    private final PaymentTypeService paymentTypeService;

    public PaymentTypeController(PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentType>> readAllPaymentTypes() {
        List<PaymentType> paymentTypes = paymentTypeService.readAll();

        return ResponseEntity.ok(paymentTypes);
    }

    @GetMapping("/{paymentTypeId}")
    public ResponseEntity<PaymentType> readOnePaymentType(@PathVariable Long paymentTypeId) {
        PaymentType paymentType = paymentTypeService.readById(paymentTypeId);

        return ResponseEntity.ok(paymentType);
    }

    @PostMapping
    public ResponseEntity<PaymentType> addPaymentType(@RequestBody @Valid PaymentTypeRequest paymentTypeRequest) {
        PaymentType paymentType = paymentTypeService.addPaymentType(paymentTypeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentType);
    }

    @PutMapping("/{paymentTypeId}")
    public ResponseEntity<PaymentType> updatePaymentType(@PathVariable Long paymentTypeId,
                                                     @RequestBody @Valid PaymentTypeRequest paymentTypeRequest) {
        PaymentType paymentType = paymentTypeService.updatePaymentType(paymentTypeId, paymentTypeRequest);
        return ResponseEntity.ok(paymentType);
    }

    @DeleteMapping("/{paymentTypeId}")
    public ResponseEntity<Map<String, String>> deletePaymentType(@PathVariable Long paymentTypeId) {

        paymentTypeService.deletePaymentType(paymentTypeId);

        return ResponseEntity.ok(Collections.singletonMap("message", "PaymentType deleted successfully"));
    }

}
