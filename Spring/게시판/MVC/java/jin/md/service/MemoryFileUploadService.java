package jin.md.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jin.md.fileset.Path;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemoryFileUploadService implements FileUploadService {

	@Override
	public String saveStore(MultipartFile file) {
		String ofname = file.getOriginalFilename();
		int idx = ofname.lastIndexOf(".");
		String ofheader = ofname.substring(0, idx);
		String ext = ofname.substring(idx);
		long ms = System.currentTimeMillis();
		
		StringBuilder sb = new StringBuilder();
		sb.append(ofheader);
		sb.append("_");
		sb.append(ms);
		sb.append(ext);
		String saveFileName = sb.toString();
		System.out.println("아아아아");
		
		long fsize = file.getSize();
		System.out.println("#ofname: "+ ofname+", saveFileName: "+saveFileName+ ", fsize: "+ fsize);
		
		//업로드로직
		boolean flag = writeFile(file, saveFileName);
		if(flag) {
			log.info("#업로드성공");
		}else {
			log.info("#업로드실패");
		}
		return Path.FILE_STORE + saveFileName;
	}
	private boolean writeFile(MultipartFile file, String saveFileName) {
		File dir = new File(Path.FILE_STORE);
		if(!dir.exists()) dir.mkdirs();
		FileOutputStream fos = null;
		try {
			byte data[] = file.getBytes();
			fos = new FileOutputStream(Path.FILE_STORE+saveFileName);
			fos.write(data);
			fos.flush();
			return true;
		}catch(IOException ie) {
			ie.printStackTrace();
			return false;
		}finally {
			try {
				if(fos != null) fos.close();
			}catch(IOException ie) {
				 ie.printStackTrace();
			}
		}
	}

}
