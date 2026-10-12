package dio.budgeting.infrastructure.http;

import dio.budgeting.infrastructure.http.response.VoiceCommandResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/finance")
public class FinanceVoiceController {

    private final TransactionController transactionController;

    public FinanceVoiceController(TransactionController transactionController) {
        this.transactionController = transactionController;
    }

    @PostMapping(value = "/voice", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoiceCommandResponse> processVoiceCommand(@RequestParam("file") MultipartFile file) {
        return transactionController.processVoiceCommand(file);
    }
}
