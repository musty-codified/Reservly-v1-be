//package com.mustycodified.Reservlyv1be.controllers;
//
//import com.mustycodified.Reservlyv1be.dtos.paystack.TransactionInitRequestDto;
//import com.mustycodified.Reservlyv1be.dtos.responseDtos.ApiResponse;
//import com.mustycodified.Reservlyv1be.dtos.responseDtos.TransactionResponseDto;
//import com.mustycodified.Reservlyv1be.services.PaymentService;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/v1/payment")
//@Tag(name = "Payment Endpoint", description = "<h3>To deposit: </h3> " +
//        "<ol>" +
//        "<li>Go to '/deposit/initiate' endpoint and enter your details. " +
//        "The <b>callback</b> and <b>metadata</b> are optional. " +
//        "just leave them the way they are</li> " +
//        "<li>Copy the payment link returned in the response object to any browser to make your payment.</li>" +
//        "<li>Copy the reference code returned if it was successful and go to '/verify/{payment_reference}' endpoint to verify if your deposit was successful</li>" +
//        "</ol> " )
// public class PaymentController {
//
//    private final PaymentService paymentService;
//    private final Logger logger = LoggerFactory.getLogger(PaymentController.class);
//
//
//    @Operation(summary = "Initiates a transaction to get a payment link")
//    @PostMapping("/deposit/initiate")
//    public ResponseEntity<ApiResponse> getPaymentUrl(@RequestBody TransactionInitRequestDto transactionDto) {
//
//        return ResponseEntity.ok(paymentService.getPaymentLink(transactionDto));
//    }
//
//    @Operation(summary = "Checks if a transaction was successful or not")
//    @GetMapping("/verify/{payment_reference}")
//    public ResponseEntity<ApiResponse<TransactionResponseDto>> confirmPayment(
//            @Parameter(description = "Use the reference number generated when the transaction was initiated")
//            @PathVariable String payment_reference) {
//
//        return ResponseEntity.ok(paymentService.verifyTransaction(payment_reference));
//    }
//
//}
