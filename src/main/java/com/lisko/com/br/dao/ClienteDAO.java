
package com.lisko.com.br.dao;

import com.lisko.com.br.entity.Cliente;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClienteDAO {
    
    public static List<Cliente> getClientes() {
        
        try (SessionFactory sessionfactory = new Configuration().configure("hibernate-sqlserver.cfg.xml").buildSessionFactory(); Session session = sessionfactory.openSession()) {
            
            String query = " SELECT"
                                    +" DISTINCT(A.CLI),"
                                    +" A.EMAIL,"
                                    +" A.COBEMAIL"
                            +" FROM"
                            + " ("
                            + "SELECT"
                                +" E1.E1_CLIENTE AS CLI,"
                                +" A1.A1_EMAIL AS EMAIL,"
                                +" A1.A1_BLEMAIL COBEMAIL"
                                +" FROM"
                                +" SE1010 E1"
                                +" INNER JOIN"
                                +" SA1010 A1 ON A1.A1_COD = E1.E1_CLIENTE AND A1.A1_LOJA = E1.E1_LOJA"
                                +" WHERE"
                                +" (E1.D_E_L_E_T_ <> '*') AND"
                                +" (A1.D_E_L_E_T_ <> '*') AND"
                                +" (E1.E1_SALDO > 0) AND"
                                +" (A1.A1_BLEMAIL = 1)"
                                +") A";

            List<Cliente> clientes = (List<Cliente>) session.createNativeQuery(query, Cliente.class)
                    .getResultList();

            session.beginTransaction().commit();
            session.close();
            sessionfactory.close();
          
            return clientes;
        }
        
    }
    
}
