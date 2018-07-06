/**
 * 
 */
package com.hzk.library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hzk.library.ServiceImpl.RoleServiceImpl;

/**
 * @author kecheng
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleTest {
	@Autowired
	private RoleServiceImpl roleService;

	@Test
	public void test() {
		System.out.println(roleService.getRoleByID(2));
	}

}
