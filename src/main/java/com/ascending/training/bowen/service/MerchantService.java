package com.ascending.training.bowen.service;


import com.ascending.training.bowen.model.Merchant;
import com.ascending.training.bowen.repository.MerchantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {
    @Autowired
    private MerchantDao merchantDao;

    public boolean save(Merchant merchant){
        return merchantDao.save(merchant);
    }

    public boolean update(Merchant merchant){
        return merchantDao.update(merchant);
    }

    public boolean delete(String merchantName){
        return merchantDao.delete(merchantName);
    }

    public List<Merchant> getMerchants(){
        return merchantDao.getMerchants();
    }

    public Merchant getMerchantByName(String merchantName){
        return merchantDao.getMerchantByName(merchantName);
    }
}
