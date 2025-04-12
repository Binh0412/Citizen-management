package com.example.CitizenManagement.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import com.example.CitizenManagement.dto.ExpenseViewDto;
import com.example.CitizenManagement.dto.ExpenseViewDtoItf;
import com.example.CitizenManagement.entity.Apartment;
import com.example.CitizenManagement.entity.Expense;
import com.example.CitizenManagement.repository.ApartmentRepository;
import com.example.CitizenManagement.repository.ExpenseRepository;
import com.example.CitizenManagement.service.ExpenseService;
import com.example.CitizenManagement.utils.Common;
import com.example.CitizenManagement.utils.Constant;
import com.example.CitizenManagement.utils.DataResponse;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepository;	
	
	@Autowired
	private ApartmentRepository apartmentRepository;

	@Override
	public DataResponse createExpense(Expense ex) {
		DataResponse res = new DataResponse();
		try {
			Apartment a = apartmentRepository.getApartment(ex.getApartmentId());
			if (a == null) {
				res.setStatus(Constant.NOT_FOUND);
				res.setMessage("Không tìm thấy căn hộ");
				return res;
			}

			LocalDate currentDate = LocalDate.now();
	        LocalDate previousMonthDate = currentDate.minusMonths(1);
	        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMM");
	        String previousMonth = previousMonthDate.format(dateFormatter);
	        // select invoice by id căn hộ and previousMonth --> nếu có thì có nghĩa là có hóa đơn rồi --> báo lỗi
	        Expense expenseDB = expenseRepository.getExpensebyApartmentIdAndMonth(ex.getApartmentId(), previousMonth);
	        if(expenseDB != null) {
	        	res.setStatus(Constant.NOT_FOUND);
				res.setMessage("Hoá đơn đã tồn tại");
				return res;
	        } 
        	ex.setMon(previousMonth);
	        ex.setGarbageExpense(BigDecimal.valueOf(20000));
	       
	        if(a.getConditions() == 1) {
	        	ex.setManagementExpense(BigDecimal.valueOf(100000));
	        }
	        if(a.getConditions() == 2) {
	        	ex.setManagementExpense(BigDecimal.valueOf(5000000));
	        }	   
	        BigDecimal totalExpense = ex.getElectricityExpense().add(ex.getWaterExpense()).add(ex.getGarbageExpense()).add(ex.getManagementExpense());
	        ex.setTotalExpense(totalExpense);
	        ex.setConditions(false);
			expenseRepository.save(ex);
			res.setStatus(Constant.SUCCESS);
			res.setMessage("Tạo thành công");
	        
		} catch (Exception e) {
			res.setStatus(Constant.ERROR);
			res.setMessage("Tạo thất bại");
		}
		return res;
	}

	@Override
	public DataResponse getListExpense(Pageable pageable) {
		DataResponse res = new DataResponse();		
		try {
			Page<ExpenseViewDtoItf> page = expenseRepository.getListExpense(pageable);
			if (page.getContent().size() == 0) {
				res.setStatus(Constant.NOT_FOUND);
				res.setMessage("Không tìm thấy danh sách ");
			} else {
				res.setStatus(Constant.SUCCESS);
				List<ExpenseViewDto> listDto = Common.mapList(page.getContent(), ExpenseViewDto.class);
                Pageable p = PageRequest.of(page.getNumber(), page.getSize(), page.getSort());
                Page<ExpenseViewDto> pageDto = PageableExecutionUtils.getPage(listDto, p, page::getTotalElements);
                res.setResult(pageDto);
			}
			return res;

		} catch (Exception e) {
			res.setStatus(Constant.FAIL);
			res.setMessage(Constant.ERROR);
			return res;
		}
	}
	
	@Override
	public DataResponse getExpense(Long id) {
		DataResponse res = new DataResponse();
		try {
			Expense e = expenseRepository.getExpense(id);
			if (e == null) {
				res.setStatus(Constant.NOT_FOUND);
				res.setMessage("Không tìm thấy");
			} else {
				res.setStatus(Constant.SUCCESS);
				res.setMessage("Thành công");
				res.setResult(e);
			}
			return res;

		} catch (Exception e) {
			res.setStatus(Constant.FAIL);
			res.setMessage(Constant.ERROR);
			return res;
		}
	}

	@Override
	public DataResponse updateExpense(Expense e) {
		DataResponse res = new DataResponse();
		try {
			Long id = e.getId();
			Expense expenseDB = expenseRepository.getExpense(id);
			expenseDB.setConditions(true);
			expenseRepository.save(expenseDB);
			res.setStatus(Constant.SUCCESS);
			res.setMessage("Thành công");
			return res;
		} catch (Exception e2) {
			res.setStatus(Constant.FAIL);
			res.setMessage(Constant.ERROR);
			return res;
		}
	}
	
	
	public int add(int a, int b) {
		return a+b;
	}
	
	public int add(int a, int b, int c) {
		return a+b+c;
	}

}
