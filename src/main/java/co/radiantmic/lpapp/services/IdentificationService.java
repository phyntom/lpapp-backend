package co.radiantmic.lpapp.services;

import co.radiantmic.lpapp.domain.Identification;
import co.radiantmic.lpapp.exception.ConnectionException;
import co.radiantmic.lpapp.utilities.XmlParserUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.jdom2.Namespace;

import java.io.StringReader;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class IdentificationService {

    @Value("${nida.service.url}")
    private String NIDA_API_URL;

    @Value("${nida.service.radiant-access-code}")
    private String ACCESS_CODE;

    private OkHttpClient client;

    private final Logger log = LoggerFactory.getLogger(IdentificationService.class.getName());

    @Value("${kyc_url}")
    private String kycURL = "http://mtnkyc:8011/MTNR_KYC_Microloan/ProxyService/MTNR_KYC_Microloan?WSDL";

    @Value("${kyc_username}")
    private String kycUsername;

    @Value("${kyc_password}")
    private String kycPassword;

    public Identification getCustomerIdentification(String nationalId) {

        try {
            client = new OkHttpClient().newBuilder().
                    connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS).build();
            Request request = new Request.Builder().url(NIDA_API_URL + "/" + nationalId)
                    .addHeader("radiant-access-code",
                            ACCESS_CODE).get().build();
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            String responseString = responseBody.string();
            ObjectMapper reader = new ObjectMapper();
            reader.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            StringReader stringReader = new StringReader(responseString);
            JsonNode root = reader.readTree(stringReader);
            Identification identification = reader.readValue(root.get("responseBody").textValue(), Identification.class);
            log.info("Body {}", identification.getDateOfBirth());
            return identification;
        } catch (SocketTimeoutException ex) {
            log.error("Error | Connection fails. Unable to connect to NIDA service | {} ", ex.toString());
            throw new ConnectionException("Error | Connection fails. Unable to connect to NIDA service");
        } catch (Exception ex) {
            log.error("Error | Unable to get customer identification details | {} ", ex.toString());
            throw new ConnectionException("Error | Unable to get customer identification details");
        }
    }

    public Identification getCustomerIdentificationKyc(String mobileNumber) {

        try {
            Identification identification;
            client = new OkHttpClient().newBuilder().
                    connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS).build();

            String senderId = kycUsername;
            String externalApplicationName = kycUsername;
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            String transactionId = getTransactionId(mobileNumber);
            String xmlData = "<soapenv:Envelope  xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                    "xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"><soapenv:Header><wsse:Security  " +
                    "xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">" +
                    "<wsse:UsernameToken><wsse:Username>" + kycUsername + "</wsse:Username><wsse:Password>" + kycPassword + "" +
                    "</wsse:Password></wsse:UsernameToken></wsse:Security></soapenv:Header><soapenv:Body xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                    "xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" ><xs:FetchKYCDetailsRequest xmlns:com=\"http://mtn.com/data/common\">" +
                    "<xs:CommonInfromation><com:TransactionId>" + transactionId + "</com:TransactionId> <com:OrderDateTime>" + dateFormatter.format(new Date()) +
                    "</com:OrderDateTime><com:OpCoID>RW</com:OpCoID><com:SenderID>" + senderId + "</com:SenderID></xs:CommonInfromation>" +
                    "<xs:MSISDN>" + mobileNumber + "</xs:MSISDN><xs:ExternalApplication>" + externalApplicationName + "</xs:ExternalApplication>" +
                    "<xs:ServiceCode>GSM</xs:ServiceCode></xs:FetchKYCDetailsRequest>" +
                    "</soapenv:Body>" +
                    "</soapenv:Envelope>";
            log.info("Request Sent - {}", xmlData);
            MediaType mediaType = MediaType.parse("application/xml");
            RequestBody body = RequestBody.create(mediaType, xmlData);
            Request request = new Request.Builder().url(kycURL).method("POST", body).build();
//            Response response = client.newCall(request).execute();
//            ResponseBody responseBody = response.body();
//            String responseString = responseBody.string();
            String responseString = "<soapenv:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "    <soapenv:Header />\n" +
                    "    <soap-env:Body xmlns:soap-env=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "        <xs:FetchKYCDetailsResponse xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n" +
                    "            <xs:Status>\n" +
                    "                <com:StatusCode xmlns:com=\"http://mtn.com/data/common\">0</com:StatusCode>\n" +
                    "                <com:StatusInfo xmlns:com=\"http://mtn.com/data/common\">Request has been Processed Successfully</com:StatusInfo>\n" +
                    "            </xs:Status>\n" +
                    "            <xs:TransactionID>1580377509875795</xs:TransactionID>\n" +
                    "            <xs:CustomerDetails>\n" +
                    "                <xs:FirstName>Aimable</xs:FirstName>\n" +
                    "                <xs:LastName>RUHUMURIZA</xs:LastName>\n" +
                    "                <xs:Title>MR</xs:Title>\n" +
                    "                <xs:Gender>M</xs:Gender>\n" +
                    "                <xs:Nationality>RWANDA</xs:Nationality>\n" +
                    "                <xs:EmailID>DUMMY</xs:EmailID>\n" +
                    "                <xs:City>Huye</xs:City>\n" +
                    "            </xs:CustomerDetails>\n" +
                    "            <xs:DocumentID>\n" +
                    "                <xs:IDDesc>NATIONAL IDENTITY CARD</xs:IDDesc>\n" +
                    "                <xs:IDType>NID</xs:IDType>\n" +
                    "                <xs:IDNumber>1198380045854089</xs:IDNumber>\n" +
                    "            </xs:DocumentID>\n" +
                    "            <xs:RegistrationStatus>\n" +
                    "                <com:StatusCode xmlns:com=\"http://mtn.com/data/common\">AC</com:StatusCode>\n" +
                    "                <com:StatusInfo xmlns:com=\"http://mtn.com/data/common\">RegisteredComplete</com:StatusInfo>\n" +
                    "            </xs:RegistrationStatus>\n" +
                    "        </xs:FetchKYCDetailsResponse>\n" +
                    "    </soap-env:Body>\n" +
                    "</soapenv:Envelope>";
            StringReader responseReader = new StringReader(responseString);
            Namespace rootNameSpace =
                    Namespace.getNamespace("http://schemas.xmlsoap.org/soap/envelope/");
            Namespace soapNameSpace =
                    Namespace.getNamespace("http://schemas.xmlsoap.org/soap/envelope/");
            Namespace kycNameSpace =
                    Namespace.getNamespace("http://www.w3.org/2001/XMLSchema");
            Namespace comNameSpace =
                    Namespace.getNamespace("http://mtn.com/data/common");
            Optional<Document> optDocument = XmlParserUtil.streamParser(responseReader);
            if (optDocument.isPresent()) {
                Document document = optDocument.get();
                Element root = document.getRootElement();
                Element detailElement = root.getChild("Body", soapNameSpace).getChild("FetchKYCDetailsResponse", kycNameSpace);
                Element statusElement = detailElement.getChild("Status", kycNameSpace);
                String statusCode = statusElement.getChild("StatusCode", comNameSpace).getText();
                String statusDescription = statusElement.getChild("StatusInfo", comNameSpace).getText();
                if (statusCode.equals("0")) {
                    Element customerElement = detailElement.getChild("CustomerDetails", kycNameSpace);
                    String firstName = customerElement.getChild("FirstName", kycNameSpace).getText();
                    String lastName = customerElement.getChild("LastName", kycNameSpace).getText();
                    String title = customerElement.getChild("Title", kycNameSpace).getText();
                    String gender = customerElement.getChild("Gender", kycNameSpace).getText();
                    String city = customerElement.getChild("City", kycNameSpace).getText();
                    String nationality = customerElement.getChild("Nationality", kycNameSpace).getText();
                    String email = customerElement.getChild("EmailID", kycNameSpace).getText();
                    Element idElement = detailElement.getChild("DocumentID", kycNameSpace);

                    String idDescription = idElement.getChild("IDDesc", kycNameSpace).getText();
                    String idType = idElement.getChild("IDType", kycNameSpace).getText();
                    String documentNumber = idElement.getChild("IDNumber", kycNameSpace).getText();

                    identification = new Identification();
                    identification.setFirstName(firstName);
                    identification.setLastName(lastName);
                    identification.setGender(gender);
                    identification.setDocumentNumber(documentNumber);
                    identification.setNationality(nationality);
                    log.info("KYC Response |  {}", statusCode);
                    return identification;
                } else {
                    log.info("KYC Response | {} | Description {}", statusCode, statusDescription);
                    return null;
                }
            } else {
                return null;
            }

        }
//        catch (SocketTimeoutException ex) {
//            log.error("Error | Connection fails. Unable to connect to NIDA service | {} ", ex.toString());
//            throw new ConnectionException("Error | Connection fails. Unable to connect to NIDA service");
//        }
        catch (Exception ex) {
            log.error("Error | Unable to get customer identification details | {} ", ex.toString());
            throw new ConnectionException("Error | Unable to get customer identification details");
        }

    }

    public static String getTransactionId(String msisdn) {

        Long transactionId;
        Date dt = new Date();
        long diff = 999;

        if (msisdn.length() >= 3) {
            diff = Long.parseLong(msisdn.substring(msisdn.length() - 3));
            diff = diff + System.nanoTime();
            diff = diff % 1000;
        }
        transactionId = (dt.getTime() * 1000) + diff;
        return transactionId.toString();
    }

}
