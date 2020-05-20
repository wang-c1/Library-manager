package com.javaeetest.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.javaeetest.entity.BookType;
import com.javaeetest.service.BookTypeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BookTypeAction extends ActionSupport implements ModelDriven<BookType> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private BookType model = new BookType();
	BookTypeService bookTypeService;
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public BookTypeService getBookTypeService() {
		return bookTypeService;
	}

	public void setBookTypeService(BookTypeService bookTypeService) {
		this.bookTypeService = bookTypeService;
	}

	@Override
	public BookType getModel() {
		return model;
	}

	@Override
	public String execute() throws Exception {
		List<BookType> typeList = bookTypeService.getAllBookType();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("typeList", typeList);
		return null;
	}
	
	/**
	 * 添加图书类型的action执行的方法
	 * @return
	 */
	public String addBookType() {
		try{
			if(bookTypeService.addBookType(model)){
				inputStream=new ByteArrayInputStream("1".getBytes("UTF-8"));
				System.out.println("添加类别成功返回1");
			}else{
				inputStream=new ByteArrayInputStream("0".getBytes("UTF-8"));
			}
		}catch(Exception e){
			
		}
		return "ajax_succ";
	}
	
	public String deleteBookType() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		System.out.println("typeId:" + typeId);
		bookTypeService.deleteBookType(typeId);
		return SUCCESS;
	}
	
	/**
	 * 验证类型名是否存在的action执行的方法
	 * @return
	 */
	public String validateTypeName() {
		System.out.println("进入ajax检验");
		String typeName = model.getTypeName();
		try{
			if(bookTypeService.validateTypeName(typeName) == true){
				inputStream=new ByteArrayInputStream("1".getBytes("UTF-8"));
			}else{
				inputStream=new ByteArrayInputStream("0".getBytes("UTF-8"));
			}
		}catch(Exception e){
			
		}
		return "ajax_succ";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
