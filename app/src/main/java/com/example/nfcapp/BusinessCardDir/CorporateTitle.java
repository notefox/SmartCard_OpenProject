package com.example.nfcapp.BusinessCardDir;

import java.util.NoSuchElementException;

public enum CorporateTitle {

    Chief_business_officer("CBO - Business"),
    Chief_brand_officer ("CBO - Brand"),
    Chief_executive_officer ("CEO - Execution"),
    Chief_operating_officer ("COO - Operating"),
    Chief_financial_officer ("CFO - financial"),
    Chief_human_resources_officer ("CHRO - Human Resources"),
    Chief_information_officer ("CIO - Information"),
    Chief_marketing_officer ("CMO - Marketing"),
    Chief_product_officer ("CPO - Product"),
    Chief_technology_officer ("CTO - Technology"),
    Other("other");

    String shorts;

    CorporateTitle(String shorts) {
        this.shorts = shorts;
    }

    public String getShorts() {
        return shorts;
    }
}