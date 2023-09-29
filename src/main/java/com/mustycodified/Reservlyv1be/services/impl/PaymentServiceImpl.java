//package com.mustycodified.Reservlyv1be.services.impl;
//
//import com.mustycodified.Reservlyv1be.dtos.paystack.TransactionInitRequestDto;
//import com.mustycodified.Reservlyv1be.dtos.responseDtos.ApiResponse;
//import com.mustycodified.Reservlyv1be.dtos.responseDtos.TransactionResponseDto;
//import com.mustycodified.Reservlyv1be.enums.TransactionType;
//import com.mustycodified.Reservlyv1be.repositories.TransactionRepository;
//import com.mustycodified.Reservlyv1be.repositories.WalletRepository;
//import com.mustycodified.Reservlyv1be.services.PaymentService;
//import com.mustycodified.Reservlyv1be.services.TransactionService;
//import com.mustycodified.Reservlyv1be.services.WalletService;
//import com.mustycodified.Reservlyv1be.utils.AppUtils;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import javax.servlet.http.HttpServletRequest;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.Objects;
//
//
//@Service
//@RequiredArgsConstructor
//public class PaymentServiceImpl implements PaymentService {
//    @Value("${paystack.SecretKey}")
//    private String apiKey;
//    private final RestTemplate restTemplate;
//    private final AppUtils appUtil;
//    private final TransactionRepository transactionRepository;
//    private final TransactionService transactionService;
//    private final WalletService walletService;
//    private HttpServletRequest servletRequest;
//
//    private final WalletRepository walletRepository;
//
//    private final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
//
//    @Override
//    public ApiResponse getPaymentLink(TransactionInitRequestDto transactionDto) {
//        String url = "https://api.paystack.co/transaction/initialize";
//
//        transactionDto.setAmount(transactionDto.getAmount() + "00");
//        transactionDto.setEmail(transactionDto.getEmail());
////        transactionDto.setCallback_url("http://" + servletRequest.getServerName() + ":8080" + "/verify/{payment_reference}");
//
//        //Set authorization header for querying Paystack's end points
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + apiKey);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        logger.info("Secret key is: " + apiKey);
//
//        HttpEntity<TransactionInitRequestDto> entity = new HttpEntity<>(transactionDto, headers);
//
//        return restTemplate.exchange(url, HttpMethod.POST, entity, ApiResponse.class).getBody();
//
//    }
//
//    @Override
//    public ApiResponse<TransactionResponseDto> verifyTransaction(String payment_reference) {
//        String url = "https://api.paystack.co/transaction/verify/" + payment_reference;
//
//        //Set authorization header for querying Paystack's end points
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + apiKey);
//        HttpEntity entity = new HttpEntity<>(headers);
//
//        ResponseEntity<ApiResponse> transactionResponseDto =
//                restTemplate.exchange(url, HttpMethod.GET, entity, ApiResponse.class);
//
//        TransactionResponseDto responseData = appUtil.getMapper().convertValue(Objects
//                .requireNonNull(transactionResponseDto.getBody()).getData(), TransactionResponseDto.class);
//        responseData.setAmount(responseData.getAmount().divide(new BigDecimal(100), 2, RoundingMode.DOWN));
//
//        //if transaction is not logged, log it...
//        if (!transactionRepository
//                .existsByReferenceOrId(responseData.getReference(), responseData.getId())) {
//
//            if (responseData.getStatus().equalsIgnoreCase("Success")) {
//                walletService.updateWallet(responseData.getCustomer().getEmail(), responseData.getAmount());
//            }
//
//            //save transaction to DB for transaction history purposes
//            responseData.setTransactionType(TransactionType.TRANSACTION_TYPE_DEPOSIT.getTransaction());
//            logger.info("Transaction type: " + TransactionType.TRANSACTION_TYPE_DEPOSIT.getTransaction());
//            transactionService.logTransaction(responseData);
//        }
//
//        return new ApiResponse<>(responseData.getGateway_response(),
//                responseData.getStatus().equalsIgnoreCase("success"),responseData);    }
//
//}