package dio.budgeting;

import dio.budgeting.application.TransactionService;
import dio.budgeting.domain.Category;
import dio.budgeting.domain.Transaction;
import dio.budgeting.domain.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionService(transactionRepository);
    }

    @Test
    void should_calculateTotalByCategory_correctly() {
        var t1 = new Transaction("Supermercado A", 50, Category.ALIMENTACAO);
        var t2 = new Transaction("Supermercado B", 75, Category.ALIMENTACAO);

        when(transactionRepository.findAllByCategory(Category.ALIMENTACAO))
                .thenReturn(List.of(t1, t2));

        BigDecimal total = transactionService.calcularTotalPorCategoria("ALIMENTACAO");

        assertThat(total).isEqualByComparingTo(new BigDecimal("125.00"));
    }

    @Test
    void should_returnZero_when_noTransactionsFound() {
        when(transactionRepository.findAllByCategory(Category.TRANSPORTE))
                .thenReturn(List.of());

        BigDecimal total = transactionService.calcularTotalPorCategoria("transporte");

        assertThat(total).isEqualByComparingTo(new BigDecimal("0.00"));
    }
}
