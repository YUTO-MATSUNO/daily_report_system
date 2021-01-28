package controllers.good;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class GoodShow
 */
@WebServlet("/reports/GoodShow")
public class GoodShow extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodShow() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());

        EntityManager em = DBUtil.createEntityManager();

//        String id = request.getParameter("id");         //?からレポートIDを受け取る
//        System.out.println(id);
//
        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));     //IDからそのレポートを見つける

//        Employee e = (Employee)request.getSession().getAttribute("login_employee");         //ログインしているIDをセットする

        List<Employee> employeeList = r.getGoodEmployees();

        em.close();

//        request.setAttribute("report", r);
        request.setAttribute("employeeList", employeeList);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/GoodShow.jsp");
        rd.forward(request, response);

    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
