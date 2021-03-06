package jin.board.service;

import org.springframework.web.multipart.MultipartFile;

import jin.board.domain.Board;
import jin.board.domain.BoardListResult;

public interface BoardService {
	BoardListResult getBoardListResult(int cp, int ps);
	BoardListResult getBoardListResult(int cp, int ps, String option, String content);
	Board getBoard(long seq);
	void write(Board board);
	void edit(Board board);
	void remove(long seq);
	String saveStore(long seq, MultipartFile file); 
	Long selectMaxSeq(); 
}
