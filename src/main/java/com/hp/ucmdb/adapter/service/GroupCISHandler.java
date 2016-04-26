package com.hp.ucmdb.adapter.service;

import com.hp.ucmdb.adapter.bean.GroupCISBean;
import com.hp.ucmdb.adapter.util.TimeHelper;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;


public class GroupCISHandler {

    public static final String PAGE = "page";
    public static final String END_TIME = "endTime";
    public static final String START_TIME = "startTime";
    public static final String BATCH_ID = "batchId";
    public static final String SEARCH_FILE = "searchFile";
    private static final int BEFORE_TIME = 3;

    /**
     */
    public boolean shouldGenerateDefaultBean(HttpServletRequest request) {
        return allParametersAreNull(request, GroupCISHandler.BATCH_ID, GroupCISHandler.START_TIME, GroupCISHandler.END_TIME, GroupCISHandler.PAGE);
    }


    /**
     * @return Get the recent three days summary information.
     */
    public GroupCISBean handleDefaultParams() {
        GroupCISBean bean = new GroupCISBean();
        bean.setStartTime(TimeHelper
                .GetBeforeTime(GroupCISHandler.BEFORE_TIME));
        bean.setEndTime(TimeHelper.getCurrentTime());
        bean.setPage(1);
        return bean;
    }

    /**
     */
    public GroupCISBean handleRequestParams(HttpServletRequest request) {
        GroupCISBean bean = new GroupCISBean();
        String batchId = request.getParameter(GroupCISHandler.BATCH_ID);
        bean.setBatchId(StringUtils.isNotEmpty(batchId) ? batchId : "");
        bean
                .setStartTime(request
                        .getParameter(GroupCISHandler.START_TIME));
        bean.setEndTime(request.getParameter(GroupCISHandler.END_TIME));

        String page = request.getParameter(GroupCISHandler.PAGE);
        if (page != null && page != "") {
            bean.setPage(Integer.parseInt(page));
        }
        return bean;
    }
    
    private boolean allParametersAreNull(HttpServletRequest request, String... parameters) {
    	
    	for (String parameter : parameters) {
			if (request.getParameter(parameter) != null) {
				return false;
			}
		}
    	return true;
    }
}