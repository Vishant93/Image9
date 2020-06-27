package com.vishant.awsimageupload.bucket;

public enum BucketName {
	PROFILE_IMAGE("vishant.code-image-app-upload-123");
	private final String bucketName;
	
	BucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	public String getBucketName() {
		return bucketName;
	}
}

