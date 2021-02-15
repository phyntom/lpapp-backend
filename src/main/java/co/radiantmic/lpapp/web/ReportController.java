package co.radiantmic.lpapp.web;

import co.radiantmic.lpapp.exception.BadRequestException;
import co.radiantmic.lpapp.model.IndivSale;
import co.radiantmic.lpapp.services.ReportService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reports/policies")
public class ReportController {

    private StringReader reader;

    private ObjectMapper mapper;

    private final ReportService reportService;

    public ReportController(ReportService reportService) {

        this.reportService = reportService;
    }

    @PostMapping("/indiv")
    private ResponseEntity<?> getIndividualPolicies(@RequestBody String body) {

        reader = new StringReader(body);
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            JsonNode root = mapper.readTree(reader);
            if (root.get("startDate") != null && root.get("endDate") != null) {
                String startDate = root.get("startDate").textValue();
                String endDate = root.get("endDate").textValue();
                List<IndivSale> indivSaleList = reportService.getIndividualPolicies(startDate, endDate);
                return new ResponseEntity<>(indivSaleList, HttpStatus.OK);
            } else {
                BadRequestException exception = new BadRequestException(HttpStatus.BAD_REQUEST.value(), "Bad request", LocalDateTime.now());
                return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error");
        }
    }

    @PostMapping("/group")
    private ResponseEntity<?> getGroupPolicies(@RequestBody String body) {

        reader = new StringReader(body);
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            JsonNode root = mapper.readTree(reader);
            if (root.get("startDate") != null && root.get("endDate") != null) {
                String startDate = root.get("startDate").textValue();
                String endDate = root.get("endDate").textValue();
                List<IndivSale> indivSaleList = reportService.getGroupPolicies(startDate, endDate);
                return new ResponseEntity<>(indivSaleList, HttpStatus.OK);
            } else {
                BadRequestException exception = new BadRequestException(HttpStatus.BAD_REQUEST.value(), "Bad request", LocalDateTime.now());
                return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error");
        }
    }
}
