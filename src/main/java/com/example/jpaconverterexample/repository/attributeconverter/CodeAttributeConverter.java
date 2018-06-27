package com.example.jpaconverterexample.repository.attributeconverter;

import com.example.jpaconverterexample.config.CodeConverterConfig;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CodeAttributeConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String entityAttributeValue) {
        return CodeConverterConfig.prefix.concat(entityAttributeValue);
    }

    @Override
    public String convertToEntityAttribute(String databaseColumnValue) {
        return databaseColumnValue.replace(CodeConverterConfig.prefix,"");
    }
}
