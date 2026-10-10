package ru.productstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.productstar.model.Transaction;
import ru.productstar.service.TransactionService;

@Controller
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/summary")
    @ResponseBody
    public String summary() {
        StringBuilder result = new StringBuilder();

        result.append("<h2>Transactions</h2>");
        result.append("<ul>");

        for (Transaction transaction : transactionService.getAll()) {
            result.append("<li>")
                    .append(transaction.getType())
                    .append(": ")
                    .append(transaction.getName())
                    .append(" (")
                    .append(transaction.getSum())
                    .append(")")
                    .append("</li>");
        }

        result.append("</ul>");
        result.append("<b>Balance: ")
                .append(transactionService.getBalance())
                .append("</b>");

        return result.toString();
    }

    @PostMapping("/expenses/add")
    public String addExpense(@RequestParam String name,
                             @RequestParam int sum) {

        transactionService.addExpense(name, sum);

        return "redirect:/summary";
    }

    @PostMapping("/incomes/add")
    public String addIncome(@RequestParam String name,
                            @RequestParam int sum) {

        transactionService.addIncome(name, sum);

        return "redirect:/summary";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/summary";
    }


}