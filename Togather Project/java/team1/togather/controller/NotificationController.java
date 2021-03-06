package team1.togather.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import team1.togather.service.NotificationService;
import team1.togather.domain.NotificationCriteria;
import team1.togather.domain.Member;
import team1.togather.domain.Notification;
import team1.togather.domain.nPageMaker;

@AllArgsConstructor
@Controller
@RequestMapping("notification")
public class NotificationController {
	
	
	private NotificationService service;
	
	@ResponseBody
	@GetMapping("listRest")
	public List<Notification> listRest(String option, String notificationSearch, Integer page, Integer pageSize){
		System.out.println("listRest́•ˆ option: "+option+" notificationSearch: "+notificationSearch+" page: "+page+" pagesize: "+pageSize);
		
		Map<String, String> map = new HashMap<String, String>();
		NotificationCriteria cri = new NotificationCriteria(page, pageSize);
		map.put("option", option);
		map.put("notificationSearch", notificationSearch);
		map.put("startRow", String.valueOf(cri.getStartRow()));
		map.put("endRow", String.valueOf(cri.getEndRow()));

		System.out.println(" map:" +map);
		return service.getNotificationBySearch(map);
	}
	
	@GetMapping("/listCri")
	public void listCriGET(Model model, NotificationCriteria cri) {
		model.addAttribute("notificationList", service.listPageCri(cri));
	}
	
	@GetMapping("notice")
	public String notificationList(NotificationCriteria cri, Model model, HttpServletRequest request) {
		System.out.println("notification ́†¡: criê°’: " +cri);
		model.addAttribute("notificationList", service.listPageCri(cri)); // =listPageCri()
		
		System.out.println("1 :" + service.listPageCri(cri));
		
		if(request.getParameter("page")!=null) {
			String pageAt = request.getParameter("page");
			System.out.println("í˜„́?¬ í?˜́?´́§€: "+pageAt);
			cri.setPage(Integer.parseInt(pageAt));
		}
		if(request.getParameter("pageSize")!=null) {
			String pageSize = request.getParameter("pageSize");
			System.out.println("í˜„́?¬ í?˜́?´́§€́‚¬́?´́¦ˆ: "+pageSize);
			cri.setPageSize(Integer.parseInt(pageSize));
		}
		nPageMaker pm = new nPageMaker();
		pm.setCri(cri);
		pm.setTotalCount(service.pageCount()); //calcDate()́‹¤í–‰
		
		
		model.addAttribute("pm", pm);
		model.addAttribute("cri", cri);
		System.out.println("notificatioń»¨í?¸ë¡¤ëŸ¬́•ˆ  ́†¡: cri2ê°’: " +cri);
		return "notification/notice";
	}
	
	@PostMapping("notificationWithSearch")
	public String notificationListWithSearch(NotificationCriteria cri, Model model, HttpServletRequest request) {
		System.out.println("notificatioń»¨í?¸ë¡¤ëŸ¬́•ˆ listpageGET: criê°’: " +cri);
		model.addAttribute("notification", service.listPageCri(cri)); // =listPageCri()
		if(request.getParameter("page")!=null) {
			String pageAt = request.getParameter("page");
			System.out.println("í˜„́?¬ í?˜́?´́§€: "+pageAt);
			cri.setPage(Integer.parseInt(pageAt));
		}
		if(request.getParameter("pageSize")!=null) {
			String pageSize = request.getParameter("pageSize");
			System.out.println("í˜„́?¬ í?˜́?´́§€́‚¬́?´́¦ˆ: "+pageSize);
			cri.setPageSize(Integer.parseInt(pageSize));
		}
		if(request.getParameter("notificationSearch")!=null) {
			String notification = request.getParameter("notification");
			System.out.println("í˜„́?¬ notificationSearch: "+notification);

		}
		nPageMaker pm = new nPageMaker();
		pm.setCri(cri);
		pm.setTotalCount(service.pageCount()); //calcDate()́‹¤í–‰
		model.addAttribute("pm", pm);
		model.addAttribute("cri", cri);
		System.out.println("́˜¤ëƒ? ́—¬ê¸°" + cri);
		return "notification/notice";
		
	}
	
	//ê³µ́§€́‚¬í•­ ́??́„¸í•œë‚´́?©
	@GetMapping("noticeContent")
	public ModelAndView content(long nseq) {
		service.updateNView(nseq);
		Notification notification = service.getNotificationContent(nseq);
		System.out.println("notification " + notification.getNseq() );
		ModelAndView mv = new ModelAndView("notification/noticeContent","noticeContent",notification);		
		return mv ;
		//return "notification/noticeContent";
		
		}
	
	
	//́?¸í’‹í?¼́?¸ë?° ́ •ë³´ ë„£́–´́§„ ́ƒ?íƒœ
	@GetMapping("noticeInput")
	public ModelAndView noticeInput(HttpSession session) {
		Member member =(Member)session.getAttribute("m");
		ModelAndView mv = new ModelAndView("notification/noticeInput","member",member);
		System.out.println("́”¨ë°œë…„́•„");
		return mv;		
	}
	
	//ë„£ê³ ë‚˜́„œ
	@PostMapping("noticeInsert")
	public String noticeInsert(Notification notification) {
		service.insert(notification);
		System.out.println("ê°’ ");
		return "redirect:notice";
	}
	//́—…ë?°́?´í?¸í?¼
	@GetMapping("noticeUpdate")
	public ModelAndView noticeUpdate(Long nseq, HttpSession session) {
		Member member =(Member)session.getAttribute("m");
		Notification notification = service.getNotificationContent(nseq);
		ModelAndView mv = new ModelAndView("notification/noticeUpdate" ,"notification",notification);
		mv.addObject(member);
		System.out.print("́†¡́†¡ë³´́„? : "+ notification);
		return mv;
	}
	// ́—…ë?°́?´í?¸ ́ˆ˜́ •í•˜ê¸° ëˆ„ë¥´ë©´
	@PostMapping("noticeUpdate")
	public String noticeUpdate(Notification notification) {
		service.update(notification);
		System.out.println("notification : "+ notification);
		return "redirect:notice";
	}
	@GetMapping("noticeDelete")
	public String noticeDelete(Long nseq) {
		service.delete(nseq);
		System.out.println();
		return "redirect:notice";
	}
	
	


}
