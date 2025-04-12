package com.example.CitizenManagement.service.impl;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import com.example.CitizenManagement.dto.VehicleViewDto;
import com.example.CitizenManagement.dto.VehicleViewItfDto;
import com.example.CitizenManagement.entity.Vehicle;
import com.example.CitizenManagement.repository.VehicleRepository;
import com.example.CitizenManagement.service.VehicleService;
import com.example.CitizenManagement.utils.Common;
import com.example.CitizenManagement.utils.Constant;
import com.example.CitizenManagement.utils.DataResponse;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	private VehicleRepository vehicleRepository;
	

	@Override
	public DataResponse createVehicle(Vehicle v) {
		DataResponse res = new DataResponse();
		try {
			Vehicle v1 = vehicleRepository.getByParkLocation(v.getParkLocation());
			if(v1 != null) {
				res.setStatus(Constant.ERROR);
				res.setMessage("Vị trí đỗ xe đã được sử dụng");
				return res;
			} 
			vehicleRepository.save(v); 
			res.setStatus(Constant.SUCCESS);
			res.setMessage("Thêm phương tiện thành công");
			return res;
		} catch (Exception e) {
			res.setStatus(Constant.ERROR);
			res.setMessage("that bai");
			return res;
		}
	}

	@Override
	public DataResponse getListVehicle(Pageable pageable) {
		DataResponse res = new DataResponse();		
		try {
			Page<VehicleViewItfDto> page = vehicleRepository.getListVehicle(pageable);
			if (page.getContent().size() == 0) {
				res.setStatus(Constant.NOT_FOUND);
				res.setMessage("Không tìm thấy danh sách ");
			} else {
				res.setStatus(Constant.SUCCESS);
				List<VehicleViewDto> listDto = Common.mapList(page.getContent(), VehicleViewDto.class);
                Pageable p = PageRequest.of(page.getNumber(), page.getSize(), page.getSort());
                Page<VehicleViewDto> pageDto = PageableExecutionUtils.getPage(listDto, p, page::getTotalElements);
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
	public DataResponse getListVehicle1() {
		DataResponse res = new DataResponse();
		try {
			List<Vehicle> list = vehicleRepository.getListVehicle();
			if (list == null || list.isEmpty()) {
				res.setStatus(Constant.NOT_FOUND);
				res.setMessage("Không tìm thấy danh sách ");
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
	public DataResponse getVehicle(Long id) {
		DataResponse res = new DataResponse();
		try {
			Vehicle v = vehicleRepository.getVehicle(id);
			if (v == null) {
				res.setStatus(Constant.NOT_FOUND);
				res.setMessage("Không tìm thấy phương tiện");
			} else {
				res.setStatus(Constant.SUCCESS);
				res.setMessage("Thành công");
				res.setResult(v);
			}
			return res;

		} catch (Exception e) {
			res.setStatus(Constant.FAIL);
			res.setMessage(Constant.ERROR);
			return res;
		}
	}
	
	@Override
	public DataResponse getVehicleByOwnerId(Long vehicleOwnerId) {
		DataResponse res = new DataResponse();
		try {
			List<Vehicle> list = vehicleRepository.getVehicleByOwnerId(vehicleOwnerId);
			if (list == null || list.isEmpty()) {
				res.setStatus(Constant.NOT_FOUND);
				res.setMessage("Không tìm thấy danh sách ");
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

//	@Override
//	public DataResponse getVehicleByApartmentId(Long apartmentId) {
//		DataResponse res = new DataResponse();
//		try {
//			List<Vehicle> list = vehicleRepository.getVehicleByApartmentId(apartmentId);
//			if (list == null || list.isEmpty()) {
//				res.setStatus(Constant.NOT_FOUND);
//				res.setMessage("Không tìm thấy danh sách ");
//			} else {
//				res.setStatus(Constant.SUCCESS);
//				res.setMessage("Thành công");
//				res.setResult(list);
//			}
//			return res;
//
//		} catch (Exception e) {
//			res.setStatus(Constant.FAIL);
//			res.setMessage(Constant.ERROR);
//			return res;
//		}
//	}

	@Override
	public DataResponse deleteVehicle(Long id) {
		DataResponse res = new DataResponse();
		try {
			Vehicle v = vehicleRepository.getVehicle(id);
			if (v == null) {
				res.setStatus(Constant.NOT_FOUND);
				res.setMessage("Không tìm thấy công dân ");
				return res;
			}
			vehicleRepository.deleteVehicle(id);
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
	public DataResponse updateVehicle(Vehicle v) {
		DataResponse res = new DataResponse();
		try {
			Long id = v.getId();
			Vehicle v1 = vehicleRepository.getVehicle(id);
			if(v.getVehicleOwnerId() != null) {
				v1.setVehicleOwnerId(v.getVehicleOwnerId());
			}
			if(v.getLicensePlate() != null && !v.getLicensePlate().equals("")) {
				v1.setLicensePlate(v.getLicensePlate());
			}			
			if(v.getVehicleType() != null) {
				v1.setVehicleType(v.getVehicleType());
			}
			if(v.getParkLocation() != null && !v.getParkLocation().equals("")) {
				v1.setParkLocation(v.getParkLocation());;
			}
			vehicleRepository.save(v1);
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
