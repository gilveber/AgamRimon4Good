package b2b.gettingNewCustomers;

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

@WebServlet(name = "NewB2BCustomer", value = "/NewB2BCustomer")
public class NewB2BCustomer extends HttpServlet {

    public static EntityManager entityManager=EntitiesSuite.AgamRimon___MainSchema___OnRemote___FromRemote.getEntityManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        String ID=request.getParameter("ID");

        System.out.println("email="+email+"   id="+ID);

        B2BCustomer customer = Tools.DBTools.findByField(B2BCustomer.class, "customerCardCode", ID, entityManager);

        customer.setEmail(email);
        Tools.DBTools.persistEntity(customer, entityManager);
        response.sendRedirect("http://agamrimon.com/NewB2BCustomers/Confirm_Email_Submission.html");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
