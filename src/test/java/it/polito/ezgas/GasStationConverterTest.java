package it.polito.ezgas;
import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
public class GasStationConverterTest {
	private Integer gasStationId, gasStationId2;
    private String gasStationName="GAS STATION NAME";
    private String gasStationAddress="GAS STATION ADDRESS";
    private boolean hasDiesel=true;
    private boolean hasSuper=true;
    private boolean hasSuperPlus=true;
    private boolean hasGas=true;
    private boolean hasMethane=true;
    private boolean hasPremiumDiesel=true;
    
    private String carSharing="CAR SHARING";
    private double lat=123.321;
    private double lon=546.234;
    private double dieselPrice=56.98;
    private double superPrice=23.67;
    private double superPlusPrice=99.1;
    private double gasPrice=32.33;
    private double methanePrice=65.78;
    private double premiumDieselPrice=33.72;
    private Integer reportUser=735;
    private String reportTimestamp="05-11-2020";
    private double reportDependability=0;
	
    private String correctString;
    
    @Test
    public void testGasStationConverter(){
    	GasStationConverter object = new GasStationConverter();
    }
	
    @Test
	public void testToDto() {
		GasStation gasStation = new GasStation(gasStationName,gasStationAddress,hasDiesel,hasSuper, 
				hasSuperPlus, hasGas, hasMethane, hasPremiumDiesel, carSharing, lat, lon ,
				dieselPrice,superPrice,superPlusPrice,gasPrice,methanePrice, premiumDieselPrice, reportUser,
				reportTimestamp,reportDependability);
		gasStationId = gasStation.getGasStationId();
		
		GasStationDto gasStationDto = GasStationConverter.toDto(gasStation);
		correctString = "\n{\n"
				+ "gasStationId = " + this.gasStationId + ",\n"
				+ "gasStationName = " + this.gasStationName + ",\n"
				+ "gasStationAddress = " + this.gasStationAddress + ",\n"
				+ "hasDiesel = " + this.hasDiesel + ",\n"
				+ "hasSuper = " + this.hasSuper + ",\n"
				+ "hasSuperPlus = " + this.hasSuperPlus + ",\n"
				+ "hasGas = " + this.hasGas + ",\n"
				+ "hasMethane = " + this.hasMethane + ",\n"
				+ "hasPremiumDiesel = " + this.hasPremiumDiesel + ",\n"
				+ "carSharing = " + this.carSharing + ",\n"
				+ "lat = " + this.lat + ",\n"
				+ "lon = " + this.lon + ",\n"
				+ "dieselPrice = " + this.dieselPrice + ",\n"
				+ "superPrice = " + this.superPrice + ",\n"
				+ "superPlusPrice = " + this.superPlusPrice + ",\n"
				+ "gasPrice = " + this.gasPrice + ",\n"
				+ "methanePrice = " + this.methanePrice + ",\n"
				+ "premiumDieselPrice = " + this.premiumDieselPrice + ",\n"
				+ "reportUser = " + this.reportUser + ",\n"
				+ "userDto = " + null + ",\n"
				+ "reportTimestamp = " + this.reportTimestamp + ",\n"
				+ "reportDependability = " + computeReportDependability() + ",\n"
				+ "}\n";
		assertEquals(correctString,gasStationDto.toString());
	}
    
    @Test
	public void testToDtoWithUser() {
		GasStation gasStation = new GasStation(gasStationName,gasStationAddress,hasDiesel,hasSuper, 
				hasSuperPlus, hasGas, hasMethane, hasPremiumDiesel, carSharing, lat, lon ,
				dieselPrice,superPrice,superPlusPrice,gasPrice,methanePrice, premiumDieselPrice, reportUser,
				reportTimestamp,reportDependability);
		gasStationId = gasStation.getGasStationId();
		User user = new User("userName", "password", "email", 0);
		gasStation.setUser(user);
		GasStationDto gasStationDto = GasStationConverter.toDto(gasStation);
		correctString = "\n{\n"
				+ "gasStationId = " + this.gasStationId + ",\n"
				+ "gasStationName = " + this.gasStationName + ",\n"
				+ "gasStationAddress = " + this.gasStationAddress + ",\n"
				+ "hasDiesel = " + this.hasDiesel + ",\n"
				+ "hasSuper = " + this.hasSuper + ",\n"
				+ "hasSuperPlus = " + this.hasSuperPlus + ",\n"
				+ "hasGas = " + this.hasGas + ",\n"
				+ "hasMethane = " + this.hasMethane + ",\n"
				+ "hasPremiumDiesel = " + this.hasPremiumDiesel + ",\n"
				+ "carSharing = " + this.carSharing + ",\n"
				+ "lat = " + this.lat + ",\n"
				+ "lon = " + this.lon + ",\n"
				+ "dieselPrice = " + this.dieselPrice + ",\n"
				+ "superPrice = " + this.superPrice + ",\n"
				+ "superPlusPrice = " + this.superPlusPrice + ",\n"
				+ "gasPrice = " + this.gasPrice + ",\n"
				+ "methanePrice = " + this.methanePrice + ",\n"
				+ "premiumDieselPrice = " + this.premiumDieselPrice + ",\n"
				+ "reportUser = " + this.reportUser + ",\n"
				+ "userDto = " + gasStationDto.getUserDto() + ",\n"
				+ "reportTimestamp = " + this.reportTimestamp + ",\n"
				+ "reportDependability = " + gasStationDto.getReportDependability() + ",\n"
				+ "}\n";
		assertEquals(correctString,gasStationDto.toString());
	}
    
