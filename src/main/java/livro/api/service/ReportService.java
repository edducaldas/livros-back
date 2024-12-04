package livro.api.service;

import jakarta.servlet.http.HttpServletResponse;
import livro.api.dto.Relatorio;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Relatorio> buscarRelatorio() {
        String sql = "SELECT autor, livro, editora, edicao, ano_publicacao, assunto FROM vw_relatorio_livro_autor_assunto";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Relatorio(
                rs.getString("autor"),
                rs.getString("livro"),
                rs.getString("editora"),
                rs.getLong("edicao"),
                rs.getString("ano_publicacao"),
                rs.getString("assunto")

        ));
    }


    public void exportReport(HttpServletResponse response) throws JRException, IOException {
        List<Relatorio> relatorios =  buscarRelatorio();

        File file = ResourceUtils.getFile("classpath:relatorios/livro.jrxml");
        if (!file.exists()) {
            throw new FileNotFoundException("Arquivo não encontrado: " + file.getAbsolutePath());
        }
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(relatorios);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Eduardo Caldas");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
    }
}
