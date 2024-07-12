package com.example.common.controller;



import javax.servlet.http.HttpServletRequest;

import com.example.util.FtMap;


public class AbstractController {


	protected FtMap getFtMap(HttpServletRequest request) {
		return new FtMap(request.getParameterMap());
	}


}
