package com.crisdev.api.storeapi.service;

import com.crisdev.api.storeapi.dto.request.PaymentTypeRequest;
import com.crisdev.api.storeapi.persistence.entity.PaymentType;

import java.util.List;

public interface PaymentTypeService {
    List<PaymentType> readAll();
    PaymentType readById(Long paymentTypeId);
    PaymentType addPaymentType(PaymentTypeRequest paymentTypeRequest);
    PaymentType updatePaymentType(Long id, PaymentTypeRequest paymentTypeRequest);

    void deletePaymentType(Long id);
}
