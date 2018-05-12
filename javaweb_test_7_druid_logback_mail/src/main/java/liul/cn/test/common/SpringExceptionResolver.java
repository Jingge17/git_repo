package liul.cn.test.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {
    String url = request.getRequestURL().toString();
    ModelAndView mv;
    JsonData result = JsonData.fail(ex.getMessage());
    mv = new ModelAndView("jsonView", result.toMap());
    return mv;
	}


}
