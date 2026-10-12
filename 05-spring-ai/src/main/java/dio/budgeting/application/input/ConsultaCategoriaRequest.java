package dio.budgeting.application.input;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record ConsultaCategoriaRequest(
    @JsonPropertyDescription("A categoria da transação para busca, ex: ALIMENTACAO, TRANSPORTE, LAZER, GROCERIES, PHARMA, AUTO")
    String categoria
) {}
