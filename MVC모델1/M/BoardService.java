package board.mvc.model;
import java.util.ArrayList;

import mvc.domain.Board;

public class BoardService {
	private BoardDAO dao;
	
	//SingleTon Object Model - start!
	private static final BoardService instance = new BoardService();
	private BoardService() {
		dao = new BoardDAO();
	}
	public static BoardService getInstance() {
		return instance;
	}
	public ArrayList<Board> listS(){
		return dao.list();
	}
	public boolean insertS(Board dto) {
		return dao.insert(dto);
	}
	public void deleteS(int seq) {
		dao.delete(seq);
	}
	public ArrayList<Board> updateS(int seq) {
		return dao.update(seq);
	}
	public void updateGetS(Board dto, String writer) {
		dao.updateGet(dto, writer);
	}
}
