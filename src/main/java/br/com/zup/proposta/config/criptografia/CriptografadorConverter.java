package br.com.zup.proposta.config.criptografia;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;

public class CriptografadorConverter implements AttributeConverter<String, String> {

    @Autowired
    Criptografador criptografador;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return criptografador.encode(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return criptografador.decode(dbData);
    }
}
