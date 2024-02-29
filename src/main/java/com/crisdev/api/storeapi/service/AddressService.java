package com.crisdev.api.storeapi.service;

import com.crisdev.api.storeapi.dto.request.AddressRequest;
import com.crisdev.api.storeapi.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {
    List<AddressResponse> readAll(Long id);
    AddressResponse findById(Long id);
    AddressResponse createNewAddress(AddressRequest addressRequest);
    AddressResponse updateAddress(Long id, AddressRequest addressRequest);
    AddressResponse updateDefaultAddress(Long userId,Long addressId);
    void deleteAddress(Long id);


}
