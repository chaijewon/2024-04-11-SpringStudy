package com.sist.vo;
// 데이터베이스 : CRUD 
import java.util.*;

import lombok.Data;
@Data
public class BoardVO {
   private int no,hit;
   private String name,subject,content,pwd,dbday;
   private Date regdate;
}
