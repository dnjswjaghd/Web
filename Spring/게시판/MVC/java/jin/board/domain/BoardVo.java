package jin.board.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class BoardVo {
	private int cp = 1;
	private int ps = 5;
	
	public int getStartRow() {
		return (cp-1)*ps;
	}
	public int getEndRow() {
		return cp*ps;
	}
}
