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
@RequestMapping("/address")
public class AddressController {
	@Autowired	
	private AddressService addressService;
	
	@GetMapping("/list.do")
	public ModelAndView list() {
		List<Address> list = addressService.listS();
		/*
		ModelAndView mv = new ModelAndView();
		mv.setViewName("address/list");
		mv.addObject("list", list);
		*/
		
		ModelAndView mv = new ModelAndView("address/list", "list", list);
		return mv;
	}
	@GetMapping("/write.do") //view로 이동
	public String write() {
		
		return "address/write";
	}
	@PostMapping("/write.do")
	public String write(Address address) {
		addressService.insertS(address);
		return "redirect:list.do";
	}
	@GetMapping("/delete.do")
	public String delete(long seq) {
		addressService.deleteS(seq);
		return "redirect:list.do";
	}
}
