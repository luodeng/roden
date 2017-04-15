package com.roden.spring.task.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;

public class ReloadJob implements Job {
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			System.out.println("任务：Reload执行中-------------------------------");
			Properties p = new Properties();
			FileInputStream fis = new FileInputStream(
					"src/main/java/com/roden/spring/task/quartz/job.properties");
			p.load(fis);			
			Iterator<String> it = p.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				QuartzTask.update(key, "group", p.getProperty(key));
			}
			System.out.println("任务：Reloadr完成-------------------------------");
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
