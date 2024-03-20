package vn.edu.ptit.sqa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.sqa.dto.CustomerLoanListing;
import vn.edu.ptit.sqa.service.LoanService;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;
    @GetMapping("/customers/{id}")
    public CustomerLoanListing getCustomerLoans(@PathVariable Long id) {
        return loanService.getCustomerLoans(id);
    }
}
