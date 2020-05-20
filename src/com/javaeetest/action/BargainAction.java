package com.javaeetest.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.javaeetest.entity.Bargain;
import com.javaeetest.entity.BargainBook;
import com.javaeetest.entity.Book;
import com.javaeetest.service.BargainService;
import com.javaeetest.service.BookService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BargainAction extends ActionSupport implements
		ModelDriven<Bargain> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BargainService bargainService;
	Bargain model = new Bargain();
	private InputStream inputStream;
	private BookService bookService;
	private int bookId;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public BargainService getBargainService() {
		return bargainService;
	}

	public void setBargainService(BargainService bargainService) {
		this.bargainService = bargainService;
	}

	@Override
	public String execute() throws Exception {
		List<BargainBook> bargainingList = bargainService
				.getAllBargainingBook();
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println(bargainingList.size());
		//System.out.println(bargainingList.get(0).getBookName());
		//System.out.println(bargainingList.get(1).getBookName());
		request.setAttribute("bargainingList", bargainingList);
		return SUCCESS;
	}

	public String addBargainShow() {
		return SUCCESS;
	}

	/**
	 * 把正在进行打折的图书去取消
	 * 
	 * @return
	 */
	public String noBargain() {
		System.out.println("进入到nobargain");
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("request得到：" + request.getParameter("bargainId"));
		System.out.println(model.getBargainId());
		try {
			if (bargainService.noBargainBook(model.getBargainId())) {
				inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
			} else {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "ajax_succ";
	}

	/**
	 * 添加打折图书
	 * 
	 * @return
	 */
	public String addBargain() {
		model.setBargainStatus(1);
		System.out.println("进入到addBargain");
		Book book = bookService.getBookByBookId(bookId);
		model.setBook(book);
		try {
			if (bargainService.addBargainBook(model)) {
				inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
			} else {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "ajax_succ";
	}

	@Override
	public Bargain getModel() {
		return model;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

}
