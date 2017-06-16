/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eos.pibe.jobs;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Tomas
 */
public class HelloQuartzListener implements ServletContextListener {

    private Scheduler scheduler;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

    @Override
    public void contextInitialized(ServletContextEvent ctx) {
        // define the job and tie it to our HelloJob class
        JobDetail job = JobBuilder.newJob(ReclamosJob.class)
                .withIdentity("myJob", "group1").build();

        // Trigger the job to run now, and then every 10 minutes
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInMinutes(1).repeatForever())
                .build();
        // Tell quartz to schedule the job using our trigger

        try {
            scheduler = ((StdSchedulerFactory) ctx.getServletContext()
                    .getAttribute(
                            QuartzInitializerListener.QUARTZ_FACTORY_KEY))
                    .getScheduler();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            System.out.println("Quartz error ->"+e.getMessage());
        }
    }

    
}
