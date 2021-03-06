package team1.togather.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j; 
import team1.togather.domain.Gathering;
import team1.togather.domain.GroupTab;
import team1.togather.domain.MemInGroup;
import team1.togather.domain.Member;
import team1.togather.fileset.Path;
import team1.togather.service.GatheringService;
import team1.togather.service.GroupTabService;

@Log4j
@Controller
@AllArgsConstructor
@RequestMapping("groupTab")
public class GroupTabController {
	@Autowired
	private GroupTabService groupTabService;
	@Autowired
	private GatheringService gatheringService; 


	@GetMapping("myGroup.do")
	public ModelAndView myGroup(MemInGroup memInGroup) {
		List<GroupTab> list = groupTabService.myGroup(memInGroup);
		List<Long> groupMemberCount = new ArrayList<>();
		for(int i =0;i<list.size();i++) {
			groupMemberCount.add(groupTabService.groupMemberCount(list.get(i).getGseq()));
		}
		List<Member> namelist = groupTabService.selectAllname();
		ModelAndView mv = new ModelAndView("groupTab/myGroup", "list", list);
		mv.addObject("groupMemberCount", groupMemberCount);
		mv.addObject("namelist", namelist);
		return mv;
	}
	@GetMapping("groupInfo.do")
	public ModelAndView groupInfo(long gseq,MemInGroup memInGroup) {
		GroupTab groupInfo = groupTabService.selectByGSeqS(gseq);
		Long groupMemberCount = groupTabService.groupMemberCount(gseq);
		Member groupMemberName = groupTabService.groupInfoMemberName(gseq);
		List<Map<String,String>> memInGroupName = groupTabService.memInGroupName(memInGroup);
		Long memInGroupCheck = groupTabService.memInGroupCheck(memInGroup);
		List<Gathering> gatheringList = gatheringService.ga_selectByGseqS(gseq); //?????? ?????? ???????????? (????????????)
		Long gatheringCountInGroup = groupTabService.gatheringCountInGroup(gseq);
		ModelAndView mv = new ModelAndView("groupTab/groupInfo", "groupInfo", groupInfo);
		mv.addObject("groupMemberCount", groupMemberCount);
		mv.addObject("groupMemberName", groupMemberName);
		mv.addObject("memInGroupCheck",memInGroupCheck);
		mv.addObject("memInGroupName",memInGroupName);
		mv.addObject("gatheringList", gatheringList);//?????? ?????? ???????????? (????????????)
		mv.addObject("gatheringCountInGroup", gatheringCountInGroup);//??????info ????????????(????????????)

		return mv;
	}
	@GetMapping("groupCreate.do")
	public String groupCreate() {
		return "groupTab/groupCreate";
	}
	@PostMapping("groupCreate.do")
	public String groupCreate(GroupTab groupTab,HttpSession session) {
		String fname = null;
		MultipartFile uploadFile = groupTab.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String ofname = uploadFile.getOriginalFilename(); //????????? ????????????
			String ext = FilenameUtils.getExtension(ofname); //????????? ????????? ?????????
			
			UUID uuid = UUID.randomUUID(); //UUID ????????? -> ?????? ??? ????????????. //?????? ????????? ??? ?????? ??????????????? ????????? ???????????? ????????? ???????????? ???????...
			fname = ofname + uuid + "." + ext;
			try {
				uploadFile.transferTo(new File(Path.FILE_STORE + fname));
			}catch(IOException ie) {}
			groupTab.setFname(fname);
		}
		groupTabService.insertS(groupTab);
		GroupTab g = groupTabService.insertGroupInfo(groupTab);
		Member m =(Member)session.getAttribute("m");
		return "redirect:groupInfo.do?gseq="+g.getGseq()+"&mnum="+m.getMnum();	
	}
	@GetMapping("groupDelete.do")
	public String groupDelete(long gseq) {
		groupTabService.memInGroupDelete(gseq);
		groupTabService.deleteS(gseq);
		return "redirect:/";
	} 
	@PostMapping("groupUpdatecheck")
	@ResponseBody
	public Long groupUpdatecheck(MemInGroup memInGroup) {
		//0=????????? 1=????????? 2=??????
		Long grade = groupTabService.grade(memInGroup);
		if(grade ==null) {//???????????? ??????
			grade=(long) 3;
			return grade;
		}else {	
			if(grade==0 || grade ==1) {//?????????????????? ?????????
				return grade;
			}else {//????????????
				return grade;
			}
		}
	}
	@PostMapping("groupDeletecheck")
	@ResponseBody
	public Long groupDeletecheck(MemInGroup memInGroup) {
		//0=????????? 1=????????? 2=??????
		Long grade = groupTabService.grade(memInGroup);
		if(grade ==null) {//???????????? ??????
			grade=(long) 3;
			return grade;
		}else {	
			return grade;
		}
	}
	@GetMapping("groupUpdate.do")
	public ModelAndView groupUpdate(long gseq) {
		GroupTab updateList = groupTabService.selectByGSeqS(gseq);
		ModelAndView mv = new ModelAndView("groupTab/groupUpdate", "updateList", updateList);
		return mv;
		
	}
	@PostMapping("groupUpdate.do")
	public String groupUpdate(GroupTab groupTab, HttpSession session) {
		System.out.println("???????????????");
		long gseq = groupTab.getGseq();
		System.out.println("groupUpdate_gseq: " + gseq + "groupUpdate_groupTab: " + groupTab);
		
		String fname = groupTab.getFname();
		MultipartFile uploadFile = groupTab.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String ofname = uploadFile.getOriginalFilename(); //????????? ????????????
			int idx = ofname.lastIndexOf("."); //??????????????? ?????????
			String ofheader = ofname.substring(0, idx);
			String ext = FilenameUtils.getExtension(ofname); //????????? ????????? ?????????
			
			UUID uuid = UUID.randomUUID(); 
			fname = ofheader + uuid + "." + ext;
			try {
				uploadFile.transferTo(new File(Path.FILE_STORE + fname));
			}catch(IOException ie) {}
			groupTab.setFname(fname);
		}
		
		groupTabService.updateS(groupTab);
		
		Member m =(Member)session.getAttribute("m");
		return "redirect:groupInfo.do?gseq="+gseq+"&mnum="+m.getMnum();
	}
	@PostMapping("memInGroup")
	@ResponseBody
	public int memInGroup(MemInGroup memInGroup,HttpSession session) {
		int check=0;
		long limit = groupTabService.LIMIT(memInGroup);
		Long groupMemberCount = groupTabService.groupMemberCount(memInGroup.getGseq());
		if(limit<=groupMemberCount) {
			check=1;
			
			
		}else {
			groupTabService.memInGroup(memInGroup);
			check=0;
		}
		return check;
	}
	@PostMapping("groupQuit")
	@ResponseBody
	public String groupQuit(MemInGroup memInGroup,long gseq) {
		List<Map<String,String>> memInGroupName = groupTabService.memInGroupName(memInGroup);
		groupTabService.groupQuit(memInGroup);
		Long groupMemberCount = groupTabService.groupMemberCount(gseq);
		String result="";
		if(groupMemberCount==0) {
			groupTabService.memInGroupDelete(gseq);
			groupTabService.deleteS(gseq);
			result="0";
		}else {
			result="1";
		}
		return result;
	}
	
	//03.26 ????????????
		@PostMapping("gatheringCreateCheck")
		@ResponseBody
		public Long gatheringCreateCheck(long gseq) {
			System.out.println("#Controller: " + gseq);
			Long check = groupTabService.gatheringCountInGroup(gseq);
			if(check >= 5) {
				System.out.println("#gatheringCreateCheck: " + check);
				return (long)0;
			}else{
				return (long)1;
			}
		}
}
