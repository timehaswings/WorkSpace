package com.wings.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyTimer {
	
	/** 
	 * 普通thread 
	 * 这是最常见的，创建一个thread，然后让它在while循环里一直运行着， 
	 * 通过sleep方法来达到定时任务的效果。这样可以快速简单的实现，代码如下： 
	 */  
	public void timerThread(){
        final long timeInterval = 1000;  
        Runnable runnable = new Runnable() {  
            public void run() {  
                while (true) {  
                    // ------- code for task to run  
                    System.out.println("Hello !!");  
                    // ------- ends here  
                    
                    try {  
                        Thread.sleep(timeInterval);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        };  
        Thread thread = new Thread(runnable);  
        thread.start();  
	}
	
	/** 
	 * 于第一种方式相比，优势 1>当启动和去取消任务时可以控制 2>第一次执行任务时可以指定你想要的delay时间 
	 * 在实现时，Timer类可以调度任务，TimerTask则是通过在run()方法里实现具体任务。 Timer实例可以调度多任务，它是线程安全的。 
	 * 当Timer的构造器被调用时，它创建了一个线程，这个线程可以用来调度任务。 下面是代码： 
	 */  
	public void timerTimerTask(long delay,long intevalTime,TimerTask task){
        Timer timer = new Timer();  
        timer.scheduleAtFixedRate(task, delay, intevalTime);  
	}
	
	/** 
	 * ScheduledExecutorService是从Java SE5的java.util.concurrent里，做为并发工具类被引进的，这是最理想的定时任务实现方式。  
	 * 相比于上两个方法，它有以下好处： 
	 * 1>相比于Timer的单线程，它是通过线程池的方式来执行任务的  
	 * 2>可以很灵活的去设定第一次执行任务delay时间 
	 * 3>提供了良好的约定，以便设定执行的时间间隔 
	 * 下面是实现代码，我们通过ScheduledExecutorService#scheduleAtFixedRate展示这个例子，通过代码里参数的控制，首次执行加了delay时间。 
	 */  
	
	public void timerExecutors(long delayTime,long intervalTime,Runnable runnable){
        ScheduledExecutorService service = Executors  
                .newSingleThreadScheduledExecutor();  
        
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
        service.scheduleAtFixedRate(runnable, delayTime, intervalTime, TimeUnit.SECONDS);  
	}
	
	
	/**
	 * 每日定时任务
	 * @param date 时间格式：HH:mm:ss
	 * @param runnable 任务
	 * @throws ParseException
	 */
	public void exeDay(String time,Runnable runnable) throws ParseException{
		exeDay(time,1000*60*60*24,runnable);
	}
	
	/**
	 * 当日定时任务
	 * @param time 时间格式：HH:mm:ss
	 * @param intervalTime 间隔 s
	 * @param runnable 任务
	 * @throws ParseException
	 */
	public void exeDay(String time,long intervalTime,Runnable runnable) throws ParseException{
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		long etime=sdf2.parse(sdf1.format(new Date())+" "+time).getTime();
		long ctime=System.currentTimeMillis();
		if(ctime < etime)
			timerExecutors((etime-ctime)/1000,intervalTime,runnable);
		else if (ctime == etime) 
			timerExecutors(0,intervalTime,runnable);
		else
			timerExecutors((etime+1000*60*60*24-ctime)/1000,intervalTime,runnable);
	}
	
	/**
	 * 每周定时任务
	 * @param time
	 * @param week
	 * @param runnable
	 * @throws ParseException
	 */
	public void exeWeek(String time,int week,Runnable runnable) throws ParseException{
		if(!(week>=0 && week<=6)){
			return;
		}
		int cweek=getWeekDay();
		if(week > cweek)
			timerExecutors((week-cweek)*1000*60*60*24,7*1000*60*60*24,runnable);
		else if(week == cweek)
			exeDay(time,7*1000*60*60*24,runnable);
		else 
			timerExecutors((7-(cweek-week))*1000*60*60*24,7*1000*60*60*24,runnable);
	}
	
	/**
	 * 獲取今天是周幾
	 */
	public int getWeekDay(){
	  Calendar calendar = Calendar.getInstance(); 
	  calendar.setTime(new Date()); 
	  return calendar.get(Calendar.DAY_OF_WEEK) - 1; 
	}
}
