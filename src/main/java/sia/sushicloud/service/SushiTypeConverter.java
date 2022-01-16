package sia.sushicloud.service;

import sia.sushicloud.model.SushiType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SushiTypeConverter implements AttributeConverter<SushiType, String> {

    @Override
    public String convertToDatabaseColumn(SushiType sushiType) {
        if (sushiType == null) {
            return null;
        }
        return sushiType.toString();
    }

    @Override
    public SushiType convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return SushiType.valueOf(s);
    }
}
