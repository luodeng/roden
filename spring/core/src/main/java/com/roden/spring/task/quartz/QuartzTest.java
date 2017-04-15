package com.roden.spring.task.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzTest {

	public static void main(String[] args) throws Exception {
		QuartzTest qt = new QuartzTest();
		// 通过SchedulerFactory获取一个调度器实例
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();
		
		qt.addJob(scheduler);
		qt.addJob2(scheduler);
		qt.addJob3(scheduler);
		qt.addJob4(scheduler);

		// 开始执行，start()方法被调用后，计时器就开始工作，计时调度中允许放入N个Job
		scheduler.start();		
		
		//注意junit不能测试多线程的方法，如果使用junit可以在主线程中sleep一段时间
		// 当前线程等待65秒
		Thread.sleep(65L * 1000L);
		// 关闭定时调度，定时器不再工作
		scheduler.shutdown(true);
	}

	public Scheduler addJob(Scheduler scheduler) throws SchedulerException {
		JobDetail job = JobBuilder.newJob(SimpleJob.class)
				.withIdentity("job_one", "group_one").build();
		CronTrigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("trigger_one", "group_one")
				.withSchedule(
						CronScheduleBuilder.cronSchedule("0/20 * * * * ?"))
				.build();

		scheduler.scheduleJob(job, trigger);
		return scheduler;
	}

	public Scheduler addJob2(Scheduler scheduler) throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
				.withIdentity("job2", "group_one").build();

		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("trigger2", "group_one")
				.startNow()
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(10) // 时间间隔
								.withRepeatCount(5) // 重复次数(将执行6次)
				).build();

		scheduler.scheduleJob(jobDetail, trigger);
		return scheduler;
	}

	public Scheduler addJob3(Scheduler scheduler) throws SchedulerException {
		Date runTime = evenMinuteDate(new Date());

		// 通过过JobDetail封装HelloJob，同时指定Job在Scheduler中所属组及名称，这里，组名为group1，而名称为job1。
		JobDetail job = newJob(SimpleJob.class).withIdentity("job_three", "group3")
				.build();

		// 创建一个SimpleTrigger实例，指定该Trigger在Scheduler中所属组及名称。
		// 接着设置调度的时间规则.当前时间运行
		Trigger trigger = newTrigger().withIdentity("trigger_three", "group3")
				.startAt(runTime).build();

		// 注册并进行调度
		scheduler.scheduleJob(job, trigger);
		return scheduler;
	}

	public Scheduler addJob4(Scheduler scheduler) throws SchedulerException {
		// jobs可以在scheduled的sched.start()方法前被调用

		// job 1将每隔20秒执行一次
		JobDetail job = newJob(SimpleJob.class).withIdentity("job_four", "group3")
				.build();
		CronTrigger trigger = newTrigger().withIdentity("trigger_four", "group3")
				.withSchedule(cronSchedule("0/20 * * * * ?")).build();
		Date ft = scheduler.scheduleJob(job, trigger);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		System.out.println(job.getKey() + " 已被安排执行于: " + sdf.format(ft)
				+ "，并且以如下重复规则重复执行: " + trigger.getCronExpression());

		// job 2将每2分钟执行一次（在该分钟的第15秒)
		job = newJob(SimpleJob.class).withIdentity("job4", "group3").build();
		trigger = newTrigger().withIdentity("trigger4", "group3")
				.withSchedule(cronSchedule("15 0/2 * * * ?")).build();
		ft = scheduler.scheduleJob(job, trigger);
		System.out.println(job.getKey() + " 已被安排执行于: " + sdf.format(ft)
				+ "，并且以如下重复规则重复执行: " + trigger.getCronExpression());

		return scheduler;
	}

}
