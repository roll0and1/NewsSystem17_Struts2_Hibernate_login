package com.qiangge.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qiangge.model.News;
import com.qiangge.model.PageModel;
import com.qiangge.service.NewsService;
import com.qiangge.utils.AppException;

public class NewsAction extends ActionSupport {
	private News news;
	private int id; // 新闻id
	private int newsTypeId; // 新闻类型id
	private PageModel pageModel;
	
	private int myNewsState=2; // 默认myNews的状态
	private int typeNewsState = 1; // 默认detailNews的状态
	private int checkNewsState = 0; // 默认checkNews的状态
	private int unCheckNewsState = 0; // 默认checkNews的状态

	private int myNewsCurrentPage = 1; // 默认myNews的当前页面
	private int typeNewsCurrentPage = 1; // 默认typeNews的当前页面

	private int checkNewsCurrentPage = 1; // 默认unCheckNews的当前页面
	private int unCheckNewsCurrentPage = 1; // 默认unCheckNews的当前页面

	private int size = 5;

	public int getUnCheckNewsState() {
		return unCheckNewsState;
	}

	public void setUnCheckNewsState(int unCheckNewsState) {
		this.unCheckNewsState = unCheckNewsState;
	}

	public int getUnCheckNewsCurrentPage() {
		return unCheckNewsCurrentPage;
	}

	public void setUnCheckNewsCurrentPage(int unCheckNewsCurrentPage) {
		this.unCheckNewsCurrentPage = unCheckNewsCurrentPage;
	}

	NewsService newsService;

	private List<News> hotNewsList; // 热门新闻

	private List<News> latestNewsList;// 最新新闻

	/** 栏目新闻 **/
	private List<News> internationalNewsList; // 国际新闻

	private List<News> domesticNewsList;// 国内新闻

	private List<News> sportsNewsList;// 体育新闻

	private List<News> entertainmentNewsList;// 娱乐新闻

	private List<News> autoNewsList;// 汽车新闻

	private List<News> financialNewsList;// 财经新闻

