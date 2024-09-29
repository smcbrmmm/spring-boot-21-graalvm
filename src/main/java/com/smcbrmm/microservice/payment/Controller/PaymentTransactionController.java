package com.smcbrmm.microservice.payment.Controller;

import com.smcbrmm.microservice.payment.Constant.ApiStatus;
import com.smcbrmm.microservice.payment.Constant.Constant;
import com.smcbrmm.microservice.payment.Dto.Request;
import com.smcbrmm.microservice.payment.Dto.Response;
import com.smcbrmm.microservice.payment.Exception.ResourceNotFoundException;
import com.smcbrmm.microservice.payment.Model.Transaction;
import com.smcbrmm.microservice.payment.Service.TransactionService;
import io.micrometer.common.util.StringUtils;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payment_transaction")
@Log4j2
public class PaymentTransactionController {

    @Autowired
    TransactionService transactionService;
    //
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    Environment env;

    @GetMapping("/getAll")
    public Response<Transaction> getTransaction() {
        List<Transaction> lstOfTransaction = transactionService.getAllTransaction();

        if (CollectionUtils.isEmpty(lstOfTransaction)) {
            ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
        return new Response<>(HttpStatus.OK, ApiStatus.SUCCESS, lstOfTransaction);
    }

    @GetMapping("/getProperty/{name}")
    public Response<String> getProperty(@PathVariable String name) {
        log.info("Call to 'getPropery' with path variable : {}", name);
        if (Optional.ofNullable(name).isEmpty()) {
            return new Response<>(HttpStatus.BAD_REQUEST, ApiStatus.FAIL, List.of());
        }
        String result = Optional.ofNullable(env.getProperty(name)).orElse("Empty");
        return new Response<>(HttpStatus.OK, ApiStatus.SUCCESS, List.of(result));
    }

    @PostMapping("/transaction/save")
    public Response<String> saveTransaction(@RequestBody Request<Transaction> request, @RequestParam String userId) {
        log.info("save to transaction request : {}", request.getRequest());
        Transaction req = Transaction.builder().payerName(request.getRequest().getPayerName())
                .creditCardNo(request.getRequest().getCreditCardNo())
                .createdBy(StringUtils.isEmpty(userId) ? "Undefined" : userId)
                .createdDate(ZonedDateTime.now(ZoneId.of("Asia/Bangkok")).toLocalDateTime()).build();
        Transaction transaction = transactionService.saveTransaction(req);

        if (Optional.ofNullable(transaction).isEmpty()) {
            return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR, ApiStatus.FAIL, List.of());
        }
        return new Response<>(HttpStatus.OK, ApiStatus.SUCCESS, List.of(transaction.getId()));
    }

    @GetMapping("/transaction/findByName")
    public Response<Transaction> findByNameStartsWith(@RequestParam String prefix) {
        log.info("find payer by PayerName with prefix : {}", prefix);

        List<Transaction> lstOfTransaction = transactionService.findByPayerNameStartWith(prefix);

        if (Optional.ofNullable(lstOfTransaction).isPresent()) {
            throw new ResourceNotFoundException("Can not find payer name by prefix " + prefix);
        }

        return new Response<>(HttpStatus.OK, ApiStatus.SUCCESS, lstOfTransaction);
    }

}
