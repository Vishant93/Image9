package com.vishant.awsimageupload.datastore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.vishant.awsimageupload.profile.UserProfile;

@Repository
public class FakeUserProfileDataStore {
	private static final List<UserProfile> USER_PROFILES = new ArrayList<>();
	static {
		USER_PROFILES.add(new UserProfile(UUID.fromString("168559f8-60c1-4526-8116-983e09cbe927"), "janetiones", null));
		USER_PROFILES.add(new UserProfile(UUID.fromString("57571fcf-a4ef-45b5-a49c-f8713d3d584f"), "antoniojr", null));
	}
	public List<UserProfile> getUserProfiles() {
		return USER_PROFILES;
	}
}
