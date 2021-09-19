package tut;

import com.google.gson.JsonObject;

import arapp.AppEnv;
import arutils.util.JsonUtils;

public class Example05_TickTockSubsystemAndEnvReload {

	public static void main(String[] args) throws Exception {
		// Changed the bootstrap resource or file
		AppEnv.presetBootstrapResource("bootstrap-05-tick-tock.json");
		//What's my environment name
		System.out.println("######################################################");
		System.out.println("My environment name is: "+AppEnv.envName());
		// Get the environment as json
		JsonObject myEnviroment = AppEnv.getMeta();
		
		String prettyJson=JsonUtils.prettyPrint(myEnviroment);
		System.out.println(prettyJson);
		System.out.println("######################################################");
		
		// Wait for Tick Tock, to come up
		AppEnv.ready();
		
		// Wait a second
		Thread.sleep(1000L);
		
		
		System.out.println("######################################################");
		// Completely reload the environment, if shell scripts/DB environment are used, they will be reevaluated
		// newEnv - nukes tock timer, only ticks ticking
		AppEnv.presetEnvName("newEnv");
		AppEnv.reloadEnvironment();
		System.out.println("My environment name is: "+AppEnv.envName());
		System.out.println(JsonUtils.prettyPrint(AppEnv.getMeta()));
		System.out.println("######################################################");
		
		
		// Wait a second
		Thread.sleep(1000L);
				

		
		System.out.println("######################################################");
		// Reload all subsystems, conf might be different now
		AppEnv.reloadSubSystems();
		
		// Wait a second
		Thread.sleep(1000L);
				
				
		// We are done
		AppEnv.destroy();
		
		
		
		
	}
/*

######################################################
BOOTSTRAP: Ignoring non-existent dependency "not going to be here" for subsystem "tickTock" : {"class":"tut.TickTockSubSystem","depends":["not going to be here"],"timers":{"tick":{"milliseconds":500},"tock":{"milliseconds":400,"startAfterMilliseconds":500}}}
My environment name is: localdev1
{
	"subsystems" : {
		"initConcurrency" : 1,
		"entries" : {
			"tickTock" : {
				"class" : "tut.TickTockSubSystem",
				"depends" : [
					"not going to be here"
				],
				"timers" : {
					"tick" : {
						"milliseconds" : 500
					},
					"tock" : {
						"milliseconds" : 400,
						"startAfterMilliseconds" : 500
					}
				}
			}
		}
	}
}

######################################################
I'm a useless subsystem, but I can do tick tock, my name is: "tickTock" and my conf: {"class":"tut.TickTockSubSystem","depends":["not going to be here"],"timers":{"tick":{"milliseconds":500},"tock":{"milliseconds":400,"startAfterMilliseconds":500}}}
tick : null	1
tock : null	2
tick : 1632067113773	3
tock : 1632067114273	4
######################################################
tick : 1632067114273	5
My environment name is: newEnv
{
	"subsystems" : {
		"initConcurrency" : 1,
		"entries" : {
			"tickTock" : {
				"class" : "tut.TickTockSubSystem",
				"depends" : [
					"zzzz..."
				],
				"timers" : {
					"tick" : {
						"milliseconds" : 200
					},
					"tock" : null
				},
				"description:" : "I'm nuking Tock timer here'"
			}
		}
	}
}

######################################################
tock : 1632067114673	6
tick : 1632067114773	7
tock : 1632067115073	8
tick : 1632067115274	9
######################################################
BOOTSTRAP: Ignoring non-existent dependency "zzzz..." for subsystem "tickTock" : {"class":"tut.TickTockSubSystem","depends":["zzzz..."],"timers":{"tick":{"milliseconds":200},"tock":null},"description:":"I'm nuking Tock timer here'"}
I'm a useless subsystem, but I can do tick tock, my name is: "tickTock" and my conf: {"class":"tut.TickTockSubSystem","depends":["zzzz..."],"timers":{"tick":{"milliseconds":200},"tock":null},"description:":"I'm nuking Tock timer here'"}
tick : null	1
tick : 1632067115779	2
tick : 1632067115979	3
tick : 1632067116179	4
tick : 1632067116380	5


 */
}
