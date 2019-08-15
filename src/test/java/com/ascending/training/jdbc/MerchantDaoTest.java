package com.ascending.training.jdbc;

import com.ascending.training.bowen.jdbc.MerchantDao;
import com.ascending.training.bowen.model.Merchant;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MerchantDaoTest {

    @Test
    public void getMerchantTest() {
        MerchantDao merchantDao = new MerchantDao();
        List<Merchant> merchants = merchantDao.getMerchant();
        int expectedNumOfMerchant = 2;


        for (Merchant merchant : merchants){
            System.out.println(merchant.getName());
        }

        Assert.assertEquals(expectedNumOfMerchant,merchants.size());
    }
}
