package com.sist.vo;
import java.util.*;

import lombok.Data;
/*
 *    Java / JSP / Oracle / Spring / JavaScript  
 *    => MySQL , Spring-Boot , Front-End  
 *    => MSA , Docker , CI/CD , NextJS , TypeScript , NuxtJS , NodeJS 
 *    => RPA 
 */
@Data
public class BoardVO {
   private int no,hit;
   private String name,subject,content,pwd,dbday;
   private Date regdate;
   
}