    @Test
	public void testToEntity() {
		GasStationDto gasStationDto = new GasStationDto(123,gasStationName,gasStationAddress,hasDiesel,hasSuper, 
				hasSuperPlus, hasGas, hasMethane, hasPremiumDiesel, carSharing, lat, lon ,
				dieselPrice,superPrice,superPlusPrice,gasPrice,methanePrice, premiumDieselPrice, reportUser,
				reportTimestamp,reportDependability);
		GasStation gasStation = GasStationConverter.toEntity(gasStationDto);
		correctString = "\n{\n"
				+ "gasStationId = " + gasStation.getGasStationId() + ",\n"
				+ "gasStationName = " + gasStation.getGasStationName() + ",\n"
				+ "gasStationAddress = " + gasStation.getGasStationAddress() + ",\n"
				+ "hasDiesel = " + gasStation.getHasDiesel() + ",\n"
				+ "hasSuper = " + gasStation.getHasSuper()+ ",\n"
				+ "hasSuperPlus = " + gasStation.getHasSuperPlus() + ",\n"
				+ "hasGas = " + gasStation.getHasGas() + ",\n"
				+ "hasMethane = " + gasStation.getHasMethane() + ",\n"
				+ "hasPremiumDiesel = " + gasStation.getHasPremiumDiesel() + ",\n"
				+ "carSharing = " + gasStation.getCarSharing() + ",\n"
				+ "lat = " + gasStation.getLat() + ",\n"
				+ "lon = " + gasStation.getLon() + ",\n"
				+ "dieselPrice = " + gasStation.getDieselPrice() + ",\n"
				+ "superPrice = " + gasStation.getSuperPrice() + ",\n"
				+ "superPlusPrice = " + gasStation.getSuperPlusPrice() + ",\n"
				+ "gasPrice = " + gasStation.getGasPrice() + ",\n"
				+ "methanePrice = " + gasStation.getMethanePrice() + ",\n"
				+ "premiumDieselPrice = " + gasStation.getPremiumDieselPrice() + ",\n"
				+ "reportUser = " + gasStation.getReportUser() + ",\n"
				+ "userDto = " + null + ",\n"
				+ "reportTimestamp = " + gasStation.getReportTimestamp() + ",\n"
				+ "reportDependability = " + gasStation.getReportDependability() + ",\n"
				+ "}\n";
		assertEquals(correctString, gasStationDto.toString());
	}
	
