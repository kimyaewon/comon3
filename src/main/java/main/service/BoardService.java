package main.service;

import java.util.List;

public interface BoardService {
	/*
	 * 일반게시판 저장처리
	 */
	public String insertNBoard(BoardVO vo) throws Exception;
	/*
	 * 일반게시판 화면목록
	 */
	public List<?> selectNBoardList(BoardVO vo) throws Exception;// List여러개의 값 가져옴/ <?>는 와일드카드라는 명칭으로 어떤 제네릭타입이든 다 받아줌 /제네릭 타입 : 데이터 형식에 의존하지 않고, 하나의 값이 여러 다른 데이터 타입들을 가질 수 있도록 하는 방법이다.
		/*
		 * Total 개수 얻기
		 */
	public int selectNBoardTotal(BoardVO vo) throws Exception;
	/*
	 *  상세화면
	 */
	public BoardVO selectNBoardDetail(int unq) throws Exception;
	
	// 5개 출력
	public List<?> selectNBoardsample(BoardVO vo) throws Exception;
//	BoardVO selectNBoardsample(int unq) throws Exception;

	/*
	 * 2022.04.29 수정처리(최소작성)
	 * 개발자 : 김예원
	 * 2022.11.22 수정처리(변경작성)
	 * 개발자 : 누구누구
	 */
	public int updateNBoard(BoardVO vo) throws Exception;
	/*
	 * 암호확인
	 */
	public int selectNBoardPass(BoardVO vo) throws Exception;
	/*
	 * 삭제처리
	 */
	public int deleteNBoard(BoardVO vo) throws Exception;
	
}
