package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import model.regAccountLogic;

/**
 * Servlet implementation class RegConfirm
 */
@WebServlet("/RegConfirm")
public class RegConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Account newAccount = request.getSession().getAttribute("resultAccount") instanceof Account?
				(Account)request.getSession().getAttribute("resultAccount") : null;
		regAccountLogic regLogic = new regAccountLogic();
		regLogic.CreateAnAccount(newAccount);
		request.setAttribute("account", newAccount);
		RequestDispatcher ds = request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
		ds.forward(request, response);
	}

}
