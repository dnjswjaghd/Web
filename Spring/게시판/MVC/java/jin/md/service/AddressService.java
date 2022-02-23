package jin.md.service;

import java.util.List;

import jin.md.domain.Address;

public interface AddressService {
	List<Address> listS();
	void insertS(Address address);
	void deleteS(long seq);
}
