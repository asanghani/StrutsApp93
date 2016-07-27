/* ********Tutorila - 05(Understing namespace) 
 * browser --> web.xml(struts filter) -->struts.xml(Action Mapping)-->Action class(ActionMethod and servlice logic)-->return .jsp file.
 * http://localhost:8080/StrutsApp/tutorials/getTutorial.action
 * http://<server>:<port>/<webapp>/<namespace>/<action>.action
 * Using namespace in struts.xml we can group action(same kind of action under one namespace  --/tutorials)
 * 
 ********** Tutorial - 06(A StrutsTag in jsp and a Business Service) 
 * TutorialFinderService class created.
 * 
 * There is two way to get variable value from java to jsp
 * 1.Put that variable in session.
 * 2.Using Struts tag.Struts framework has it's own tag library to display value java to jsp. 
 * <%@ taglib prefix="s" uri="/struts-tags" %>
 * 
 * VeryImportant concept
 * -To Display variable outside(Jsp), first you need to make that variable to global with seetter and getter.
 * 
 *********** Tutorial - 07 The valueStack thread safe explanation(No practicle)
 * In servlet and jsp data transmission, servlet doen't create separate path for each request so need to implement thread safe concept.
 * but in struts framework is difference, not need to make thread safe because struts create individual object for each request.
 * For each request it's create new actionclass instance.  
 * Now this actionclass instance(object) needs to take view jsp, 
 * How it's work?-->using valuestack
 * 
 * ValueStack - valueStack is very important object in struts framework.
 *              valuestack is use in struts to save many objects.
 *              As above for each request struts create new actionobject and this all actionobjects store in valuestack.
 *    
 *********** Tutorial - 08 Accessing input parameter(doGet). 
 * Two way to submit paramete from jsp to java (1)Through url(doGet) (2)Through form object(doPost)    
 * 
 *(1)Using doGet method we passing lang parameter 
 *      http://localhost:8080/StrutsApp/tutorials/getTutorial.action?language=english    
 *      -Create global variable in action class with getter and setter
 *      -Automatically english going to copy in actionclass language parameter
 *      -So all this automatic happen in interceptors.
 *      
 *      ->New request with language parameter 
 *      ->Actionobject created for that request on valuestack with declare variable in actionclass and gette and setter
 *      -->Intersepter store request parameter value on actionobject
 *      Note:As many request that many Action objects setting on valuestack.  
 *      
 * *********** Tutorial - 09 post requests to actions(doPost).  
 *      -->Create new searchform.jsp     
 *      -->http://localhost:8080/StrutsApp/searchform.jsp
 *      -->In search.jsp, we created one form with text filed and give that id as "language"
 *      -->also we declared language variable with getter and setter method in action class. so it's automatically save in value.
 *      -->get method or post method don't worry automatic sturts copy value in action class variable as long as have same name and getter and setter method.
 *      -->at paresent we using all html tag but struts2 provide tab too, which has more extra future.
 *      -->Let's use same struts tag inplace of html tag in searchform1.jsp
 *      -->if you use struts form you don't need to add .action in action name.
 *      
 *      
 * * *********** Tutorial - 10 Login Action and best practice.  
 *     -->Create login.jsp
 *     -->http://localhost:8080/StrutsApp/login.jsp
 *     -->Create loginAction class
 *     -->create new mapping in struts.xml for login functionality
 * 
 * * * *********** Tutorial - 11 Login Action and best practice part-2.  
 *  
 *      -->Until right now, we seen we return result in double quote but that's not good practice.
 *      -->Declare private static variable and then return that variable.
 *      -->ex - private static String SUCCESS = "success";
 *      -->In struts2 we can do two way(1)struts provide interface, which we need to implement.
 *      -->(2)Struts provide class which we need to be extend(class is powerfull then interface)
 *      
 *      -->Let's look at first option, In TutorialAction class
 *      -->public class TutorialAction implements Action {
 *      -->If we Implement Action class than we don't need to declate private string like SUCCESS or ERROR.
 *      -->Action interface taking care automatically.
 *      -->Let's divide struts.xml into other xml and we include this file in sturts.xml
 *      -->Create login.xml and include in struts.xml
 *      
 *      -->Other best practice about hiperlink.Let's say we have manu and cliking manu tab it navigate to other .jsp
 *      -By giving it's to hiperlink it's not good practice.
 *      -->So,what we can do in struts is, There is call alise and dummy action in struts which redirect .jsp
 *      -->dummy Action means, we don't need to implement actionclass just need to redirection.
 *      -->in struts.xml crate package call search without giving class name(Redirection)
 *      -->read moare about dummy class and redirection if needed.
 *      -->http://localhost:8080/StrutsApp/searchForm
 *      
 ************** Tutorial - 12 Action Wildcards mapping.
 * 
		   <package name="search" namespace="/" extends="struts-default" >
		    	<action name="search*">
		        	<result>/searchform1.jsp</result>   
		    	</action>       
			</package>
	-->	As you seen in action"search*". Any action come with search----- it will execute this action.
	-->also we can use wildcard concept in action class mapping too
	-->Read more about wildcard if needed.	
	-->http://localhost:8080/StrutsApp/searchform      
	
	
************** Tutorial - 13 Action Support class
*    --> As we show in early tutorial we used action interface to return some static jsp name. so it's not that good.
*    -->Actionsupport class is powerful then interface.
*    -->In TutorialAction class let's extend ActionSupport class
*    -->This class has method call Validate() which use to validate form(Real word will do by java script)
*    -->let's override validate() and addFieldError() method (LoginAction.java)
*    -->we don't need to worry about redirection to loginpage back once filed empty. it's do automatically redirect to
*     result = input
*     
*     <package name="login" namespace="/" extends="struts-default" >
    	<action name="login" class="org.delta.action.LoginAction">
        	<result name="success">/success.jsp</result>
        	<result name="failure">/error.jsp</result>    
        	<result name="input">/login.jsp</result>      
    	</action>       
	</package>
	
	http://localhost:8080/StrutsApp/login.jsp
	-->For advance validation read more
	Note:-internally actionsupport class implement Action interface.
*    
* 
* 
*************** Tutorial - 14 Configure methods in actions mappings	
*  -->until right now we just use execute() method but if we want to execute some perticular method than
*  -->in TutorialAction class let's create method call someOtherMethod()
*  -->In struts.xml- added mehtod ="someOtherMethod",
*  -->If we don't give method name then it's call default execute() method.
*  <package name="login" namespace="/" extends="struts-default" >
    	<action name="login" class="org.delta.action.LoginAction" method="someOtherMethod">
        	<result name="success">/success.jsp</result>
        	<result name="failure">/error.jsp</result>    
        	<result name="input">/login.jsp</result>      
    	</action>       
	</package>
*  http://localhost:8080/StrutsApp/tutorials/getTutorial
*  -->you can have action name and method name same
* 
*  <package name="login" namespace="/" extends="struts-default" >
    	<action name="*" class="org.delta.action.LoginAction" method="{1}">
        	<result name="success">/success.jsp</result>
        	<result name="failure">/error.jsp</result>    
        	<result name="input">/login.jsp</result>      
    	</action>       
	</package>
	
*  
**************** Tutorial - 15 Using Model objects(M-V-C)- practical-part -1(only one video)-First way
*	-->until right now we seen we passing parameter individual not in package.let's say we have 100 feild in form.
*   -->Model object - model object is package which use to send information( viewform data) between to layer. view to backend.
*   -->Let's create model class first User.java
*   -->change LoginAction.java class accordingly model
*   -->change struts.xml in login package with method="verifyLogin"
*   -->http://localhost:8080/StrutsApp/login.jsp
*   -->until right now we passing iduvidual filed value from .jsp to action and than setting that individual value in user object
*   -->now we flot this user object between action class and service class.
*   
*   
***************** Tutorial - 15 Using Model objects(M-V-C)- practical-part -2(Only one video)-second way
*	-->Now, let's direct we set user object value from view rather than action class.
*   -->login1.jsp
*   -->change in loginAction.java
*   -->cate userloginService.java
*   -->http://localhost:8080/StrutsApp/login1.jsp
*
****************** Tutorial - 15 Using Model objects(M-V-C)- practical-part -3(Only one video)-third way(model driven)
*    -->struts has capability to handle all this, just need to implements ModelDriven interface in actionclass
*	 -->public class LoginAction extends ActionSupport implements ModelDriven { --and implement mehtod getModel()
*	 -->way Modeldriven help is, it's say struts to your action class has model object , if you want to help struts to
*       to populate.
*    -->create login2.jsp   	
*
********************Tutorial - 16--Interceptor -->Look for word file.
*		
*/

package org.delta.action;

import org.delta.service.TutorialFinderService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TutorialAction extends ActionSupport{
	
	String bestTutorialSite;
	private String language;
    //private static String SUCCESS = "success";
	
	public String someOtherMethod(){
		
		System.out.println("Some other method get executed");
		return SUCCESS;
	}
	public String execute(){
		System.out.println("I'm in Action class");
		System.out.println(getLanguage());
		TutorialFinderService tutorialfinderservice = new TutorialFinderService();
		bestTutorialSite = tutorialfinderservice.getBestTutorialSite();
		return SUCCESS; 
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getBestTutorialSite() {
		return bestTutorialSite;
	}

	public void setBestTutorialSite(String bestTutorialSite) {
		this.bestTutorialSite = bestTutorialSite;
	}
	

	
}
