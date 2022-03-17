package team1.togather.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team1.togather.domain.Board;
import team1.togather.domain.BoardCriteria;
import team1.togather.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardmapper;

	@Override
	public List<Board> listPage(int page){
		if(page <= 0) {
			page = 1;
		}
		page = (page - 1)*10;
		return boardmapper.listPage(page);
	}
	@Override
	public List<Board> listCri(BoardCriteria cri) {
		return boardmapper.listPageCri(cri);
	}
	@Override
	public int pageCount() {
		return boardmapper.pageCount();
	}

	@Override
	public void insert(Board board) {
		boardmapper.insert(board);
	}
}
