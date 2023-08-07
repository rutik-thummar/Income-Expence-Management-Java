package com.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.account.model.IncomeExpense;

@Repository
public interface IncomeExpenseRepository extends JpaRepository<IncomeExpense, Integer> {

	@Query(value = "select sum(income_expense.amount) from account.income_expense where type=?1", nativeQuery = true)
	public Integer findByType(String typeName);
	
	@Query(value = "select sum(income_expense.amount) from account.income_expense", nativeQuery = true)
	public Integer findTotal();
	
	@Query(value = "SELECT * from account.income_expense WHERE DATE_FORMAT(trans_date, \"%m\") = ?1 AND DATE_FORMAT(trans_date, \"%Y\") = ?2  ORDER BY trans_date ASC", nativeQuery = true)
	public List<IncomeExpense> getMonthlyReport(int i, int j);
	
	@Query(value = "SELECT * from account.income_expense WHERE DATE_FORMAT(trans_date, \"%m\") = ?1 AND DATE_FORMAT(trans_date, \"%Y\") = ?2 And type=?3  ORDER BY trans_date ASC", nativeQuery = true)
	public List<IncomeExpense> getMonthlyReport(int i, int j,String type);
	
	
	@Query(value = "SELECT sum(amount) from account.income_expense WHERE DATE_FORMAT(trans_date, \"%m\") = ?1 AND DATE_FORMAT(trans_date, \"%Y\") = ?2", nativeQuery = true)
	public Double getMonthlyBalance(int i, int j);
	
	@Query(value = "SELECT sum(amount) from account.income_expense WHERE DATE_FORMAT(trans_date, \"%m\") = ?1 AND DATE_FORMAT(trans_date, \"%Y\") = ?2 And type=?3", nativeQuery = true)
	public Double getMonthlyBalance(int i, int j,String type);
	
	
	
}
	