package team1.togather.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import team1.togather.domain.Board;
import team1.togather.domain.BoardCriteria;
import team1.togather.domain.PageMaker;
import team1.togather.service.BoardServiceImpl;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardServiceImpl service;
	
	@GetMapping("/listCri")
	public void listCriGET(Model model, BoardCriteria cri) {
		model.addAttribute("boardList", service.listCri(cri));
	}
	
	@GetMapping("listPage")
	public String boardList(BoardCriteria cri, Model model, HttpServletRequest request) {
		System.out.println("board컨트롤러안 listpageGET: cri값: " +cri);
		model.addAttribute("boardList", service.listCri(cri)); // =listPageCri()
		if(request.getParameter("page")!=null) {
			String pageAt = request.getParameter("page");
			System.out.println("현재 페이지: "+pageAt);
			cri.setPage(Integer.parseInt(pageAt));
		}
		if(request.getParameter("pageSize")!=null) {
			String pageSize = request.getParameter("pageSize");
			System.out.println("현재 페이지사이즈: "+pageSize);
			cri.setPageSize(Integer.parseInt(pageSize));
		}
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(service.pageCount()); //calcDate()실행
		
		model.addAttribute("pm", pm);
		model.addAttribute("cri", cri);
		return "board/boardMain";
	}
	
	@GetMapping("boardInput")
	public String boardInputPage() {
		return "board/boardInput";
	}
	@PostMapping("boardInsert")
	public String boardInsert(Board board) {
		service.insert(board);
		
		return "listPage";//여기부터 boardList로 가는법
	}
}
