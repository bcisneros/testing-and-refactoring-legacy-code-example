package com.hp.ucmdb.adapter.bean;

import static com.hp.ucmdb.adapter.bean.GroupCISBean.NO_ELEMENTS_MESSAGE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class GroupCISBeanTest {

	private static final int SEVENTY_FIRST_ELEMENT = 71;
	private static final int EIGHT_PAGE = 8;
	private static final int NO_ELEMENTS = 0;

	@Test
	public void should_return_start_index_based_on_page_and_number_of_elements_by_page() {
		GroupCISBean bean = new GroupCISBean();
		bean.setPage(EIGHT_PAGE);
		assertThat(bean.getStartIndex(), is(SEVENTY_FIRST_ELEMENT));

	}

	@Test
	public void should_retrieve_no_elements_message_when_total_count_is_zero() throws Exception {
		GroupCISBean bean = new GroupCISBean();
		bean.setTotalCount(NO_ELEMENTS);
		assertThat(bean.getCountTitle(), is(NO_ELEMENTS_MESSAGE));
	}

	@Test
	public void should_retrieve_count_title_when_total_count_is_greater_than_zero() throws Exception {
		GroupCISBean bean = new GroupCISBean();
		bean.setPage(EIGHT_PAGE);
		bean.setEndIndex(80);
		bean.setTotalCount(100);
		assertThat(bean.getCountTitle(), is("Showing 71 to 80 of 100"));
	}
}
