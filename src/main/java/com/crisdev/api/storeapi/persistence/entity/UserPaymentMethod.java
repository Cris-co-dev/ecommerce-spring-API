package com.crisdev.api.storeapi.persistence.entity;

import com.crisdev.api.storeapi.persistence.entity.security.User;
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
   private String provider;
   @Column(name = "account_number")
   private String accountNumber;
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

   public String getProvider() {
      return provider;
   }
   public void setProvider(String provider) {
      this.provider = provider;
   }

   public String getAccountNumber() {
      return accountNumber;
   }

   public void setAccountNumber(String account_number) {
      this.accountNumber = account_number;
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
