package tut;

import com.google.gson.JsonObject;

import arapp.AppEnv;
import arutils.util.JsonUtils;

public class Example06_ProcSubSystemInDB {

	public static void main(String[] args) throws Exception {
		// Changed the bootstrap resource or file
		AppEnv.presetBootstrapResource("bootstrap-06-with-proc-and-db.json");
		
		Long procId=AppEnv.systemProcessId();
		System.out.println("My unique process id in the db: "+procId);
		
		Thread.sleep(1000L);
		
		System.out.println("######################################################");
		AppEnv.reloadSubSystems();
		
		procId=AppEnv.systemProcessId();
		System.out.println("After reload, its the same: "+procId);
		
		// Wait a second
		Thread.sleep(1000L);
				
				
		// We are done
		AppEnv.destroy();
		
		
		
		
	}
/*

BOOTSTRAP: Ignoring non-existent dependency "e" for subsystem "d" : {"enabled":true,"depends":["e"]}
My unique process id in the db: 139
I'm a useless subsystem, but I can do tick tock, my name is: "tickTock" and my conf: {"class":"tut.TickTockSubSystem","depends":["processMaintenance","a"],"timers":{"tick":{"milliseconds":100},"tock":{"startAfterMilliseconds":400,"milliseconds":200}}}
tick : null	1
tick : 1632069532556	2
tick : 1632069532656	3
tick : 1632069532756	4
tock : null	5
tick : 1632069532856	6
tick : 1632069532956	7
tock : 1632069532956	8
tick : 1632069533057	9
tick : 1632069533157	10
tock : 1632069533156	11
tick : 1632069533257	12
tick : 1632069533357	13
######################################################
BOOTSTRAP: Ignoring non-existent dependency "e" for subsystem "d" : {"enabled":true,"depends":["e"]}
tock : 1632069533356	14
tick : 1632069533458	15
I'm a useless subsystem, but I can do tick tock, my name is: "tickTock" and my conf: {"class":"tut.TickTockSubSystem","depends":["processMaintenance","a"],"timers":{"tick":{"milliseconds":100},"tock":{"startAfterMilliseconds":400,"milliseconds":200}}}
After reload, its the same: 139
tick : null	1
tick : 1632069533565	2
tick : 1632069533665	3
tick : 1632069533765	4
tock : null	5
tick : 1632069533865	6
tick : 1632069533966	7
tock : 1632069533962	8
tick : 1632069534066	9
tick : 1632069534166	10
tock : 1632069534163	11
tick : 1632069534266	12
tick : 1632069534367	13
tock : 1632069534363	14
tick : 1632069534467	15
DB Profiler has been interrupted
 */
}
