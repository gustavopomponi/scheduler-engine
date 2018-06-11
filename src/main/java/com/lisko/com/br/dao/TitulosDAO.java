
package com.lisko.com.br.dao;

import com.lisko.com.br.entity.Titulos;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TitulosDAO {
    
    
    public static List<Titulos> getTitulos(String IDCliente) {
        
        try (SessionFactory sessionfactory = new Configuration().configure("hibernate-sqlserver.cfg.xml").buildSessionFactory(); Session session = sessionfactory.openSession()) {
            
            String query = "SELECT A.*"
                          +" FROM ("
                          + "SELECT"
                          +" E1.E1_CLIENTE AS CLI,"
                          +" E1.E1_LOJA AS LOJA,"
                          +" A1.A1_NOME AS NOME,"
                          +" A1.A1_EMAIL AS EMAIL,"
                          +" E1.E1_PREFIXO AS PFX,"
                          +" SUBSTRING(E1.E1_NUM, PATINDEX('%[^0]%', E1.E1_NUM+'.'), LEN(E1.E1_NUM)) AS NUM,"
                          +" FORMAT(E1.E1_SALDO, 'C', 'pt-br') AS 'SALDO',"
                          +" FORMAT(SUM(E1.E1_SALDO) OVER(),'C', 'pt-br') AS TOTAL_SALDO,"
                          +" SUBSTRING(E1.E1_EMISSAO,7,2) + '/' + SUBSTRING(E1.E1_EMISSAO,5,2) + '/' + SUBSTRING(E1.E1_EMISSAO,1,4) AS EMISSAO,"
	                  +" SUBSTRING(E1.E1_VENCREA,7,2) + '/' + SUBSTRING(E1.E1_VENCREA,5,2) + '/' + SUBSTRING(E1.E1_VENCREA,1,4) AS VENC,"
                          +" DATEDIFF ( day , cast(E1.E1_VENCREA as date)  , GETDATE() ) AS DIAS,"
                          +" FORMAT(E1.E1_VALOR,'C', 'pt-br') AS VALOR,"
                          +" FORMAT(SUM(E1.E1_VALOR) OVER(),'C', 'pt-br') AS TOTAL_VALOR"
                          +" FROM"
                          +" SE1010 E1"
                          +" INNER JOIN"
                          +" SA1010 A1 ON A1.A1_COD = E1.E1_CLIENTE AND A1.A1_LOJA = E1.E1_LOJA"
                          +" WHERE"
                          +" (E1.D_E_L_E_T_ <> '*') AND"
                          +" (A1.D_E_L_E_T_ <> '*') AND"
                          +" (DATEDIFF ( day , cast(E1.E1_VENCREA as date)  , GETDATE() )  > 10) AND"
                          +" (E1.E1_SALDO > 0) AND"
                          +" (E1.E1_CLIENTE = ?1)"
                          +" ) A";
                       

            List<Titulos> titulos = session.createNativeQuery(query, Titulos.class)
                    .setParameter(1, IDCliente).getResultList();

            session.beginTransaction().commit();
            
            session.close();
            sessionfactory.close();
          
            return titulos;
        }
        
    }
    
}
