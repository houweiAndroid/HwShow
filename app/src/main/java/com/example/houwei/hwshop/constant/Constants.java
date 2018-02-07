package com.example.houwei.hwshop.constant;

/**
 * Created by zm on 16/8/18.
 */
public class Constants {

    public static final boolean debug = true;
    public static  boolean ifPayOfCard=true;
    public static boolean ifPayOfOrder=true;

    public static final String getBaseUrl() {
        if (debug) {
//            return "https://stg1.pahyb.com:8001/app-hyb-buyer-server";//旧核心
//            return "http://10.0.0.17:8088/app-hyb-buyer-server"; // ß毛冬云
//            return "http://192.168.1.100:9080/app-hyb-buyer-server"; ß//
//            return "https://stg1.pahyb.com:8004/app-hyb-buyer-server";
            return "https://stg1.pahyb.com/app-hyb-buyer-server";
//            return "http://192.168.1.100:8070/app-hyb-buyer-server"; //
//            return "http://192.168.43.243:8080/app-hyb-buyer-server"; //
//            return "http://192.168.1.107:8080/app-hyb-buyer-server"; //
//            return "http://192.168.43.32:8070/app-hyb-buyer-server";
//            return "http://192.168.43.40:8090/app-hyb-buyer-server"; //球爷

        } else {
            return "https://www.pahyb.com/app-hyb-buyer-server";
        }
    }

    public static final String getImageUrl() {
        if (debug) {
//            return "https://stg1.pahyb.com:8004/";
//            return "https://stg1.pahyb.com:8001/";
            return "https://stg1.pahyb.com/";
        } else {
            return "https://www.pahyb.com/";
        }
    }

    public static final String getH5Url() {
        if (debug) {
            //return "http://172.20.10.2:8088/app-hyb-buyer-server";
//            return "https://stg1.pahyb.com:8004/app-hyb-h5-server";
            return "https://stg1.pahyb.com/app-hyb-h5-server";
        } else {
            return "https://www.pahyb.com/app-hyb-h5-server";
        }
    }


    public static final String PARTNER = "2088221727681150";

    public static final String getPartner() {
        if (debug) {
            return "2088221727681150";
        } else {
            return "2088121869418903";
        }
    }

    public static final String getSeller() {
        if (debug) {
            return "pahuiyuanbao@163.com";
        } else {
            return "hyb.pingan@pingan.com.cn";
        }
    }

    public static final String getRsaPrivate() {
        if (debug) {
            return "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAK/wIg7dR6U/zLfJtEWq6cf22/IKNBnoRvVzC/EAe8cwC84+BazB+Cvn57fI4KksIgyml0DSkud1/1ux84PLrQb0jxHyu8yMs6Aghw1etUwrknfgPfFzv8/mp4mPhVVZvY0/TydnTt0vwRdaLrY+GaZWLNvw0wW48nUcPjwqoVITAgMBAAECgYEAq6SJhZjp6dS5Y2BcCE+67gPvBk5gEBplB9bqgn+nQmekTNyDasx90k/X55wJWeIdgdwQ2gPVoAylSQ7t9NmC/hTzQLBTGfH4oN5MSXoeWkQYWwHQdG74J3KlDZjCIBCkUWiPFNCo/ABBT7cGF2jvMAH5eTrkuiGxHhOv7DojLSkCQQDYUgXk7wCwyAr5Dq9t+0mUdt9piMZDJcWnn6cku+o6We/seAxcDnQTO6CLTcMTUkk+LZhVvNGHVtnVVd4PatVtAkEA0DXVW5XcdYh9Xv4B5Wd1le6hggdrshY6hcU62E2/2dfXO6vm0EY70mYtayUzBp7riY0NA7zoauIa2Ote822VfwJAZfkUij0YiD1k3s9LZ38jynbXDnrJa3Hs4P3j3u8ulV88OK7GtMajdUWQgtqfcQZDPKplu1UNOM16VpTp3z6vaQJBAIDmYxFRJOwKtPsjLA9WzsAkvQ5gA7d79X0tjnGtKLH340MI+FtGr/Bg24tyuzVFG7LLhRwkToslMdPLW7w+wocCQQCneb6LkLZ6W1PKeko2FdFB4TVnX6sWgH8aYsQgligKQl+oVtYtxN7qUrHCnP2Yje/A/H+WIIGhfLBTSHdb35dU";
        } else {
            return "MIICXAIBAAKBgQCnvU+54zSjipJ0MUtMBmnCqePi4LzNNJFXeBQsAQpG/vmToZ0DOEZ7TULWZVvG50iUNOSLRVhTjtg1hkUmwEbiWU3qfKzD+qYIIhhHAN8+hpIT72hQeNHLBB26d8zd4Ue5NbyyA/XI9KLNdr6jw6x3WLfs04I56nRHBC2ScrpdhQIDAQABAoGALLN1oKsc5wxRtSdB0rPO0XKm6VpM1j7REMdOUN/CFmNyrVYfWrFPjyJq0mnWiG4wWFa0JS1IuZPSqIjsusP434C6eh+1GhiVVy8yGsWCYGy5+EIsZUm/QQ4aIOY2oAj2roPovXGEYoQvWb+VzZfN0GzkMxoY0p7IXI+tG0noMe0CQQDWwMa6aJKbQtUOuF2yURKJh5RihmBUcYne+tGj8Rgm74K3MgCvbWd6DDwW4Ec+D6rBppl+c8olpQZwk7A98+5PAkEAx/TrcMelzgiSvWbjlM717bt+EVnY84NoMD6N4pE7vp061ssM4S18mi2HygcQUkKMFcakyCL2N3p+zHqEKTP16wJAeFWvRbbhJ7TsVAJFJm09WOkq4YZwpgC1A8hB/n5TTUX6VhLjxWkliYMfjKYB5am7wiVLb0sMo8lz8VMgd6afCwJAQGFWUQinFPBjtsHi8q20NygQDiwck0MzD52Ed3eFgfkD0dasve+0V7vjJaQimCm/Ct/bfGjtV62EF/g2h0GoowJBAL++KNL2JcMhSCm0xL8yFbFhVDMaIEXkyZbHILazncPVcmYs5L6DrRPVoc0aIro6DoctfobImknFlK4NJE44Tck=";
        }
    }
}
