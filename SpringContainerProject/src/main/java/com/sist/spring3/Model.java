package com.sist.spring3;
// 공통 기반 => 모든 클래스가 동일한 기능 수행 
public interface Model {
  public String execute();
  default public void write() {} // jdk1.8 => 추상클래스 사용이 거의 사라짐
  // => 인터페이스 : 자바스크립트 (프로그램 언어형식)  
}
