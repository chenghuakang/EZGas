package it.polito.ezgas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.utils.Constants;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GasStationRepositoryTest {
	
	@Autowired 
	private GasStationRepository gasStationRepository;
	private final int NUMBER_OF_GAS_STATIONS=15;
	private final int NUMBER_OF_CAR_SHARING=2;
	private final int NUMBER_OF_GAS_PROXIMITY = 5;
	private final double MAX_PRICE=5.00;
	private final double MAX_DEPENDABILITY=5.00;
	private final double MAX_LAT = Constants.MAX_LAT - 1.0;
	private final double MAX_LON = Constants.MAX_LON - 1.0;
	

	@Before
	public void init() {
		Random random = new Random();
		for(int i=0;i<NUMBER_OF_GAS_STATIONS;i++) {
			gasStationRepository.save(new GasStation(
					"gasstationname"+i, 
					"gasstationaddress"+i, 
					random.nextBoolean(),
					random.nextBoolean(),
					random.nextBoolean(),
					random.nextBoolean(),
					random.nextBoolean(),
					Integer.toString(random.nextInt(NUMBER_OF_CAR_SHARING)), 
					random.nextDouble()*MAX_LAT*2-MAX_LAT, 
					random.nextDouble()*MAX_LON*2-MAX_LON, 
					random.nextDouble()*MAX_PRICE,
					random.nextDouble()*MAX_PRICE,
					random.nextDouble()*MAX_PRICE,
					random.nextDouble()*MAX_PRICE,
					random.nextDouble()*MAX_PRICE,
					i,
					"reporttimestamp"+i,
					random.nextDouble()*MAX_DEPENDABILITY));
		}
	}
	
	@Test
	public void testFindByProximity() {
		double lat = MAX_LAT + Constants.KM1_LAT*2;
		double lon = MAX_LON + Constants.KM1_LON*2;
		double deltaLat = Constants.KM1_LAT / NUMBER_OF_GAS_PROXIMITY;
		double deltaLon = Constants.KM1_LON / NUMBER_OF_GAS_PROXIMITY;
		
		for(int i = 0; i < NUMBER_OF_GAS_PROXIMITY; i++)
			gasStationRepository.save(new GasStation(
					"gasStationByProximity" + i, "gasStationByProximityAddress" + i, false, false, false, false, false, 
					null, lat + deltaLat * i, lon + deltaLon * i, -1.0, -1.0, -1.0, -1.0, -1.0, null, null, 0.0));
		
		assertEquals(NUMBER_OF_GAS_PROXIMITY, gasStationRepository.findByProximity(lat, lon).size());
	}
	
	@Test
	public void testFindByCarSharingOrderByGasStationName() {
		int i,j,sum=0;
		List<GasStation> gasStationList;
		for(i=0;i<NUMBER_OF_CAR_SHARING;i++) {
			gasStationList=gasStationRepository.findByCarSharingOrderByGasStationName(Integer.toString(i));
			for(j=0;j<gasStationList.size()-1;j++) {
				assertEquals(Integer.toString(i),gasStationList.get(j).getCarSharing());
				assertTrue("Expected "+gasStationList.get(j).getGasStationName()+" to be lexicographically before "+
							gasStationList.get(j+1).getGasStationName()+"!",
						gasStationList.get(j).getGasStationName().compareTo(
						gasStationList.get(j+1).getGasStationName())<=0);
			}
			assertEquals(Integer.toString(i),gasStationList.get(j).getCarSharing());
			sum+=gasStationList.size();
		}
		assertEquals(NUMBER_OF_GAS_STATIONS,sum);
	}
	
	@Test
	public void testSaveNewGasStation() {
		GasStation gasStation;
		gasStation = new GasStation("gas station name", "gas station address", 
				true, true, false, true, false, "car sharing", 20, 80, 1.43, 1.65, 1.22, 
				1.20, 1.01, 100234, "report timestamp", 5.34);
		gasStation = gasStationRepository.save(gasStation);
		assertNotNull(gasStation);
	}
	
	@Test
	public void testUpdateOldGasStation() {
		GasStation gasStation;
		int oldId, newId;
		final String oldName="old name";
		final String newName="new name";
		String name;
		
		gasStation = new GasStation("gas station name", "gas station address", 
				true, true, false, true, false, "car sharing", 20, 80, 1.43, 1.65, 1.22, 
				1.20, 1.01, 100234, "report timestamp", 5.34);
		gasStation.setGasStationName(oldName);
		gasStation = gasStationRepository.save(gasStation);
		oldId=gasStation.getGasStationId();
		name=gasStation.getGasStationName();
		
		assertEquals(oldName, name);
		
		gasStation.setGasStationName(newName);
		gasStation = gasStationRepository.save(gasStation);
		newId=gasStation.getGasStationId();
		name=gasStation.getGasStationName();
		
		assertEquals(oldId,newId);
		assertEquals(newName,name);
	}

	@Test
	public void testFindByHasMethaneOrderByMethanePriceAscTrue() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasMethaneOrderByMethanePriceAsc(true);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(true,gasStationList.get(i).getHasMethane());
			assertTrue("Expected "+gasStationList.get(i).getMethanePrice()+" to be smaller than "+
						gasStationList.get(i+1).getMethanePrice()+"!",
					gasStationList.get(i).getMethanePrice()<gasStationList.get(i+1).getMethanePrice());
		}
		assertEquals(true,gasStationList.get(i).getHasMethane());
	}
	
	@Test
	public void testFindByHasMethaneOrderByMethanePriceAscFalse() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasMethaneOrderByMethanePriceAsc(false);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(false,gasStationList.get(i).getHasMethane());
			assertTrue("Expected "+gasStationList.get(i).getMethanePrice()+" to be smaller than "+
						gasStationList.get(i+1).getMethanePrice()+"!",
					gasStationList.get(i).getMethanePrice()<gasStationList.get(i+1).getMethanePrice());
		}
		assertEquals(false,gasStationList.get(i).getHasMethane());
	}
	
	@Test
	public void testFindByHasMethaneOrderByMethanePriceAscTotal() {
		List<GasStation> gasStationListTrue = gasStationRepository.findByHasMethaneOrderByMethanePriceAsc(true);
		List<GasStation> gasStationListFalse = gasStationRepository.findByHasMethaneOrderByMethanePriceAsc(false);
		assertEquals(NUMBER_OF_GAS_STATIONS,gasStationListTrue.size()+gasStationListFalse.size());
	}
	
	@Test
	public void testFindByHasDieselOrderByDieselPriceAscTrue() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasDieselOrderByDieselPriceAsc(true);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(true,gasStationList.get(i).getHasDiesel());
			assertTrue("Expected "+gasStationList.get(i).getDieselPrice()+" to be smaller than "+
						gasStationList.get(i+1).getDieselPrice()+"!",
					gasStationList.get(i).getDieselPrice()<gasStationList.get(i+1).getDieselPrice());
		}
		assertEquals(true,gasStationList.get(i).getHasDiesel());
	}
	
	@Test
	public void testFindByHasDieselOrderByDieselPriceAscFalse() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasDieselOrderByDieselPriceAsc(false);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(false,gasStationList.get(i).getHasDiesel());
			assertTrue("Expected "+gasStationList.get(i).getDieselPrice()+" to be smaller than "+
						gasStationList.get(i+1).getDieselPrice()+"!",
					gasStationList.get(i).getDieselPrice()<gasStationList.get(i+1).getDieselPrice());
		}
		assertEquals(false,gasStationList.get(i).getHasDiesel());
	}
	
	@Test
	public void testFindByHasDieselOrderByDieselPriceAscTotal() {
		List<GasStation> gasStationListTrue = gasStationRepository.findByHasDieselOrderByDieselPriceAsc(true);
		List<GasStation> gasStationListFalse = gasStationRepository.findByHasDieselOrderByDieselPriceAsc(false);
		assertEquals(NUMBER_OF_GAS_STATIONS,gasStationListTrue.size()+gasStationListFalse.size());
	}
	
	@Test
	public void testFindByHasSuperOrderBySuperPriceAscTrue() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasSuperOrderBySuperPriceAsc(true);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(true,gasStationList.get(i).getHasSuper());
			assertTrue("Expected "+gasStationList.get(i).getSuperPrice()+" to be smaller than "+
						gasStationList.get(i+1).getSuperPrice()+"!",
					gasStationList.get(i).getSuperPrice()<gasStationList.get(i+1).getSuperPrice());
		}
		assertEquals(true,gasStationList.get(i).getHasSuper());
	}
	
	@Test
	public void testFindByHasSuperOrderBySuperPriceAscFalse() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasSuperOrderBySuperPriceAsc(false);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(false,gasStationList.get(i).getHasSuper());
			assertTrue("Expected "+gasStationList.get(i).getSuperPrice()+" to be smaller than "+
						gasStationList.get(i+1).getSuperPrice()+"!",
					gasStationList.get(i).getSuperPrice()<gasStationList.get(i+1).getSuperPrice());
		}
		assertEquals(false,gasStationList.get(i).getHasSuper());
	}
	
	@Test
	public void testFindByHasSuperOrderBySuperPriceAscTotal() {
		List<GasStation> gasStationListTrue = gasStationRepository.findByHasSuperOrderBySuperPriceAsc(true);
		List<GasStation> gasStationListFalse = gasStationRepository.findByHasSuperOrderBySuperPriceAsc(false);
		assertEquals(NUMBER_OF_GAS_STATIONS,gasStationListTrue.size()+gasStationListFalse.size());
	}
	
	@Test
	public void testFindByHasSuperPlusOrderBySuperPlusPriceAscTrue() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasSuperPlusOrderBySuperPlusPriceAsc(true);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(true,gasStationList.get(i).getHasSuperPlus());
			assertTrue("Expected "+gasStationList.get(i).getSuperPlusPrice()+" to be smaller than "+
						gasStationList.get(i+1).getSuperPlusPrice()+"!",
					gasStationList.get(i).getSuperPlusPrice()<gasStationList.get(i+1).getSuperPlusPrice());
		}
		assertEquals(true,gasStationList.get(i).getHasSuperPlus());
	}
	
	@Test
	public void testFindByHasSuperPlusOrderBySuperPlusPriceAscFalse() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasSuperPlusOrderBySuperPlusPriceAsc(false);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(false,gasStationList.get(i).getHasSuperPlus());
			assertTrue("Expected "+gasStationList.get(i).getSuperPlusPrice()+" to be smaller than "+
						gasStationList.get(i+1).getSuperPlusPrice()+"!",
					gasStationList.get(i).getSuperPlusPrice()<gasStationList.get(i+1).getSuperPlusPrice());
		}
		assertEquals(false,gasStationList.get(i).getHasSuperPlus());
	}
	
	@Test
	public void testFindByHasSuperPlusOrderBySuperPlusPriceAscTotal() {
		List<GasStation> gasStationListTrue = gasStationRepository.findByHasSuperPlusOrderBySuperPlusPriceAsc(true);
		List<GasStation> gasStationListFalse = gasStationRepository.findByHasSuperPlusOrderBySuperPlusPriceAsc(false);
		assertEquals(NUMBER_OF_GAS_STATIONS,gasStationListTrue.size()+gasStationListFalse.size());
	}
	
	@Test
	public void testFindByHasGasOrderByGasPriceAscTrue() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasGasOrderByGasPriceAsc(true);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(true,gasStationList.get(i).getHasGas());
			assertTrue("Expected "+gasStationList.get(i).getGasPrice()+" to be smaller than "+
						gasStationList.get(i+1).getGasPrice()+"!",
					gasStationList.get(i).getGasPrice()<gasStationList.get(i+1).getGasPrice());
		}
		assertEquals(true,gasStationList.get(i).getHasGas());
	}
	
	@Test
	public void testFindByHasGasOrderByGasPriceAscFalse() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasGasOrderByGasPriceAsc(false);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(false,gasStationList.get(i).getHasGas());
			assertTrue("Expected "+gasStationList.get(i).getGasPrice()+" to be smaller than "+
						gasStationList.get(i+1).getGasPrice()+"!",
					gasStationList.get(i).getGasPrice()<gasStationList.get(i+1).getGasPrice());
		}
		assertEquals(false,gasStationList.get(i).getHasGas());
	}
	
	@Test
	public void testFindByHasGasOrderByGasPriceAscTotal() {
		List<GasStation> gasStationListTrue = gasStationRepository.findByHasGasOrderByGasPriceAsc(true);
		List<GasStation> gasStationListFalse = gasStationRepository.findByHasGasOrderByGasPriceAsc(false);
		assertEquals(NUMBER_OF_GAS_STATIONS,gasStationListTrue.size()+gasStationListFalse.size());
	}
	
	
	
}
