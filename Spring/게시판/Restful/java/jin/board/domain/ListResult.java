package jin.board.domain;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListResult {
	private int totalPageCount;
	private int cp;
	private int totalCount;
	private int ps;
	private int totalRecordCount;
	private int firstPage;
	private int lastPage;
	private int pageCount;
	private int firstRecordIndex;
	private int lastRecordIndex;
	private int pps;
	private int first;
	private int second;
	private boolean hasPreviousPage;
	private boolean hasNextPage;
	
	public ListResult(int cp, int ps, int pps) {
		this.cp = cp;
		this.ps = ps;
		this.pps = pps;
		this.first = cp*ps-ps;
		this.second = cp*ps;
	}
	
	public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
 
        if (totalRecordCount > 0) {
            calculation();
        }
    }
	private void calculation() {
		totalPageCount = (int)Math.ceil((this.totalRecordCount/(double)ps)); 
		if(this.getCp() > totalPageCount) { 
			this.setCp(totalPageCount);
		}
		firstPage = ((this.getCp()-1)/ this.getPps()) * this.getPps() +1;
		lastPage = firstPage + this.getPps() -1 ;
		if(lastPage > totalPageCount) {
			lastPage = totalPageCount;
		}
		firstRecordIndex = (this.getCp()-1) * this.getPs();
		lastRecordIndex = this.getCp() * this.getPs();
		
		hasPreviousPage = firstPage == 1 ? false : true;
		if(hasPreviousPage == false) {
			if(cp == firstPage) {
				hasPreviousPage = true;
			}else {
				hasPreviousPage = false;
			}
		}
		
		hasNextPage = (lastPage * this.getPs()) >= totalRecordCount ? false : true;
		if(hasNextPage == false) {
			if(cp != lastPage) {
				hasNextPage = true;
			}else {
				hasNextPage = false;
			}
		}
		
	}
	
}
