package com.mustycodified.Reservlyv1be.services.impl;

import com.mustycodified.Reservlyv1be.dtos.responseDtos.TransactionResponseDto;
import com.mustycodified.Reservlyv1be.entities.Transaction;
import com.mustycodified.Reservlyv1be.repositories.TransactionRepository;
import com.mustycodified.Reservlyv1be.services.TransactionService;
import com.mustycodified.Reservlyv1be.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AppUtils appUtil;
    @Override
    public TransactionResponseDto logTransaction(TransactionResponseDto transactionResponseDto) {

        if(!transactionRepository
                .existsByReferenceOrId(transactionResponseDto.getReference(), transactionResponseDto.getId())){
            Transaction transaction = appUtil.getMapper().convertValue(transactionResponseDto, Transaction.class);
            transaction.setCustomerEmail(transactionResponseDto.getCustomer().getEmail());
            transactionRepository.save(transaction);

        }

        return transactionResponseDto;    }
}
