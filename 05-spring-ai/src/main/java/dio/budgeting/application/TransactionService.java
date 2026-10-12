package dio.budgeting.application;

import dio.budgeting.domain.Category;
import dio.budgeting.domain.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public BigDecimal calcularTotalPorCategoria(String categoria) {
        Category category = Category.fromString(categoria);
        return calcularTotalPorCategoria(category);
    }

    public BigDecimal calcularTotalPorCategoria(Category category) {
        long totalAmount = transactionRepository.findAllByCategory(category)
                .stream()
                .mapToLong(t -> t.getAmount())
                .sum();

        return BigDecimal.valueOf(totalAmount).setScale(2, RoundingMode.HALF_UP);
    }
}
