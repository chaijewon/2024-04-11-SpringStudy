package com.sist.temp;

public class SawonVO {
    private String name;
    private String dept;
    private static SawonVO vo;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
    public static SawonVO newInstance()
    {
    	if(vo==null)
    		vo=new SawonVO();
    	return vo;
    }
  
}
