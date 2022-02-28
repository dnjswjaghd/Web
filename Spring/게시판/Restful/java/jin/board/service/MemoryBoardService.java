package jin.board.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jin.board.domain.Board;
import jin.board.domain.BoardListResult;
import jin.board.domain.BoardVo;
import jin.board.mapper.BoardMapper;
import jin.md.fileset.Path;

@Service
public class MemoryBoardService implements BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public String saveStore(long seq, MultipartFile file) {
		String ofname = file.getOriginalFilename();
		int idx = ofname.lastIndexOf(".");
		String ofheader = ofname.substring(0, idx);
		String ext = ofname.substring(idx);
		long ms = System.currentTimeMillis();
		
		StringBuilder sb = new StringBuilder();
		sb.append(ofheader);
		sb.append("_");
		sb.append(ms);
		sb.append(ext);
		String saveFileName = sb.toString();
		System.out.println("아아아아");
		
		long fsize = file.getSize();
		System.out.println("#ofname: "+ ofname+", saveFileName: "+saveFileName+ ", fsize: "+ fsize);
		
		//업로드로직
		boolean flag = writeFile(seq, file, saveFileName);
		if(flag) {
			System.out.println("#업로드성공");
		}else {
			System.out.println("#업로드실패");
		}
		return Path.FILE_STORE + saveFileName;
	}
	private boolean writeFile(long seqdepricated, MultipartFile file, String saveFileName) {
		long seq = selectMaxSeq(); 
		File dir = new File(Path.FILE_STORE+Long.toString(seq)+"/");
		if(!dir.exists()) dir.mkdirs();
		FileOutputStream fos = null;
		try {
			byte data[] = file.getBytes();
			fos = new FileOutputStream(Path.FILE_STORE+Long.toString(seq)+"/"+saveFileName);
			fos.write(data);
			fos.flush();
			return true;
		}catch(IOException ie) {
			ie.printStackTrace();
			return false;
		}finally {
			try {
				if(fos != null) fos.close();
			}catch(IOException ie) {
				 ie.printStackTrace();
			}
		}
	}
	@Override
	public Long selectMaxSeq() {
		return boardMapper.selectMaxSeq();
	}
	@Override
	public BoardListResult getBoardListResult(int cp, int ps) {
		long totalCount = boardMapper.selectCount();
		BoardVo boardVo = new BoardVo(cp, ps);
		List<Board> list = boardMapper.selectPerPage(boardVo);
		return new BoardListResult(cp, totalCount, ps, list);
	}
	@Override
	public BoardListResult getBoardListResult(int cp, int ps, String option, String ocontent) {
		BoardVo boardVo = new BoardVo(cp, ps, option, ocontent);
		Long totalCount = 0L;
		if(boardMapper.selectCountByOption(boardVo)==null) {
			 totalCount = 0L;
		}else {
			 totalCount = boardMapper.selectCountByOption(boardVo);
		}
		List<Board> list = boardMapper.selectPerPageWithOption(boardVo);
		System.out.println("listsize: " + list.size());
		return new BoardListResult(cp, totalCount, ps, list);
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