package co.radiantmic.lpapp.services;

import co.radiantmic.lpapp.model.GroupSale;
import co.radiantmic.lpapp.model.IndivSale;
import co.radiantmic.lpapp.utilities.Converters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private EntityManager em;

    private StoredProcedureQuery storedProcedureQuery;

    private List<IndivSale> indivSaleList;

    private List<GroupSale> groupSaleList;

    private Logger logger = LoggerFactory.getLogger(ReportService.class.getName());

    /**
     * @param startDate
     * @param endDate
     * @return
     */
    public List getIndividualPolicies(String startDate, String endDate) throws Exception {

        indivSaleList = new ArrayList<>();
        storedProcedureQuery = em.createStoredProcedureQuery("getIndivSalesReport");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);

        storedProcedureQuery.setParameter(1, startDate);
        storedProcedureQuery.setParameter(2, endDate);
        List<Object[]> result = storedProcedureQuery.getResultList();
        for (Object[] record : result) {
            IndivSale indivSale = new IndivSale();
            for (int i = 0; i < record.length; i++) {
                indivSale.setPolicyNumber(record[0].toString());
                indivSale.setEntityName(record[1].toString());
                indivSale.setAge((Integer) record[2]);
                indivSale.setNationalId((String) record[3]);
                indivSale.setPolicyType((Integer) record[4]);
                indivSale.setStartDate(record[5].toString());
                indivSale.setEndDate(record[6].toString());
                indivSale.setBankName(record[7].toString());
                indivSale.setCreatedBy(record[8].toString());
                indivSale.setSumInsured((BigInteger) record[9]);
                indivSale.setDuration((Integer) record[10]);
                indivSale.setTotalPremium(Converters.getBigInteger(record[11]));
                indivSale.setAccessories(Converters.getBigInteger(record[12]));
                indivSale.setTotalNetPremium(Converters.getBigInteger(record[13]));
                indivSale.setCreatedOn(((java.sql.Timestamp) record[14]).toLocalDateTime());
            }
            indivSaleList.add(indivSale);
        }
        return indivSaleList;
    }

    /**
     * @param startDate
     * @param endDate
     * @return
     */
    public List getGroupPolicies(String startDate, String endDate) throws Exception {

        groupSaleList = new ArrayList<>();
        storedProcedureQuery = em.createStoredProcedureQuery("getGroupSalesReport");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);

        storedProcedureQuery.setParameter(1, startDate);
        storedProcedureQuery.setParameter(2, endDate);
        List<Object[]> result = storedProcedureQuery.getResultList();
        for (Object[] record : result) {
            GroupSale groupSale = new GroupSale();
            for (int i = 0; i < record.length; i++) {
                groupSale.setPolicyNumber(record[0].toString());
                groupSale.setEntityName(record[1].toString());
                groupSale.setAge((Integer) record[2]);
                groupSale.setNationalId((String) record[3]);
                groupSale.setPolicyType((String) record[4]);
                groupSale.setStartDate(record[5].toString());
                groupSale.setEndDate(record[6].toString());
                groupSale.setBankName(record[7].toString());
                groupSale.setCreatedBy(record[8].toString());
                groupSale.setSumInsured((BigInteger) record[9]);
                groupSale.setDuration((Integer) record[10]);
                groupSale.setTotalPremium(Converters.getBigInteger(record[11]));
                groupSale.setAccessories(Converters.getBigInteger(record[12]));
                groupSale.setTotalNetPremium(Converters.getBigInteger(record[13]));
                groupSale.setMemberSumInsured((BigInteger) record[14]);
                groupSale.setMemberNames((String) record[15]);
                groupSale.setCreatedOn(((java.sql.Timestamp) record[16]).toLocalDateTime());
            }
            groupSaleList.add(groupSale);
        }
        return groupSaleList;
    }

}


