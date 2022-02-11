package team1.togather.model;

public class ReplySQL {
	   final static String LIST = "select * from REPLY where bnum=? order by rseq desc, lev asc";
	   final static String INSERT = "insert into REPLY values(BOARD_SEQ.nextval, ?, ?, ?, ?, 0, SYSDATE, 0)";
	   
}