package dio.budgeting.domain;

import java.text.Normalizer;

public enum Category {
    GROCERIES,
    PHARMA,
    AUTO,
    ALIMENTACAO,
    TRANSPORTE,
    LAZER,
    SAUDE,
    OUTROS;

    public static Category fromString(String value) {
        if (value == null || value.isBlank()) {
            return OUTROS;
        }

        String normalized = Normalizer.normalize(value.trim().toUpperCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        for (Category category : values()) {
            if (category.name().equalsIgnoreCase(normalized)) {
                return category;
            }
        }

        if (normalized.contains("ALIMENT") || normalized.contains("COMIDA") || normalized.contains("MERCADO") || normalized.contains("RESTAURANTE")) {
            return ALIMENTACAO;
        }
        if (normalized.contains("TRANSP") || normalized.contains("UBER") || normalized.contains("CARRO") || normalized.contains("GASOLINA")) {
            return TRANSPORTE;
        }
        if (normalized.contains("FARM") || normalized.contains("REMED") || normalized.contains("SAUDE") || normalized.contains("MEDIC")) {
            return SAUDE;
        }
        if (normalized.contains("LAZER") || normalized.contains("CINEMA") || normalized.contains("VIAGEM") || normalized.contains("SHOW")) {
            return LAZER;
        }

        return OUTROS;
    }
}
