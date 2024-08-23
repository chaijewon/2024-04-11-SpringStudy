package com.sist.spring5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	// Spring 4 => xml + java
	// Spring 5 => java
	// Spring 6
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String[] xml={"sawon.xml","member.xml"};
        ApplicationContext app=
        		new ClassPathXmlApplicationContext("my*.xml");
        Sawon sa=(Sawon)app.getBean("sa");
        sa.print();
        Member mem=(Member)app.getBean("mem");
        mem.print();
	}

}
