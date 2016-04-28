package com.hp.ucmdb.adapter.service;

import static org.apache.commons.lang.StringUtils.isNotEmpty;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hp.ucmdb.adapter.bean.GroupCISBean;
import com.hp.ucmdb.adapter.util.TimeHelper;

public class GroupCISHandler {

	public static final String PAGE = "page";
	public static final String END_TIME = "endTime";
	public static final String START_TIME = "startTime";
	public static final String BATCH_ID = "batchId";
	public static final String SEARCH_FILE = "searchFile";
	public static final int BEFORE_TIME = 3;

	@Autowired
	private TimeHelper timeHelper;

	/**
	 */
	public boolean shouldGenerateDefaultBean(HttpServletRequest request) {
		return allParametersAreNull(request, BATCH_ID, START_TIME, END_TIME, PAGE);
	}

	/**
	 * @return Get the recent three days summary information.
	 */
	public GroupCISBean handleDefaultParams() {
		GroupCISBean bean = new GroupCISBean();
		bean.setStartTime(timeHelper.dateBeforeTodayBy(GroupCISHandler.BEFORE_TIME));
		bean.setEndTime(timeHelper.today());
		bean.setPage(1);
		return bean;
	}

	/**
	 */
	public GroupCISBean handleRequestParams(HttpServletRequest request) {
		GroupCISBean bean = new GroupCISBean();
		String batchId = request.getParameter(GroupCISHandler.BATCH_ID);
		bean.setBatchId(isNotEmpty(batchId) ? batchId : "");
		bean.setStartTime(request.getParameter(GroupCISHandler.START_TIME));
		bean.setEndTime(request.getParameter(GroupCISHandler.END_TIME));

		String page = request.getParameter(GroupCISHandler.PAGE);
		if (isNotEmpty(page)) {
			bean.setPage(Integer.parseInt(page));
		}
		return bean;
	}

	private boolean allParametersAreNull(HttpServletRequest request, String... parameters) {
		for (String parameter : parameters) {
			if (request.getParameter(parameter) != null)
				return false;
		}
		return true;
	}
}