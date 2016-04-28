package com.hp.ucmdb.adapter.bean;

import com.hp.ucmdb.adapter.util.PageHelper;

import java.util.ArrayList;

public class GroupCISBean {

    public static final String NO_ELEMENTS_MESSAGE = "Showing 0 of 0";
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
     * @param endIndex the endIndex to set
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
     * @param endTime the endTime to set
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
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the startIndex
     */
    public int getStartIndex() {
        return (page - 1) * PageHelper.PER_PAGE_COUNT + 1;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
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
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return Generate String as Showing 1 to 20 of 2.
     */
    public String getCountTitle() {
        StringBuilder output = new StringBuilder();
        if (totalCount <= 0) {
            output.append(NO_ELEMENTS_MESSAGE);
        } else {
            output.append("Showing ");
            output.append(getStartIndex());
            output.append(" to ");
            output.append(endIndex);
            output.append(" of ");
            output.append(totalCount);
        }

        return output.toString();
    }
}
