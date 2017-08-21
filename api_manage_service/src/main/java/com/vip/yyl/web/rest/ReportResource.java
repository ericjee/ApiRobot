package com.vip.yyl.web.rest;

import com.vip.yyl.repository.NodeRepository;
import com.vip.yyl.service.dto.NodeDTO;
import com.vip.yyl.service.dto.NodeStatusResponseDTO;
import com.vip.yyl.service.dto.ReportDTO;
import com.vip.yyl.service.dto.TagDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReportResource {
    Logger logger = LoggerFactory.getLogger(ReportResource.class);

    @Inject
    private NodeRepository nodeRepository;

    @Autowired
    private ApiResource apiResource;

    /**
     * This API can be used to generate report for "RUN PROJECT" functionality.
     */
    @RequestMapping(value="/report/projects/{id}", method = RequestMethod.GET)
    public void generateRunProjectReport(@PathVariable("id") Long id) {

    }

    @RequestMapping(value="/processor/projects/{id}/report", method = RequestMethod.GET)
    public void generateRunProjectReport(@PathVariable("id") String id, @RequestParam(value = "envId", required = false) String envId, HttpServletResponse response) throws URISyntaxException {
	List<NodeStatusResponseDTO> nodeStatusResponse = apiResource.runProjectById(id, envId);
	generateRunNodeReport(nodeStatusResponse, response);
    }

    @RequestMapping(value="/processor/folders/{id}/report", method = RequestMethod.GET)
    public void generateRunFolderReport(@PathVariable("id") String id, @RequestParam(value = "envId", required = false) String envId, HttpServletResponse response) throws URISyntaxException {
	List<NodeStatusResponseDTO> nodeStatusResponse = apiResource.runFolderById(id, envId);
	generateRunNodeReport(nodeStatusResponse, response);
    }

    public void generateRunNodeReport(List<NodeStatusResponseDTO> nodeStatusResponse, HttpServletResponse response) {
	String reportTemplateFilePath = "report-template" + File.separator + "rf_doc_template.jasper";
	Resource resource = new ClassPathResource(reportTemplateFilePath);

	List<ReportDTO> apiNodes = new ArrayList<ReportDTO>();
	ReportDTO node = new ReportDTO();
	apiNodes.add(node);

	Map<String, Object> params = new HashMap<String, Object>();
	node.setNodeStatusResponse(nodeStatusResponse);

	JRBeanCollectionDataSource ds1 = new JRBeanCollectionDataSource(apiNodes);
	JRBeanCollectionDataSource ds2 = new JRBeanCollectionDataSource(nodeStatusResponse);
	params.put("nodeStatusResponse", ds2);

	try (InputStream inputStream = resource.getInputStream();) {
	    response.setContentType("application/pdf");
	    response.setHeader("Content-Disposition", "attachment; filename=\"report.pdf\"");
	    response.setHeader("Cache-Control", "no-cache");
	    JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, params, ds1);
	    JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	} catch (JRException e) {
	    logger.error(e.getMessage(), e);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
    }

    /**
     * This API can be used to generate REST API documentation for a project.
     */
    @RequestMapping(value="/documentation/projects/{id}", method = RequestMethod.GET)
    public void generateProjectApiDocumentation(@PathVariable("id") Long id, HttpServletResponse response) {
	String reportTemplateFilePath = "report-template" + File.separator + "rf_doc_template.jasper";
	Resource resource = new ClassPathResource(reportTemplateFilePath);

	// TODO : NodeDTO and TagDTO are used for testing purpose. Need to define a custom DTO for reports.
	// TODO : Use NodeStatusResponseDTO instead of TagDTO.
	List<NodeDTO> apiNodes = new ArrayList<NodeDTO>();

	NodeDTO node = new NodeDTO();
	apiNodes.add(node);

	List<TagDTO> tags = new ArrayList<TagDTO>();
	TagDTO tag = new TagDTO();
	//tag.setId(24);
	tag.setName("GET Workspaces");
	tag.setDescription("A workspace is a collection of projects. This API returns list of available workspaces.");
	tags.add(tag);

	TagDTO tag2 = new TagDTO();
	//tag2.setId(30L);
	tag2.setName("Post Workspace");
	tag2.setDescription("This API is used to create a new workspace.");
	tags.add(tag2);
	node.setTags(tags);

	JRBeanCollectionDataSource ds1 = new JRBeanCollectionDataSource(apiNodes);

	Map<String, Object> params = new HashMap<String, Object>();
	JRBeanCollectionDataSource ds2 = new JRBeanCollectionDataSource(tags);
	params.put("tags", ds2);

	try (InputStream inputStream = resource.getInputStream();) {
	    response.setContentType("application/pdf");
	    response.setHeader("Content-Disposition", "attachment; filename=\"report.pdf\"");
	    response.setHeader("Cache-Control", "no-cache");
	    JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, params, ds1);
	    JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	} catch (JRException e) {
	    logger.error(e.getMessage(), e);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
    }
}
