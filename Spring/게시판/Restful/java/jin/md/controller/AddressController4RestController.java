package jin.md.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jin.md.domain.Address;
import jin.md.service.AddressService;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("rest_addr")
public class AddressController4RestController {
	@GetMapping("/write.do") //view로 이동
	public String write() {
		return "rest_addr/write";
	}
	

}