    @Test
   	public void testToEntityWithUser() {
    	GasStationDto gasStationDto = new GasStationDto(123,gasStationName,gasStationAddress,hasDiesel,hasSuper, 
				hasSuperPlus, hasGas, hasMethane, hasPremiumDiesel, carSharing, lat, lon ,
				dieselPrice,superPrice,superPlusPrice,gasPrice,methanePrice, premiumDieselPrice, reportUser,
				reportTimestamp,reportDependability);
		UserDto userDto = new UserDto(123,"userName", "password", "email", 0);
		gasStationDto.setUserDto(userDto);
   		GasStation gasStation = GasStationConverter.toEntity(gasStationDto);
   		correctString = "\n{\n"
				+ "gasStationId = " + gasStation.getGasStationId() + ",\n"
				+ "gasStationName = " + gasStation.getGasStationName() + ",\n"
				+ "gasStationAddress = " + gasStation.getGasStationAddress() + ",\n"
				+ "hasDiesel = " + gasStation.getHasDiesel() + ",\n"
				+ "hasSuper = " + gasStation.getHasSuper()+ ",\n"
				+ "hasSuperPlus = " + gasStation.getHasSuperPlus() + ",\n"
				+ "hasGas = " + gasStation.getHasGas() + ",\n"
				+ "hasMethane = " + gasStation.getHasMethane() + ",\n"
				+ "hasPremiumDiesel = " + gasStation.getHasPremiumDiesel() + ",\n"
				+ "carSharing = " + gasStation.getCarSharing() + ",\n"
				+ "lat = " + gasStation.getLat() + ",\n"
				+ "lon = " + gasStation.getLon() + ",\n"
				+ "dieselPrice = " + gasStation.getDieselPrice() + ",\n"
				+ "superPrice = " + gasStation.getSuperPrice() + ",\n"
				+ "superPlusPrice = " + gasStation.getSuperPlusPrice() + ",\n"
				+ "gasPrice = " + gasStation.getGasPrice() + ",\n"
				+ "methanePrice = " + gasStation.getMethanePrice() + ",\n"
				+ "premiumDieselPrice = " + gasStation.getPremiumDieselPrice() + ",\n"
				+ "reportUser = " + gasStation.getReportUser() + ",\n"
				+ "userDto = " + userDto + ",\n"
				+ "reportTimestamp = " + gasStation.getReportTimestamp() + ",\n"
				+ "reportDependability = " + gasStation.getReportDependability() + ",\n"
				+ "}\n";
   		assertEquals(correctString, gasStationDto.toString());
   	}
   	
    
    @Test
	public void testToDtoList() {
		GasStation gasStation1 = new GasStation(gasStationName,gasStationAddress,hasDiesel,hasSuper, 
				hasSuperPlus, hasGas, hasMethane, hasPremiumDiesel, carSharing, lat, lon ,
				dieselPrice,superPrice,superPlusPrice,gasPrice,methanePrice, premiumDieselPrice, reportUser,
				reportTimestamp,reportDependability);
		gasStationId = gasStation1.getGasStationId();

		GasStation gasStation2 = new GasStation("station", "5th street", true, false, 
				false, true, false, true, "CarCar", 35.0, 45.787, 0.54, -110.0, 12.0, 356.768, 
				0.007, 1.1, 4321, "05-11-2020", 4.36);
		 gasStationId2 = gasStation2.getGasStationId();
		List<GasStation> listEntity = Arrays.asList(gasStation1, gasStation2);
		List<GasStationDto> listDto = GasStationConverter.toDto(listEntity);
		String correctString1 ="\n{\n"
				+ "gasStationId = " + this.gasStationId + ",\n"
				+ "gasStationName = " + this.gasStationName + ",\n"
				+ "gasStationAddress = " + this.gasStationAddress + ",\n"
				+ "hasDiesel = " + this.hasDiesel + ",\n"
				+ "hasSuper = " + this.hasSuper + ",\n"
				+ "hasSuperPlus = " + this.hasSuperPlus + ",\n"
				+ "hasGas = " + this.hasGas + ",\n"
				+ "hasMethane = " + this.hasMethane + ",\n"
				+ "hasPremiumDiesel = " + this.hasPremiumDiesel + ",\n"
				+ "carSharing = " + this.carSharing + ",\n"
				+ "lat = " + this.lat + ",\n"
				+ "lon = " + this.lon + ",\n"
				+ "dieselPrice = " + this.dieselPrice + ",\n"
				+ "superPrice = " + this.superPrice + ",\n"
				+ "superPlusPrice = " + this.superPlusPrice + ",\n"
				+ "gasPrice = " + this.gasPrice + ",\n"
				+ "methanePrice = " + this.methanePrice + ",\n"
				+ "premiumDieselPrice = " + this.premiumDieselPrice + ",\n"
				+ "reportUser = " + this.reportUser + ",\n"
				+ "userDto = " + null + ",\n"
				+ "reportTimestamp = " + this.reportTimestamp + ",\n"
				+ "reportDependability = " + computeReportDependability() + ",\n"
				+ "}\n";
		
		String correctString2="\n{\n"
				+ "gasStationId = "+gasStationId2+",\n"
				+ "gasStationName = station,\n"
				+ "gasStationAddress = 5th street,\n"
				+ "hasDiesel = true,\n"
				+ "hasSuper = false,\n"
				+ "hasSuperPlus = false,\n"
				+ "hasGas = true,\n"
				+ "hasMethane = false,\n"
				+ "hasPremiumDiesel = true,\n"
				+ "carSharing = CarCar,\n"
				+ "lat = 35.0,\n"
				+ "lon = 45.787,\n"
				+ "dieselPrice = 0.54,\n"
				+ "superPrice = -110.0,\n"
				+ "superPlusPrice = 12.0,\n"
				+ "gasPrice = 356.768,\n"
				+ "methanePrice = 0.007,\n"
				+ "premiumDieselPrice = 1.1,\n"
				+ "reportUser = 4321,\n"
				+ "userDto = null,\n"
				+ "reportTimestamp = 05-11-2020,\n"
				+ "reportDependability = 45.0,\n"
				+ "}\n";
		assertEquals(correctString1 +correctString2 , listDto.get(0).toString() + listDto.get(1).toString());
		
	}
    
	public double computeReportDependability() {
		if(this.reportTimestamp == null)
			return 0;
		
		double obsolescence;
		Integer userReputation = (int) this.reportDependability;
		Date today = new Date();
		SimpleDateFormat toFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date reportDate;
		try {
			reportDate = toFormat.parse(this.reportTimestamp);
		}catch(ParseException e) {
			reportDate = new Date();
		}
		long diffInMillies = today.getTime() - reportDate.getTime();
		long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		if(diffInDays > 7)
			obsolescence = 0;
		else obsolescence = 1 - (double) diffInDays / 7;
		
		return 50 * (userReputation + 5) / 10 + 50 * obsolescence;
	}

	
}
