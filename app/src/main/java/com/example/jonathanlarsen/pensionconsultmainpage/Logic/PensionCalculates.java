package com.example.jonathanlarsen.pensionconsultmainpage.Logic;

public class PensionCalculates {

    private int pensionSavingStart = 0;
    private int pensionSavingEnd = 0;
    private int years = 0;

    private int monthlyPay = 0;
    private float interestRate = 0.05f;

    private int result = 0;

    private int ownPayment = 0;
    private int rentIncome = 0;

    private float laborMarket = 0.92f;
    private float tax = 0.63f;
    private float inflation = 1.02f;
    private float inflationPercent = (inflation - 1.00f) * 100;

    private int[] monthtlyStatus;

    public void setPensionStart(int start) { this.pensionSavingStart = start; }
    public void setPensionEnd(int end) { this.pensionSavingEnd = end; }
    public void setMonthlyPay(int monthlyPay) { this.monthlyPay = monthlyPay; }

    public int getOwnPayment() { return ownPayment; }
    public int getRentIncome() { return rentIncome; }
    public float getRent() { return  interestRate * 100; }
    public float getInflation() { return (float) Math.rint(inflationPercent); }
    public int[] getMonthtlyStatus() { return monthtlyStatus; }
    public int getYears() { return years; }


    public int getResult() {
        calculateResult();
        return result;
    }

    private void calculateResult() {
        years = pensionSavingEnd - pensionSavingStart;
        monthtlyStatus = new int[years];

        float tmpResult = 0;
        for (int i = 0; i < years; i++) {
            // Yearly returns
            tmpResult = (tmpResult + monthlyPay*12) * (1+interestRate);

            // used to plot the graph
            monthtlyStatus[i] = (int) (tmpResult * laborMarket * tax * inflation);
        }

        tmpResult = monthtlyStatus[years-1];

        // parse result to an int
        result = (int) tmpResult;

        // statistics - how much is paid and how much is earned
        ownPayment = years * monthlyPay * 12;
        rentIncome = result - ownPayment;

    }

}
