package com.crisdev.api.storeapi.persistence.entity;

import com.crisdev.api.storeapi.persistence.entity.security.User;
import com.crisdev.api.storeapi.persistence.entity.util.PaymentMethodProviderEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_payment_method")
public class UserPaymentMethod implements Serializable {

   @Id
   @GeneratedValue
   private Long id;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

   @ManyToOne
   @JoinColumn(name = "payment_type_id")
   private PaymentType paymentType;

   @Enumerated(value = EnumType.STRING)
   private PaymentMethodProviderEnum paymentMethodProviderEnum;
   private String account_number;
   private Date expiryDate;
   private boolean isDefault;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public PaymentType getPaymentType() {
      return paymentType;
   }

   public void setPaymentType(PaymentType paymentType) {
      this.paymentType = paymentType;
   }

   public PaymentMethodProviderEnum getPaymentMethodProviderEnum() {
      return paymentMethodProviderEnum;
   }

   public void setPaymentMethodProviderEnum(PaymentMethodProviderEnum paymentMethodProviderEnum) {
      this.paymentMethodProviderEnum = paymentMethodProviderEnum;
   }

   public String getAccount_number() {
      return account_number;
   }

   public void setAccount_number(String account_number) {
      this.account_number = account_number;
   }

   public Date getExpiryDate() {
      return expiryDate;
   }

   public void setExpiryDate(Date expiryDate) {
      this.expiryDate = expiryDate;
   }

   public boolean isDefault() {
      return isDefault;
   }

   public void setDefault(boolean aDefault) {
      isDefault = aDefault;
   }
}
