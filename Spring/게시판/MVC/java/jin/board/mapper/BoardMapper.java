package jin.board.mapper;

import java.util.List;

import jin.board.domain.Board;
import jin.board.domain.BoardVo; 

public interface BoardMapper {
	List<Board> selectPerPage(BoardVo boardvo);
	List<Board> selectPerPageWithOption(BoardVo boardvo);
	Board selectBySeq(long seq);
	long selectCount();
	Long selectCountByOption(BoardVo boardvo); 
	
	void insert(Board board);
	void update(Board board);
	void delete(long seq);
	
	long selectMaxSeq();
	//List<Board> selectCountByWriter (Board )
	
}
