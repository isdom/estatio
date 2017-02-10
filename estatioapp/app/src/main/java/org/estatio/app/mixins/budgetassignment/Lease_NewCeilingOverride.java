package org.estatio.app.mixins.budgetassignment;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.joda.time.LocalDate;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.SemanticsOf;

import org.estatio.budgetassignment.dom.override.BudgetOverrideForMax;
import org.estatio.budgetassignment.dom.override.BudgetOverrideRepository;
import org.estatio.budgetassignment.dom.override.BudgetOverrideType;
import org.estatio.budget.dom.budgetcalculation.BudgetCalculationType;
import org.estatio.dom.charge.Charge;
import org.estatio.dom.lease.Lease;

@Mixin
public class Lease_NewCeilingOverride {

    private final Lease lease;
    public Lease_NewCeilingOverride(Lease lease){
        this.lease = lease;
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(contributed = Contributed.AS_ACTION)
    public BudgetOverrideForMax newCeiling(
            final BigDecimal maxValue,
            @Parameter(optionality = Optionality.OPTIONAL)
            final LocalDate startDate,
            @Parameter(optionality = Optionality.OPTIONAL)
            final LocalDate endDate,
            final Charge invoiceCharge,
            @Parameter(optionality = Optionality.OPTIONAL)
            final Charge incomingCharge,
            @Parameter(optionality = Optionality.OPTIONAL)
            final BudgetCalculationType type
    ) {
        return budgetOverrideRepository.newBudgetOverrideForMax(maxValue,lease,startDate,endDate,invoiceCharge,incomingCharge,type,BudgetOverrideType.CEILING.reason);
    }

    public String validateNewCeiling(
            final BigDecimal maxValue,
            final LocalDate startDate,
            final LocalDate endDate,
            final Charge invoiceCharge,
            final Charge incomingCharge,
            final BudgetCalculationType type
    ){
        return budgetOverrideRepository.validateNewBudgetOverride(lease, startDate, endDate, invoiceCharge, incomingCharge, type, BudgetOverrideType.CEILING.reason);
    }

    @Inject
    private BudgetOverrideRepository budgetOverrideRepository;

}
