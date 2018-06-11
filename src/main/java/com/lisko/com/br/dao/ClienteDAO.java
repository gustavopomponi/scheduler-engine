
package com.lisko.com.br.dao;

import com.lisko.com.br.entity.Cliente;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClienteDAO {
    
    public static List<Cliente> getClientes() {
        
        try (SessionFactory sessionfactory = new Configuration().configure("hibernate-sqlserver.cfg.xml").buildSessionFactory(); Session session = sessionfactory.openSession()) {
            
            String query = "SELECT TOP 1 B.*"
                            +" FROM"
                            +" ("
                            +" SELECT"
                                    +" DISTINCT(A.CLI),"
                                    +" A.EMAIL"
                            +" FROM"
                            + " ("
                            + "SELECT"
                                +" E1.E1_CLIENTE AS CLI,"
                                +" E1.E1_LOJA AS LOJA,"
                                +" A1.A1_NOME AS NOME,"
                                +" A1.A1_EMAIL AS EMAIL,"
                                +" E1.E1_PREFIXO AS PFX,"
                                +" E1.E1_NUM AS NUM,"
                                +" E1.E1_SALDO AS SALDO,"
                                +" E1.E1_EMISSAO AS EMISSAO,"
                                +" E1.E1_VENCREA AS VENC,"
                                +" DATEDIFF ( day , cast(E1.E1_VENCREA as date)  , GETDATE() ) AS DIAS"
                                +" FROM"
                                +" SE1010 E1"
                                +" INNER JOIN"
                                +" SA1010 A1 ON A1.A1_COD = E1.E1_CLIENTE AND A1.A1_LOJA = E1.E1_LOJA"
                                +" WHERE"
                                +" (E1.D_E_L_E_T_ <> '*') AND"
                                +" (A1.D_E_L_E_T_ <> '*') AND"
                                +" (DATEDIFF ( day , cast(E1.E1_VENCREA as date)  , GETDATE() )  > 10) AND"
                                +" (E1.E1_SALDO > 0)"
                            +" ) A"
                            +" ) B";

         
            
            List<Cliente> clientes = (List<Cliente>) session.createNativeQuery(query, Cliente.class)
                    .getResultList();

            session.beginTransaction().commit();
            session.close();
            sessionfactory.close();
          
            return clientes;
        }
        
    }
    
}
