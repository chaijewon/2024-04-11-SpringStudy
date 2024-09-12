package com.sist.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import java.text.*;
@Component
public class TimeScheduler {
  public void timeData(String time)
  {
	  try
	  {
		  HttpServletRequest request=((ServletRequestAttributes)
				    RequestContextHolder.getRequestAttributes()).getRequest();
		  request.setAttribute("time", time);
	  }catch(Exception ex) {}
  }
  //@Scheduled(fixedDelay = 1000)
  public void task() {
	  Date date=new Date();
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	  System.out.println("현재 시간:"+sdf.format(date));
	  //timeData(sdf.format(date));
  }
}
