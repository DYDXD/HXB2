package testthread;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 16:52 2019/3/11
 * @Modified By:
 */
//测试线程池
public class TestThreadPool {
    public static void main(String[] args) {
        // 创建3个线程的线程池
        ThreadPool t = ThreadPool.getThreadPool(3);
        t.execute(new Runnable[]{new Task(1), new Task(2), new Task(3)});
        t.execute(new Runnable[]{new Task(4), new Task(5), new Task(6)});
        System.out.println(t);
        t.destroy();// 所有线程都执行完成才destroy
        System.out.println(t);
        //实践结果 确实有三个线程 但是实现了同一个任务
    }

    // 任务类
    static class Task implements Runnable {
        private volatile int i;

        //注意i++虽然是共享的  但是并不具有原子性
        public Task(int j) {
            i = j;
        }

        public Task() {
        }

        @Override
        public void run() {// 执行任务
            System.out.println("任务 " + (i) + " 完成");
        }
    }
}
