package board.mvc.model;

class BoardSQL {
	static final String LIST = "select * from BOARD order by seq desc";
	static final String INSERT = "insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
	static final String DELETE = "delete from BOARD where SEQ=?";
	static final String SELECT = "select * from BOARD where SEQ=? order by seq desc";//content
	//static final String UPDATE = "select * from BOARD where SEQ=? order by seq desc";
}
