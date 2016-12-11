package com.mirae.index

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

import com.google.common.collect.Maps

@Controller
class IndexController {


	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		println '/에 왔음'
		mav.setViewName('index')
		mav.addObject('data', '이것은 데이터다.')
		return mav
	}


}
