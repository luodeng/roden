package com.roden.spring.task.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

public class QuartzTask {
	public static Scheduler scheduler = null;		
	
	public static void main(String[] args) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		scheduler = sf.getScheduler();
		
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(
				"src/main/java/com/roden/spring/task/quartz/job.properties");
		p.load(fis);		
		Enumeration<?> en = p.propertyNames(); 
		while (en.hasMoreElements()) { 	
			 String key = (String) en.nextElement();	
			 Class clazz=Class.forName("com.roden.spring.task.quartz."+key);
			 QuartzTask.addJob(scheduler, key,"group", p.getProperty(key),clazz);
		  }			
		fis.close();		
		scheduler.start();		
		
	}	
	//添加任务
	public static Scheduler addJob(Scheduler scheduler,String name,String group,String cron,Class clazz)
			throws SchedulerException {
		JobDetail job = JobBuilder.newJob(clazz)
				.withIdentity(name, group).build();
		CronTrigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity(name, group)
				.withSchedule(
						CronScheduleBuilder.cronSchedule(cron))
				.build();

		scheduler.scheduleJob(job, trigger);
		
		return scheduler;
	}

	// 暂停任务
	public static void pause(String name, String group)
			throws Exception {
		JobKey jobKey = JobKey.jobKey(name, group);
		scheduler.pauseJob(jobKey);
	}

	// 恢复任务
	public static void resume(String name, String group)
			throws Exception {
		JobKey jobKey = JobKey.jobKey(name, group);
		scheduler.resumeJob(jobKey);
	}

	// 删除任务
	public static void delete(String name, String group)
			throws Exception {
		JobKey jobKey = JobKey.jobKey(name, group);
		scheduler.deleteJob(jobKey);
	}

	// 立即运行任务
	public void trigger(String name, String group)
			throws Exception {
		JobKey jobKey = JobKey.jobKey(name, group);
		scheduler.triggerJob(jobKey);

	}
	// 更新任务
	public static void update(String name, String group, String cron)
			throws Exception {			
		//此处name为设定任务时的trigge的name，注意与 JobDetail的name区别，否则会获取不到，建设将二者设为一致
		TriggerKey triggerKey = TriggerKey.triggerKey(name, group);		
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		if(!cron.equals(trigger.getCronExpression())){
			System.out.println("更改任务："+name+"定时时间由"+trigger.getCronExpression()+" 修改为 "+cron);
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			scheduler.rescheduleJob(triggerKey, trigger);	
		}
	}
}
