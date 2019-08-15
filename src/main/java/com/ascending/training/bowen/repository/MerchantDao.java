package com.ascending.training.bowen.repository;

import com.ascending.training.bowen.model.Merchant;

import java.util.List;

public interface MerchantDao {
    boolean save(Merchant merchant);
    boolean update(Merchant merchant);
    boolean delete(String merchantName);
    List<Merchant> getMerchants();
    Merchant getMerchantByName(String merchantName);
}
