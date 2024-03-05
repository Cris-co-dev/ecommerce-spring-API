package com.crisdev.api.storeapi.service;

import com.crisdev.api.storeapi.dto.request.UserPaymentMethodRequest;
import com.crisdev.api.storeapi.dto.response.UserPaymentMethodResponse;

import java.util.List;

public interface UserPaymentMethodService {
    List<UserPaymentMethodResponse> readAll(Long userId);
    UserPaymentMethodResponse readById(Long id);
    UserPaymentMethodResponse addPaymentMethod(UserPaymentMethodRequest userPaymentMethodRequest);
    UserPaymentMethodResponse updatePaymentMethod(Long id, UserPaymentMethodRequest userPaymentMethodRequest);
    UserPaymentMethodResponse updateDefaultPaymentMethod(Long userId, Long paymentMethodId);
    void deletePaymentMethod(Long id);

}
