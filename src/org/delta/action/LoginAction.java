package org.delta.action;
import org.apache.commons.lang3.StringUtils;
import org.delta.model.User;
import org.delta.service.userloginService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
public class LoginAction extends ActionSupport implements ModelDriven<User> {
	//private User user = new User();------->we don't need to do this struts do this intialization for us as kaushik say but it don't work
	private User user = new User();      //---------------New style, using model object-------->
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void validate() {
		if(StringUtils.isEmpty(user.getUserId())){
			addFieldError("userId","UserID can't be blank");	
		}
		if(StringUtils.isEmpty(user.getPassword())){
			addFieldError("password","password can't be blank");
		}
    
	}
	public String verifyLogin(){
		System.out.println("IN Loging Action ");
		userloginService loginservice = new userloginService();
		
		if(loginservice.verifyLoginService(user)){      //passing user object inplace of field value
			return "success";
		}
		return "failure";
	}
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	/*private String userId;   //--------------------------------------------------->old style
	private String password;
	
	public void validate() {
		if(StringUtils.isEmpty(getUserId())){
			addFieldError("userId","UserID can't be blank");
			
		}
		if(StringUtils.isEmpty(getPassword())){
			addFieldError("password","password can't be blank");
		}
    
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String execute(){
		System.out.println("IN Loging Action ");
		if(getUserId().equals("userId")&& getPassword().equals("password")){
			return "success";
		}
		return "failure";
	}
*/
}
