package main.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import main.service.BoardVO;
// sql에 전달해주기 위해 vo라고함 vo가 sql까지 전달하는 의미
@Repository("boardDAO")
public class BoardDAO extends EgovAbstractDAO { 

	public String insertNBoard(BoardVO vo) {   // 게시물 작성
		return (String) insert ("boardDAO.insertNBoard",vo);
	}

	public List<?> selectNBoardList(BoardVO vo) {
		return list("boardDAO.selectNBoardList",vo); //아무거나 넣어도됨 고유한 번호이기만하면됨
	}

	public int selectNBoardTotal(BoardVO vo) {
		return (int) select("boardDAO.selectNBoardTotal",vo); // 총게시물갯수
	}

	public BoardVO selectNBoardDetail(int unq) {
		return (BoardVO) select("boardDAO.selectNBoardDetail",unq); // BoardVO 한줄 쓸때 씀 List는 여러줄
	}

	public List<?> selectNboardsample(BoardVO vo) {
		return list("boardDAO.selectNBoardsample",vo); 
	}

	public int updateNBoard(BoardVO vo) {
		return update("boardDAO.updateNBoard",vo);
	}

	public int selectNBoardPass(BoardVO vo) {
		return (int) select("boardDAO.selectNBoardPass",vo);
	}

	public int deleteNBoard(BoardVO vo) {
		return delete("boardDAO.delteNBoard",vo) ;
	}

//	public int selectNBoardPass(BoardVO vo) {
//		return (int) select("boardDAO.selectNBoardPass",vo);
//	}


	
}
		
		//unq: 수정 삭제, 상세보기
			//title: 목록출력
			
			//DESC : 역순정렬
			
