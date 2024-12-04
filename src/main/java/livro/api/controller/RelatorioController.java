package livro.api.controller;


import jakarta.servlet.http.HttpServletResponse;
import livro.api.service.ReportService;
import net.sf.jasperreports.engine.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/export")
    public void createPDF(HttpServletResponse response) throws IOException, JRException {
        response.setContentType("application/pdf");

        // Formatação do nome do arquivo com data e hora
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        // Cabeçalhos para download do PDF
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=report_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        // Chama o serviço que gera o PDF
        reportService.exportReport(response);
    }

}