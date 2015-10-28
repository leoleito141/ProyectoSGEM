package com.sgem.enums.convertidores;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sgem.enums.Tipo;
 
@Converter(autoApply = true)
public class TipoConverter implements AttributeConverter<Tipo, String> {
 
    @Override
    public String convertToDatabaseColumn(Tipo attribute) {
        switch (attribute) {
            case LOGIN :
                return "LOGIN";
            case CIERRE_SESION:
                return "CIERRE_SESION";
            default:
                throw new IllegalArgumentException("Desconocido" + attribute);
        }
    }
 
    @Override
    public Tipo convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "LOGIN":
                return Tipo.LOGIN;
            case "CIERRE_SESION":
                return Tipo.CIERRE_SESION;
            default:
                throw new IllegalArgumentException("Desconocido" + dbData);
        }
    }
}