package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.MusicVO;
public interface MusicMapper {
  @Select("SELECT mno,title,singer,album,idcrement,state "
		 +"FROM genie_music ORDER BY mno ASC")
  public List<MusicVO> musicListData();
  
  @Select("SELECT mno,title,singer,album,idcrement,state "
		 +"FROM genie_music "
		 +"WHERE title LIKE '%'||#{title}||'%'")
  public List<MusicVO> musicTitleFindData(String title);
  
  @Select("SELECT mno,title,singer,album,idcrement,state "
			 +"FROM genie_music "
			 +"WHERE singer LIKE '%'||#{singer}||'%'")
  public List<MusicVO> musicSingerFindData(String singer);
  
  @Select("SELECT mno,title,singer,album,idcrement,state "
			 +"FROM genie_music "
			 +"WHERE album LIKE '%'||#{album}||'%'")
  public List<MusicVO> musicAlbumFindData(String album);
  // => XML로 구사 => 어노테이션 연결 
  @Select("<script>"
		 +"SELECT mno,title,singer,album,idcrement,state "
		 +"FROM genie_music "
		 +"WHERE "
		 +"<choose>"
		 +"<when test=\"type==1\">"
		 +"title LIKE '%'||#{fd}||'%'"
         +"</when>"
         +"<when test=\"type==2\">"
		 +"singer LIKE '%'||#{fd}||'%'"
         +"</when>"
         +"<when test=\"type==3\">"
		 +"album LIKE '%'||#{fd}||'%'"
         +"</when>"
		 +"</choose>"
         +"</script>")
  // Map / VO 
  public List<MusicVO> musicFindData(@Param("type") int type,@Param("fd") String fd);
}
