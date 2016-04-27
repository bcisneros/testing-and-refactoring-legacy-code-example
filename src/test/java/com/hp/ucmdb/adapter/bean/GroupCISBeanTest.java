package com.hp.ucmdb.adapter.bean;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GroupCISBeanTest {

	@Test
	public void should_calculate_startIndex() {
		GroupCISBean bean = new GroupCISBean();
		bean.setPage(8);
		assertThat(bean.getStartIndex(), is(71));
	}
	
	@Test
	public void should_show_zero_of_zero_message_when_total_count_is_zero() throws Exception {
		GroupCISBean bean = new GroupCISBean();
		bean.setTotalCount(0);
		assertThat(bean.getCountTitle(), is("Showing 0 of 0"));
	}
	
	@Test
	public void should_show_count_title_according_start_index_end_index_and_total_count() throws Exception {
		GroupCISBean bean = new GroupCISBean();
		bean.setTotalCount(100);
		bean.setPage(8);
		bean.setEndIndex(80);
		assertThat(bean.getCountTitle(), is("Showing 71 to 80 of 100"));
	}

}
