package com.crisdev.api.storeapi.service.impl;

import com.crisdev.api.storeapi.dto.request.UserPaymentMethodRequest;
import com.crisdev.api.storeapi.dto.response.UserPaymentMethodResponse;
import com.crisdev.api.storeapi.exception.ObjectNotFoundException;
import com.crisdev.api.storeapi.persistence.entity.PaymentType;
import com.crisdev.api.storeapi.persistence.entity.UserPaymentMethod;
import com.crisdev.api.storeapi.persistence.entity.security.User;
import com.crisdev.api.storeapi.persistence.repository.PaymentTypeRepository;
import com.crisdev.api.storeapi.persistence.repository.UserPaymentMethodRepository;
import com.crisdev.api.storeapi.persistence.repository.security.UserRepository;
import com.crisdev.api.storeapi.service.UserPaymentMethodService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserPaymentMethodServiceImpl implements UserPaymentMethodService {

    private final UserPaymentMethodRepository userPaymentMethodRepository;
    private final UserRepository userRepository;
    private final PaymentTypeRepository paymentTypeRepository;

    public UserPaymentMethodServiceImpl(UserPaymentMethodRepository userPaymentMethodRepository, UserRepository userRepository, PaymentTypeRepository paymentTypeRepository) {
        this.userPaymentMethodRepository = userPaymentMethodRepository;
        this.userRepository = userRepository;
        this.paymentTypeRepository = paymentTypeRepository;
    }

    @Override
    public List<UserPaymentMethodResponse> readAll(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + userId));

        return userPaymentMethodRepository.findAllByUser(user).stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserPaymentMethodResponse readById(Long id) {

        UserPaymentMethod userPaymentMethod = userPaymentMethodRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("PaymentMethod not found with id: " + id));

        return entityToResponse(userPaymentMethod);
    }

    @Override
    public UserPaymentMethodResponse addPaymentMethod(UserPaymentMethodRequest userPaymentMethodRequest) {

        User user = userRepository.findById(userPaymentMethodRequest.getUserId())
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + userPaymentMethodRequest.getUserId()));

        PaymentType paymentType = paymentTypeRepository.findById(userPaymentMethodRequest.getPaymentTypeId())
                .orElseThrow(() -> new ObjectNotFoundException("PaymentType not found with id: " + userPaymentMethodRequest.getPaymentTypeId()));

        List<UserPaymentMethod> paymentMethods = userPaymentMethodRepository.findAllByUser(user);

        if (userPaymentMethodRequest.isDefault() && !paymentMethods.isEmpty()) {
            paymentMethods.forEach(pm -> {
                pm.setDefault(false);
                userPaymentMethodRepository.save(pm);
            });
        }
        SimpleDateFormat format = new SimpleDateFormat("MM/yy");

        UserPaymentMethod userPaymentMethod = new UserPaymentMethod();
        buildPM(userPaymentMethodRequest, userPaymentMethod, user, paymentType, format);

        UserPaymentMethod savedPaymentMethod = userPaymentMethodRepository.save(userPaymentMethod);

        return entityToResponse(savedPaymentMethod);
    }

    @Override
    public UserPaymentMethodResponse updatePaymentMethod(Long id, UserPaymentMethodRequest userPaymentMethodRequest) {


        User user = userRepository.findById(userPaymentMethodRequest.getUserId())
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + userPaymentMethodRequest.getUserId()));

        PaymentType paymentType = paymentTypeRepository.findById(userPaymentMethodRequest.getPaymentTypeId())
                .orElseThrow(() -> new ObjectNotFoundException("PaymentType not found with id: " + userPaymentMethodRequest.getPaymentTypeId()));

        List<UserPaymentMethod> paymentMethods = userPaymentMethodRepository.findAllByUser(user);

        if (userPaymentMethodRequest.isDefault() && !paymentMethods.isEmpty()) {
            paymentMethods.forEach(pm -> {
                pm.setDefault(false);
                userPaymentMethodRepository.save(pm);
            });
        }
        SimpleDateFormat format = new SimpleDateFormat("MM/yy");

        UserPaymentMethod userPaymentMethod = userPaymentMethodRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("PaymentMethod not found with id: " + id));

        buildPM(userPaymentMethodRequest, userPaymentMethod, user, paymentType, format);

        UserPaymentMethod updatedPaymentMethod = userPaymentMethodRepository.save(userPaymentMethod);

        return entityToResponse(updatedPaymentMethod);
    }

    @Override
    public UserPaymentMethodResponse updateDefaultPaymentMethod(Long userId, Long paymentMethodId) {

        UserPaymentMethod userPaymentMethod = userPaymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new ObjectNotFoundException("Address not found with id: " + paymentMethodId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + userId));

        List<UserPaymentMethod> paymentMethods = userPaymentMethodRepository.findAllByUser(user);

        paymentMethods.forEach(pm -> {

            if (pm.getId().equals(paymentMethodId)){
                pm.setDefault(!pm.isDefault());
            }else {
                pm.setDefault(false);
                userPaymentMethodRepository.save(pm);            }
        });

        UserPaymentMethod updatedPaymentMethod = userPaymentMethodRepository.save(userPaymentMethod);

        return entityToResponse(updatedPaymentMethod);
    }

    @Override
    public void deletePaymentMethod(Long id) {
        UserPaymentMethod userPaymentMethod = userPaymentMethodRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("PaymentMethod not found with id: " + id));

        userPaymentMethodRepository.delete(userPaymentMethod);
    }

    private UserPaymentMethodResponse entityToResponse(UserPaymentMethod paymentMethod) {
        UserPaymentMethodResponse response = new UserPaymentMethodResponse();
        BeanUtils.copyProperties(paymentMethod, response);
        return response;
    }

    private static void buildPM(UserPaymentMethodRequest userPaymentMethodRequest, UserPaymentMethod userPaymentMethod, User user, PaymentType paymentType, SimpleDateFormat format) {
        userPaymentMethod.setUser(user);
        userPaymentMethod.setPaymentType(paymentType);
        userPaymentMethod.setProvider(userPaymentMethodRequest.getProvider());
        userPaymentMethod.setAccountNumber(userPaymentMethodRequest.getAccountNumber());
        userPaymentMethod.setDefault(userPaymentMethodRequest.isDefault());

        try {
            userPaymentMethod.setExpiryDate(format.parse(userPaymentMethodRequest.getDate()));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
