package jin.md.service;

import java.util.List;

import jin.md.domain.Address;

public interface AddressAjaxService {
	List<Address> listS();
	void insertS(Address address);
	void deleteS(long seq);
	
	//for Ajax 
	Address selectBySeqS(long seq);
	List<Address> selectByNameS(String name);
}
