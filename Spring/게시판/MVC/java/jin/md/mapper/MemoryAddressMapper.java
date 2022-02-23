package jin.md.mapper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import jin.md.domain.Address;
import lombok.extern.log4j.Log4j;
@Log4j
public class MemoryAddressMapper implements  AddressMapper {
	@Autowired
	private AddressMapper addressMapper;

	public ArrayList<Address> list() {
		ArrayList<Address> addrList = new ArrayList<>();
		log.info("#AddressMapperTests testList(): "+ addressMapper.list());
		return addrList;
	}
	public void insert(Address address) {
		address = new Address(-1L, "스프링", "모델", null);
		addressMapper.insert(address);
		log.info("#AddressMapperTests testInsert() 수행 성공 ");
	}
	public void delete(long seq) {
		seq = 5L;
		addressMapper.delete(seq);
		log.info("#AddressMapperTests testDelete() 수행성공");
	}
}
