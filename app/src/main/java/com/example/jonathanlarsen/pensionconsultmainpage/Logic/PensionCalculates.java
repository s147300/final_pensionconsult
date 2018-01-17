package com.example.jonathanlarsen.pensionconsultmainpage.Logic;

import android.widget.EditText;

public class PensionCalculates {

    private int pensionSavingStart = 0;
    private int pensionSavingEnd = 0;

    private int amount = 0;
    private int result = 0;


    public void setPensionStart(int start) { this.pensionSavingStart = start; }
    public void setPensionEnd(int end) { this.pensionSavingEnd = end; }
    public void setAmount(int amount) { this.amount = amount; }


    public int getResult() {
        calculateResult();
        return result;
    }

    private void calculateResult() {
        int years = pensionSavingEnd - pensionSavingStart;
        result = years * amount;
    }

}
