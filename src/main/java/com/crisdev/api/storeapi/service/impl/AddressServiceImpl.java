package com.crisdev.api.storeapi.service.impl;

import com.crisdev.api.storeapi.dto.request.AddressRequest;
import com.crisdev.api.storeapi.dto.response.AddressResponse;
import com.crisdev.api.storeapi.exception.ObjectNotFoundException;
import com.crisdev.api.storeapi.persistence.entity.Address;
import com.crisdev.api.storeapi.persistence.entity.Country;
import com.crisdev.api.storeapi.persistence.entity.security.User;
import com.crisdev.api.storeapi.persistence.repository.AddressRepository;
import com.crisdev.api.storeapi.persistence.repository.CountryRepository;
import com.crisdev.api.storeapi.persistence.repository.security.UserRepository;
import com.crisdev.api.storeapi.service.AddressService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CountryRepository countryRepository;

    public AddressServiceImpl(UserRepository userRepository, AddressRepository addressRepository, CountryRepository countryRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public List<AddressResponse> readAll(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + id));

        return user.getAddresses().stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    @Override
    public AddressResponse findById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Address not found with id: " + id));

        return entityToResponse(address);
    }

    @Override
    public AddressResponse createNewAddress(AddressRequest addressRequest) {
        Country country = countryRepository.findById(addressRequest.getCountryId())
                .orElseThrow(() -> new ObjectNotFoundException("Country not found with id: " + addressRequest.getCountryId()));

        User user = userRepository.findById(addressRequest.getUserId())
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + addressRequest.getUserId()));

        if (addressRequest.isDefault()) {
            user.getAddresses().forEach(a -> a.setDefault(false));
        }

        Address address = new Address();
        address.setAddressLineOne(address.getAddressLineTwo());
        address.setAddressLineTwo(address.getAddressLineTwo());
        address.setCity(addressRequest.getCity());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setRegion(addressRequest.getRegion());
        address.setCountry(country);
        address.setUser(user);
        address.setDefault(addressRequest.isDefault());
        Address savedAddress = addressRepository.save(address);

        return entityToResponse(savedAddress);
    }

    @Override
    public AddressResponse updateAddress(Long id, AddressRequest addressRequest) {

        Country country = countryRepository.findById(addressRequest.getCountryId())
                .orElseThrow(() -> new ObjectNotFoundException("Country not found with id: " + addressRequest.getCountryId()));

        User user = userRepository.findById(addressRequest.getUserId())
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + addressRequest.getUserId()));

        if (addressRequest.isDefault()) {
            user.getAddresses().forEach(a -> a.setDefault(false));
        }

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Address not found with id: " + id));
        address.setAddressLineOne(address.getAddressLineTwo());
        address.setAddressLineTwo(address.getAddressLineTwo());
        address.setCity(addressRequest.getCity());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setRegion(addressRequest.getRegion());
        address.setCountry(country);
        address.setUser(user);
        address.setDefault(addressRequest.isDefault());
        Address updatedAddress = addressRepository.save(address);

        return entityToResponse(updatedAddress);
    }

    @Override
    public AddressResponse updateDefaultAddress(Long userId, Long addressId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + userId));

        user.getAddresses().forEach(address -> address.setDefault(Objects.equals(address.getId(), addressId)));
        userRepository.save(user);

        return entityToResponse(addressRepository.findById(addressId)
                .orElseThrow(() -> new ObjectNotFoundException("Address not found with id: " + addressId)));
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Address not found with id: " + id));

        addressRepository.delete(address);
    }

    private AddressResponse entityToResponse(Address address) {
        AddressResponse addressResponse = new AddressResponse();
        BeanUtils.copyProperties(address, addressResponse);

        return addressResponse;
    }
}
