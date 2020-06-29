package com.vishant.awsimageupload.profile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.ContentType.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vishant.awsimageupload.bucket.BucketName;
import com.vishant.awsimageupload.filestore.FileStore;

@Service
public class UserProfileService {
	private final UserProfileDataAccessService userProfileDataAccessService;
	private final FileStore fileStore;
	
	@Autowired
	public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
		this.userProfileDataAccessService = userProfileDataAccessService;
		this.fileStore = fileStore;
	}
	List<UserProfile> getUserProfiles() {
		return userProfileDataAccessService.getUserProfiles();
	}
	public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
		isFileEmpty(file);
		isImage(file);
		UserProfile user = getUserProfileOrThrow(userProfileId);
		
		Map<String, String> metaData = extractMetaData(file);
		
		String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
		String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
		
		try {
			fileStore.save(path, filename, Optional.of(metaData), file.getInputStream());
			user.setUserProfileImageLink(filename);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		
	}
	public byte[] downloadUserProfileImage(UUID userProfileId) {
		UserProfile user = getUserProfileOrThrow(userProfileId);
		String path = String.format("%s/%s", 
				BucketName.PROFILE_IMAGE.getBucketName(), 
				user.getUserProfileId());
		return user.getUserProfileImageLink()
			.map(key -> fileStore.download(path, key))
			.orElse(new byte[0]);
	}
	private Map<String, String> extractMetaData(MultipartFile file) {
		Map<String,String> metaData = new HashMap();
		metaData.put("Content-type", file.getContentType());
		metaData.put("Content-length", String.valueOf(file.getSize()));
		return metaData;
	}
	private UserProfile getUserProfileOrThrow(UUID userProfileId) {
        return userProfileDataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
    }
	private void isImage(MultipartFile file) {
		if (!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(), ContentType.IMAGE_PNG.getMimeType(), ContentType.IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
			throw new IllegalStateException("File must be an image! [" + file.getContentType() + "]");
		}
	}
	private void isFileEmpty(MultipartFile file) {
		if (file.isEmpty()) {
			throw new IllegalStateException("Can't upload empty file [ " + file.getSize() + "]");
		}
	}
}
