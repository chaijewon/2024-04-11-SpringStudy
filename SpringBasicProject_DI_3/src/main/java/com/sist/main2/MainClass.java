package com.sist.main2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        AnnotationConfigApplicationContext app=
        		new AnnotationConfigApplicationContext(MemberConfig.class);
        Member m=(Member)app.getBean("mem");
        m.print();
	}

}
