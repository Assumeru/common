package org.ee.task;

public abstract class AbstractTask implements Task {
	protected boolean running;
	protected Thread thread;

	@Override
	public void run() {
		while(running) {
			work();
		}
	}

	protected abstract void work();

	@Override
	public void start() {
		if(!running) {
			running = true;
			thread = new Thread(this, getClass().getName());
			thread.setDaemon(true);
			thread.start();
		}
	}

	@Override
	public void stop() {
		running = false;
		thread.interrupt();
	}
}
