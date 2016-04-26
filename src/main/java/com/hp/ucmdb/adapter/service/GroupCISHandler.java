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
	 * Determines if is required to generate a default bean depending on the
	 * existing request parameters
	 * 
	 * @param request
	 *            A {@code HttpServletRequest} object to extract the parameters
	 * @return true when all parameters are null and false when at least one is
	 *         not null
	 */
	public boolean shouldGenerateDefaultBean(HttpServletRequest request) {
		return allParametersAreNull(request, BATCH_ID, START_TIME, END_TIME, PAGE);
	}


    /**
     * @return Get the recent three days summary information.
     */
    public GroupCISBean handleDefaultParams() {
        GroupCISBean bean = new GroupCISBean();
        bean.setStartTime(getStartTime());
        bean.setEndTime(getEndTime());
        bean.setPage(1);
        return bean;
    }


	protected String getEndTime() {
		return TimeHelper.getCurrentTime();
	}


	protected String getStartTime() {
		return TimeHelper
                .GetBeforeTime(GroupCISHandler.BEFORE_TIME);
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