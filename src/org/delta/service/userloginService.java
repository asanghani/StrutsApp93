package org.delta.service;

import org.delta.model.User;

public class userloginService {
	
	public boolean verifyLoginService(User user){
		System.out.println("IN LogingService ");
		
		if(user.getUserId().equals("userId")&& user.getPassword().equals("password")){
			return true;
		}
		return false;
	}

}
