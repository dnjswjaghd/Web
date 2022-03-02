package jin.md.mapper;

import java.util.List;

import jin.md.domain.Address;

public interface AddressMapper {
	List<Address> list(); 
	void insert(Address address);
	void delete(long seq);
}
