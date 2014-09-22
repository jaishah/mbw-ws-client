package ws.client.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import org.codehaus.jettison.json.JSONObject;

/**
 * Servlet implementation class RegisterServiceClientServlet
 */
@WebServlet({ "/RegisterServiceClientServlet", "/RegisterService" })
public class RegisterServiceClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServiceClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> map = request.getParameterMap();
        Set set = map.entrySet();
	    Iterator it = set.iterator();
	    String email = request.getParameter("emailId");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", 
			"no-cache, no-store, must-revalidate, pre-check=0, post-check=0, max-age=0, private,  must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", -1); // Proxies.
		Client client = Client.create();
		//JSONObject json= new JSONObject();

		Form form = new Form();
		WebResource webResource = null;
		while (it.hasNext()) {
	            Map.Entry<String, String[]> entry = (Entry<String, String[]>) it.next();
	            String paramName = entry.getKey();
	            String[] paramValues = entry.getValue();
	            if(paramValues.length==1){
	            	if(paramName.equalsIgnoreCase("endpoint")){
	            		webResource = client.resource(paramValues[0]);
	            	}else if (paramName.equalsIgnoreCase("submit")){
	            		System.out.println(paramValues[0]);
	            	}else
	            		//json.put(paramName, paramValues[0]);
	            	form.add(paramName, paramValues[0]);
	            }
	            
		 }
		/*ClientResponse wsResponse = webResource.accept("application/json").type(MediaType.APPLICATION_JSON).post(
                ClientResponse.class, json);*/

/*JSONObject result =  wsResponse.getEntity(JSONObject.class);
String respCode=(String)result.get("response_code");
if(respCode.equals("00")){
String tokenRecived=(String)result.get("token");
System.out.println("tokenRecived" + tokenRecived);
System.out.println("suucessfully Stored data for provisioning");
String redirect_url=(String)result.get("redirect_url");
System.out.println("redirect_url " + redirect_url);

}
if(respCode.equals("Err_20")){ 
String errorMsg= (String)result.getString("response_message");
System.out.println(errorMsg); 
}
if(respCode.equals("Err_04")){ 
String errorMsg= (String)result.getString("response_message");
System.out.println(errorMsg); 
}
if(respCode.equals("Err_16")){ 
String errorMsg= (String)result.getString("response_message");
System.out.println(errorMsg); 
}
System.out.println("responese  : "+wsResponse.getStatus());
System.out.println("responese  : "+wsResponse.getStatus());
}*/
	
			ClientResponse wsResponse = webResource.type("application/x-www-form-urlencoded")
					   .post(ClientResponse.class, form);
			Form output = wsResponse.getEntity(Form.class);
			PrintWriter out = response.getWriter();
			Set<String> h = output.keySet();
			for (String key : h) {
				if(output.getFirst(key).equalsIgnoreCase("success")){
					out.write("Provisioning successful.");
					out.println();
					out.write("Please check your inbox( "+email+" ) for activation email.");	
				}
				else{
					out.write(key+"=" + output.getFirst(key));	
				}
				
			}
			out.close();
	}


	}

/*JSONObject json= new JSONObject();

json.put("package", "FINANCE:SECURITY");
json.put("firstName", "shanthi");
json.put("lastName", "Gopi");
json.put("emailId", "wstest2000@mbc.co");
json.put("address1", "pune");
json.put("address2", "pune");
json.put("city", "pune");
json.put("state", "2");
json.put("country", "14");
json.put("zipcode", "M60 1NW");
json.put("telephone", "1123456789");
json.put("company", "ws test");
json.put("staffNum", "1111111");
json.put("isExistingCustomer", "NEW");
json.put("years", "2");
json.put("branchNo", "123");
json.put("brand", "SBG");

ClientResponse wsResponse = webResource.accept("application/json").type(MediaType.APPLICATION_JSON).post(
		                   ClientResponse.class, json);

JSONObject result =  wsResponse.getEntity(JSONObject.class);
String respCode=(String)result.get("response_code");
if(respCode.equals("00")){
	   String tokenRecived=(String)result.get("token");
	   System.out.println("tokenRecived" + tokenRecived);
	   System.out.println("suucessfully Stored data for provisioning");
	   String redirect_url=(String)result.get("redirect_url");
	   System.out.println("redirect_url " + redirect_url);
	 
}
if(respCode.equals("Err_20")){ 
	   String errorMsg= (String)result.getString("response_message");
	   System.out.println(errorMsg); 
}
if(respCode.equals("Err_04")){ 
	   String errorMsg= (String)result.getString("response_message");
	   System.out.println(errorMsg); 
}
if(respCode.equals("Err_16")){ 
	   String errorMsg= (String)result.getString("response_message");
	   System.out.println(errorMsg); 
}
System.out.println("responese  : "+wsResponse.getStatus());
System.out.println("responese  : "+wsResponse.getStatus());
}

}
*/
