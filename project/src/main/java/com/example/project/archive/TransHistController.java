package com.example.project.archive;
// package com.example.project;

// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;

// @Controller
// @RequestMapping("/transhist")
// public class TransHistController {
//    @Autowired
//    TransHistRepository transHistRepo;
//
//    /*
//     * Method to merge all transaction history for targeted account
//     * Map to TransactionHist Page
//     * Example URL: http://localhost:8080/transhist/display?accountNum=67890
//     */
//    @GetMapping("/display")
//    public String display(@Param("accountNum") Long accountNum, Model model) {
//        List<Transaction> depositList = displayDeposit(accountNum);
//        List<Transaction> withdrawList = displayWithdrawal(accountNum);
//
//        List<Transaction> mergedList = new ArrayList<>(depositList);
//        // TODO can add sort by date
//        mergedList.addAll(withdrawList);
//
//        model.addAttribute("transactionList", mergedList);
//        model.addAttribute("depositList", depositList);
//        model.addAttribute("withdrawList", withdrawList);
//
//        return "TransactionHist";
//    }
//
//    /*
//     * End point for testing ONLY retrieving deposit transaction history
//     * Example URL: http://localhost:8080/transhist/test/deposit?accountNum=67890
//     * Return ResponseBody
//     */
//    @GetMapping("/test/deposit")
//    @ResponseBody
//    public List<Transaction> displayDeposit(@Param("accountNum") Long accountNum) {
//        List<Transaction> depositList = (List<Transaction>) transHistRepo.findAll();
//        List<Transaction> depositAccount = new ArrayList<>();
//
//        for (Transaction transaction : depositList) {
//            if (transaction.getToAccount().equals(accountNum))
//                depositAccount.add(transaction);
//        }
//        return depositAccount;
//    }
//
//    /*
//     * End point for testing ONLY retrieving withdrawals transaction history
//     * Example URL: http://localhost:8080/transhist/test/withdraw?accountNum=12345
//     * Return ResponseBody
//     */
//    @GetMapping("/test/withdraw")
//    @ResponseBody
//    public List<Transaction> displayWithdrawal(@Param("accountNum") Long accountNum) {
//        List<Transaction> withdrawalList = (List<Transaction>) transHistRepo.findAll();
//        List<Transaction> withdrawalAccount = new ArrayList<>();
//
//        for (Transaction transaction : withdrawalList) {
//
//            if (transaction.getFromAccount().equals(accountNum))
//                withdrawalAccount.add(transaction);
//        }
//        return withdrawalAccount;
//    }
// }
