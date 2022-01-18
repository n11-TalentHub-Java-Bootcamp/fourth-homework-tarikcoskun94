package com.n11.fourthhomeworktarikcoskun94.util;

import java.util.Date;

public final class InterestRateUtil {

    public static Double getInterestRateByDate(Date date) {

        Double rate;
        Date rateDate = DateUtil.createDate("01-01-2018");

        if (date.before(rateDate)) {
            rate = 0.015D;
        } else {
            rate = 0.020D;
        }
        return rate;
    }
}
