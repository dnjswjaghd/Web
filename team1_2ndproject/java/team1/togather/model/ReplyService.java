package team1.togather.model;

import java.util.ArrayList;

import team1.togather.domain.Reply;

public class ReplyService {
private ReplyDAO dao;
   
   private static final ReplyService instance = new ReplyService();
   private ReplyService() {
      dao = new ReplyDAO();
   }
   public static ReplyService getInstance() {
      return instance;
   }
   public ArrayList<Reply> listS(long bnum){
      return dao.list(bnum);
   }
   public boolean insertS(Reply dto) {
      return dao.insert(dto);
   }
}