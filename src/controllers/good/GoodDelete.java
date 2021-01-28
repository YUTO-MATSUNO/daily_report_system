package controllers.good;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Good;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class GoodDelete
 */
@WebServlet("/GoodDelete")
public class GoodDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodDelete() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            EntityManager em = DBUtil.createEntityManager();

            String id = request.getParameter("id");         //?からレポートIDを受け取る
            System.out.println(id);

            Employee e = (Employee)request.getSession().getAttribute("login_employee");

            Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

            Good g = null;

            System.out.println("いいねを解除");

            try {
                g = em.createNamedQuery("checkRegisteredGood", Good.class)
                      .setParameter("employee", e)
                      .setParameter("report", r)
                      .getSingleResult();
            } catch(NoResultException ex) {}

            em.getTransaction().begin();
            em.remove(g);       // データ削除
            em.getTransaction().commit();
            em.close();

            request.getSession().removeAttribute("id");

            System.out.println(request.getContextPath() + "/reports/show?id=" + r.getId());

            response.sendRedirect(request.getContextPath() + "/reports/show?id=" + r.getId());
        }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
