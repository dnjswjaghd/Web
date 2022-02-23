package jin.board.mapper;

import java.util.List;

import jin.board.domain.Board;
import jin.board.domain.BoardVo;

public interface BoardMapper {
	List<Board> selectPerPage(BoardVo boardvo);
	Board selectBySeq(long seq);
	long selectCount();
	
	void insert(Board board);
	void update(Board board);
	void delete(long seq);
	
}
