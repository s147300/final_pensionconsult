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
    private float tax = 0.61f;
    private float inflation = 1.02f;
    private float inflationPercent = (inflation - 1.00f) * 100;

    private int[] yearlyStatus;

    public void setPensionStart(int start) { this.pensionSavingStart = start; }
    public void setPensionEnd(int end) { this.pensionSavingEnd = end; }
    public void setMonthlyPay(int monthlyPay) { this.monthlyPay = monthlyPay; }

    public int getOwnPayment() { return ownPayment; }
    public int getRentIncome() { return rentIncome; }
    public float getRent() { return  interestRate * 100; }
    public float getInflation() { return (float) Math.rint(inflationPercent); }
    public int[] getYearlyStatus() { return yearlyStatus; }
    public int getYears() { return years; }


    public int getResult() {
        calculateResult();
        return result;
    }

    private void calculateResult() {
        years = pensionSavingEnd - pensionSavingStart;

        int yearlyPay = monthlyPay * 12;

        // Function created with the knowlegde of the function below
        // B * ((1 + r)^n - 1) / r
        int currentPay = 0;
        yearlyStatus = new int[years];
        for (int p = 0; p < years; p++) {
            // add up the current pay for every year that passed
            currentPay = currentPay + yearlyPay;

            // function to calculate the total status every year.
            double tmp = (int) ( (yearlyPay * (Math.pow(1 + interestRate, p+1) - 1)) / interestRate);

            // Withdraw the current pay and calculate the rent
            tmp = tmp - currentPay;
            tmp = tmp * laborMarket * tax * inflation + currentPay;
            yearlyStatus[p] = (int) tmp;
        }

        // save the data to the variables
        result = yearlyStatus[years-1];
        rentIncome = result - currentPay;
        ownPayment = currentPay;

    }

}
