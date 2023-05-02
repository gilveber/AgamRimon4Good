package referrers;

import entities.B2BCustomer;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import miscs.EntitiesSuite;
import miscs.Tools;

import java.io.IOException;

@WebServlet(name = "AppInstall", value = "/AppInstall")
public class AppInstall extends HttpServlet {

    public static EntityManager entityManager = EntitiesSuite.AgamRimon___MainSchema___OnRemote___FromRemote.getEntityManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String customerCardCode = request.getParameter("customerCardCode");

        B2BCustomer customer = Tools.DBTools.findByField(B2BCustomer.class, "customerCardCode", customerCardCode, entityManager);

        customer.setAppInstalled(customer.getAppInstalled()+1);

        Tools.DBTools.persistEntity(customer, entityManager);

        response.sendRedirect("http://server.smart-sale.co.il/mobile.aspx");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
