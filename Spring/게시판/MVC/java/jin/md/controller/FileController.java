package jin.md.controller;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jin.md.fileset.Path;
import jin.md.service.FileUploadService;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/file")
public class FileController {
	@Autowired
	private FileUploadService service;
	
	@GetMapping("form.do")
	public String formFu() {
		return "file/form";
	}
	@PostMapping("upload.do")
	public String upload(@RequestParam String name, @RequestParam MultipartFile file) {
		String ofname = file.getOriginalFilename(); 
		if(ofname != null) ofname = ofname.trim();
		if(ofname.length() != 0) {
			String url = service.saveStore(file);
			log.info("#url: "+url);
		}
		return "redirect:list.do"; 
	}
	@GetMapping("list.do")
	public ModelAndView fileList() {
		File fStore = new File(Path.FILE_STORE);
		if(!fStore.exists()) fStore.mkdirs();
		File files[] = fStore.listFiles();
		
		
		return new ModelAndView("file/list", "files", files);
	}
	@GetMapping("del.do")
	public String del(@RequestParam String fname) {
		File file = new File(Path.FILE_STORE, fname);
		if(file.exists()) file.delete();
		
		return "redirect:list.do";
	}
	@GetMapping("download.do")
	public ModelAndView download(String fname) {
		File file = new File(Path.FILE_STORE, fname);
		if(file.exists()) {
			return new ModelAndView("fileDownloadView", "downloadFile", file);
		}else {
			return new ModelAndView("redirect:list.do");
		}
	}
	///여기부터 멀티파일
	@GetMapping("form_mt.do")
	public String formFuMt() {
		return "file/form_mt";
	}
	@PostMapping("upload_mt.do")
	public String uploadMt(ArrayList<MultipartFile> files) {
		for(MultipartFile file : files) {
			String str = upload("", file);
			System.out.println("str: "+ str);
		}
		return "redirect:list.do";
	}

}
