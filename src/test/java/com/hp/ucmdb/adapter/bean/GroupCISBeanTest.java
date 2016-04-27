package com.hp.ucmdb.adapter.bean;

import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class GroupCISBeanTest {

	@Test
	public void should_calculate_startIndex() {
		GroupCISBean bean = new GroupCISBean();
		bean.setPage(8);
		assertThat(bean.getStartIndex(), CoreMatchers.is(71));
	}

}
