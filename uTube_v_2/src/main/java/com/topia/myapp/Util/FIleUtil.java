package com.topia.myapp.Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FIleUtil {

	private final String uploadPath = Paths.get("C:", "upload", "member").toString();

	private final String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public String uploadFiles(MultipartFile uploadFile) {
		String saveName = "no_image.png";
		
		/* uploadPath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리를 생성 */
		File dir = new File(uploadPath);
		if (dir.exists() == false) {
			dir.mkdirs();
		}
		

		if(uploadFile.getSize()>0) {
			String originalName = uploadFile.getOriginalFilename();
			String extension = FilenameUtils.getExtension(originalName);
			/* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
			saveName = getRandomString() + "." + extension;
			try {
				File target = new File(uploadPath, saveName);
				uploadFile.transferTo(target);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return saveName;
	}
}
