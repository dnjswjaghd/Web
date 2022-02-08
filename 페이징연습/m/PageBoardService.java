package page.board.model;

import java.util.ArrayList;

import page.board.domain.PageBoard;

public class PageBoardService {
	private PageBoardDAO dao;
	
	private static final PageBoardService instance = new PageBoardService();
	private PageBoardService(){
		dao = new PageBoardDAO();
	}
	public static PageBoardService getInstance() {
		return instance;
	}
	public ArrayList<PageBoard> listS(){
		return dao.list();
	}
	public ArrayList<PageBoard> listS(int a){
		return dao.list(a);
	}
	
}
