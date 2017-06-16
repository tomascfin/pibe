/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eos.pibe.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ReclamosJob implements Job{
        @Override
	public void execute(JobExecutionContext context)
	throws JobExecutionException {

		System.out.println("Mensaje cad 1 minuto");

	}

}