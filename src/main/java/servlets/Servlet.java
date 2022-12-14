package servlets;

import beans.Catalogo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      HttpSession session = request.getSession(true);
      String libroSelect = "";
      if (request.getParameter("libro") != null && request.getParameter("agregar") != null) {
        if (session.getAttribute("libros") != null) {
          ArrayList<String> arrLibros = (ArrayList<String>) session.getAttribute("libros");
          String libro = request.getParameter("libro");
          libroSelect = libro;
          if (!arrLibros.contains(libro)) {
            arrLibros.add(libro);
          } else {
            out.println("<a style='color:red'>Ya has elegido el libro " + libro + "</a>");
          }
          session.setAttribute("libros", arrLibros);
        } else {
          ArrayList<String> arrLibros = new ArrayList<>();
          String libro = request.getParameter("libro");
          arrLibros.add(libro);
          session.setAttribute("libros", arrLibros);
        }
      }
      if (request.getParameter("borrar") != null) {
        session.invalidate();
        session = request.getSession(true);
      }
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Ejercicio 0 | Edgar Martínez Palmero</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<form method='POST'>");
      out.println("<select name='libro'>");
      String[] tituloLibros = Catalogo.getNombres();
      for (String tituloLibro : tituloLibros) {
        if (libroSelect.equalsIgnoreCase(tituloLibro)) {
          out.println("<option value='" + tituloLibro + "' selected>" + tituloLibro + "</option>" );
        } else {
          out.println("<option value='" + tituloLibro + "'>" + tituloLibro + "</option>");
        }
      }
      out.println("</select>");
      out.println("<input type='submit' name='agregar' value='Agregar'>");
      out.println("<input type='submit' name='borrar' value='Borrar sesion'>");
      out.println("</form>");
      if (session.getAttribute("libros") == null) {
        out.println("<h2>No se han elegido libros</h2>");
      } else {
        out.println("<h2>TU ELECCIÓN:</h2>");
        out.println("<ul>");
        ArrayList<String> arrLibros = (ArrayList<String>) session.getAttribute("libros");
        Iterator it = arrLibros.iterator();
        while (it.hasNext()) {
          out.println("<li>" + it.next() + "</li>");
        }
        out.println("<ul>");
      }
      out.println("</body>");
      out.println("</html>");
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }
}
