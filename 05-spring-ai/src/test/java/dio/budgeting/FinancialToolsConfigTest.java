package dio.budgeting;

import dio.budgeting.application.TransactionService;
import dio.budgeting.application.input.ConsultaCategoriaRequest;
import dio.budgeting.infrastructure.config.FinancialToolsConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinancialToolsConfigTest {

    @Mock
    private TransactionService transactionService;

    @Test
    void should_formatTotalCorrectly_when_consultarGastosPorCategoriaCalled() {
        FinancialToolsConfig config = new FinancialToolsConfig();
        Function<ConsultaCategoriaRequest, String> tool = config.consultarGastosPorCategoria(transactionService);

        when(transactionService.calcularTotalPorCategoria("ALIMENTACAO"))
                .thenReturn(new BigDecimal("150.50"));

        String result = tool.apply(new ConsultaCategoriaRequest("ALIMENTACAO"));

        assertThat(result).startsWith("O total gasto na categoria ALIMENTACAO foi de R$");
        assertThat(result).contains("150");
    }
}
