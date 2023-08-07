package com.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.account.dto.IncomeExpenseDTO;
import com.account.dto.MonthlyReport;
import com.account.model.IncomeExpense;
import com.account.service.IncomeExpenseService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class IncomeExpenseController {

	@Autowired
	IncomeExpenseService incomeExpenseService;

	@PostMapping(value = "/add", consumes = { "text/plain;charset=UTF-8",
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody IncomeExpenseDTO incomeExpenseAdd) {
		incomeExpenseService.add(incomeExpenseAdd);
		return ResponseEntity.ok("Record added successfully");
	}

	@GetMapping(value = "/list", produces = "application/json")
	public List<IncomeExpenseDTO> incomeExpenseList() {
		return incomeExpenseService.getList();
	}

	@GetMapping(value = "/getbalancesheet", produces = "application/json")
	public double balanceReport() {
		return incomeExpenseService.balanceReport();
	}

	@PostMapping(value = "/get", consumes = { "text/plain;charset=UTF-8",
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<IncomeExpense> getMonthlyRecord(@RequestBody MonthlyReport monthlyReport) {
		return incomeExpenseService.getMonthlyReport(Integer.parseInt(monthlyReport.getMonth().substring(5, 7)),
				Integer.parseInt(monthlyReport.getMonth().substring(0, 4)), monthlyReport.getType());
	}

	@PostMapping(value = "/getmonthlybalance", consumes = { "text/plain;charset=UTF-8",
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public double monthlyBalanceReport(@RequestBody MonthlyReport monthlyReport) {
		return incomeExpenseService.monthlyBalanceReport(monthlyReport);
	}

	@GetMapping(value = "/deleteById/{id}")
	public void deleteRecord(@PathVariable("id") int id) {
		incomeExpenseService.deleteRecord(id);
	}
	@PutMapping(value = "/update", consumes = { "text/plain;charset=UTF-8",
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public IncomeExpenseDTO updateRecord(@RequestBody IncomeExpenseDTO updateRecord) {
		return incomeExpenseService.update(updateRecord);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public IncomeExpenseDTO getById(@PathVariable("id") long id) {
		return incomeExpenseService.getById(id);
	}
}
