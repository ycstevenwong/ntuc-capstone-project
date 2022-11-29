package com.example.project.serviceImpl;

import com.example.project.model.Account;
import com.example.project.model.DormantAccount;
import com.example.project.repository.DormantAccountRepo;
import com.example.project.service.DormantAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
public class DormantAccountServiceImpl implements DormantAccountService {

    @Autowired
    private DormantAccountRepo dormantAccountRepo;

    @Override
    @Transactional
    public DormantAccount moveClosedAccountInfoIntoDormant(Account account) {
        DormantAccount dormantAccount = new DormantAccount();
        dormantAccount.setDormantAccountNumber(account.getAccountNumber());
        dormantAccount.setId(account.getCustomer().getId());
        dormantAccount.setStatus("CLOSED");
        dormantAccountRepo.saveAndFlush(dormantAccount);
        return dormantAccount;
    }
}
