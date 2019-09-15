package com.jh.test;

public class TestMaiinApplication implements Runnable{
	
	
	
	
	public static void main(String args [])  {
		
		
		for (int i =0;i<3;i++) {
			TestMaiinApplication tt = new TestMaiinApplication();
			
			
			Thread th = new Thread(tt);
			
			th.start();
			
			
			
		}
	
		
		
		
	}

	@Override
	public void run() {
		int k = (int) (Math.random()*100);
		try {
			for (int i =0;i<10;i++) {
				Thread.sleep(1000);
				
				System.out.println("##"+k+"##"+i);
			}
			
			
			
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	

}
