package dp.springboot_aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试用controller
 *
 * @author chenjie
 * @create 2017-09-11 9:55
 */

@RestController
public class Test1Controller {

	@GetMapping("/test1")
	public String m1(@RequestParam int id, @RequestParam String name) {
		return "id-->" + id + ";name-->" + name;
	}

	@GetMapping("/test2")
	public String m2() {
		return "this is test2";
	}

	/**
	 * 测试get传参数不需要  @RequestParam
	 */
	@GetMapping("/test3")
	public String m3(Integer id) {
		return "测试get请求不需要 注解 【@RequestParam】,id = " + id;
	}
}
