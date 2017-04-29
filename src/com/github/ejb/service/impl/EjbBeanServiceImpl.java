package com.github.ejb.service.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.github.ejb.service.EjbBeanService;

@Stateless
@Remote(EjbBeanService.class)
public class EjbBeanServiceImpl implements EjbBeanService{

	@Override
	public String sayHi(String name) {
		return name+" say:hello world!ejb!";
	}

}
