package web.eng.recipes.servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/RecipeControlServlet")
@MultipartConfig
public class RecipeControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RecipeControlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		
		
		JSONArray jObj = null;
		try {
			jObj = new JSONArray(request.getParameter("recie_ingredients"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Part img = request.getPart("primary_img");

		writeImg(img, title, "primary_img");

		System.out.println(title);
	}

	private String writeImg(Part img, String title, String imgName) throws IOException{
		OutputStream out = null;
		InputStream filecontent = null;
		String directory="C:\\Users\\Stanislav\\Documents\\uni\\INfoVOrgan\\9\\";
		String name=imgName+".png";
		
		new File(directory+title).mkdirs();
		
		String pathToDir=directory+title+"\\";
		String imgPath = pathToDir + name;

		try {
			out = new FileOutputStream(new File(imgPath));
			filecontent = img.getInputStream();

			int read = 0;
			//final byte[] bytes = new byte[1024];

			while ((read = filecontent.read()) != -1) {
				out.write(read);
			}
			
			return imgPath;
		} catch (FileNotFoundException e) {
			return "";
		}finally{
			out.close();
		}
	}

}
