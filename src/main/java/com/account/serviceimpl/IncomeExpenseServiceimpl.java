package com.account.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.account.dto.IncomeExpenseDTO;
import com.account.dto.MonthlyReport;
import com.account.model.IncomeExpense;
import com.account.repository.IncomeExpenseRepository;
import com.account.service.IncomeExpenseService;

@Service
public class IncomeExpenseServiceimpl implements IncomeExpenseService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired(required = false)
	private IncomeExpenseRepository incomeExpenseRepository;

	@Override
	@Transactional
	public IncomeExpenseDTO add(IncomeExpenseDTO incomeExpenseAdd) {
		IncomeExpense incomeExpense = new IncomeExpense();
		modelMapper.map(incomeExpenseAdd, incomeExpense);
		if (Integer.toString(incomeExpenseAdd.getAmount()).charAt(0) == '-') {
			incomeExpense.setType("Expense");
		} else {
			incomeExpense.setType("Income");
		}
		incomeExpenseRepository.save(incomeExpense);
		return incomeExpenseAdd;
	}

	@Override
	@Transactional
	public List<IncomeExpenseDTO> getList() {
		List<IncomeExpense> incomeExpenseList = incomeExpenseRepository.findAll(Sort.by(Direction.DESC, "trasDate"));
		return incomeExpenseList.stream().map(t -> {
			IncomeExpenseDTO incomeExpenseDto = new IncomeExpenseDTO();
			modelMapper.map(t, incomeExpenseDto);
			return incomeExpenseDto;
		}).collect(Collectors.toList());
	}

	@Override
	public Integer balanceReport() {
		return incomeExpenseRepository.findTotal();
	}

	@Override
	public double monthlyBalanceReport(MonthlyReport monthlyReport) {
		try {
			if (monthlyReport.getType().equalsIgnoreCase("All")) {
				return incomeExpenseRepository.getMonthlyBalance(
						Integer.parseInt(monthlyReport.getMonth().substring(5, 7)),
						Integer.parseInt(monthlyReport.getMonth().substring(0, 4)));
			} else {
				return incomeExpenseRepository.getMonthlyBalance(
						Integer.parseInt(monthlyReport.getMonth().substring(5, 7)),
						Integer.parseInt(monthlyReport.getMonth().substring(0, 4)), monthlyReport.getType());
			}
		} catch (NullPointerException e) {
			return 0;
		}
	}

	@Override
	public void deleteRecord(int id) {
		incomeExpenseRepository.deleteById(id);
	}

	@Override
	@Transactional
	public IncomeExpenseDTO update(IncomeExpenseDTO updateRecord) {
		IncomeExpense incomeExpense = incomeExpenseRepository.findById(updateRecord.getId()).get();
		modelMapper.map(updateRecord, incomeExpense);
		if (Integer.toString(updateRecord.getAmount()).charAt(0) == '-') {
			incomeExpense.setType("Expense");
		} else {
			incomeExpense.setType("Income");
		}
		incomeExpenseRepository.save(incomeExpense);
		modelMapper.map(incomeExpense, updateRecord);
		return updateRecord;
	}

	@Override
	public IncomeExpenseDTO getById(long id) {
		IncomeExpense incomeExpense = incomeExpenseRepository.findById((int) id).get();
		IncomeExpenseDTO income = new IncomeExpenseDTO();
		modelMapper.map(incomeExpense, income);
		return income;
	}

	@Override
	public List<IncomeExpense> getMonthlyReport(int i, int j, String type) {
		if (type.equalsIgnoreCase("All")) {
			return incomeExpenseRepository.getMonthlyReport(i, j);
		} else {
			return incomeExpenseRepository.getMonthlyReport(i, j, type);
		}
	}
}
