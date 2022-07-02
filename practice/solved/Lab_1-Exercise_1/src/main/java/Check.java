import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Check
 */
@WebServlet("/Check")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get User Data (lab)
		final String labID = request.getParameter("id");
		// Examine User Input
		final String checkResponse = checkResponse(labID);
		
//		System.out.println(checkResponse);
		
		// Return Examination Outcome
		response.getWriter().write(checkResponse);
	}
	
	/** @return A String indicating whether the given username is valid or not */
	private String checkResponse(String labID) {
		if (labID == null || labID.trim().equals("")) return "Not specified !";
		if (labID.equals("lab1")) return "Date of the lab: 1/1/2022";
		if (labID.equals("lab2")) return "Date of the lab: 2/2/2022";
		if (labID.equals("lab3")) return "Date of the lab: 3/3/2022";
		if (labID.equals("lab4")) return "Date of the lab: 4/4/2022";
		return "Not specified";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
