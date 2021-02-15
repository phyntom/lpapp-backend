package co.radiantmic.lpapp.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Converters {

    private static Logger logger = LoggerFactory.getLogger(Converters.class.getName());

    public static BigInteger getBigInteger(Object value) {

        BigInteger result = new BigInteger("0");
        if (value != null) {
            try {
                if (value instanceof BigInteger) {
                    result = (BigInteger) value;
                } else if (value instanceof String) {
                    result = new BigInteger((String) value);
                } else if (value instanceof Number) {
                    result = new BigInteger(String.valueOf(((Number) value)));
                } else {
                    //throw new ClassCastException("Not possible to coerce [" + value   + "] from class " + value.getClass() + " into a BigInteger.");
                    logger.debug("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigInteger.");
                }
            } catch (NumberFormatException e) {
                logger.error("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal. " + e);
            } catch (ClassCastException e) {
                logger.error("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal. " + e);
            } catch (Exception e) {
                logger.error("Exception caught. " + e);
            }
        }
        return result;
    }

    public static BigDecimal getBigDecimal(Object value) {
        BigDecimal result = new BigDecimal(0);
        if (value != null) {
            try {
                if (value instanceof BigDecimal) {
                    result = (BigDecimal) value;
                } else if (value instanceof String) {
                    result = new BigDecimal((String) value);
                } else if (value instanceof BigInteger) {
                    result = new BigDecimal((BigInteger) value);
                } else if (value instanceof Number) {
                    result = new BigDecimal(((Number) value).doubleValue());
                } else {
                    //throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
                    logger.error("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
                }
            } catch (NumberFormatException e) {
                logger.error("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal. " + e);
            } catch (ClassCastException e) {
                logger.error("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal. " + e);
            } catch (Exception e) {
                logger.error("Exception caught. " + e);
            }
        }
        return result;
    }
}
