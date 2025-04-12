package com.example.CitizenManagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import com.example.CitizenManagement.dto.ApartmentViewDto;
import com.example.CitizenManagement.dto.ApartmentViewDtoItf;
import com.example.CitizenManagement.dto.ApartmentViewDtoo;
import com.example.CitizenManagement.dto.ApartmentViewDtooItf;
import com.example.CitizenManagement.entity.Apartment;
import com.example.CitizenManagement.entity.Citizen;
import com.example.CitizenManagement.repository.ApartmentRepository;
import com.example.CitizenManagement.repository.CitizenRepository;
import com.example.CitizenManagement.service.ApartmentService;
import com.example.CitizenManagement.utils.Common;
import com.example.CitizenManagement.utils.Constant;
import com.example.CitizenManagement.utils.DataResponse;

@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ApartmentRepository apartmentRepository;
	
	@Autowired
	private CitizenRepository citizenRepository;

	@Override
	public DataResponse createApartment(Apartment a) {
		DataResponse res = new DataResponse();
		try {
			Apartment ap = apartmentRepository.getByFloorAndRoomNo(a.getFloor(), a.getRoomNo());
			if(ap != null) {
				res.setStatus(Constant.ERROR);
				res.setMessage("Căn hộ đã tồn tại");
				return res;
			} 
			apartmentRepository.save(a);
			res.setStatus(Constant.SUCCESS);
			res.setMessage("Thanh cong");
			return res;
			
		} catch (Exception e) {
			res.setStatus(Constant.ERROR);
			res.setMessage("that bai");
			return res;
		}
	}
	
	@Override
	public DataResponse getListApartment(Pageable pageable) {
		DataResponse res = new DataResponse();
		try {
			String mon = Common.getLastMonth();
			Page<ApartmentViewDtooItf> page = apartmentRepository.getListApartment(pageable, mon);
			if (page.getContent().size() == 0) {
				res.setStatus(Constant.NOT_FOUND);
				res.setMessage("Không tìm thấy danh sách ");
			} else {
				res.setStatus(Constant.SUCCESS);
				List<ApartmentViewDtoo> listDto = Common.mapList(page.getContent(), ApartmentViewDtoo.class);
                Pageable p = PageRequest.of(page.getNumber(), page.getSize(), page.getSort());
                Page<ApartmentViewDtoo> pageDto = PageableExecutionUtils.getPage(listDto, p, page::getTotalElements);
                res.setResult(pageDto);
			}
			return res;

		} catch (Exception e) {
			res.setStatus(Constant.FAIL);
			res.setMessage(Constant.ERROR);
			return res;
		}
	}
	
	/*
	 * a = 5
	 * b = 10
	 * c = a = 5
	 * a = b = 10
	 * b = c = 5
	 * 
	 * a = a + b // a = 15, b = 10
	 * b = a - b // a = 15, b = 5
	 * a = a - b // a = 10, b = 5
	 * 
	 * 
	 * a = 13
	 * b = 7
	 * 
	 * a = a + b = 20
	 * b = a - b = 13
	 * a = a - b = 7
	 * */

	
	@Override
	public DataResponse getListApartmentName() {
		DataResponse res = new DataResponse();
		try {
			List<ApartmentViewDtoItf> list = apartmentRepository.getListApartmentViewDto();
			List<ApartmentViewDto> list1 = new ArrayList<>();
			if(list != null) {
				list1 = list.stream().map(e -> mapper.map(e, ApartmentViewDto.class)).collect(Collectors.toList());
			}
			if (list == null || list.isEmpty()) {
				res.setStatus(Constant.NOT_FOUND);
				res.setMessage("Không tìm thấy danh sách");
			} else {
				res.setStatus(Constant.SUCCESS);
				res.setMessage("Thành công");
				res.setResult(list1);
			}
			return res;
		} catch (Exception e) {
			res.setStatus(Constant.FAIL);
			res.setMessage(Constant.ERROR);
			return res;
		}
	}

	@Override
	public DataResponse getApartment(Long id) {
		DataResponse res = new DataResponse();
		try {
			String mon = Common.getLastMonth();
			ApartmentViewDtooItf aDtoItf = apartmentRepository.getApartmentViewDtoo(id, mon);
			ApartmentViewDtoo aDto = new ApartmentViewDtoo();			
			if (aDtoItf == null) {
				res.setStatus(Constant.NOT_FOUND);
				res.setMessage("Không tìm thấy căn hộ");
			} else {
				aDto = mapper.map(aDtoItf, ApartmentViewDtoo.class);
				res.setStatus(Constant.SUCCESS);
				res.setMessage("Thành công");
				res.setResult(aDto);
			}
			return res;

		} catch (Exception e) {
			res.setStatus(Constant.FAIL);
			res.setMessage(Constant.ERROR);
			return res;
		}
	}

	@Override
	public DataResponse updateApartment(Apartment apartment) {
		DataResponse res = new DataResponse();
		try {
			Long id = apartment.getId();
			Apartment apartmentDB = apartmentRepository.getApartment(id);
			if(apartmentDB == null) {
				res.setStatus(Constant.ERROR);
				res.setMessage("Căn hộ không tồn tại");
				return res;
			}
			List<Citizen> citizensInApartment = citizenRepository.getCitizenByApartmentId(id);
			if(apartment.getConditions() == 0) {
				
				for(Citizen c : citizensInApartment) {
					c.setIsOwner(false);
					citizenRepository.save(c);
				}
				apartmentDB.setConditions(0);
				apartmentDB.setApartmentOwnerId(null);
				apartmentRepository.save(apartmentDB);
				
				res.setStatus(Constant.SUCCESS);
				res.setMessage("Cập nhật thành công");
				return res;
			}
			if(apartment.getApartmentOwnerId() == null) {
				res.setStatus(Constant.ERROR);
				res.setMessage("Vui lòng chọn chủ hộ");
				return res;
			}
			for(Citizen c : citizensInApartment) {
				if(c.getId() == apartment.getApartmentOwnerId()) {
					c.setIsOwner(true);
				} else {					
					c.setIsOwner(false);
				}
				citizenRepository.save(c);
			}
			apartmentDB.setConditions(apartment.getConditions());
			apartmentDB.setApartmentOwnerId(apartment.getApartmentOwnerId());
			apartmentRepository.save(apartmentDB);

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
