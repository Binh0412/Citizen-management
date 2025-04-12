package com.example.CitizenManagement.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import com.example.CitizenManagement.dto.CitizenViewDto;
import com.example.CitizenManagement.dto.CitizenViewDtoItf;
import com.example.CitizenManagement.entity.Citizen;
import com.example.CitizenManagement.repository.ApartmentRepository;
import com.example.CitizenManagement.repository.CitizenRepository;
import com.example.CitizenManagement.service.CitizenService;
import com.example.CitizenManagement.utils.Common;
import com.example.CitizenManagement.utils.Constant;
import com.example.CitizenManagement.utils.DataResponse;

@Service
@Transactional
public class CitizenServiceImpl implements CitizenService {
	
	@Autowired
	private CitizenRepository citizenRepository;
	
	@Autowired
	private ApartmentRepository apartmentRepository;

	@Override
	public DataResponse createCitizen(Citizen c) { // 2024/07/15 --> 20240715
		c.getDateOfBirth(); // yyyy-MM-dd --> yyyyMMdd
		String DateOfBirth = Common.convertStringDate(c.getDateOfBirth(), "yyyy-MM-dd", "yyyyMMdd");
		c.setDateOfBirth(DateOfBirth);
		c.getDayIn();
		String dayIn = Common.convertStringDate(c.getDayIn(), "yyyy-MM-dd", "yyyyMMdd");
		c.setDayIn(dayIn);
		c.setIsOwner(false);
		DataResponse res = new DataResponse();
		try {
			citizenRepository.save(c); 
			res.setStatus(Constant.SUCCESS);
			res.setMessage("Thêm thành công");
			return res;
		} catch (Exception e) {
			res.setStatus(Constant.ERROR);
			res.setMessage("Thất bại");
			return res;
		}
	}

	@Override
	public DataResponse getListCitizen(Pageable pageable) {
		DataResponse res = new DataResponse();		
		try {
			Page<CitizenViewDtoItf> page = citizenRepository.getListCitizen(pageable);
			if (page.getContent().size() == 0) {
				res.setStatus(Constant.NOT_FOUND);
				res.setMessage("Không tìm thấy danh sách ");
			} else {
				res.setStatus(Constant.SUCCESS);
				List<CitizenViewDto> listDto = Common.mapList(page.getContent(), CitizenViewDto.class);
                Pageable p = PageRequest.of(page.getNumber(), page.getSize(), page.getSort());
                Page<CitizenViewDto> pageDto = PageableExecutionUtils.getPage(listDto, p, page::getTotalElements);
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
	public DataResponse getCitizenByOwnerId(Pageable pageable) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public DataResponse getCitizen(Long id) {
		DataResponse res = new DataResponse();
		try {
			Citizen c = citizenRepository.getCitizen(id);
			if (c == null) {
				res.setStatus(Constant.NOT_FOUND);
				res.setMessage("Không tìm thấy công dân");
			} else {
				res.setStatus(Constant.SUCCESS);
				res.setMessage("Thành công");
				res.setResult(c);
			}
			return res;

		} catch (Exception e) {
			res.setStatus(Constant.FAIL);
			res.setMessage(Constant.ERROR);
			return res;
		}
	}

	@Override
	public DataResponse getCitizenByApartmentId(Long apartmentId) {
		DataResponse res = new DataResponse();
		try {
			List<Citizen> list = citizenRepository.getCitizenByApartmentId(apartmentId);
			if (list == null || list.isEmpty()) {
				res.setStatus(Constant.NOT_FOUND);
				res.setMessage("Không tìm thấy thành viên trong căn hộ ");
			} else {
				res.setStatus(Constant.SUCCESS);
				res.setMessage("Thành công");
				res.setResult(list);
			}
			return res;

		} catch (Exception e) {
			res.setStatus(Constant.FAIL);
			res.setMessage(Constant.ERROR);
			return res;
		}
	}

	@Override
	public DataResponse deleteCitizen(Long id) {
		DataResponse res = new DataResponse();
		try {
			Citizen c = citizenRepository.getCitizen(id);
			if (c == null) {
				res.setStatus(Constant.NOT_FOUND);
				res.setMessage("Không tìm thấy công dân ");
				return res;
			}
			citizenRepository.deleteCitizen(id);
			res.setStatus(Constant.SUCCESS);
			res.setMessage("Xoá thành công");
			return res;

		} catch (Exception e) {
			res.setStatus(Constant.FAIL);
			res.setMessage(Constant.ERROR);
			return res;
		}
	}

	@Override
	public DataResponse updateCitizen(Citizen c) {
		DataResponse res = new DataResponse();
		try {
			Long id = c.getId();
			Citizen c1 = citizenRepository.getCitizen(id);
			if(c.getName() != null && !c.getName().equals("")) {
				c1.setName(c.getName());
			}
			if(c.getGender() != null ) {
				c1.setGender(c.getGender());
			}
			if(c.getDateOfBirth() != null && !c.getDateOfBirth().equals("")) {
				c.getDateOfBirth(); // yyyy-MM-dd --> yyyyMMdd
				String DateOfBirth = Common.convertStringDate(c.getDateOfBirth(), "yyyy-MM-dd", "yyyyMMdd");
				c.setDateOfBirth(DateOfBirth);
				c1.setDateOfBirth(c.getDateOfBirth());
			}
			if(c.getIdentificationNumber() != null && !c.getIdentificationNumber().equals("")) {
				c1.setIdentificationNumber(c.getIdentificationNumber());
			}
			if(c.getPhone() != null && !c.getPhone().equals("")) {
				c1.setPhone(c.getPhone());
			}
			if(c.getApartmentId() != null ) {
				c1.setApartmentId(c.getApartmentId());
			}
			if(c.getDayIn() != null && !c.getDayIn().equals("")) {
				c.getDayIn();
				String dayIn = Common.convertStringDate(c.getDayIn(), "yyyy-MM-dd", "yyyyMMdd");
				c.setDayIn(dayIn);
				c1.setDayIn(c.getDayIn());
			}
			if(c.getIsOwner() != null ) {
				c1.setIsOwner(c.getIsOwner());
			}
			citizenRepository.save(c1);
			res.setStatus(Constant.SUCCESS);
			res.setMessage("Thành công");
			return res;
		} catch (Exception e) {
			res.setStatus(Constant.FAIL);
			res.setMessage(Constant.ERROR);
			return res;
		}
	}

	

}