	public String check() {
		// 初始化session
		Map<String, Object> session = ActionContext.getContext().getSession();
		// 设置标签位
		boolean flag = false;
		// 获取用户信息
		Integer userId = (Integer) session.get("userId");
		// 判断用户是否登录
		if (null == userId) {
			// 用户未登录，重定向到login
			return "login";
		}
		try {
			// 获取当前用户创建的新闻信息
			flag = newsService.check(checkNewsState, id);
			if (flag) {
				// 重定向到toUncheck
				return "toUncheck";
			} else {
				return "error";
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 跳转到错误页面
			return "error";
		}

	}

	public String create() {
		String message = null;
		HttpServletRequest request = null;
		// 获取userId
		Map<String, Object> session = ActionContext.getContext().getSession();
		int userId = (int) session.get("userId");
		String name = (String) session.get("name");
		// 验证用户是否登录
		if (null == name) {
			return "login";
		}

		news.setUserId(userId);

		news.setState(0); // 设置状态为"未审核"

		try {
			boolean flag = newsService.create(news);
			if (flag) {
				message = "创建成功";
			} else {
				message = "创建失败";
			}
			// 将message和news对象存入到request中
			// 获取request
			request = (HttpServletRequest) ActionContext.getContext().get(
					ServletActionContext.HTTP_REQUEST);
			// 将message和news对象存入request
			request.setAttribute("message", message);
			request.setAttribute("news", news);
			return "create";

		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 重定向到错误页面
			return "error";
		}
	}

	/**
	 * 查看新闻详情并更新点击数 同时还得获取最近更新和热点内容板块的新闻列表
	 * 
	 * @return
	 * @throws AppException
	 */
	public String detail() throws AppException {
		boolean flag = false;
		int num = 10;
		try {
			News typeNews = newsService.preview(id); // 获取新闻
			flag = newsService.updateClick(id); // 更新点击次数
			// 将typeNews存入request
			Map<String, Object> request = (Map<String, Object>) ActionContext
					.getContext().get("request");
			request.put("typeNews", typeNews);

			// 获取最近更新和热点内容板块的新闻列表
			hotNewsList = newsService.getHotNews(typeNewsState, num);
			latestNewsList = newsService.getLatestNews(typeNewsState, num);

			// 将newsList存入request

			request.put("hotNewsList", hotNewsList);
			request.put("latestNewsList", latestNewsList);

			if (flag) {
				return "typeNewsDetail";
			} else {
				return "error";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "error";

		}
	}

	public List<News> getAutoNewsList() {
		return autoNewsList;
	}


	public int getCheckNewsCurrentPage() {
		return checkNewsCurrentPage;
	}

	public int getCheckNewsState() {
		return checkNewsState;
	}

	public List<News> getDomesticNewsList() {
		return domesticNewsList;
	}

	public List<News> getEntertainmentNewsList() {
		return entertainmentNewsList;
	}

	public List<News> getFinancialNewsList() {
		return financialNewsList;
	}

	public List<News> getHotNewsList() {
		return hotNewsList;
	}

	public int getId() {
		return id;
	}

	public List<News> getInternationalNewsList() {
		return internationalNewsList;
	}

	public List<News> getLatestNewsList() {
		return latestNewsList;
	}

	public int getMyNewsCurrentPage() {
		return myNewsCurrentPage;
	}

	public int getMyNewsState() {
		return myNewsState;
	}

	public News getNews() {
		return news;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public int getNewsTypeId() {
		return newsTypeId;
	}

	public PageModel getPageModel() {
		return pageModel;
	}

	public int getSize() {
		return size;
	}

	public List<News> getSportsNewsList() {
		return sportsNewsList;
	}

	/**
	 * 获取分类新闻列表 同时还得获取最近更新和热点内容板块的新闻列表
	 * 
	 * @return
	 * @throws AppException
	 */
	public String getTypeNews() throws AppException {
		int num = 10;
		try {

			PageModel typeNewsPageModel = newsService.getTypeNews(
					typeNewsState, newsTypeId, typeNewsCurrentPage, size);
			System.out.println("typesize:"
					+ typeNewsPageModel.getTypeNewList().size());
			// 获取最近更新和热点内容板块的新闻列表
			hotNewsList = newsService.getHotNews(typeNewsState, num);
			latestNewsList = newsService.getLatestNews(typeNewsState, num);

			// 将typeNewsPageModel，newsList存入request
			Map<String, Object> request = (Map<String, Object>) ActionContext
					.getContext().get("request");
			request.put("typeNewsPageModel", typeNewsPageModel);
			request.put("hotNewsList", hotNewsList);
			request.put("latestNewsList", latestNewsList);

			// 将newsTypeId 存入request

			request.put("newsTypeId", newsTypeId);
			return "typeNews";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public int getTypeNewsCurrentPage() {
		return typeNewsCurrentPage;
	}

	public int getTypeNewsState() {
		return typeNewsState;
	}

	public String index() {

		int num = 10;
		try {
			hotNewsList = newsService.getHotNews(typeNewsState, num);
			latestNewsList = newsService.getLatestNews(typeNewsState, num);
			internationalNewsList = newsService.getNewsByType(1, typeNewsState,
					num);
			domesticNewsList = newsService.getNewsByType(2, typeNewsState, num);
			entertainmentNewsList = newsService.getNewsByType(3, typeNewsState,
					num);
			sportsNewsList = newsService.getNewsByType(4, typeNewsState, num);
			financialNewsList = newsService
					.getNewsByType(5, typeNewsState, num);
			autoNewsList = newsService.getNewsByType(6, typeNewsState, num);

			// 将newsList存入request
			Map<String, Object> request = (Map<String, Object>) ActionContext
					.getContext().get("request");
			request.put("hotNewsList", hotNewsList);
			request.put("latestNewsList", latestNewsList);
			request.put("internationalNewsList", internationalNewsList);
			request.put("domesticNewsList", domesticNewsList);
			request.put("entertainmentNewsList", entertainmentNewsList);
			request.put("sportsNewsList", sportsNewsList);
			request.put("financialNewsList", financialNewsList);
			request.put("autoNewsList", autoNewsList);
			return "index";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}

	public String myNews() {
		// 初始化session
		Map<String, Object> session = ActionContext.getContext().getSession();
		// 获取用户信息
		Integer userId = (Integer) session.get("userId");
		// 判断用户是否登录
		if (null == userId) {
			// 用户未登录，重定向到login
			return "login";
		}
		try {
			// 获取当前用户创建的新闻信息
			PageModel pageModel = newsService.getList(myNewsState, userId,
					myNewsCurrentPage, size);
			// // 将newsList存入request
			Map<String, Object> request = (Map<String, Object>) ActionContext
					.getContext().get("request");
			request.put("pageModel", pageModel);
			request.put("myNewsState", myNewsState);
			return "myNews";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 跳转到错误页面
			return "error";
		}

	}

	public String preview() {
		// 判断是是否登录
		Map<String, Object> session = ActionContext.getContext().getSession();
		Integer userId = (Integer) session.get("userId");
		if (null == userId) {
			return "login";
		}

		News news = null;
		try {
			news = newsService.preview(id);
			// 将news存入request
			Map<String, Object> request = (Map<String, Object>) ActionContext
					.getContext().get("request");
			request.put("news", news);
			return "preview";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}

	public void setAutoNewsList(List<News> autoNewsList) {
		this.autoNewsList = autoNewsList;
	}

	public void setCheckNewsCurrentPage(int checkNewsCurrentPage) {
		this.checkNewsCurrentPage = checkNewsCurrentPage;
	}


	public void setCheckNewsState(int checkNewsState) {
		this.checkNewsState = checkNewsState;
	}

	public void setDomesticNewsList(List<News> domesticNewsList) {
		this.domesticNewsList = domesticNewsList;
	}

	public void setEntertainmentNewsList(List<News> entertainmentNewsList) {
		this.entertainmentNewsList = entertainmentNewsList;
	}

	public void setFinancialNewsList(List<News> financialNewsList) {
		this.financialNewsList = financialNewsList;
	}

	public void setHotNewsList(List<News> hotNewsList) {
		this.hotNewsList = hotNewsList;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setInternationalNewsList(List<News> internationalNewsList) {
		this.internationalNewsList = internationalNewsList;
	}

	public void setLatestNewsList(List<News> latestNewsList) {
		this.latestNewsList = latestNewsList;
	}

	public void setMyNewsCurrentPage(int myNewsCurrentPage) {
		this.myNewsCurrentPage = myNewsCurrentPage;
	}

	public void setMyNewsState(int myNewsState) {
		this.myNewsState = myNewsState;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setNewsTypeId(int newsTypeId) {
		this.newsTypeId = newsTypeId;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setSportsNewsList(List<News> sportsNewsList) {
		this.sportsNewsList = sportsNewsList;
	}

	public void setTypeNewsCurrentPage(int typeNewsCurrentPage) {
		this.typeNewsCurrentPage = typeNewsCurrentPage;
	}

	public void setTypeNewsState(int typeNewsState) {
		this.typeNewsState = typeNewsState;
	}

	public String toCreate() {
		// 获取session
		Map<String, Object> session = ActionContext.getContext().getSession();
		String name = (String) session.get("name");
		if (null == name) {
			return "login";
		} else {
			return "create";
		}
	}

	public String toUncheck() {
		// 初始化session
		Map<String, Object> session = ActionContext.getContext().getSession();
		// 获取用户信息
		Integer userId = (Integer) session.get("userId");
		// 判断用户是否登录
		if (null == userId) {
			// 用户未登录，重定向到login
			return "login";
		}
		try {
			// 获取当前用户创建的新闻信息
			pageModel = newsService.getList(unCheckNewsState,
					unCheckNewsCurrentPage, size);
			// 将newsList存入request
			Map<String, Object> request = (Map<String, Object>) ActionContext
					.getContext().get("request");
			request.put("newsPageModel", pageModel);
			return "check";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 跳转到错误页面
			return "error";
		}

	}

}
