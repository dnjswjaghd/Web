package jin.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardVo {
	private int cp = 1;
	private int ps = 5;
	private String option ="글쓴이";
	private String ocontent = "장";
	
	public BoardVo(int cp, int ps) {
		this.cp = cp;
		this.ps = ps;
	}

	
	public int getStartRow() {
		return (cp-1)*ps;
	}
	public int getEndRow() {
		return cp*ps;
	}
}
