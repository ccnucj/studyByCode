package dp.springboot_aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义切面
 *
 * @author chenjie
 * @create 2017-09-11 9:59
 */

@Configuration
@Aspect
public class MyAspect {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Pointcut("execution(* dp.springboot_aop.*.*(..))")
    public void myPointcut() {
    }

    @Before("myPointcut()")
    public int testAop(JoinPoint joinPoint) {
        String servletPath = httpServletRequest.getServletPath();
        System.out.println(servletPath);
        System.out.println("参数列表如下:");
        for (Object o : joinPoint.getArgs()) {
            System.out.println(o.toString());
        }
        if(servletPath.equals("/test2")) {
            throw new RuntimeException("异常了");
        }
        return 6666;
    }
}
