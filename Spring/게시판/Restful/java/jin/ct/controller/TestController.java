package jin.ct.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jin.ct.domain.Human;
import jin.ct.domain.HumanList;
import jin.ct.domain.ToDoDTO;
import lombok.extern.log4j.Log4j;


@Log4j
@Controller
@RequestMapping("/test/*")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("")
	public void m01() {
		//log.info("#m01() - default URL");
		logger.info("#m01() - default URL");
		log.info("#m01() - default URL");
	}
	@RequestMapping("/base1")
	public void m02() {
		log.info("#m02() - Get방식 & Post방식 &...");
	}
	@RequestMapping(value="/base2", method = RequestMethod.GET)
	public void m03() { 
		log.info("#m03() - Get방식");
	}
	@RequestMapping(value="/base3", method = {RequestMethod.GET, RequestMethod.POST})
	public void m04() { 
		log.info("#m04() - Get방식 & Post방식"); 
	}
	@RequestMapping(value="/form", method = RequestMethod.GET)
	public String form() { 
		return "test/form";
	}
	@RequestMapping(value="/param1", method = RequestMethod.GET)
	public void m05(String name) { //넘어오는 이름값과 파라미터가같으면 @RequestParam()안써줘도됨
		log.info("#m05() - name: " + name);
	}
	@RequestMapping(value="/param2", method = RequestMethod.GET)
	public void m06(@RequestParam("na") String name, int age) { //다르면 이런식으로 넘어오는 이름값을 써줘야함(박스포장지)
		log.info("#m06() - name: " + name + " age: "+ age);
	}
	@RequestMapping(value="/param3")
	public void m07(Human dto) { //다르면 이런식으로 넘어오는 이름값을 써줘야함(박스포장지)
		log.info("#m07() - dto:"+ dto);
		log.info("name: "+dto.getName()+" age:"+dto.getAge());
	}
	@RequestMapping(value="/param4")
	public void m08(@RequestParam ArrayList<String> names) { //다르면 이런식으로 넘어오는 이름값을 써줘야함(박스포장지)
		log.info(names);
	}
	@RequestMapping(value="/param5")
	public void m09(@RequestParam("na") ArrayList<String> names) { //다르면 이런식으로 넘어오는 이름값을 써줘야함(박스포장지)
		log.info(names);
	}
	@RequestMapping(value="/param6")
	public void m10(@RequestParam String[] names) { //다르면 이런식으로 넘어오는 이름값을 써줘야함(박스포장지)
		log.info(names);
	}
	@RequestMapping(value="/param7")
	public void m11(HumanList hlist) { //다르면 이런식으로 넘어오는 이름값을 써줘야함(박스포장지)
		log.info("#m11() - hlist: "+hlist);
	}
	@RequestMapping(value="/param8")
	public void m12(String dump, @RequestParam("s") int seq, Human dto) { //다르면 이런식으로 넘어오는 이름값을 써줘야함(박스포장지)
		 log.info("#m12() - dto:" + dto + ", dump: " + dump + ", seq: " + seq);

	}
	@RequestMapping(value="/param9")
	  public void m13(ToDoDTO dto) {
	      log.info("#m12() - dto:" + dto);
	      Date cdate = dto.getCdate();
	      log.info("#m12() - cdate:" + cdate);
	      
	      showDate(cdate);
	   }
	   private void showDate(Date cdate) {
	      Calendar cal = Calendar.getInstance();
	      cal.setTime(cdate);
	      int y = cal.get(Calendar.YEAR);
	      int m = cal.get(Calendar.MONTH); //1월 -> 0
	      int d1 = cal.get(Calendar.DATE);
	      int d2 = cal.get(Calendar.DAY_OF_WEEK); 
	      String day = "";
	      switch(d2) {
	          case 1: day = "일"; break;
	          case 2: day = "월"; break;
	          case 3: day = "화"; break;
	          case 4: day = "수"; break;
	          case 5: day = "목"; break;
	          case 6: day = "금"; break;
	          case 7: day = "토"; break;
	      }
	      log.info("#m13() : " + y + "년 "+(m+1)+"월 "+d1+"일("+day+")"); 
	   }
	}