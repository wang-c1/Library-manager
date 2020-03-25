package com.javaeetest.action;

import com.javaeetest.entity.TypeSale;
import com.javaeetest.service.TypeSumSaleService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TypeSumSaleAction extends ActionSupport implements
		ModelDriven<TypeSale> {
	private TypeSumSaleService typeSum;
	private TypeSale model = new TypeSale();

	@Override
	public String execute() throws Exception {
		System.out.println("sadsadsadas");
		typeSum.typeSumSale();
		return SUCCESS;
	}

	@Override
	public TypeSale getModel() {
		// TODO Auto-generated method stub
		return model;
	}

}
