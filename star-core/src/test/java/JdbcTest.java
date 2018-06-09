import com.star.cloud.Application;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class JdbcTest {
	
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	@Autowired
	private JdbcTemplate secondJdbcTemplate ;
	@Test
	public void testThead(){
	
	
	}
	
	@Test
	public void testInsert(){
		String sql = "INSERT INTO `kehu` ( `id`, `kehu_num`, `kehu_card`, `sex`, `birth_date`, `address`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag` ) VALUES\n" +
				"\t( '11112', '1010', '123456798', '1', '2018-02-21 19:45:43', '北京昌平高教园', NULL, NULL, NULL, NULL, NULL, '0' );";
		jdbcTemplate.execute(sql);
		String sql2 = "INSERT INTO `jeeplus_schema`.`goods`(`id`, `name`, `category_id`, `price`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('5b5cab46889c4119989e6333cb33ceac', '香香', '3fa938e1b6c346f9bcb129313e782ac1', '28', '1', '2016-05-04 21:19:22', '1', '2016-05-04 21:19:22', '', '0');\n";
		secondJdbcTemplate.execute(sql2);
		int i = 12;
		int y = 0;
		y = i/0;
		
	}
	
	@Test
	public void testJdbc2(){
		List<Map<String, Object>> maps = secondJdbcTemplate.queryForList("SELECT * FROM sys_user");
		System.out.println("================secondJdbcTemplate===============");
		System.out.println(maps.size());
		System.out.println("================secondJdbcTemplate==========");
	}
	@Test
	public void testJdbc(){
		long startTime = System.currentTimeMillis();
		jdbcTemplate.execute("SELECT count(*) FROM c_cust_sale_2018");
		List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM c_cust_sale_2018 limit 5");
		long endTime = System.currentTimeMillis();
		
		System.out.println("===============jdbcTemplate==============");
		System.out.println(endTime-startTime);
		System.out.println(maps.size());
		System.out.println("===============jdbcTemplate============");
	}
}
