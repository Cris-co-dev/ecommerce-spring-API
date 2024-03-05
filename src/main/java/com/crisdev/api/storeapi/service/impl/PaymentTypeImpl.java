package com.crisdev.api.storeapi.service.impl;

import com.crisdev.api.storeapi.dto.request.PaymentTypeRequest;
import com.crisdev.api.storeapi.exception.ObjectNotFoundException;
import com.crisdev.api.storeapi.persistence.entity.PaymentType;
import com.crisdev.api.storeapi.persistence.repository.PaymentTypeRepository;
import com.crisdev.api.storeapi.service.PaymentTypeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PaymentTypeImpl implements PaymentTypeService {

    private final PaymentTypeRepository paymentTypeRepository;

    public PaymentTypeImpl(PaymentTypeRepository paymentTypeRepository) {
        this.paymentTypeRepository = paymentTypeRepository;
    }

    @Override
    public List<PaymentType> readAll() {

        List<PaymentType> paymentTypes = new ArrayList<>();
        paymentTypeRepository.findAll().forEach(paymentTypes::add);

        return paymentTypes;
    }

    @Override
    public PaymentType readById(Long paymentTypeId) {
        return paymentTypeRepository.findById(paymentTypeId)
                .orElseThrow(() -> new ObjectNotFoundException("PaymentType not found with id: " + paymentTypeId));
    }

    @Override
    public PaymentType addPaymentType(PaymentTypeRequest countryRequest) {
        return paymentTypeRepository.save(new PaymentType(countryRequest.getValue()));
    }

    @Override
    public PaymentType updatePaymentType(Long id, PaymentTypeRequest countryRequest) {
        PaymentType paymentType = paymentTypeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("PaymentType not found with id: " + id));

        paymentType.setValue(countryRequest.getValue());

        return paymentTypeRepository.save(paymentType);
    }

    @Override
    public void deletePaymentType(Long id) {
        PaymentType paymentType = paymentTypeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("PaymentType not found with id: " + id));

        paymentTypeRepository.delete(paymentType);
    }
}
