package com.renqi.takemedicine.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * 功能：
 */

public class CardBean implements IPickerViewData {
    int id;
    String cardNo;
    boolean isSetPicker=false;

    public boolean isSetPicker() {
        return isSetPicker;
    }

    public void setSetPicker(boolean setPicker) {
        isSetPicker = setPicker;
    }

    public CardBean(int id, String cardNo) {
        this.id = id;
        this.cardNo = cardNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public String getPickerViewText() {
        return cardNo;
    }

}

