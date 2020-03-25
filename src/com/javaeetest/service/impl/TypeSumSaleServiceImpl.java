package com.javaeetest.service.impl;

import java.util.List;

import net.sf.json.JSONArray;

import com.javaeetest.dao.impl.TypeSumSaleImpl;
import com.javaeetest.entity.TypeSale;
import com.javaeetest.service.TypeSumSaleService;

public class TypeSumSaleServiceImpl implements TypeSumSaleService {
	private TypeSumSaleImpl typeSumSale;

	@Override
	public String typeSumSale() {
		// TODO Auto-generated method stub
		List<TypeSale> typeSumSales = typeSumSale.typeSumSales();
		JSONArray json = JSONArray.fromObject(typeSumSales);
		System.out.println(json.toString());

		return null;
	}
}
