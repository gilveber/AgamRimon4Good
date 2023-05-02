package restAPIs.ImageServer;

import entities.B2BCustomer;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import miscs.EntitiesSuite;
import miscs.R;
import miscs.Tools;

import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "ImageServer", value = "/ImageServer")
public class ImageServer extends HttpServlet {

    public static byte[] logoBytes = Tools.IOTools.fileToByteArray(R.Rs.Agam_Logo.get2WayFile().file);
    public static EntityManager entityManager = EntitiesSuite.AgamRimon___MainSchema___OnRemote___FromRemote.getEntityManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String customerCardCode = request.getParameter("customerCardCode");
        final String imageName = request.getParameter("imageName");


        if(!imageName.equals("Agam-Logo.png")){
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        B2BCustomer customer = Tools.DBTools.findByField(B2BCustomer.class, "customerCardCode", customerCardCode, entityManager);

        customer.setEmailOpened(customer.getEmailOpened()+1);

        Tools.DBTools.persistEntity(customer, entityManager);

        response.setContentType("image/png");

        OutputStream os = response.getOutputStream();

        os.write(logoBytes);
        os.flush();
        os.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
