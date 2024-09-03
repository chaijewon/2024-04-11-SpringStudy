package com.sist.main;
class My
{
	public void execute()
	{
		System.out.println("Hello AOP");
	}
}
class MyTarget 
{
	private My my;
	public MyTarget(My my)
	{
		this.my=my;
	}
	//weaving => proxy패턴 => 대리자 
	public void execute()
	{
		my.execute();
		System.out.println("my.execute실행");
	}
}
public class MainClass2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        My m=new My();
        m.execute();
        MyTarget mm=new MyTarget(m);
        mm.execute();
	}

}
