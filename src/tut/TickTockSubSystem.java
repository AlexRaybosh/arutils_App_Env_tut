package tut;

import java.util.concurrent.atomic.AtomicLong;
import com.google.gson.JsonObject;
import arapp.SubSystem;

public class TickTockSubSystem extends SubSystem {

	@Override
	public boolean init(boolean initial, JsonObject conf) {
		System.out.println("I'm a useless subsystem, but I can do tick tock, my name is: \""+getName()+"\" and my conf: "+conf);
		return true;
	}

	@Override
	public void destroy() {}

	@Override
	public boolean tick(String tickName, Long lastRun) throws Exception {
		System.out.println(tickName+" : "+lastRun+"\t"+cnt.incrementAndGet());
		return true;
	}
	AtomicLong cnt=new AtomicLong();

}

