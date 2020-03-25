package com.javaeetest.dao.impl;

import java.util.List;

import com.javaeetest.common.dao.impl.BaseDaoImpl;
import com.javaeetest.dao.TypeSumSale;
import com.javaeetest.entity.TypeSale;

public class TypeSumSaleImpl extends BaseDaoImpl<TypeSale> implements
		TypeSumSale {

	@Override
	public List<TypeSale> typeSumSales() {
		// TODO Auto-generated method stub
		String hql = "select typeName,sum(bookSales) from tb_book,tb_bookType where tb_book.typeId=tb_bookType.typeId group by typeName";
		List<TypeSale> typeSale = find(hql);
		if (typeSale != null && typeSale.size() > 0) {
			return typeSale;
		}
		return null;
	}

}
