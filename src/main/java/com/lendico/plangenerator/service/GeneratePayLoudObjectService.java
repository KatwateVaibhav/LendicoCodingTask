package com.lendico.plangenerator.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lendico.plangenerator.exception.PlanGeneratorException;
import com.lendico.plangenerator.model.RequestObject;
import com.lendico.plangenerator.model.ResponseObject;
import com.lendico.plangenerator.service.calculationbasics.CalculationBasics;
import com.lendico.plangenerator.service.calculationbasics.PlanGeneratorConstants;

/**
 * This class responsible for provide responseObject
 */
@Service
public class GeneratePayLoudObjectService implements GeneratePayLoudObjectInterface {

	private static final int POSITIVE_LOAN_AMOUNT = 0;
	private static final int POSITIVE_DURATION_IN_MONTHS = 0;
	private static final int NOMINAL_RATE = 0;
    @Autowired
     CalculationBasics calculationBasics;

    /**
     * This method provide a Collection of ResponseObject
     * @param requestObject
     * @return
     */
    @Override
    public Collection<ResponseObject> generateResponsePayLoad(RequestObject requestObject) {
        /**
         * create a loop started with 0 and ended with requestObject.getDuration()
         * this loop create responseObject for each month (for example if user desired duration = 24, then this loop create 24 responseObject )
         */   
    	checkIfValid(requestObject);
    	
        return IntStream.range(0,requestObject.getDuration())
                .boxed()
                .map(monthIndex->{
                    /**
                     * create ResponseObject for each month with this method{@link #responseObject(RequestObject, int)}
                     * monthIndex is a index for each month (started with 0 for first month and ended with (requestObject.getDuration()-1) for last month )
                     */
                    ResponseObject responseObject=responseObject(requestObject,monthIndex);
                    /**
                     * after each ResponseObject created, remainingOutstandingPrincipals and initialOutstandingPrincipal should be change for
                     * next month. in first month initialOutstandingPrincipal is user desired loanAmount (for example 5000) ,
                     * but for next month initialOutstandingPrincipal is previous remainingOutstandingPrincipals
                     * (for example after first month calculation, remainingOutstandingPrincipals=4807.65).This value should be set for next month initialOutstandingPrincipal
                     * this line set previous month remainingOutstandingPrincipals to requestObject loanAmount and send it as new initialOutstandingPrincipal with
                     * requestObject. because of we don't need previous values for requestObject(previous month responseObject created), we don't care
                     * about changed values.
                     */
                    requestObject.setLoanAmount(responseObject.getRemainingOutstandingPrincipals());
                    return  responseObject;
                }).collect(Collectors.toList());
    	}


    @Override
    public ResponseObject responseObject(RequestObject requestObject, int monthIndex) {
        
    	
    	// calculate principal 
         
        double principle=calculationBasics.principalCalculation(requestObject, monthIndex);
        
        // calculate interest
        
        double interest = calculationBasics.interestCalulation(requestObject);
        
        // generate next month Timestamp
         
        Timestamp date=calculationBasics.nextMonth(requestObject.getStartDate(),monthIndex);
        /*
         * check if principal exceeds the initialOutstandingPrincipal 
         *  principal value set to initialOutstandingPrincipal , else initialOutstandingPrincipal used for calculation
         * (normally it happen in last month)
         *
         * after this check rounded initialOutstandingPrincipal with BigDecimal.ROUND_HALF_DOWN
         */
        double roundedInitialOutstandingPrincipal=
                (requestObject.getLoanAmount()>principle)
                        ? BigDecimal.valueOf(requestObject.getLoanAmount()).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue():principle;
     
        return new ResponseObject(BigDecimal.valueOf(principle+interest).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue(), date,roundedInitialOutstandingPrincipal, interest, principle, roundedInitialOutstandingPrincipal-principle).build();
    }

	private void checkIfValid(RequestObject requestObject) {
		if (null == requestObject.getDuration() || null == requestObject.getLoanAmount() || null == requestObject.getNominalRate()) {
			throw new PlanGeneratorException(PlanGeneratorConstants.INVALID_ARGS_ERROR,	HttpStatus.BAD_REQUEST.value());
		}
		if (requestObject.getDuration() < POSITIVE_DURATION_IN_MONTHS) {
			throw new PlanGeneratorException(PlanGeneratorConstants.DURATION_VALIDATION_MSG,
					HttpStatus.BAD_REQUEST.value());
		}

		if (requestObject.getLoanAmount() < POSITIVE_LOAN_AMOUNT) {
			throw new PlanGeneratorException(PlanGeneratorConstants.LOAN_AMOUNT_VALIDATION_MSG,
					HttpStatus.BAD_REQUEST.value());
		}
		if (requestObject.getNominalRate() < NOMINAL_RATE) {
			throw new PlanGeneratorException(PlanGeneratorConstants.NOMINAL_RATE_VALIDATION_MSG,
					HttpStatus.BAD_REQUEST.value());
		}

		if (requestObject.getStartDate() == null) {
			throw new PlanGeneratorException(PlanGeneratorConstants.START_DATE_VALIDATION_MSG,
					HttpStatus.BAD_REQUEST.value());
		}
	}
}
