package com.sist.main;
import java.util.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sist.config.DataBaseConfig;
import com.sist.config.MusicConfig;
import com.sist.dao.*;
import com.sist.vo.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class[] cls={MusicConfig.class,DataBaseConfig.class};
        AnnotationConfigApplicationContext app=
        	new AnnotationConfigApplicationContext(cls);
        MusicDAO dao=(MusicDAO)app.getBean("dao");
        List<MusicVO> list=dao.musicListData();
        for(MusicVO vo:list)
        {
        	System.out.println(vo.getMno()+"."+vo.getTitle());
        }
        System.out.println("===============================");
        Scanner scan=new Scanner(System.in);
        System.out.print("1. 곡명 검색 , 2. 가수 검색 , 3. 앨범 검색:");
        int no=scan.nextInt();
        System.out.print("검색어 입력:");
        String fd=scan.next();
        /*List<MusicVO> fList=new ArrayList<MusicVO>();
        if(no==1)
        	fList=dao.musicTitleFindData(fd);
        else if(no==2)
        	fList=dao.musicSingerFindData(fd);
        else if(no==3)
        	fList=dao.musicAlbumFindData(fd);*/
        List<MusicVO> fList=dao.musicFindData(no, fd);
        
        System.out.println("========== 결과 출력 ==============");
        for(MusicVO vo:fList)
        {
        	System.out.println("곡명:"+vo.getTitle());
        	System.out.println("가수명:"+vo.getSinger());
        	System.out.println("앨범:"+vo.getAlbum());
        }
        
        
	}

}
