package vn.edu.ptit.sqa.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.ptit.sqa.model.*;
import vn.edu.ptit.sqa.repository.LoanRepository;
import vn.edu.ptit.sqa.repository.SavingRepository;
import vn.edu.ptit.sqa.service.ReportService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final LoanRepository loanRepository;
    private final SavingRepository savingRepository;

    @Override
    public GeneralReport generateGeneralReport(LocalDate from, LocalDate to) {
        NewLoanSummary loanSummary = loanRepository.summaryForNewLoan(from, to);
        NewSavingSummary savingSummary = savingRepository.summaryForNewSaving(from, to);

        return GeneralReport.builder()
            .numberOfLoan(loanSummary.getNewLoan())
            .lendingAmount(Objects.requireNonNullElse(loanSummary.getTotalAmount(), BigDecimal.ZERO))
            .numberOfSaving(savingSummary.getNewSaving())
            .savingDepositAmount(Objects.requireNonNullElse(savingSummary.getTotalAmount(), BigDecimal.ZERO))
            .build();
    }

    public LoanReport generateLoanReport(LocalDate from, LocalDate to) {
        NewLoanSummary loanSummary = loanRepository.summaryForNewLoan(from, to);
        List<LoanPurposeDistribution> loanPurposeDistributions = loanRepository.getLoanDistributionByPurpose(from, to);

        return LoanReport.builder()
            .numberOfLoan(loanSummary.getNewLoan())
            .amountForLending(Objects.requireNonNullElse(loanSummary.getTotalAmount(), BigDecimal.ZERO))
            .purposeDistribution(loanPurposeDistributions)
            .build();
    }
}
