package dio.budgeting.infrastructure.config;

import dio.budgeting.application.TransactionService;
import dio.budgeting.application.input.ConsultaCategoriaRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.math.BigDecimal;
import java.util.function.Function;

@Configuration
public class FinancialToolsConfig {

    @Bean
    @Description("Busca o valor total de despesas cadastradas para uma categoria específica")
    public Function<ConsultaCategoriaRequest, String> consultarGastosPorCategoria(TransactionService service) {
        return request -> {
            BigDecimal total = service.calcularTotalPorCategoria(request.categoria());
            return String.format("O total gasto na categoria %s foi de R$ %.2f", request.categoria(), total);
        };
    }
}
