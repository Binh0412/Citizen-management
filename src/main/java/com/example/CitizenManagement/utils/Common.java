package com.example.CitizenManagement.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.CitizenManagement.config.ModelMapperConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author OS
 * <br> Common
 */
public class Common {
	
	private static ApplicationContext context = new AnnotationConfigApplicationContext(ModelMapperConfig.class);
	private static ModelMapper mapper = context.getBean(ModelMapper.class);
	private static ObjectMapper oMapper = new ObjectMapper();

	public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
		return source.stream().map(element -> mapper.map(element, targetClass)).collect(Collectors.toList());
	}

	public static <T> List<T> convertStringToListObject(String str) {
		List<T> list = new ArrayList<>();
		try {
			list = oMapper.readValue(str, new TypeReference<List<T>>() {
			});
		} catch (Exception e) {
			list = null;
		}
		return list;
	}
	
	public static Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(extractPrincipal(securityContext.getAuthentication()));
    }
	
	private static String extractPrincipal(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            return springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }
	
	public static String getCurrentTimeString() {
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.YYYYMMDDHHMMSS);
        String date = formatter.format(now);
        return date;
	}
	
	
	public static Date getDateFromString(String date, String format) {
		try {
			DateFormat df = new SimpleDateFormat(format);
			Date d = df.parse(date);
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static String convertDateToString (Date date, String format) {
		try {
			DateFormat df = new SimpleDateFormat(format);
			String dateString = df.format(date);
			return dateString;
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String convertStringDate(String date, String formatInput, String formatOutput) {
		try {
			DateFormat input = new SimpleDateFormat(formatInput);
			DateFormat output = new SimpleDateFormat(formatOutput);
			Date dateInput = input.parse(date);
			return output.format(dateInput);
		} catch (Exception e) {
			return date;
		}
	}
	
	public static String getLastMonth() {
		LocalDate currentDate = LocalDate.now();
        LocalDate previousMonthDate = currentDate.minusMonths(1);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMM");
        String previousMonth = previousMonthDate.format(dateFormatter);
        return previousMonth;
	}
	
	
}
