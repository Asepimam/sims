package com.sims.sims.shared.utils;

public class InvoiceNumber {
    //genereate invoice number
    public static String generatedInvoiceNumber(){
        String invoiceNumber = "INV-" + System.currentTimeMillis();
        return invoiceNumber;
    }
}
