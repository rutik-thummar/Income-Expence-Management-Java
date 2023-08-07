package com.account.service;

import java.util.List;

import com.account.dto.IncomeExpenseDTO;
import com.account.dto.MonthlyReport;
import com.account.model.IncomeExpense;


public interface IncomeExpenseService {

	IncomeExpenseDTO add(IncomeExpenseDTO incomeExpenseAdd);

	List<IncomeExpenseDTO> getList();
	
	Integer balanceReport();
	
	double monthlyBalanceReport(MonthlyReport monthlyReport);
	
	List<IncomeExpense> getMonthlyReport(int i, int j, String type) ;
	
	void deleteRecord(int id);
	
	IncomeExpenseDTO update(IncomeExpenseDTO incomeExpense);
	
	IncomeExpenseDTO getById(long id);
}
