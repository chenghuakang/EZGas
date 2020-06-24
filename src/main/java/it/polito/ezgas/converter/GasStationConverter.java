package it.polito.ezgas.converter;

import java.util.List;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import static java.util.stream.Collectors.toList;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class GasStationConverter {
	
	
	public static GasStationDto toDto(GasStation entity) {
		GasStationDto dto = new GasStationDto();
		
		dto.setGasStationId(entity.getGasStationId());
		dto.setGasStationName(entity.getGasStationName());
		dto.setGasStationAddress(entity.getGasStationAddress());
		dto.setHasDiesel(entity.getHasDiesel());
		dto.setHasSuper(entity.getHasSuper());
		dto.setHasSuperPlus(entity.getHasSuperPlus());
		dto.setHasGas(entity.getHasGas());
		dto.setHasMethane(entity.getHasMethane());
		dto.setHasPremiumDiesel(entity.getHasPremiumDiesel());

		dto.setCarSharing(entity.getCarSharing());
		dto.setLat(entity.getLat());
		dto.setLon(entity.getLon());
		dto.setDieselPrice(entity.getDieselPrice());
		dto.setSuperPrice(entity.getSuperPrice());
		dto.setSuperPlusPrice(entity.getSuperPlusPrice());
		dto.setGasPrice(entity.getGasPrice());
		dto.setMethanePrice(entity.getMethanePrice());
		dto.setPremiumDieselPrice(entity.getPremiumDieselPrice());
		
		dto.setReportUser(entity.getReportUser());
		dto.setUserDto(entity.getUser() != null ? UserConverter.toDto(entity.getUser()): null);
		dto.setReportTimestamp(entity.getReportTimestamp());	
		dto.setReportDependability(entity.getReportDependability());
		dto.setReportDependability(dto.computeReportDependability());
		
		return dto;
	}
	
	public static List<GasStationDto> toDto(List<GasStation> entityList) {
		return entityList.stream()
				.map(entity -> GasStationConverter.toDto(entity))
				.collect(toList());
	}
	
	public static GasStation toEntity(GasStationDto dto) {
		GasStation entity = new GasStation();
		
		entity.setGasStationId(dto.getGasStationId());
		entity.setGasStationName(dto.getGasStationName());
		entity.setGasStationAddress(dto.getGasStationAddress());
		entity.setHasDiesel(dto.getHasDiesel());
		entity.setHasSuper(dto.getHasSuper());
		entity.setHasSuperPlus(dto.getHasSuperPlus());
		entity.setHasGas(dto.getHasGas());
		entity.setHasMethane(dto.getHasMethane());
		entity.setHasPremiumDiesel(dto.getHasPremiumDiesel());
		
		entity.setCarSharing(dto.getCarSharing());
		entity.setLat(dto.getLat());
		entity.setLon(dto.getLon());
		entity.setDieselPrice(dto.getDieselPrice());
		entity.setSuperPrice(dto.getSuperPrice());
		entity.setSuperPlusPrice(dto.getSuperPlusPrice());
		entity.setGasPrice(dto.getGasPrice());
		entity.setMethanePrice(dto.getMethanePrice());
		entity.setPremiumDieselPrice(dto.getPremiumDieselPrice());
		
		entity.setReportUser(dto.getReportUser());
		entity.setUser(dto.getUserDto() != null ?  UserConverter.toEntity(dto.getUserDto()): null);
		entity.setReportTimestamp(dto.getReportTimestamp());
		entity.setReportDependability(dto.getReportDependability());
		
		return entity;
	}

}


