package com;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.account.dto.IncomeExpenseDTO;
import com.account.dto.MonthlyReport;
import com.account.model.IncomeExpense;
import com.account.service.IncomeExpenseService;



@SpringBootTest
class IncomeExpenceApplicationTests {
	
	@Autowired
	IncomeExpenseService expenseService;

	@Test
	void contextLoads() {
	}
	
	@Test
	@Disabled 
	public void addTest() {
		IncomeExpenseDTO expense=new IncomeExpenseDTO();
		expense.setName("Dhaval");
		expense.setTrasDate(new Date(2022,10,10));
		expense.setType("Income");
		expense.setAmount(3500);
		IncomeExpenseDTO expenseDTO = expenseService.add(expense);
		 assertThat(expenseDTO.getAmount()).isEqualTo(3500);
	}
	
	@Test
	@Disabled 
	void getListTest() {
		boolean actualResult = false;
		List<IncomeExpenseDTO> EmployeeList = expenseService.getList();
		if (EmployeeList.size() != 0) {
			actualResult = true;
		}
		assertThat(actualResult).isTrue();
	}

	@Test
	@Disabled 
    public void balanceReportTest() {
        Integer result = expenseService.balanceReport();
        int excepetedResult = 14010;
		assertThat(result).isEqualTo(excepetedResult);
    }

	@Test
	@Disabled 
    public void monthlyBalanceReportTest() {
		MonthlyReport monthlyReport = new MonthlyReport();
		monthlyReport.setType("Income");
		monthlyReport.setMonth("2023-08");
		double result = expenseService.monthlyBalanceReport(monthlyReport);
		assertThat(result).isEqualTo(33.0);
    }
	
	@Test
	@Disabled 
	void getMonthlyReportTest() {
		List<IncomeExpense> EmployeeList = expenseService.getMonthlyReport(8, 2023, "income");
     	assertThat(EmployeeList.isEmpty()).isTrue();
	}

	@Test
	@Disabled 
	void updateTest() {
		IncomeExpenseDTO expenseDTO = new IncomeExpenseDTO();
		expenseDTO.setId(430);
		expenseDTO.setName("testing");
		expenseDTO.setTrasDate(new Date(2022,10,10));
		expenseDTO.setType("Income");
		expenseDTO.setAmount(3600);
		IncomeExpenseDTO flag = expenseService.update(expenseDTO);
		assertThat(flag.getId()).isEqualTo(430);
	}
	
	
	@Test
	@Disabled
	void getByIdTest() {
		IncomeExpenseDTO expenseDTO = expenseService.getById(430);
		assertThat(expenseDTO.getAmount()).isEqualTo(3600);
	}


	
}
