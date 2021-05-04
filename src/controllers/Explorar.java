package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.*;
import model.bean.*;

/**
 * Servlet implementation class Explorar
 */
@WebServlet("/Explorar")
public class Explorar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Explorar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ArrayList<Element> elements;
			int id = 0;
			/* Obtención del parametro id de elemento a mostrar. Si no se recibe parametro, id tendrá valor 0 */
			if(request.getParameter("id") != null && request.getParameter("id").length()>0) {
				id = Integer.parseInt(request.getParameter("id"));
			}
	
			FileSystemModel fs = new FileSystemModel();

			// Obtener el elemento actual
			Element element = fs.getElement(id);
			request.setAttribute("actual_element",element);
			if(element != null && element.getType().equals("archivo")) {
				// si el elemento actual es un archivo se quiere mostrar la lista del padre al que pertenece
				id = element.getParent_id();
			}
			
			// SE PROCEDE A MODIFICAR LA BASE DE DATOS
			
			//INSERTAR
			
			String newtype = request.getParameter("newtype");
			String newname = request.getParameter("newname");
			if(newtype!=null && newtype.length()>0 && (newtype.equals("carpeta") || newtype.equals("archivo")) && newname!=null && newname.length()>0) {
				String extension = "";
				if(newtype.equals("archivo")) {
					String[] split = newname.split("\\.");
					newname = split[0];
					if(split.length>1){
						extension = split[1].substring(0, 5);
					}else {
						extension = "file";
					}
				}
				fs.insertElement(id,newname,extension,newtype);
			}
			
			// ELIMINAR
			String action = request.getParameter("action");
			String idtodelete = request.getParameter("idtodelete");
			if(action!=null && action.length()>0 && action.equals("delete") && idtodelete.length()>0) {
				fs.deleteElement(Integer.parseInt(idtodelete));
			}
			
			// SE PROCEDE A MOSTRAR EL CONTENIDO
			
			// Obtencion de la lista de elementos, teniendo en cuenta la ordenación
			String orderby= request.getParameter("orderby");
			String ascdesc= request.getParameter("ascdesc");
			if(orderby != null && orderby.length()>0  && (orderby.equals("name") || orderby.equals("updated_at")) ) {
				if(ascdesc != null && ascdesc.length()>0  && (ascdesc.equals("asc") || ascdesc.equals("desc")) ) {
					elements = fs.getElements(id,orderby,ascdesc);
					if(ascdesc.equals("asc")) {
						ascdesc="desc";
					}else{
						ascdesc="asc";
					}
				}else {
					elements = fs.getElements(id,orderby);
					ascdesc = "asc";
				}
			}else {
				orderby = "name";
				ascdesc = "asc";
				// obtención de elmentos si no hay ordenación
				elements = fs.getElements(id);
			}
			
			
			request.setAttribute("elements",elements);
			request.setAttribute("orderby",orderby);
			request.setAttribute("ascdesc",ascdesc);
			request.setAttribute("title", "Explorar");

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}	
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
