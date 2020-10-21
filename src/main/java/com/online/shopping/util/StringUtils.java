package com.karyathalo.pudo.util;

public class StringUtils {

    public static String getRandomQuotationNumber() {
        long timeSeed = System.nanoTime();
        double randSeed = Math.random() * 1000;
        long midSeed = (long) (timeSeed * randSeed);

        String s = midSeed + "";
        return "QUT-" + s.substring(0, 9);
    }

    public static String getRandomOrderNumber() {
        long timeSeed = System.nanoTime();
        double randSeed = Math.random() * 1000;
        long midSeed = (long) (timeSeed * randSeed);

        String s = midSeed + "";

        int dum1 = Integer.parseInt(s.substring(0, 3));
        int dum2 = Integer.parseInt(s.substring(4, 7));

        if (dum1 < 1111 || dum2 < 11111) {
//            getRandomStationNumber();
            dum1 += 1111;
            dum2 += 11111;
        }

        return "ORD-" + dum1 + "-"+ dum2;
    }


    public static String getRandomStationNumber() {
        long timeSeed = System.nanoTime();
        double randSeed = Math.random() * 1000;
        long midSeed = (long) (timeSeed * randSeed);

        String s = midSeed + "";

        int dum1 = Integer.parseInt(s.substring(0, 2));
        int dum2 = Integer.parseInt(s.substring(3, 6));

        if (dum1 < 111 || dum2 < 1111) {
//            getRandomStationNumber();
            dum1 += 111;
            dum2 += 1111;
        }

        return "STA-" + dum1 + "-"+ dum2;
    }

    //TODO: Refactor getRandomSellerNumber & getRandomStationNumber
    public static String getRandomSellerNumber() {
        long timeSeed = System.nanoTime();
        double randSeed = Math.random() * 1000;
        long midSeed = (long) (timeSeed * randSeed);

        String s = midSeed + "";

        int dum1 = Integer.parseInt(s.substring(0, 2));
        int dum2 = Integer.parseInt(s.substring(3, 6));

        if (dum1 < 111 || dum2 < 1111) {
//            getRandomStationNumber();
            dum1 += 111;
            dum2 += 1111;
        }

        return "SLR-" + dum1 + "-"+ dum2;
    }

    public static String getRandomUserId() {
        long timeSeed = System.nanoTime();
        double randSeed = Math.random() * 1000;
        long midSeed = (long) (timeSeed * randSeed);

        String s = midSeed + "";

        int dum1 = Integer.parseInt(s.substring(0, 3));
        int dum2 = Integer.parseInt(s.substring(4, 7));
        int dum3 = Integer.parseInt(s.substring(8, 11));

        return "USR-" + dum1 + "-"+ dum2 + "-"+ dum3;
    }

    public static Integer generateOtp(){

        long timeSeed = System.nanoTime();
        double randSeed = Math.random() * 1000;
        long midSeed = (long) (timeSeed * randSeed);

        String s = midSeed + "";

        int dum1 = Integer.parseInt(s.substring(0, 6));

        if (dum1 >= 111111){
            return dum1;
        }

        return generateOtp();
    }

    public static String generateShippingId(){

        long timeSeed = System.nanoTime();
        double randSeed = Math.random() * 1000;
        long midSeed = (long) (timeSeed * randSeed);

        String s = midSeed + "";

        int dum1 = Integer.parseInt(s.substring(0, 6));

        if (dum1 >= 111111){
            return "SHIP-" + dum1;
        }

        return generateShippingId();
    }

    public static Integer generateFileNumber(){

        long timeSeed = System.nanoTime();
        double randSeed = Math.random() * 1000;
        long midSeed = (long) (timeSeed * randSeed);

        String s = midSeed + "";

        int dum1 = Integer.parseInt(s.substring(0, 8));

        if (dum1 >= 11111111){
            return dum1;
        }

        return generateOtp();
    }

}
