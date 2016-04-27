package com.hp.ucmdb.adapter.bean;

import com.hp.ucmdb.adapter.util.PageHelper;

import java.util.ArrayList;

public class GroupCISBean {

	private static final String COUNT_TITLE_MESSAGE_FORMAT = "Showing %d to %d of %d";
	private static final String NO_RECORDS_FOUND_MESSAGE = "Showing 0 of 0";
	public static final int DEFAULT_INITIAL_PAGE_VALUE = 1;
	private String id;
	private String batchId;
	private String startTime;
	private String endTime;
	private int page = DEFAULT_INITIAL_PAGE_VALUE;
	private int endIndex;
	private int totalCount = 0;
	private ArrayList<CISGroupBean> groupBeans;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<CISGroupBean> getGroupBeans() {
		return groupBeans;
	}

	public void setGroupBeans(ArrayList<CISGroupBean> groupBeans) {
		this.groupBeans = groupBeans;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	/**
	 * @return the endIndex
	 */
	public int getEndIndex() {
		return endIndex;
	}

	/**
	 * @param endIndex
	 *            the endIndex to set
	 */
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the startIndex
	 */
	public int getStartIndex() {
		return (getPage() - 1) * PageHelper.PER_PAGE_COUNT + 1;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * Retrieves the pager total message that informs the actual information
	 * about what records are displayed on the results page
	 * 
	 * For example: "Showing 71 to 80 of 100" means that you are in the 8th page
	 * and there are 100 records on the result list
	 * 
	 * When total count is 0 it displays "Showing 0 of 0" as default message
	 * 
	 * @return The count title String based on the total count number
	 */
	public String getCountTitle() {
		return totalCount <= 0 ? NO_RECORDS_FOUND_MESSAGE
				: String.format(COUNT_TITLE_MESSAGE_FORMAT, getStartIndex(), endIndex, totalCount);
	}
}
