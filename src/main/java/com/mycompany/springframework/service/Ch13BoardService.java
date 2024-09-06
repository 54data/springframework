package com.mycompany.springframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.mybatis.Ch13BoardDao;
import com.mycompany.springframework.dto.Ch13Board;
import com.mycompany.springframework.dto.Ch13Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch13BoardService {
	// 데이터베이스와 작업하기 위한 DAO 객체
	@Autowired
	private Ch13BoardDao boardDao; // mybatis가 관리 객체로 만들어 주었던 것
	
	public List<Ch13Board> getBoardList(Ch13Pager pager) {
		List<Ch13Board> list = boardDao.selectList(pager);
		return list;
	}
	
	public Ch13Board getBoard(int bno) {
		Ch13Board board = boardDao.selectByBno(bno);
		return board; // 하나의 게시물 가져올 때는 주로 PK 칼럼을 사용한다.
	}
	
	public void writeBoard(Ch13Board board) {
		// board - 컬럼 정보 들이 들어가 있음 (mid, btitle 등등)
		log.info("실행");
		log.info("insert 전 bno : " + board.getBno()); // insert 되기 전 0이 출력
		boardDao.insert(board);
		
		// bno를 이용해서 추가적인 비즈니스 로직을 작성해야할 경우
		// board에는 사용자가 bno를 입력하는 게 아니기 때문에 들어있지 않다.
		log.info("insert 후 bno : " + board.getBno()); // 존재함
		int bno = board.getBno();
	}
	
	public void updateBoard(Ch13Board board) {
		boardDao.update(board);
	}
	
	public void deleteBoard(int bno) {
		boardDao.delete(bno);
		// 비즈니스 로직이 거의 없기 때문에 그냥 호출만 하는 경우도 있다.
	}

	public int getTotalRows() {
		int totalRows = boardDao.countRows();
		return totalRows;
	}

	public Ch13Board getBoardAttach(int bno) {
		Ch13Board board = boardDao.selectAttachByBno(bno);
		return board;
	}

	public void addHitCount(int bno) {
		boardDao.updateHitcount(bno);
	}
}