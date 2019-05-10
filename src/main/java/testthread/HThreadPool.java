package testthread;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 10:06 2019/3/12
 * @Modified By:
 */
public class HThreadPool {

    private static int worker_num = 5;
    private WorkThread[] workThreads;
    private static volatile int finish_task = 0;
    private List<Runnable> taskQueue = new LinkedList<>();
    //创建一个线程池变量被连续引用 不过多创建
    private static HThreadPool hThreadPool;

    //默认构造方法
    private HThreadPool() {
        this(5);
    }

    //有参构造方法
    private HThreadPool(int worker_num) {
        HThreadPool.worker_num = worker_num;
        workThreads = new WorkThread[worker_num];
        for (int i = 0; i < worker_num; i++) {
            workThreads[i] = new WorkThread();
            workThreads[i].start();
        }

    }

    //singleton 模式
    public static HThreadPool getHThreadPool(int worker_num1) {
        if (worker_num1 < 0) {
            worker_num1 = HThreadPool.worker_num;
        }
        if (hThreadPool == null) {
            hThreadPool = new HThreadPool(worker_num1);
        }
        return hThreadPool;
    }

    //默认singleton 模式
    public static HThreadPool getHThreadPool() {
        return new HThreadPool(HThreadPool.worker_num);
    }

    //执行任务
    public void execute(Runnable task) {
        synchronized (taskQueue) {
            taskQueue.add(task);
        }
        taskQueue.notify();
    }

    //批量执行任务
    public void execute(Runnable[] tasks) {
        synchronized (taskQueue){
            for (Runnable task : tasks) {
                taskQueue.add(task);
            }
            taskQueue.notify();
        }

    }

    public void execute(List<Runnable> tasks) {
        synchronized (taskQueue){
            for (Runnable task : tasks) {
                taskQueue.add(task);
            }
            taskQueue.notify();
        }
    }

    //销毁线程池
    public void destroy() {
        while (!taskQueue.isEmpty()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < worker_num; i++) {
            workThreads[i].stopWorker();
            workThreads[i] = null;
        }
        hThreadPool = null;
        taskQueue.clear();

    }


    private class WorkThread extends Thread {
        private boolean isRunning = true;

        @Override
        public void run() {
            Runnable r = null;
            while (isRunning) {
                synchronized (taskQueue) {
                    while (isRunning && taskQueue.isEmpty()) {
                        try {
                            taskQueue.wait(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (!taskQueue.isEmpty()) {
                        r = taskQueue.remove(0);
                    }
                }
                if (r != null) {
                    r.run();
                }
                finish_task++;
                r = null;
            }
        }

        public void stopWorker() {
            isRunning = false;
        }


    }


}
