package main.service.impl;

import java.util.List;


import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import main.service.BoardService;
import main.service.BoardVO;


// 컨트롤러와 연결되는 설정
@Service("boardService") // BoardController와 동일해야함
public class BoardServiceImpl implements BoardService { // class 이름 BoardServiceImpl 

	@Resource(name="boardDAO")
	public BoardDAO boardDAO;
	
	//오버라이딩 (Overriding) : 상속 관계에서 만 가능
	@Override
	public String insertNBoard(BoardVO vo) throws Exception { 
		// public(접근지정자 아무나접근할수 있을 가진 String을 반환하는 메소드 이름 insertNBoard 파라미터는 괄호안에 있는 필수요소를 말함
		return boardDAO.insertNBoard(vo); // return 값을 줄려고 씀 실행한 메소드에 던진다
	}

	@Override // selectNBoardList메소드 실행한 곳이 @Service("boardService")
	public List<?> selectNBoardList(BoardVO vo) throws Exception {
		return boardDAO.selectNBoardList(vo); //하나의 service를 만드는 것은 sql실행을 위해서 만듬 이쪽으로 <List> 형태 결과값이 넘어옴
	}

	@Override
	public int selectNBoardTotal(BoardVO vo) throws Exception {
		return boardDAO.selectNBoardTotal(vo);
	}

	@Override
	public BoardVO selectNBoardDetail(int unq) throws Exception {
		return boardDAO.selectNBoardDetail(unq);
	}
	
	
	@Override
	public List<?> selectNBoardsample(BoardVO vo) {
		return boardDAO.selectNboardsample(vo);
	}

	@Override  //오버라이딩: 상속받은 자바파일을 재구성하는 똑같은 이름으로 다시만드는 기법
	public int updateNBoard(BoardVO vo) throws Exception { //괄호안에있는건 무조건 데이터를 받겠다 / controller에서 서비스를 실행하게 되면 괄호에 담긴다 이유는 sql에 전달하기 위해서
		return boardDAO.updateNBoard(vo); //괄호안에있는건 데이터전달
	}

	@Override
	public int selectNBoardPass(BoardVO vo) throws Exception {
		return boardDAO.selectNBoardPass(vo);
	}

	@Override
	public int deleteNBoard(BoardVO vo) throws Exception {
		return boardDAO.deleteNBoard(vo);
	}

	
	
}
