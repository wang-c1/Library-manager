package com.javaeetest.action;

import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;

import com.javaeetest.entity.Book;
import com.javaeetest.entity.BookType;
import com.javaeetest.entity.PageBean;
import com.javaeetest.service.BookService;
import com.javaeetest.service.BookTypeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BookAction extends ActionSupport implements ModelDriven<Book> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookService bookService;
	private BookTypeService bookTypeService;
	private Book model = new Book();

	private File doc;
	private String fileName;
	private String contentType;
	private String targetFileName;
	private String dir;

	private int page;

	private InputStream inputStream;
	private JFreeChart chart;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPage() {
		return page;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public void setDoc(File file) {
		this.doc = file;
	}

	public void setDocFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setDocContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getTargetFileName() {
		return targetFileName;
	}

	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}

	public String getAllBookType() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<BookType> list = bookTypeService.getAllBookType();
		request.setAttribute("bookTypeListInBookAction", list);
		return SUCCESS;
	}

	private String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(100000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}

	/**
	 * 添加新书
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addBook() throws Exception {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		@SuppressWarnings("deprecation")
		String realPath = ServletActionContext.getRequest().getRealPath("");
		String upload = "\\upload";
		StringBuffer s = new StringBuffer();
		String sub = realPath.substring(0, realPath.length() - 20);
		s.append(sub).append(upload);
		/* String uploadPath = realPath.replaceAll("\\javaeetest", ""); */
		/* System.out.println("uploadPath:"+uploadPath); */
		System.out.println(ServletActionContext.getRequest().getContextPath());
		//String targetDirectory = s.toString();
		String targetDirectory = "D:\\软件下载\\eclipse\\workplace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\Library-user\\upload";
		String targetDirectory1 = "D:\\软件下载\\eclipse\\workplace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\Library-manager\\upload";
		targetFileName = generateFileName(fileName);
		System.out.println(targetDirectory);
		setDir(targetDirectory + "\\" + targetFileName);
		File target = new File(targetDirectory, targetFileName);
		File target1 = new File(targetDirectory1, targetFileName);
		try {
			FileUtils.copyFile(doc, target);
			FileUtils.copyFile(doc, target1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("realPath:" + realPath);
        System.out.println(targetFileName);
		model.setBookPicture(targetFileName);
		BookType type = bookTypeService.getBookTypeByTypeName(request
				.getParameter("typeId"));
		model.setType(type);
		model.setBookShelveTime(new Timestamp(new Date().getTime()));
		model.setBookSales(0);
		model.setBookStatus(1);
		model.setBargainStatus(0);
		bookService.addBook(model);

		return SUCCESS;
	}

	/**
	 * 图书信息展示
	 * 
	 * @return
	 */
	public String showBookList() {
		@SuppressWarnings("unchecked")
		PageBean<Book> pageBean = bookService.getPageBean(10, page, "");
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("pageBean", pageBean);

		return SUCCESS;
	}

	/**
	 * 图书详细信息
	 * 
	 * @return
	 */
	public String singleBook() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = bookService.getBookByBookId(bookId);
		request.setAttribute("singleBook", book);
		return SUCCESS;
	}

	/**
	 * 修改图书信息
	 * 
	 * @return
	 */
	public String updateBook() {
		System.out.println("进入更改图书函数action");
		System.out.println("bookId:" + model.getBookId());
		HttpServletRequest request = ServletActionContext.getRequest();
		int increment = Integer.parseInt(request.getParameter("increment"));
		boolean updateResult = false;
		try {
			updateResult = bookService.addBookAmount(model.getBookId(),
					increment);
			System.out.println(updateResult);
			if (updateResult) {
				System.out.println("进入true");
				inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
			} else {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
				System.out.println("进入false");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "ajax_succ";
	}
	/**
	 * 删除图书信息
	 * 
	 * @return
	 */
	public String deleteBook() {
		//System.out.println("进入更改图书函数action");
		//System.out.println("bookId:" + model.getBookId());
		HttpServletRequest request = ServletActionContext.getRequest();
		//int increment = Integer.parseInt(request.getParameter("increment"));
		//bookService.deleteBarginBook(model.getBookId());
		bookService.deleteBook(model.getBookId());
		return SUCCESS;
	}

	/**
	 * 搜索图书
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String showBookForSearch() throws UnsupportedEncodingException {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		String searchCriteria = new String(
				request.getParameter("searchCriteria"));
		System.out.println("搜索条件：" + searchCriteria);
		@SuppressWarnings("unchecked")
		PageBean<Book> pageBean = bookService.getPageBean(10, page,
				searchCriteria);
		request.setAttribute("pageBean", pageBean);

		return SUCCESS;

	}

	/**
	 * 图书下架执行的函数
	 * 
	 * @return
	 */
	public String offselvesBook() {
		System.out.println("进入下架函数action");
		System.out.println("bookId:" + model.getBookId());
		try {
			if (bookService.offselvesBook(model.getBookId())) {
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
	 * 搜索特价图书
	 * 
	 * @return
	 */
	public String getBookBySearchCriteriaInBargain() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Book> list = new ArrayList<Book>();
		try {
			request.setCharacterEncoding("utf-8");
			if (request.getParameter("searchCriteriaInBargain") == null) {
				request.setAttribute("BookBySearchCriteriaList", list);
			} else {
				String searchCriteriaInBargain = new String(
						request.getParameter("searchCriteriaInBargain"));
				System.out.println(searchCriteriaInBargain + "343434");
				list = bookService
						.getBookBySearchCriteriaInBargain(searchCriteriaInBargain);
				request.setAttribute("BookBySearchCriteriaList", list);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * 图书销量统计
	 * 
	 * @return
	 */
	public JFreeChart getChart() {
		chart = ChartFactory.createPieChart3D("图书销量统计图", // 图标标题
				bookService.getTotoalSalesForEveryTypeDataset(), // 数据
				true, false, false);
		chart.setTitle(new TextTitle("图书销量统计图", new Font("黑体", Font.ITALIC, 22)));
		// 取得统计图表的第一个图例
		LegendTitle legend = chart.getLegend(0);
		// 修改图例的字体
		legend.setItemFont(new Font("宋体", Font.BOLD, 14));
		// 获得饼图的Plot对象
		PiePlot plot = (PiePlot) chart.getPlot();
		// 设置饼图各部分的标签字体
		plot.setLabelFont(new Font("隶书", Font.BOLD, 18));
		// 设定背景透明度（0-1.0之间）
		plot.setBackgroundAlpha(0.9f);
		// 设定前景透明度（0-1.0之间）
		plot.setForegroundAlpha(0.50f);
		return chart;

	}

	@Override
	public Book getModel() {
		return model;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public BookTypeService getBookTypeService() {
		return bookTypeService;
	}

	public void setBookTypeService(BookTypeService bookTypeService) {
		this.bookTypeService = bookTypeService;
	}

}
