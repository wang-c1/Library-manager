package com.javaeetest.entity;

public class BookType {
	private Integer typeId;
	private String typeName;

	public BookType() {
		super();
	}

	public BookType(String typeName) {
		super();
		this.typeName = typeName;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
