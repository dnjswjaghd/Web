package team1.togather.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	Long bnum;
	String bcategory;
	String btitle;
	Long mnum;
	String mname;
	String bcontent;
	String bfile;
	Long blike;
	Long bview;
	Date rdate; 
}
