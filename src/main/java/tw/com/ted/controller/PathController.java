package tw.com.ted.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathController {
	@RequestMapping(path = {"/"})  //外部轉址
	public String handlerMethod1() {
		return "index"; //程式內部轉址
	}
	@RequestMapping(path = {"/some"})  //外部轉址
	public String errorPage() {
		return "error"; //程式內部轉址
	}
}
