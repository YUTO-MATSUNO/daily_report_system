package controllers.good;

import java.io.IOException;

import javax.persistence.EntityManager;
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
 * いいね追加のコントローラ
 */
@WebServlet("/GoodAdd")
public class GoodAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("呼ばれた");

        EntityManager em = DBUtil.createEntityManager();

        String id = request.getParameter("id");         //?からレポートIDを受け取る
        System.out.println(id);

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));     //IDからそのレポートを見つける

        Employee e = (Employee)request.getSession().getAttribute("login_employee");         //ログインしているIDをセットする

        Good g = new Good();





        g.setEmployee(e);
        g.setReport(r);

        em.getTransaction().begin();
        em.persist(g);
        em.getTransaction().commit();
        em.close();

        //日報詳細ページに戻る
        System.out.println("日報の番号は" + r.getId());
        System.out.println(request.getContextPath() + "/reports/show?id=" + r.getId());

        response.sendRedirect(request.getContextPath() + "/reports/show?id=" + r.getId());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }



}