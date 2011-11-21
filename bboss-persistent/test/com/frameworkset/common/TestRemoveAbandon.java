package com.frameworkset.common;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frameworkset.common.poolman.DBUtil;

/**
 * 
 * 
 * <p>Title: TestRemoveAbandon.java</p>
 *
 * <p>Description: �����ݿ����ӳص�й©�������ǿ�ƻ��չ��ܽ��в���</p>
 *
 * <p>
 * bboss workgroup
 * </p>
 * <p>
 * Copyright (c) 2007
 * </p>
 * 
 * @Date 2009-6-1 ����08:58:51
 * @author biaoping.yin
 * @version 1.0
 */
public class TestRemoveAbandon {
	
	public static void testAbandonEvict()
	{
		
		T t = new T();
		t.start();
	}
	
	static class T extends Thread
	{
		public void run()
		{
			try {
				List list = new ArrayList();
				for(int i =0; i < 10; i ++)
				{
					Connection con = DBUtil.getConection();
					list.add(con);	
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(true)
			{
				System.out.println("������������" + DBUtil.getNumIdle());
				System.out.println("����ʹ����������" + DBUtil.getNumActive());
				System.out.println("ʹ�����������ֵ��" + DBUtil.getMaxNumActive());
				try {
					sleep(10000);
					DBUtil.getConection();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		testAbandonEvict();
	}
	

}