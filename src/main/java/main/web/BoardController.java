package main.web;

import java.util.List;



import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.service.BoardService;
import main.service.BoardVO;

@Controller 
public class BoardController {

	@Resource(name="boardService") // BoardService와 동일해야함 
	private BoardService boardService; // 동일 클래스 내에서만 접근이 가능.

	
	@RequestMapping("/boardWrite.do") //boardWrite.jsp파일 띄움
	public String boardWrite() {		
		
		return "board/boardWrite";
	}
	//일반 게시판 저장처리
	@RequestMapping("/boardWriteSave.do")
	//비동기전송방식으로 뭔가 실행이됬을 때 화면의 데이터를 전송하려면 어노테이션필요
	@ResponseBody
	public String insertNBoard(BoardVO vo) throws Exception { //Board vo 매개변수받아옴, inserNBoard :게시판 작성= 저장=db
		// result = null result는 정상실행시 null;
		//set 메소드 사용 
		//vo
		//controller service serviceImpl DAO
		String result = boardService.insertNBoard(vo); //vo.setName vo [name = "test"]
		String msg = "";
		if(result == null) 	msg = "ok";
		else msg = "fail";	// null 값이 아닌건 실패	

		return msg; 	// msg = message
	}
	
// log4j2.xml : sql에 설정을 담당
		//일반 게시판 화면 목록
		@RequestMapping("/boardList.do")
		public String selectBoardList(BoardVO vo, ModelMap model) throws Exception { //ModelMap:결과를 화면의 return 시키는 class 
			
			int unit = 5;
			
			// 총 데이터 개수
			int total = boardService.selectNBoardTotal(vo);
			System.out.println("total: "+total);
			
			// (double)12/10 -> ceil(1.2) -> Integer(2.0) -> 2
			int totalPage = (int) Math.ceil((double)total/unit); // ceil이라는 올림 메소드로 나누기로 묶게 되면 1.9가 2.0으로 된다 
								//오른쪽 결과는 int를 타고 배달이 됨 그래서 int가 됨
			
			int viewPage = vo.getViewPage(); //viewPage는 사용자가 볼려고 하는 페이지 즉 출력시키려는 페이지
			
			if(viewPage > totalPage || viewPage < 1) { // viewPage가 totalPage보다 큰가
				viewPage = 1;
			}
				
				
			// 1 -> 1,10  // 2 -> 11,20  // 3 -> 21,30
			// 1,2,3은 viewPage 
			// startIndex : (1-1)*10 + 1 -> 1 start상황얻으려는것
			// startIndex : (2-1)*10 + 1 = 11  ->2 페이지 보려고함
			int startIndex = (viewPage-1) * unit + 1; // 1이라는 숫자들어오면 1 2라는 숫자들어오면 11
			int endIndex = startIndex + (unit - 1);
			
			// total -> 34 (4page)
			// 1(page) -> 34 ~ 25, 2 -> 24 ~ 15 , 3(p) -> 14 ~ 5 , 4(p) -> 4 ~ 1
			/*int p1 = total -  0;
			int p2 = total - 10;
			int p3 = total - 20;
			int p4 = total - 30; */
			
			int startRowNo = total - (viewPage-1)*unit;
			
			
			vo.setStartIndex(startIndex);
			vo.setEndIndex(endIndex);
			
			List<?> list = boardService.selectNBoardList(vo); // 최종적으로 여기로 넘어옴
			System.out.println("list :" + list); //list형태로 저장이 잘되는가
			//ModelMap model
			model.addAttribute("rowNumber", startRowNo);
			model.addAttribute("total", total);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("resultList", list); // model클래스에서 addAttribute메소드를 불러서(list 이름을 resultList)라고해서 jsp로보내줌
			
			return "board/boardList";	//board/boardList jsp 페이지를 반환
		}

		// addAttribute는 model 객체에 값을 추가
		// attributeValue는 값 controller에 있음
		// attribute는 attributeName의 값을 가지고 있는 문자열
		// attributeName은 값을 얻고자 하는 속성의 이름 아무거나해도됨
		
		
			@RequestMapping("/boardDetail.do")
			public String selectNBoardDetail(BoardVO vo, ModelMap model) throws Exception {
				
				
				BoardVO boardVO = boardService.selectNBoardDetail(vo.getUnq()); //service를 태웠고
				model.addAttribute("boardVO", boardVO); //model로 태워서 jsp로 전달이 되게 세팅
				System.out.println(boardVO);
				return "board/boardDetail";
			}
			
		
		//sampleboard.jsp

		@RequestMapping("/sampleboard.do")
		public String selectNBoardsample(BoardVO vo, ModelMap model) throws Exception { 
			System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			
//			List<?> list = (List<?>) boardService.selectNBoardsample(vo);
			List<?> list = (List<?>)boardService.selectNBoardsample(vo);
			System.err.println(list);
			System.out.println("list :" + list); 
	
			model.addAttribute("resultList", list); 
			return "board/sampleboard";
		}



		//수정화면
		
		@RequestMapping("/boardModifyWrite.do")
		public String selectNBoardModifyWrite(BoardVO vo, ModelMap model) throws Exception {
			
			
			BoardVO boardVO = boardService.selectNBoardDetail(vo.getUnq()); //service를 태웠고		
			model.addAttribute("boardVO", boardVO); //model로 태워서 jsp로 전달이 되게 세팅
			
			System.out.println(boardVO);
			
			return "board/boardModifyWrite"; // jsp 페이지로넘어감
		}

		// 수정화면저장
		
		@RequestMapping("/boardModifySave.do")
		@ResponseBody //결과메시지 리턴
		public String updateNboard(BoardVO vo) throws Exception {
			
	
			int result = 0;  //result = 0
			
			int count = boardService.selectNBoardPass(vo); // password일치검사를 함/   int count = 1; unq도 넘어옴 vo에 다 들어있음
			
			if( count == 1) {  // count변수가 패스워드 일치 검사에 대한 결과를 알고 있는 변수 // count라는 변수가 성공적이다 사용자가  입력한 값이 1이면 1
				result = boardService.updateNBoard(vo); //  count변수가 성공적이면 update실행      /  int result = 1; //if문안에 선언문이 들어갈 수 없음
			}				
				else {
					result = -1;  //사용자가 패스워드입력을 제대로 하지않으면 result값은 -1
			}
			return result+"";  //result를 화면으로 보냄-저장처리를했는지 보여줘야함/result라는 변수가 최종화면에 전달이됨
		}
		
		
		// 삭제- 암호확인화면
		@RequestMapping("/passWrite.do")
		public String passWrite(int unq, ModelMap model) {
			System.err.println( unq);
			model.addAttribute("unq", unq);
			
			return "board/passWrite";
		}
		
		

		// 삭제 화면 
		@RequestMapping("/boardDelete.do")
		@ResponseBody
		public String deleteBoard(BoardVO vo) throws Exception {
	
			System.err.println("test");
			int result = 0;
			/*
			 * 암호 일치 검사
			 */
			int count = boardService.selectNBoardPass(vo);	// count = 1; (pw 정상) (일치함) // count = 0; (일치하지 않음)
			
			if( count == 1 ) {
			    result = boardService.deleteNBoard(vo); //deleteService에 태우게되면 최종sql에 도달하게됨
			}											// 삭제성공 result = 1; 삭제실패 result= 0;
			else if( count == 0) {
				result = -1;
			}		
			return result+"";
		}
		
}
