package com.mirae.index

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

import com.google.common.collect.Maps
import com.mirae.config.MiraeProperties

@Controller
class IndexController {

	@Autowired
	MiraeProperties prop

	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		println '/에 왔음'
		mav.setViewName('index')
		mav.addObject('data', "현재 모드는 ${prop.mode}")
		return mav
	}


}
