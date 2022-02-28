package jin.md.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jin.md.domain.Address;
import jin.md.service.AddressAjaxService;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController()
@RequestMapping("rest_addr")
public class AddressRestController { //CRUD
	@Autowired
	private AddressAjaxService service;
	@PostMapping("create")
	public void create(@RequestBody Address address) {
		System.out.println("#AddressRestController 내 address: "+address);
		service.insertS(address);
	}
	/*
	@PostMapping("create")
	public void create(Address address) {
		System.out.println("#AddressRestController 내 address: "+address);
		service.insertS(address);
	}*/
	@GetMapping("read")
	public List<Address> read(){
		List<Address> list = service.listS();
		return list;
	}
	@GetMapping("read/{seq}")
	public Address read(@PathVariable Long seq){
		Address address = service.selectBySeqS(seq);
		return address;
	}
	// http://127.0.0.1:8080/rest_addr/read/1.json
	
	@GetMapping(value="read", params = {"na"})
	public List<Address> read(@RequestParam("na") String name){
		List<Address> list = service.selectByNameS(name);
		return list;
	}
	// http://127.0.0.1:8080/rest_addr/read.json?na=가
	
	@DeleteMapping("delete/{seq}")
	public void delete(@PathVariable Long seq) {
		service.deleteS(seq);
	}
	// http://127.0.0.1:8080/rest_addr/delete/1.json
}
