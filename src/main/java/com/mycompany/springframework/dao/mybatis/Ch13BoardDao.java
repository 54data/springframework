package com.mycompany.springframework.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.springframework.dto.Ch13Board;
import com.mycompany.springframework.dto.Ch13Pager;

@Mapper
public interface Ch13BoardDao {
	public int insert(Ch13Board board);
	// insert에는 보통 몇 개의 행에 대한 처리 결과를 알려주는데
	// 이러한 정보가 굳이 필요가 없을 경우는 void로 처리하지만
	// 필요할 경우에는 int로 하면 된다.
	
	public List<Ch13Board> selectList(Ch13Pager pager);

	public int countRows();

	public Ch13Board selectByBno(int bno);

	public Ch13Board selectAttachByBno(int bno);

	public int update(Ch13Board board);

	public int delete(int bno);

	public int updateHitcount(int bno);
}
