package jin.md.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ajax")
public class AjaxIndexController {
	@GetMapping("test01.do")
	public String t01() {
		return "ajax/test01";
	}
	@GetMapping("test02.do")
	public String t02() {
		return "ajax/test02";
	}
	@GetMapping("test03.do")
	public String t03() {
		return "ajax/test03";
	}
	@GetMapping("test04.do")
	public String t04() {
		return "ajax/test04";
	}
}
