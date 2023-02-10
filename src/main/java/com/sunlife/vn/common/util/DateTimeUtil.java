package com.sunlife.vn.common.util;

import com.sunlife.vn.common.constant.AppConstant;
import org.apache.commons.lang3.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
	
	public static String longToDateString(Long longDate, String format) {
		Date date=new Date(longDate);
		String formattedDate = new SimpleDateFormat(format).format(date);
		return formattedDate;
	}

	public static Date parseDate(String stringDate, String stringFormatter) throws ParseException{
		try {
			if(StringUtils.isBlank(stringFormatter)) stringFormatter = AppConstant.DATE_FORMAT;
			SimpleDateFormat formatter = new SimpleDateFormat(stringFormatter);
			return formatter.parse(stringDate);
		} catch (ParseException e) {
			return null;
		}
	}
}
