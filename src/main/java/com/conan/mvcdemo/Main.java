/**
 * Copyright (C) 2011-2019 ShenZhen iBOXCHAIN Information Technology Co.,Ltd.
 * <p>
 * All right reserved.
 * <p>
 * This software is the confidential and proprietary
 * information of iBOXCHAIN Company of China.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with iBOXCHAIN inc.
 */

package com.conan.mvcdemo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author: zhangwenhao
 * @since: 2019/5/21
 */
public class Main {

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        //[time=1558424618272,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Asia/Shanghai",offset=28800000,dstSavings=0,useDaylight=false,transitions=19,
        // lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,
        // YEAR=2019,MONTH=4,WEEK_OF_YEAR=21,WEEK_OF_MONTH=4,DAY_OF_MONTH=21,DAY_OF_YEAR=141
        // ,DAY_OF_WEEK=3,DAY_OF_WEEK_IN_MONTH=3,AM_PM=1,HOUR=3,HOUR_OF_DAY=15,MINUTE=43,SECOND=38,
        // MILLISECOND=272,ZONE_OFFSET=28800000,DST_OFFSET=0]
        c.add(Calendar.MONTH, -1);
        String sumDateC = new SimpleDateFormat("yyyyMM").format(c.getTime());

        System.out.println(sumDateC);

        BigDecimal shareTurnAmount = new BigDecimal(100);
        BigDecimal shareTurnAmountLast = new BigDecimal(200);

        BigDecimal tempshareTurnAmountC = shareTurnAmount.subtract(shareTurnAmountLast).divide(shareTurnAmountLast);
        int i = tempshareTurnAmountC.compareTo(new BigDecimal(0));
        System.out.println(i);
        String shareTurnAmountC = new DecimalFormat("0.00%").format(tempshareTurnAmountC);
        System.out.println(shareTurnAmountC);

    }
}
