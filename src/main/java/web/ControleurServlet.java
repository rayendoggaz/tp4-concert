package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import dao.IConcertDao;
import dao.ConcertDaoImpl;
import metier.entities.Concert;

@WebServlet(name = "cs", urlPatterns = { "/controleur", "*.do" })
public class ControleurServlet extends HttpServlet {
    IConcertDao metier;

    @Override
    public void init() throws ServletException {
        metier = new ConcertDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/index.do")) {
            request.getRequestDispatcher("concerts.jsp").forward(request, response);
        } else if (path.equals("/chercher.do")) {
            String motCle = request.getParameter("motCle");
            ConcertModele model = new ConcertModele();
            model.setMotCle(motCle);
            List<Concert> concerts = metier.concertsParMC(motCle);
            model.setConcerts(concerts);
            request.setAttribute("model", model);
            request.getRequestDispatcher("concerts.jsp").forward(request, response);
        } else if (path.equals("/saisie.do")) {
            request.getRequestDispatcher("saisieConcert.jsp").forward(request, response);
        } else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
            String nomConcert = request.getParameter("nomConcert");
            double prix = Double.parseDouble(request.getParameter("prix"));
            Concert concert = metier.save(new Concert(nomConcert, prix));
            request.setAttribute("concert", concert);
            request.getRequestDispatcher("confirmation.jsp").forward(request, response);
        } else if (path.equals("/supprimer.do")) {
            Long id = Long.parseLong(request.getParameter("id"));
            metier.deleteConcert(id);
            response.sendRedirect("chercher.do?motCle=");
        } else if (path.equals("/editer.do")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Concert concert = metier.getConcert(id);
            request.setAttribute("concert", concert);
            request.getRequestDispatcher("editerConcert.jsp").forward(request, response);
        } else if (path.equals("/update.do")) {
            Long id = Long.parseLong(request.getParameter("id"));
            String nomConcert = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            Concert concert = new Concert();
            int idInt = id.intValue();
            concert.setIdConcert(idInt);
            concert.setNomConcert(nomConcert);
            concert.setPrix(prix);
            metier.updateConcert(concert);
            request.setAttribute("concert", concert);
            request.getRequestDispatcher("confirmation.jsp").forward(request, response);
        }else {
            response.sendError(Response.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
