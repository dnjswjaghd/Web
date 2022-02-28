package jin.md.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jin.md.service.DragDropService;

@Controller
@RequestMapping("file")
public class DragDropController {
	@Autowired
	DragDropService service;
	
	@GetMapping("/form_dd.do")
	public String form() {
		return "drag_drop/form";
	}
	@PostMapping("fileUpload.do")
	public String fileUpload(MultipartHttpServletRequest mhsr) {
		//파일 업로드 서비스 메소드 호출
		service.setMultipartRequest(mhsr);
		Map<String, List<Object>> map = service.getUpdateFileName();
		System.out.println("#DragDropController map: "+ map);
		
		String appendData = mhsr.getParameter("temp");
		System.out.println("#DragDropController appendData: "+ appendData);
		return "redirect:list.do";
	}
}
