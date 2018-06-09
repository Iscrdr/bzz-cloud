import com.star.cloud.Application;
import com.star.cloud.core.service.BaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class ServiceTest {
	
	@Autowired
	private BaseService baseService;
	
	@Test
	public void testGet(){
//		String sql = " select * from sys_user limit 2 ";
//		System.out.println("======================");
//		long startTime = System.currentTimeMillis();
//		0 baseService.insertA(sql);
//		long endTime = System.currentTimeMillis();
////		insert.forEach(map->{
////			map.forEach((k,v)->{
////				System.out.print( k + "  : " + v+",");
////			});
////			System.out.println( "");
////		});
//		System.out.println("======================");
//		System.out.println(endTime-startTime);
		
	}
}
