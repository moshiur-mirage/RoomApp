
package com.moshiur.room;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ReportController {
    
    @GetMapping("/reportView/{rtype}")
    public void generateStudentPaymentReport(@PathVariable("rtype") String rtype, HttpServletRequest request, HttpServletResponse response) throws Exception, IOException, SQLException, NamingException {

        String reportFileName = "roomtype";
        
        ReportRepository jrdao = new ReportRepository();
        Connection conn = null;
        try {
            conn = jrdao.getConnection();
            
            HashMap<String, Object> hmParams = new HashMap<String, Object>();
            hmParams.put("rtype", rtype);
            
            JasperPrint jasperReport = jrdao.getCompiledFile2(reportFileName, hmParams, request);
            
            response.setContentType("application/pdf");
            
            OutputStream out = response.getOutputStream();
            
            JasperExportManager.exportReportToPdfStream(jasperReport, out);

        } catch (SQLException sqlExp) {
            System.out.println("Exception::" + sqlExp.toString());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }
    }
    
}
