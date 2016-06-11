package Servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

/**
 * Servlet implementation class DevHomePageServlets
 */
public class DevHomePageServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	HashMap<String,String> getOrderDetailsMap = new HashMap<String,String>();
    public DevHomePageServlets() {
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
		// TODO Auto-generated method stub
		
		System.out.println("Inside doGet of DevHomePageServlets");
		String orderId= request.getParameter("Order_Id");
		System.out.println("Order_Id value : "+ orderId);
		
		DBUtil dbUtil = new DBUtil();
		getOrderDetailsMap = dbUtil.getOrderDetails(orderId);
		
		//HttpSession session = request.getSession(true);
		
		
		//session.setAttribute("ORDER_ID", getOrderDetailsMap.get("ORDER_ID"));
		
		request.setAttribute("ORDER_ID", getOrderDetailsMap.get("ORDER_ID"));
		
		
		request.setAttribute("MERCHANT_ID", getOrderDetailsMap.get("MERCHANT_ID"));
		
		
		request.setAttribute("AMOUNT", getOrderDetailsMap.get("AMOUNT"));
		
		System.out.println("Redirecting it to DevPaymentPage");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("DevPaymentPage.jsp");
		dispatcher.forward(request, response);
		
		
		
		
	}

}
