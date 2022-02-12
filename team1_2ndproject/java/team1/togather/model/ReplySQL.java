package team1.togather.model;

public class ReplySQL {
	   final static String RLIST = "select * from REPLY where bnum=? order by rseq desc, lev asc";
	   final static String RINSERT = "insert into REPLY values(RSEQ_SEQ.nextval, ?, ?, ?, ?, 0, SYSDATE, 0)";
	   final static String RUPDATE1 = "select * from REPLY where RSEQ=?";
		final static String RUPDATE2 = "update REPLY set CONTENT = ? where RSEQ = ?";
		final static String RDELETE = "delete from REPLY where RSEQ=?";
		final static String RLIKE = "update REPLY set R_LIKE = ? where RSEQ = ?";
}