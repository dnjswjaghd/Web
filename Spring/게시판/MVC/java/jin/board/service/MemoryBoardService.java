package jin.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jin.board.domain.Board;
import jin.board.domain.BoardListResult;
import jin.board.domain.BoardVo;
import jin.board.mapper.BoardMapper;

@Service
public class MemoryBoardService implements BoardService {
	@Autowired
	private BoardMapper boardMapper;
	@Override
	public BoardListResult getBoardListResult(int cp, int ps) {
		long totalCount = boardMapper.selectCount();
		BoardVo boardVo = new BoardVo(cp, ps);
		List<Board> list = boardMapper.selectPerPage(boardVo);
		return new BoardListResult(cp, totalCount, ps, list);
	}
	public BoardListResult getBoardListResult(int cp, int ps, String option, String ocontent) {
		long totalCount = boardMapper.selectCount();
		BoardVo boardVo = new BoardVo(cp, ps);
		List<Board> list = boardMapper.selectPerPage(boardVo);
		List<Board> filteredList = new ArrayList<Board>();
		for(Board li: list) {
			if(option.equals("writer")&&li.getWriter().contains(ocontent)){
				filteredList.add(li);
			}
			if(option.equals("subject")&&li.getSubject().contains(ocontent)){
				filteredList.add(li);
			}
			if(option.equals("content")&&li.getContent().contains(ocontent)){
				filteredList.add(li);
			}
		}
		return new BoardListResult(cp, filteredList.size(), ps, filteredList);
	}
	@Override
	public Board getBoard(long seq) {
		Board board = boardMapper.selectBySeq(seq);
		return board;
	}
	@Override
	public void write(Board board) {
		boardMapper.insert(board);
	}
	@Override
	public void edit(Board board) {
		boardMapper.update(board);
	}
	@Override
	public void remove(long seq) {
		boardMapper.delete(seq);
	}
}