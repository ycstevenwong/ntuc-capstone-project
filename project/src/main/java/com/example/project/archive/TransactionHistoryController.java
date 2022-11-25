package com.example.project.archive;
// package com.example.project;

// import java.math.BigDecimal;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.example.project.model.Account;
// import com.example.project.model.Transaction;
// import com.example.project.repository.AccountRepo;
// import com.example.project.repository.TransactionRepo;

// @Controller
// @RequestMapping("/account/transaction")
// public class TransactionHistoryController {

//     @Autowired
//     TransactionRepo transactionRepo;

//     @Autowired
//     AccountRepo accountRepo;

//     @GetMapping("/display")
//     public String display(@Param("accountNum") Long accountNum, Model model) {
//         List<Transaction> transactionList = (List<Transaction>) transactionRepo.findAll();
//         List<Transaction> filteredList = new ArrayList<>();
//         for (Transaction transactionRecord : transactionList) {
//             if (transactionRecord.getAccount().getAccountNumber().equals(accountNum))
//                 filteredList.add(transactionRecord);
//         }
//         // fetch current account balance
//         Optional<Account> account = accountRepo.findById(accountNum);
//         BigDecimal currentBalance = account.get().getBalance();

//         model.addAttribute("currentBalance", currentBalance);
//         model.addAttribute("transactionList", filteredList);

//         return "TransactionHistory";
//     }

//     @GetMapping("/trnf")
//     public String transfer(@RequestParam("accountNum") Long accountNum, @RequestParam("asd") String asd) {
//         System.out.println(accountNum);
//         System.out.println(asd);

//         return "TransactionHistory";
//     }

// }
